package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.model.SugMaiGppsHousingLine;
import com.suguna.breeder_revamp.model.SugMaiGppsHousingLineHist;
import com.suguna.breeder_revamp.model.SugMaiGppsHousingShed;
import com.suguna.breeder_revamp.repositories.SugMaiGppsHousingLineHistRepositories;
import com.suguna.breeder_revamp.repositories.SugMaiGppsHousingLineRepositories;
import com.suguna.breeder_revamp.repositories.SugMaiGppsHousingShedRepositories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HousingLineStockService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SugMaiGppsHousingLineRepositories housingLineRepositories;

    @Autowired
    SugMaiGppsHousingShedRepositories housingShedRepositories;

    @Autowired
    SugMaiGppsHousingLineHistRepositories historyRepositories;

    /** Side value stored on housing/consumption rows (empty / ~ → null for Oracle NVL). */
    public String canonicalSide(String side) {
        if (side == null) {
            return null;
        }
        String trimmed = side.trim();
        if (trimmed.isEmpty() || "~".equals(trimmed)) {
            return null;
        }
        return trimmed;
    }

    public String normalizeSex(String sex) {
        if (sex == null) {
            return "";
        }
        String value = sex.trim().toUpperCase();
        if (value.startsWith("F")) {
            return "F";
        }
        if (value.startsWith("M")) {
            return "M";
        }
        return value;
    }

    public long parseQty(String qty) {
        if (qty == null || qty.trim().isEmpty()) {
            return 0L;
        }
        return (long) Double.parseDouble(qty.trim());
    }

    @Transactional(rollbackFor = Exception.class)
    public void reduceNormalBirds(String flockId, String farmCode, String shedNo, String lineNo, String side,
                                   String sex, long qty, String txnType, Date txnDate, String createdBy,
                                   Long refId, String reason, String birdCategory, String pairId) {
        if (qty <= 0) {
            return;
        }
        String sexCode = normalizeSex(sex);
        LineRow line = lockLine(flockId, farmCode, shedNo, lineNo, side, sexCode);
        if (line == null) {
            throw new IllegalArgumentException("Housing line not found for shed " + shedNo + " line " + lineNo + " sex " + sexCode);
        }
        if (line.opQty < qty) {
            throw new IllegalArgumentException("Quantity " + qty + " exceeds line stock " + line.opQty + " for " + shedNo + " / " + lineNo);
        }
        long closing = line.opQty - qty;
        updateLineQty(line.txnId, qty, closing);
        reduceShed(flockId, farmCode, shedNo, sexCode, qty);
        writeHistory(line, txnType, "OUT", birdCategory, qty, line.opQty, closing, txnDate, createdBy,
                refId, reason, pairId, lineNo, side, null, null, shedNo, null, null, null, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void recordWeakIn(String flockId, String farmCode, String shedNo, String lineNo, String side,
                              String sex, long qty, Date txnDate, String createdBy, Long refId, String reason, String pairId) {
        if (qty <= 0) {
            return;
        }
        String sexCode = normalizeSex(sex);
        LineRow line = findLine(flockId, farmCode, shedNo, lineNo, side, sexCode);
        writeHistory(line, "WEAK_IN", "IN", "WEAK", qty,
                line == null ? 0L : line.opQty, line == null ? 0L : line.opQty,
                txnDate, createdBy, refId, reason, pairId, lineNo, side, lineNo, side, shedNo, shedNo, null, null, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void recordWeakOut(String flockId, String farmCode, String shedNo, String lineNo, String side,
                              String sex, long qty, Date txnDate, String createdBy, Long refId, String reason, String pairId) {
        if (qty <= 0) {
            return;
        }
        String sexCode = normalizeSex(sex);
        LineRow line = findLine(flockId, farmCode, shedNo, lineNo, side, sexCode);
        writeHistory(line, "WEAK_OUT", "OUT", "WEAK", qty,
                line == null ? 0L : line.opQty, line == null ? 0L : line.opQty,
                txnDate, createdBy, refId, reason, pairId, lineNo, side, null, null, shedNo, null, null, null, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void increaseNormalBirds(String flockId, String farmCode, String shedNo, String lineNo, String side,
                                    String sex, long qty, Date txnDate, String createdBy, Long refId, String reason,
                                    String pairId, Long branchId, Long batchId, Long age) {
        if (qty <= 0) {
            return;
        }
        String sexCode = normalizeSex(sex);
        String storageSide = canonicalSide(side);
        long destCurrent = lineSexTotal(flockId, farmCode, shedNo, lineNo, storageSide);
        assertIncomingFits(farmCode, shedNo, lineNo, storageSide, destCurrent, qty);
        LineRow destination = lockLine(flockId, farmCode, shedNo, lineNo, storageSide, sexCode);
        long destBefore;
        long destTxnId;
        String grade = "3";
        Long refBranchId = branchId;
        Long refBatchId = batchId;
        if (destination == null) {
            LineRow sibling = lockLine(flockId, farmCode, shedNo, lineNo, storageSide,
                    "F".equals(sexCode) ? "M" : "F");
            if (sibling != null) {
                refBranchId = sibling.branchId;
                refBatchId = sibling.batchId;
                grade = sibling.grade == null ? grade : sibling.grade;
            }
        }
        if (destination == null) {
            destBefore = 0L;
            SugMaiGppsHousingLine created = new SugMaiGppsHousingLine();
            created.setFLOCK_ID(flockId);
            created.setTXN_DATE(txnDate == null ? new Date() : txnDate);
            created.setFARM_CODE(farmCode);
            created.setSHED_NO(shedNo);
            created.setLINE_NO(lineNo);
            created.setSIDE(storageSide);
            created.setSEX(sexCode);
            created.setGRADE(grade);
            created.setOP_QTY(qty);
            created.setTXN_QTY(qty);
            created.setCL_QTY(qty);
            created.setCREATED_BY(createdBy);
            created.setCREATION_DATE(new Date());
            created.setBATCH_ID(refBatchId == null ? 0L : refBatchId);
            created.setBRANCH_ID(refBranchId == null ? 0L : refBranchId);
            created.setAGE(age);
            created = housingLineRepositories.save(created);
            destTxnId = created.getTXN_ID();
        } else {
            destBefore = destination.opQty;
            destTxnId = destination.txnId;
            grade = destination.grade == null ? grade : destination.grade;
            refBranchId = destination.branchId;
            refBatchId = destination.batchId;
            updateLineQty(destination.txnId, qty, destination.opQty + qty);
        }
        increaseShed(flockId, farmCode, shedNo, sexCode, qty, txnDate, createdBy, refBranchId, refBatchId, age);

        LineRow destHist = new LineRow();
        destHist.txnId = destTxnId;
        destHist.flockId = flockId;
        destHist.farmCode = farmCode;
        destHist.shedNo = shedNo;
        destHist.lineNo = lineNo;
        destHist.side = storageSide;
        destHist.sex = sexCode;
        destHist.grade = grade;
        destHist.branchId = refBranchId == null ? 0L : refBranchId;
        destHist.batchId = refBatchId == null ? 0L : refBatchId;
        writeHistory(destHist, "RECOVERY", "IN", "NORMAL", qty, destBefore, destBefore + qty,
                txnDate, createdBy, refId, reason, pairId, null, null, lineNo, storageSide, null, shedNo, null, null, null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void recordWeakMortality(String flockId, String farmCode, String shedNo, String lineNo, String side,
                                     String sex, long qty, Date txnDate, String createdBy, Long refId, String reason) {
        if (qty <= 0) {
            return;
        }
        String sexCode = normalizeSex(sex);
        LineRow line = findLine(flockId, farmCode, shedNo, lineNo, side, sexCode);
        writeHistory(line, "MORTALITY", "OUT", "WEAK", qty,
                line == null ? 0L : line.opQty, line == null ? 0L : line.opQty,
                txnDate, createdBy, refId, reason, null, lineNo, side, null, null, shedNo, null, null, null, null);
    }

    public void assertPlacementQty(String farmCode, String shedNo, String lineNo, String side, long femaleQty, long maleQty) {
        long placed = femaleQty + maleQty;
        if (placed <= 0) {
            return;
        }
        Long capacity = lineCapacity(farmCode, shedNo, lineNo, side);
        if (capacity == null || capacity <= 0) {
            return;
        }
        long allowed = allowedQty(capacity);
        if (placed > allowed) {
            throw new IllegalArgumentException("Line " + lineNo + " side " + side + " allows " + allowed
                    + " birds with placement tolerance. Entered " + placed + ".");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void recordPlacement(SugMaiGppsHousingLine line) {
        if (line == null || line.getOP_QTY() == null || line.getOP_QTY() <= 0) {
            return;
        }
        Long capacity = lineCapacity(line.getFARM_CODE(), line.getSHED_NO(), line.getLINE_NO(), line.getSIDE());
        Long tolerance = tolerancePercent();
        Long allowed = capacity == null ? null : allowedQty(capacity);
        LineRow row = new LineRow();
        row.txnId = line.getTXN_ID();
        row.flockId = line.getFLOCK_ID();
        row.farmCode = line.getFARM_CODE();
        row.shedNo = line.getSHED_NO();
        row.lineNo = line.getLINE_NO();
        row.side = line.getSIDE();
        row.sex = line.getSEX();
        row.grade = line.getGRADE();
        row.branchId = line.getBRANCH_ID();
        row.batchId = line.getBATCH_ID();
        row.opQty = line.getOP_QTY();
        writeHistory(row, "PLACEMENT", "IN", "NORMAL", line.getOP_QTY(), 0L, line.getOP_QTY(),
                line.getTXN_DATE(), line.getCREATED_BY(), null, null, null,
                null, null, line.getLINE_NO(), line.getSIDE(), null, line.getSHED_NO(),
                capacity, tolerance, allowed);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferShedLine(String flockId, String farmCode, String fromShed, String fromLine, String fromSide,
                                  String toShed, String toLine, String toSide, String sex, long qty,
                                  Date txnDate, String createdBy, Long branchId, Long batchId, Long age, String pairId) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Transfer quantity must be greater than zero");
        }
        String sexCode = normalizeSex(sex);
        if (fromShed.equals(toShed) && fromLine.equals(toLine) && sameSide(fromSide, toSide)) {
            throw new IllegalArgumentException("Source and destination line cannot be the same");
        }
        assertDestinationFlock(farmCode, flockId, toShed);
        LineRow source = lockLine(flockId, farmCode, fromShed, fromLine, fromSide, sexCode);
        if (source == null) {
            throw new IllegalArgumentException("Source housing line not found for " + fromShed + " / " + fromLine);
        }
        if (source.opQty < qty) {
            throw new IllegalArgumentException("Transfer quantity exceeds source line stock " + source.opQty);
        }
        long destCurrent = lineSexTotal(flockId, farmCode, toShed, toLine, toSide);
        assertIncomingFits(farmCode, toShed, toLine, toSide, destCurrent, qty);

        long sourceClose = source.opQty - qty;
        updateLineQty(source.txnId, qty, sourceClose);
        reduceShed(flockId, farmCode, fromShed, sexCode, qty);

        LineRow destination = lockLine(flockId, farmCode, toShed, toLine, toSide, sexCode);
        long destBefore;
        long destTxnId;
        String grade = source.grade == null ? "3" : source.grade;
        if (destination == null) {
            destBefore = 0L;
            SugMaiGppsHousingLine created = new SugMaiGppsHousingLine();
            created.setFLOCK_ID(flockId);
            created.setTXN_DATE(txnDate == null ? new Date() : txnDate);
            created.setFARM_CODE(farmCode);
            created.setSHED_NO(toShed);
            created.setLINE_NO(toLine);
            created.setSIDE(toSide);
            created.setSEX(sexCode);
            created.setGRADE(grade);
            created.setOP_QTY(qty);
            created.setTXN_QTY(qty);
            created.setCL_QTY(qty);
            created.setCREATED_BY(createdBy);
            created.setCREATION_DATE(new Date());
            created.setBATCH_ID(batchId == null ? source.batchId : batchId);
            created.setBRANCH_ID(branchId == null ? source.branchId : branchId);
            created.setAGE(age);
            created = housingLineRepositories.save(created);
            destTxnId = created.getTXN_ID();
        } else {
            destBefore = destination.opQty;
            destTxnId = destination.txnId;
            updateLineQty(destination.txnId, qty, destination.opQty + qty);
            grade = destination.grade;
        }
        increaseShed(flockId, farmCode, toShed, sexCode, qty, txnDate, createdBy,
                branchId == null ? source.branchId : branchId,
                batchId == null ? source.batchId : batchId, age);

        LineRow sourceHist = source;
        writeHistory(sourceHist, "TRANSFER_OUT", "OUT", "NORMAL", qty, source.opQty, sourceClose,
                txnDate, createdBy, null, null, pairId, fromLine, fromSide, toLine, toSide, fromShed, toShed, null, null, null);

        LineRow destHist = new LineRow();
        destHist.txnId = destTxnId;
        destHist.flockId = flockId;
        destHist.farmCode = farmCode;
        destHist.shedNo = toShed;
        destHist.lineNo = toLine;
        destHist.side = toSide;
        destHist.sex = sexCode;
        destHist.grade = grade;
        destHist.branchId = branchId == null ? source.branchId : branchId;
        destHist.batchId = batchId == null ? source.batchId : batchId;
        writeHistory(destHist, "TRANSFER_IN", "IN", "NORMAL", qty, destBefore, destBefore + qty,
                txnDate, createdBy, null, null, pairId, fromLine, fromSide, toLine, toSide, fromShed, toShed, null, null, null);
    }

    public void assertDestinationFlock(String farmCode, String sourceFlock, String toShed) {
        Query query = entityManager.createNativeQuery(
                "SELECT t.FLOCK_ID, NVL(SUM(t.OP_QTY), 0) " +
                        "FROM SUG.SUG_MAI_GPPS_HOUSING_SHED t " +
                        "WHERE t.FARM_CODE = :farm AND t.SHED_NO = :shed " +
                        "GROUP BY t.FLOCK_ID");
        query.setParameter("farm", farmCode);
        query.setParameter("shed", toShed);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.getResultList();
        long maleFemale = 0L;
        boolean otherFlockHasBirds = false;
        for (Object[] row : rows) {
            String flock = row[0] == null ? "" : row[0].toString();
            long qty = asLong(row[1]);
            maleFemale += qty;
            if (qty > 0 && !flock.equals(sourceFlock)) {
                otherFlockHasBirds = true;
            }
        }
        if (maleFemale == 0 || !otherFlockHasBirds) {
            return;
        }
        throw new IllegalArgumentException("Destination shed " + toShed + " belongs to another flock and still has birds");
    }

    private void assertIncomingFits(String farmCode, String shedNo, String lineNo, String side, long currentQty, long incoming) {
        Long capacity = lineCapacity(farmCode, shedNo, lineNo, side);
        if (capacity == null || capacity <= 0) {
            return;
        }
        long allowed = allowedQty(capacity);
        if (currentQty + incoming > allowed) {
            throw new IllegalArgumentException("Destination line " + lineNo + " allows " + allowed
                    + " birds. Current " + currentQty + " plus transfer " + incoming + " exceeds it.");
        }
    }

    private long lineSexTotal(String flockId, String farmCode, String shedNo, String lineNo, String side) {
        Query query = entityManager.createNativeQuery(
                "SELECT NVL(SUM(l.OP_QTY), 0) FROM SUG.SUG_MAI_GPPS_HOUSING_LINE l " +
                        "WHERE l.FLOCK_ID = :flock AND l.FARM_CODE = :farm AND l.SHED_NO = :shed " +
                        "AND l.LINE_NO = :lineNo AND NVL(l.SIDE, '~') = :side AND NVL(l.ALLOCATE_STATUS, 'N') = 'N'");
        query.setParameter("flock", flockId);
        query.setParameter("farm", farmCode);
        query.setParameter("shed", shedNo);
        query.setParameter("lineNo", lineNo);
        query.setParameter("side", sideKey(side));
        Object result = query.getSingleResult();
        return asLong(result);
    }

    private Long lineCapacity(String farmCode, String shedNo, String lineNo, String side) {
        Query query = entityManager.createNativeQuery(
                "SELECT NVL(SUM(a.BIRD_CAPACITY), 0) FROM sug_gpps_line_mst_v a " +
                        "WHERE a.FARM_CODE = :farm AND a.SHED_NO = :shed AND a.LINE_NO = :lineNo AND NVL(a.SIDE, '~') = :side");
        query.setParameter("farm", farmCode);
        query.setParameter("shed", shedNo);
        query.setParameter("lineNo", lineNo);
        query.setParameter("side", sideKey(side));
        Object result = query.getSingleResult();
        long capacity = asLong(result);
        return capacity <= 0 ? null : capacity;
    }

    private long allowedQty(long capacity) {
        long tolerance = tolerancePercent();
        return (long) Math.floor(capacity * (1d + (tolerance / 100d)));
    }

    private long tolerancePercent() {
        try {
            Object result = entityManager.createNativeQuery(
                            "SELECT SUG_MAI_GPPS_MOB_PKG.getPlacementTolerance(:age) FROM dual")
                    .setParameter("age", "0")
                    .getSingleResult();
            if (result == null) {
                return 0L;
            }
            String raw = result.toString().replace("%", "").trim();
            if (raw.isEmpty()) {
                return 0L;
            }
            return (long) Double.parseDouble(raw);
        } catch (Exception ex) {
            return 0L;
        }
    }

    private LineRow lockLine(String flockId, String farmCode, String shedNo, String lineNo, String side, String sex) {
        return findLine(flockId, farmCode, shedNo, lineNo, side, sex);
    }

    private LineRow findLine(String flockId, String farmCode, String shedNo, String lineNo, String side, String sex) {
        Query query = entityManager.createNativeQuery(
                "SELECT l.TXN_ID, NVL(l.OP_QTY, 0), l.FLOCK_ID, l.FARM_CODE, l.SHED_NO, l.LINE_NO, l.SIDE, l.SEX, " +
                        "l.GRADE, l.BRANCH_ID, l.BATCH_ID " +
                        "FROM SUG.SUG_MAI_GPPS_HOUSING_LINE l " +
                        "WHERE l.FLOCK_ID = :flock AND l.FARM_CODE = :farm AND l.SHED_NO = :shed " +
                        "AND l.LINE_NO = :lineNo AND l.SEX = :sex AND NVL(l.SIDE, '~') = :side " +
                        "AND NVL(l.ALLOCATE_STATUS, 'N') = 'N' " +
                        "ORDER BY l.TXN_ID DESC");
        query.setParameter("flock", flockId);
        query.setParameter("farm", farmCode);
        query.setParameter("shed", shedNo);
        query.setParameter("lineNo", lineNo);
        query.setParameter("sex", sex);
        query.setParameter("side", sideKey(side));
        query.setMaxResults(1);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.getResultList();
        if (rows.isEmpty()) {
            return null;
        }
        Object[] row = rows.get(0);
        LineRow line = new LineRow();
        line.txnId = asLong(row[0]);
        line.opQty = asLong(row[1]);
        line.flockId = text(row[2]);
        line.farmCode = text(row[3]);
        line.shedNo = text(row[4]);
        line.lineNo = text(row[5]);
        line.side = text(row[6]);
        line.sex = text(row[7]);
        line.grade = text(row[8]);
        line.branchId = asLong(row[9]);
        line.batchId = asLong(row[10]);
        return line;
    }

    private void updateLineQty(long txnId, long txnQty, long closing) {
        Query query = entityManager.createNativeQuery(
                "UPDATE SUG.SUG_MAI_GPPS_HOUSING_LINE SET OP_QTY = :closing, TXN_QTY = :txnQty, CL_QTY = :closing WHERE TXN_ID = :id");
        query.setParameter("closing", closing);
        query.setParameter("txnQty", txnQty);
        query.setParameter("id", txnId);
        if (query.executeUpdate() == 0) {
            throw new IllegalArgumentException("Housing line " + txnId + " could not be updated");
        }
    }

    private void reduceShed(String flockId, String farmCode, String shedNo, String sex, long qty) {
        Query query = entityManager.createNativeQuery(
                "UPDATE SUG.SUG_MAI_GPPS_HOUSING_SHED s " +
                        "SET s.OP_QTY = NVL(s.OP_QTY, 0) - :qty, s.TXN_QTY = :qty, s.CL_QTY = NVL(s.OP_QTY, 0) - :qty " +
                        "WHERE s.ROWID = (SELECT rid FROM (SELECT t.ROWID rid FROM SUG.SUG_MAI_GPPS_HOUSING_SHED t " +
                        "WHERE t.FLOCK_ID = :flock AND t.FARM_CODE = :farm AND t.SHED_NO = :shed AND t.SEX = :sex " +
                        "ORDER BY NVL(t.OP_QTY, 0) DESC) WHERE ROWNUM = 1) " +
                        "AND NVL(s.OP_QTY, 0) >= :qty");
        query.setParameter("qty", qty);
        query.setParameter("flock", flockId);
        query.setParameter("farm", farmCode);
        query.setParameter("shed", shedNo);
        query.setParameter("sex", sex);
        if (query.executeUpdate() == 0) {
            throw new IllegalArgumentException("Shed " + shedNo + " does not have enough " + sex + " birds");
        }
    }

    private void increaseShed(String flockId, String farmCode, String shedNo, String sex, long qty,
                               Date txnDate, String createdBy, Long branchId, Long batchId, Long age) {
        Query query = entityManager.createNativeQuery(
                "UPDATE SUG.SUG_MAI_GPPS_HOUSING_SHED s " +
                        "SET s.OP_QTY = NVL(s.OP_QTY, 0) + :qty, s.TXN_QTY = :qty, s.CL_QTY = NVL(s.OP_QTY, 0) + :qty " +
                        "WHERE s.ROWID = (SELECT rid FROM (SELECT t.ROWID rid FROM SUG.SUG_MAI_GPPS_HOUSING_SHED t " +
                        "WHERE t.FLOCK_ID = :flock AND t.FARM_CODE = :farm AND t.SHED_NO = :shed AND t.SEX = :sex " +
                        "ORDER BY NVL(t.OP_QTY, 0) DESC) WHERE ROWNUM = 1)");
        query.setParameter("qty", qty);
        query.setParameter("flock", flockId);
        query.setParameter("farm", farmCode);
        query.setParameter("shed", shedNo);
        query.setParameter("sex", sex);
        int updated = query.executeUpdate();
        if (updated > 0) {
            return;
        }
        SugMaiGppsHousingShed shed = new SugMaiGppsHousingShed();
        shed.setFLOCK_ID(flockId);
        shed.setTXN_DATE(txnDate == null ? new Date() : txnDate);
        shed.setFARM_CODE(farmCode);
        shed.setSHED_NO(shedNo);
        shed.setSEX(sex);
        shed.setOP_QTY(qty);
        shed.setTXN_QTY(qty);
        shed.setCL_QTY(qty);
        shed.setCREATED_BY(createdBy);
        shed.setCREATION_DATE(new Date());
        shed.setBATCH_ID(batchId == null ? 0L : batchId);
        shed.setBRANCH_ID(branchId == null ? 0L : branchId);
        shed.setAGE(age);
        housingShedRepositories.save(shed);
    }

    private void writeHistory(LineRow line, String txnType, String direction, String birdCategory, long qty,
                               long before, long after, Date txnDate, String createdBy, Long refId, String reason,
                               String pairId, String fromLine, String fromSide, String toLine, String toSide,
                               String fromShed, String toShed, Long capacity, Long tolerance, Long allowed) {
        SugMaiGppsHousingLineHist hist = new SugMaiGppsHousingLineHist();
        if (line != null) {
            hist.setHOUSING_TXN_ID(line.txnId);
            hist.setFLOCK_ID(line.flockId);
            hist.setFARM_CODE(line.farmCode);
            hist.setSHED_NO(line.shedNo);
            hist.setLINE_NO(line.lineNo);
            hist.setSIDE(line.side);
            hist.setSEX(line.sex);
            hist.setGRADE(line.grade);
            hist.setBRANCH_ID(line.branchId);
            hist.setBATCH_ID(line.batchId);
        }
        hist.setTXN_TYPE(txnType);
        hist.setTXN_DATE(txnDate == null ? new Date() : txnDate);
        hist.setQTY(qty);
        hist.setOP_QTY_BEFORE(before);
        hist.setCL_QTY_AFTER(after);
        hist.setDIRECTION(direction);
        hist.setBIRD_CATEGORY(birdCategory);
        hist.setREASON(reason);
        hist.setREF_ID(refId);
        hist.setPAIR_ID(pairId);
        hist.setFROM_LINE(fromLine);
        hist.setFROM_SIDE(fromSide);
        hist.setTO_LINE(toLine);
        hist.setTO_SIDE(toSide);
        hist.setFROM_SHED(fromShed);
        hist.setTO_SHED(toShed);
        hist.setCAPACITY(capacity);
        hist.setTOLERANCE_PER(tolerance);
        hist.setALLOWED_QTY(allowed);
        hist.setCREATED_BY(createdBy);
        hist.setCREATION_DATE(new Date());
        historyRepositories.save(hist);
    }

    private boolean sameSide(String left, String right) {
        return sideKey(left).equals(sideKey(right));
    }

    private String sideKey(String side) {
        if (side == null || side.trim().isEmpty()) {
            return "~";
        }
        return side.trim();
    }

    private long asLong(Object value) {
        if (value == null) {
            return 0L;
        }
        if (value instanceof Number number) {
            return number.longValue();
        }
        String text = value.toString().trim();
        if (text.isEmpty()) {
            return 0L;
        }
        return (long) Double.parseDouble(text);
    }

    private String text(Object value) {
        return value == null ? null : value.toString();
    }

    private static class LineRow {
        long txnId;
        long opQty;
        String flockId;
        String farmCode;
        String shedNo;
        String lineNo;
        String side;
        String sex;
        String grade;
        long branchId;
        long batchId;
    }
}
