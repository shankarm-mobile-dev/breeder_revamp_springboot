package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
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

    /** Mobile plan detail id (same value used as GATE_IN_ID on gate in/out table). */
    @JsonProperty("pid")
    @JsonAlias({"planId", "plan_dtl_id", "PLAN_DTL_ID", "plan_id", "PLAN_ID"})
    public String pid;

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
        this.pid = normalizeIdFromPayload(value);
    }

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

    @JsonProperty("details")
    ArrayList<SUGMAIGPPSTRANS_HDRDto.SugMaiGppsTrans_DtlDto> details;



    @Getter
    @Setter
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
        public String from_inventory_loc_desc;
        @JsonProperty("from_batch_id")
        public BigDecimal from_batch_id;

        @JsonProperty("to_inventory_location_id")
        public BigDecimal to_inventory_location_id;

        @JsonProperty("to_batch_id")
        public BigDecimal to_batch_id;

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
        @JsonProperty("receiving_qty")
        public BigDecimal receiving_qty;

        @JsonProperty("diff_qty")
        public BigDecimal diff_qty;
        @JsonProperty("entry_creation_date")
        public String entry_creation_date;

        @JsonProperty("creation_date")
        public String creation_date;

        @JsonProperty("postedflg")
        public String postedflg;
        @JsonProperty("age")
        public BigDecimal age;

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

    }
}

