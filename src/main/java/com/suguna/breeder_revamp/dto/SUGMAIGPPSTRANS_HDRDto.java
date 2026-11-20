package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
public class SUGMAIGPPSTRANS_HDRDto {
    @JsonProperty("DEVICEID")
    public String DEVICEID;

    @JsonProperty("empcode")
    public String empcode;

    @JsonProperty("from_farm_id")
    public BigDecimal from_farm_id;

    @JsonProperty("from_farm_name")
    public String from_farm_name;

    @JsonProperty("to_farm_id")
    public BigDecimal to_farm_id;

    @JsonProperty("to_branch_id")
    @JsonAlias({"TO_BRANCH_ID"})
    public BigDecimal to_branch_id;

    @JsonProperty("txn_header_id")
    public String txn_header_id;

    @JsonProperty("transfer_type")
    public String transfer_type;

    @JsonProperty("txn_date")
    public String txn_date;

    @JsonProperty("vehicle_no")
    public String vehicle_no;

    @JsonProperty("out_pass_no")
    public String out_pass_no;

    @JsonProperty("receiver_name")
    public String receiver_name;

    @JsonProperty("transfer_rsn")
    public String transfer_rsn;

    @JsonProperty("entry_creation_date")
    public String entry_creation_date;

    @JsonProperty("creation_date")
    public String creation_date;

    @JsonProperty("postedflg")
    public String postedflg;

    @JsonProperty("post_to_ERP")
    public String post_to_ERP;

    @JsonProperty("location_TYPE")
    public String location_TYPE;

    @JsonProperty("txn_time")
    public String txn_time;

    @JsonProperty("vehicletype")
    public String vehicletype;

    @JsonProperty("transportmode")
    public String transportmode;

    @JsonProperty("traynumber")
    public BigDecimal traynumber;

    @JsonProperty("boxnumber")
    public BigDecimal boxnumber;

    @JsonProperty("packmaterial")
    public String packmaterial;

    @JsonProperty("flockNo")
    @JsonAlias({"FLOCK_NO", "flockId", "flock_id", "flockID"})
    public String flockNo;

    @JsonProperty("transReason")
    @JsonAlias({"TRANS_REASON", "transferReason"})
    public String transReason;

    /**
     * Vehicle gate {@code GATE_IN_ID} (feed/medicine) or legacy single id.
     * For bird transfer with vehicle, use {@link #birdPlanTxnHeaderId} for {@code TRANS_PLAN_HDR}.
     */
    @JsonProperty("pid")
    @JsonAlias({"plan_dtl_id", "PLAN_DTL_ID", "plan_id", "PLAN_ID"})
    public String pid;

    /** {@code SUG_MAI_GPPS_TRANS_PLAN_HDR.TXN_HEADER_ID} — not the vehicle gate id. */
    @JsonProperty("birdPlanTxnHeaderId")
    @JsonAlias({"planTxnHeaderId", "bird_plan_hdr_id", "PLAN_HDR_ID", "planHdrId"})
    public String birdPlanTxnHeaderId;

    /** Optional alias; if set, does not override {@link #pid}. */
    @JsonProperty("planId")
    public String planId;

    @JsonSetter("pid")
    public void setPidFromPayload(Object value) {
        this.pid = normalizeIdFromPayload(value);
    }

    @JsonSetter("plan_dtl_id")
    public void setPlanDtlIdFromPayload(Object value) {
        this.pid = normalizeIdFromPayload(value);
    }

    @JsonSetter("PLAN_DTL_ID")
    public void setPlanDtlIdUpperFromPayload(Object value) {
        this.pid = normalizeIdFromPayload(value);
    }

    @JsonSetter("planId")
    public void setPlanIdFromPayload(Object value) {
        this.planId = normalizeIdFromPayload(value);
    }

    @JsonSetter("birdPlanTxnHeaderId")
    public void setBirdPlanTxnHeaderIdFromPayload(Object value) {
        this.birdPlanTxnHeaderId = normalizeIdFromPayload(value);
    }

    /** Hatchery plan line id (SUG_EGG_COLL_PLAN_LINES.TRANS_LINE_ID). */
    @JsonProperty("transid")
    @JsonAlias({"trans_line_id", "TRANS_LINE_ID"})
    public String transid;

    /** PLAN_DTL_ID for egg load date (PHP UpdateLoadDate {@code id}). */
    @JsonProperty("id")
    @JsonAlias({"PLAN_DTL_ID"})
    public String id;

    @JsonProperty("trans_dtl_id")
    @JsonAlias({"transDtlId", "TRANS_DTL_ID"})
    public String trans_dtl_id;

    @JsonProperty("loadstartdate")
    @JsonAlias({"load_start_date", "loading_start_date"})
    public String loadstartdate;

    @JsonProperty("loadenddate")
    @JsonAlias({"load_end_date", "loading_end_date"})
    public String loadenddate;

    private static String normalizeIdFromPayload(Object value) {
        if (value == null) {
            return null;
        }
        String text = String.valueOf(value).trim();
        if (text.endsWith(".0")) {
            text = text.substring(0, text.length() - 2);
        }
        return text;
    }

