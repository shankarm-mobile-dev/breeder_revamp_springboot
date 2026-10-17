package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.model.TransferPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Fast feed on-hand lookup (replaces per-row correlated subqueries in getfeeditemmaster).
 * Results are cached briefly per branch to speed save validation and feed master API.
 */
@Service
public class FeedInventoryService {

    private static final long CACHE_TTL_MS = 30_000L;

    private static final String FEED_STOCK_SQL =
            "WITH pending_daily AS ( " +
            "  SELECT g.branch_id, g.inventory_item_id, SUM(NVL(g.secondary_qty, 0)) AS qty " +
            "    FROM sug_mai_breeder_daily_entry g " +
            "    INNER JOIN gme_batch_header h ON h.batch_id = g.batch_id " +
            "   WHERE h.batch_status = 2 " +
            "     AND NVL(g.posted_flag, 'N') = 'N' " +
            "     AND g.created_date > TRUNC(SYSDATE - 30) " +
            "     AND g.branch_id = ?1 " +
            "   GROUP BY g.branch_id, g.inventory_item_id " +
            "), pending_transfer AS ( " +
            "  SELECT h.from_farm_id AS branch_id, h.item_id AS inventory_item_id, SUM(NVL(h.qty, 0)) AS qty " +
            "    FROM sug_mai_gpps_trans_dtl h " +
            "   WHERE h.created_date > TRUNC(SYSDATE - 30) " +
            "     AND NVL(h.posted_flag, 'N') = 'N' " +
            "     AND h.from_farm_id = ?1 " +
            "   GROUP BY h.from_farm_id, h.item_id " +
            "), stock_raw AS ( " +
            "  SELECT a.inventory_item_id, d.organization_id, " +
            "         MAX(d.subinventory_code) AS subinventory_code, " +
            "         MAX(c.segment1) AS item_group, MAX(c.segment2) AS item_category, " +
            "         MAX(a.segment1) AS item_code, MAX(a.description) AS item_description, " +
            "         MAX(a.primary_uom_code) AS primary_uom_code, " +
            "         ROUND(SUM(d.primary_transaction_quantity), 3) AS primary_qty, " +
            "         MAX(d.secondary_uom_code) AS secondary_uom_code, " +
            "         ROUND(SUM(d.secondary_transaction_quantity), 3) AS on_hand_secondary, " +
            "         MAX(TRUNC(SYSDATE) - TRUNC(d.date_received)) AS age " +
            "    FROM mtl_system_items_b a " +
            "    INNER JOIN mtl_item_categories b ON b.inventory_item_id = a.inventory_item_id " +
            "      AND b.organization_id = a.organization_id AND b.category_set_id = 1 " +
            "    INNER JOIN mtl_categories c ON c.category_id = b.category_id AND c.disable_date IS NULL " +
            "    INNER JOIN mtl_onhand_quantities_detail d ON d.inventory_item_id = a.inventory_item_id " +
            "      AND d.organization_id = a.organization_id " +
            "    LEFT JOIN mtl_lot_numbers f ON f.organization_id = d.organization_id " +
            "      AND f.inventory_item_id = d.inventory_item_id AND f.lot_number = d.lot_number " +
            "   WHERE d.organization_id = ?1 " +
            "     AND NVL(f.expiration_date, SYSDATE + 1) > SYSDATE " +
            "     AND (c.segment1 IN (SELECT flv.lookup_code FROM fnd_lookup_values flv " +
            "           WHERE flv.lookup_type = 'SUG_BREEDER_ITEM_CATEGORY' AND flv.description = 'F' " +
            "             AND flv.enabled_flag = 'Y' AND TRUNC(SYSDATE) BETWEEN flv.start_date_active " +
            "             AND NVL(flv.end_date_active, SYSDATE)) " +
            "       OR a.segment1 IN (SELECT sa.lookup_code FROM fnd_lookup_values sa " +
            "           WHERE sa.lookup_type = 'SUG_GPPS_MEDICIN_ADDL_ITEMS' AND sa.tag = 'FEED' " +
            "             AND sa.enabled_flag = 'Y' AND TRUNC(SYSDATE) BETWEEN sa.start_date_active " +
            "             AND NVL(sa.end_date_active, SYSDATE))) " +
            "   GROUP BY a.inventory_item_id, d.organization_id " +
            "   HAVING SUM(d.secondary_transaction_quantity) > 0.001 " +
            ") " +
            "SELECT 'FEED', s.organization_id, s.subinventory_code, s.inventory_item_id, " +
            "       s.item_group, s.item_category, s.item_code, s.item_description, " +
            "       s.primary_uom_code, s.primary_qty, s.secondary_uom_code, " +
            "       ROUND(GREATEST(s.on_hand_secondary - NVL(pd.qty, 0) - NVL(pt.qty, 0), 0), 3), s.age " +
            "  FROM stock_raw s " +
            "  LEFT JOIN pending_daily pd ON pd.inventory_item_id = s.inventory_item_id " +
            "    AND pd.branch_id = s.organization_id " +
            "  LEFT JOIN pending_transfer pt ON pt.inventory_item_id = s.inventory_item_id " +
            "    AND pt.branch_id = s.organization_id " +
            " WHERE GREATEST(s.on_hand_secondary - NVL(pd.qty, 0) - NVL(pt.qty, 0), 0) > 0.001";

