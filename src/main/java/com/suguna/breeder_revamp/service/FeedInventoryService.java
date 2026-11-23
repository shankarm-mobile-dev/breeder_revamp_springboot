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
            "WITH pending_daily AS \n" +
                    "                     (SELECT g.branch_id, \n" +
                    "                             g.inventory_item_id, \n" +
                    "                             SUM(NVL(g.secondary_qty, 0)) AS qty \n" +
                    "                        FROM sug_mai_breeder_daily_entry g, gme_batch_header h \n" +
                    "                       WHERE h.batch_id = g.batch_id \n" +
                    "                         AND h.batch_status = 2 \n" +
                    "                         AND NVL(g.posted_flag, 'N') = 'N' \n" +
                    "                         AND g.created_date > TRUNC(SYSDATE - 30) \n" +
                    "                         AND g.branch_id = ?1 \n" +
                    "                       GROUP BY g.branch_id, g.inventory_item_id), \n" +
                    "                    pending_transfer AS \n" +
                    "                     (SELECT h.from_farm_id AS branch_id, \n" +
                    "                             h.item_id AS inventory_item_id, \n" +
                    "                             SUM(NVL(h.qty, 0)) AS qty \n" +
                    "                        FROM sug_mai_gpps_trans_dtl h \n" +
                    "                       WHERE h.created_date > TRUNC(SYSDATE - 30) \n" +
                    "                         AND NVL(h.posted_flag, 'N') = 'N' \n" +
                    "                         AND h.from_farm_id = ?1 \n" +
                    "                       GROUP BY h.from_farm_id, h.item_id), \n" +
                    "                    stock_raw AS \n" +
                    "                     (SELECT a.inventory_item_id, \n" +
                    "                             d.organization_id, \n" +
                    "                             MAX(d.subinventory_code) AS subinventory_code, \n" +
                    "                             MAX(c.segment1) AS item_group, \n" +
                    "                             MAX(c.segment2) AS item_category, \n" +
                    "                             MAX(a.segment1) AS item_code, \n" +
                    "                             MAX(a.description) AS item_description, \n" +
                    "                             MAX(a.primary_uom_code) AS primary_uom_code, \n" +
                    "                             ROUND(SUM(d.primary_transaction_quantity), 3) AS primary_qty, \n" +
                    "                             MAX(d.secondary_uom_code) AS secondary_uom_code, \n" +
                    "                             ROUND(SUM(d.secondary_transaction_quantity), 3) AS on_hand_secondary, \n" +
                    "                             MAX(TRUNC(SYSDATE) - TRUNC(d.date_received)) AS age \n" +
                    "                        FROM mtl_system_items_b a, \n" +
                    "                             mtl_item_categories b, \n" +
                    "                             mtl_categories c, \n" +
                    "                             mtl_onhand_quantities_detail d, \n" +
                    "                             mtl_lot_numbers f\n" +
                    "                             WHERE b.inventory_item_id = a.inventory_item_id\n" +
                    "                             AND b.organization_id = a.organization_id\n" +
                    "                             AND b.category_set_id = 1\n" +
                    "                             AND c.category_id = b.category_id\n" +
                    "                             AND c.disable_date IS NULL\n" +
                    "                             AND d.inventory_item_id = a.inventory_item_id\n" +
                    "                             AND d.organization_id = a.organization_id\n" +
                    "                             AND f.organization_id(+) = d.organization_id\n" +
                    "                             AND f.inventory_item_id(+) = d.inventory_item_id\n" +
                    "                         AND f.lot_number(+) = d.lot_number\n" +
                    "                         AND d.organization_id = ?1 \n" +
                    "                         AND NVL(f.expiration_date(+), SYSDATE + 1) > SYSDATE\n" +
                    "                         and secondary_transaction_quantity > 0.001 \n" +
                    "                         AND (c.segment1 IN \n" +
                    "                             (SELECT flv.lookup_code \n" +
                    "                                 FROM fnd_lookup_values flv \n" +
                    "                                WHERE flv.lookup_type = 'SUG_BREEDER_ITEM_CATEGORY' \n" +
                    "                                  AND flv.description = 'F' \n" +
                    "                                  AND flv.enabled_flag = 'Y' \n" +
                    "                                  AND TRUNC(SYSDATE) BETWEEN flv.start_date_active \n" +
                    "                                  AND NVL(flv.end_date_active, SYSDATE) \n" +
                    "                               UNION ALL \n" +
                    "                               SELECT sa.lookup_code \n" +
                    "                                 FROM fnd_lookup_values sa \n" +
                    "                                WHERE sa.lookup_type = 'SUG_GPPS_MEDICIN_ADDL_ITEMS' \n" +
                    "                                  AND sa.tag = 'FEED' \n" +
                    "                                  AND sa.enabled_flag = 'Y' \n" +
                    "                                  AND TRUNC(SYSDATE) BETWEEN sa.start_date_active \n" +
                    "                                  AND NVL(sa.end_date_active, SYSDATE))) \n" +
                    "                       GROUP BY a.inventory_item_id, d.organization_id \n" +
                    "                      HAVING SUM(d.secondary_transaction_quantity) > 0.001) \n" +
                    "                    SELECT\n" +
                    "                     'FEED',s.organization_id,s.subinventory_code,\n" +
                    "                    s.inventory_item_id,s.item_group,s.item_category,\n" +
                    "                    s.item_code,s.item_description,s.primary_uom_code,\n" +
                    "                    s.primary_qty,s.secondary_uom_code,\n" +
                    "                    ROUND(GREATEST(s.on_hand_secondary - NVL(pd.qty, 0) - NVL(pt.qty, 0), 0), 3),\n" +
                    "                    s.age FROM stock_raw s,\n" +
                    "                    pending_daily pd,pending_transfer pt\n" +
                    "                     WHERE pd.inventory_item_id(+) = s.inventory_item_id\n" +
                    "                    AND pd.branch_id(+) = s.organization_id\n" +
                    "                    AND pt.inventory_item_id(+) = s.inventory_item_id\n" +
                    "                    AND pt.branch_id(+) = s.organization_id\n" +
                    "                    AND GREATEST(s.on_hand_secondary - NVL(pd.qty, 0) - NVL(pt.qty, 0), 0) > 0.001";

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