    /** Mobile often sends "" for optional decimals; coerce like legacy PHP payloads. */
    static BigDecimal bigDecimalFromPayload(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal bd) {
            return bd;
        }
        if (value instanceof Number number) {
            return BigDecimal.valueOf(number.doubleValue());
        }
        if (value instanceof String text) {
            if (text.isBlank()) {
                return null;
            }
            try {
                return new BigDecimal(text.trim());
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }

    @JsonProperty("details")
    ArrayList<SUGMAIGPPSTRANS_HDRDto.SugMaiGppsTrans_DtlDto> details;



    @Getter
    public static class SugMaiGppsTrans_DtlDto {
        @JsonProperty("DEVICEID")
        public String DEVICEID;

        @JsonProperty("txn_header_id")
        public String txn_header_id;
        @JsonProperty("txn_line_id")
        public String txn_line_id;

        @JsonProperty("from_farm_id")
        public BigDecimal from_farm_id;

        @JsonProperty("to_farm_id")
        public BigDecimal to_farm_id;
        @JsonProperty("from_inventory_location_id")
        public BigDecimal from_inventory_location_id;

        @JsonProperty("from_inventory_loc_desc")
        @JsonAlias({"fromShed", "from_shed"})
        public String from_inventory_loc_desc;
        @JsonProperty("from_batch_id")
        public BigDecimal from_batch_id;

        @JsonSetter("from_batch_id")
        public void setFrom_batch_id(Object value) {
            this.from_batch_id = bigDecimalFromPayload(value);
        }

        @JsonProperty("to_inventory_location_id")
        public Long to_inventory_location_id;

        @JsonSetter("to_inventory_location_id")
        public void setTo_inventory_location_id(Object value) {
            this.to_inventory_location_id = longFromPayload(value);
        }

        @JsonProperty("to_batch_id")
        public BigDecimal to_batch_id;

        @JsonSetter("to_batch_id")
        public void setTo_batch_id(Object value) {
            this.to_batch_id = bigDecimalFromPayload(value);
        }

        @JsonProperty("txn_type")
        public String txn_type;

        @JsonProperty("bird_type")
        public String bird_type;

        @JsonProperty("item_id")
        public BigDecimal item_id;
        @JsonProperty("item_desc")
        public String item_desc;

        @JsonProperty("uom")
        public String uom;
        @JsonProperty("stock_qty")
        public BigDecimal stock_qty;

        @JsonProperty("qty")
        public BigDecimal qty;

        @JsonProperty("days")
        public BigDecimal days;

        @JsonSetter("days")
        public void setDays(Object value) {
            this.days = bigDecimalFromPayload(value);
        }

        @JsonProperty("receiving_qty")
        public BigDecimal receiving_qty;

        @JsonSetter("receiving_qty")
        public void setReceiving_qty(Object value) {
            this.receiving_qty = bigDecimalFromPayload(value);
        }

        @JsonProperty("diff_qty")
        public BigDecimal diff_qty;

        @JsonSetter("diff_qty")
        public void setDiff_qty(Object value) {
            this.diff_qty = bigDecimalFromPayload(value);
        }
        @JsonProperty("entry_creation_date")
        public String entry_creation_date;

        @JsonProperty("creation_date")
        public String creation_date;

        @JsonProperty("postedflg")
        public String postedflg;
        @JsonProperty("age")
        public BigDecimal age;

        @JsonSetter("age")
        public void setAge(Object value) {
            this.age = bigDecimalFromPayload(value);
        }

        @JsonProperty("post_to_ERP")
        public String post_to_ERP;
        @JsonProperty("lotnumber")
        public String lotnumber;

        @JsonProperty("location_TYPE")
        public String location_TYPE;

        @JsonProperty("laydate")
        public String laydate;
        @JsonProperty("TXN_TIME")
        public String TXN_TIME;

        @JsonProperty("breedname")
        public String breedname;

        @JsonProperty("transfer_type")
        public String transfer_type;

        @JsonProperty("fromLine")
        public String fromLine;
        @JsonProperty("toLine")
        public String toLine;
        @JsonProperty("fromSide")
        public String fromSide;
        @JsonProperty("toSide")
        public String toSide;

        @JsonProperty("flockNo")
        @JsonAlias({"FLOCK_NO", "flockId", "flock_id", "flockID"})
        public String flockNo;

        @JsonProperty("transReason")
        @JsonAlias({"TRANS_REASON"})
        public String transReason;

        public String resolvedFlockNo() {
            if (flockNo != null && !flockNo.isBlank()) {
                return flockNo.trim();
            }
            return null;
        }

        private static Long longFromPayload(Object value) {
            if (value == null) {
                return null;
            }
            if (value instanceof Number number) {
                return number.longValue();
            }
            if (value instanceof String text) {
                if (text.isBlank()) {
                    return null;
                }
                try {
                    return new BigDecimal(text.trim()).longValue();
                } catch (NumberFormatException ignored) {
                    return null;
                }
            }
            return null;
        }
    }
}