    @Autowired
    private EntityManager entityManager;

    private final ConcurrentHashMap<String, CachedFeedStock> cache = new ConcurrentHashMap<>();

    public List<TransferPlace.FeedItemDetails> getFeedItemsForBranch(String branchId) {
        if (branchId == null || branchId.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String key = branchId.trim();
        long now = System.currentTimeMillis();
        CachedFeedStock cached = cache.get(key);
        if (cached != null && (now - cached.loadedAtMs) < CACHE_TTL_MS) {
            return cached.items;
        }
        List<TransferPlace.FeedItemDetails> loaded = loadFeedItems(key);
        cache.put(key, new CachedFeedStock(loaded, now));
        return loaded;
    }

    public void evictBranchCache(String branchId) {
        if (branchId != null) {
            cache.remove(branchId.trim());
        }
    }

    @SuppressWarnings("unchecked")
    private List<TransferPlace.FeedItemDetails> loadFeedItems(String branchId) {
        List<TransferPlace.FeedItemDetails> items = new ArrayList<>();
        List<Object[]> rows = entityManager.createNativeQuery(FEED_STOCK_SQL)
                .setParameter(1, branchId)
                .getResultList();
        if (rows == null) {
            return items;
        }
        for (Object[] row : rows) {
            TransferPlace.FeedItemDetails item = new TransferPlace.FeedItemDetails();
            item.setItemType(row[0] == null ? "FEED" : String.valueOf(row[0]));
            if (row[1] instanceof Number) {
                item.setOrganizationId(((Number) row[1]).longValue());
            }
            item.setSubInventoryCode(row[2] == null ? null : String.valueOf(row[2]));
            if (row[3] instanceof Number) {
                item.setInventoryItemId(((Number) row[3]).longValue());
            }
            item.setItemGroup(row[4] == null ? null : String.valueOf(row[4]));
            item.setItemCategory(row[5] == null ? null : String.valueOf(row[5]));
            item.setItemCode(row[6] == null ? null : String.valueOf(row[6]));
            item.setItemDescription(row[7] == null ? null : String.valueOf(row[7]));
            item.setPrimaryUomCode(row[8] == null ? null : String.valueOf(row[8]));
            item.setPrimaryTransactionQuantity(row[9] == null ? null : String.valueOf(row[9]));
            item.setSecondaryUomCode(row[10] == null ? null : String.valueOf(row[10]));
            item.setSecondaryTransactionQuantity(row[11] == null ? null : String.valueOf(row[11]));
            if (row[12] instanceof Number) {
                item.setAge(((Number) row[12]).longValue());
            }
            items.add(item);
        }
        return items;
    }

    private static final class CachedFeedStock {
        final List<TransferPlace.FeedItemDetails> items;
        final long loadedAtMs;

        CachedFeedStock(List<TransferPlace.FeedItemDetails> items, long loadedAtMs) {
            this.items = items;
            this.loadedAtMs = loadedAtMs;
        }
    }
}
