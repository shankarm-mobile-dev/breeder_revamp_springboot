package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class FarmResultDto {
    @JsonProperty("STATUS")
    String status;
    @JsonProperty("STATUSCODE")
    String statuscode;
    @JsonProperty("MESSAGE")
    String message;
    @JsonProperty("farmmst")
    ArrayList<farmmaster> farmmst;
    @JsonProperty("batchmst")
    ArrayList<batchmaster> batchmst;
    @JsonProperty("shedmst")
    ArrayList<shedmaster> shedmst;
    @JsonProperty("feedmst")
    ArrayList<feedstock> feedmst;
    @JsonProperty("medicinemst")
    ArrayList<medistocks> medicinemst;
    @JsonProperty("vaccinemst")
    ArrayList<vaccinestock> vaccinemst;
    @JsonProperty("performancemst")
    ArrayList<gppsperformance> performancemst;

    @JsonProperty("dailyentrymst")
    ArrayList<dailyentrymst> dailyentrymst;

    @JsonProperty("servicechargemst")
    ArrayList<farmerservicecharge> servicechargemst;

    @JsonProperty("servicechargenew")
    ArrayList<servicechargeheader> servicechargenew;

    @JsonProperty("eggcollectionsync")
    ArrayList<eggcollectionsync> eggcollectionsync;

    public ArrayList<eggcollectionsync> getEggcollectionsync() {
        return eggcollectionsync;
    }

    public void setEggcollectionsync(ArrayList<eggcollectionsync> eggcollectionsync) {
        this.eggcollectionsync = eggcollectionsync;
    }

    public ArrayList<servicechargeheader> getServicechargenew() {
        return servicechargenew;
    }

    public void setServicechargenew(ArrayList<servicechargeheader> servicechargenew) {
        this.servicechargenew = servicechargenew;
    }

    public ArrayList<farmerservicecharge> getServicechargemst() {
        return servicechargemst;
    }

    public void setServicechargemst(ArrayList<farmerservicecharge> servicechargemst) {
        this.servicechargemst = servicechargemst;
    }

    public ArrayList<dailyentrymst> getDailyentrymst() {
        return dailyentrymst;
    }

    public void setDailyentrymst(ArrayList<dailyentrymst> dailyentrymst) {
        this.dailyentrymst = dailyentrymst;
    }

    public ArrayList<gppsperformance> getPerformancemst() {
        return performancemst;
    }

    public void setPerformancemst(ArrayList<gppsperformance> performancemst) {
        this.performancemst = performancemst;
    }

    public ArrayList<vaccinestock> getVaccinemst() {
        return vaccinemst;
    }

    public void setVaccinemst(ArrayList<vaccinestock> vaccinemst) {
        this.vaccinemst = vaccinemst;
    }

    public ArrayList<medistocks> getMedicinemst() {
        return medicinemst;
    }

    public void setMedicinemst(ArrayList<medistocks> medicinemst) {
        this.medicinemst = medicinemst;
    }

    public ArrayList<feedstock> getFeedmst() {
        return feedmst;
    }

    public void setFeedmst(ArrayList<feedstock> feedmst) {
        this.feedmst = feedmst;
    }

    public ArrayList<shedmaster> getShedmst() {
        return shedmst;
    }

    public void setShedmst(ArrayList<shedmaster> shedmst) {
        this.shedmst = shedmst;
    }

    public ArrayList<batchmaster> getBatchmst() {
        return batchmst;
    }

    public void setBatchmst(ArrayList<batchmaster> batchmst) {
        this.batchmst = batchmst;
    }

    public ArrayList<farmmaster> getFarmmst() {
        return farmmst;
    }

    public void setFarmmst(ArrayList<farmmaster> farmmst) {
        this.farmmst = farmmst;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class farmmaster{
        @Column(name = "breeder_BRANCH", type = String.class)
        @JsonProperty("breeder_BRANCH")
        public String breeder_BRANCH;
        @Column(name = "branch_ID", type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;
        @Column(name = "opm_DIVISION", type = String.class)
        @JsonProperty("opm_DIVISION")
        public String opm_DIVISION;

    }
    public static class batchmaster{
        @Column(name = "farm_ID", type = String.class)
        @JsonProperty("farm_ID")
        public String farm_ID;
        @Column(name = "inventory_LOCATION_ID", type = String.class)
        @JsonProperty("inventory_LOCATION_ID")
        public String inventory_LOCATION_ID;

        @Column(name = "batch_ID", type = String.class)
        @JsonProperty("batch_ID")
        public String batch_ID;

        @Column(name = "batch_NO", type = String.class)
        @JsonProperty("batch_NO")
        public String batch_NO;
        @Column(name = "batch_TYPE", type = String.class)
        @JsonProperty("batch_TYPE")
        public String batch_TYPE;


        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock;
        @Column(name = "wf", type = String.class)
        @JsonProperty("wf")
        public String wf;

        @Column(name = "bird_TRANS_STATUS", type = String.class)
        @JsonProperty("bird_TRANS_STATUS")
        public String bird_TRANS_STATUS;

        @Column(name = "bird_CULLING_STATUS", type = String.class)
        @JsonProperty("bird_CULLING_STATUS")
        public String bird_CULLING_STATUS;
        @Column(name = "bird_IN_STATUS", type = String.class)
        @JsonProperty("bird_IN_STATUS")
        public String bird_IN_STATUS;

        @Column(name = "confirm_STATUS", type = String.class)
        @JsonProperty("confirm_STATUS")
        public String confirm_STATUS;
        @Column(name = "wee", type = String.class)
        @JsonProperty("wee")
        public String wee;

        @Column(name = "last_ENTRY_DATE", type = String.class)
        @JsonProperty("last_ENTRY_DATE")
        public String last_ENTRY_DATE;

        @Column(name = "hh", type = String.class)
        @JsonProperty("hh")
        public String hh;
        @Column(name = "trans_DATE", type = String.class)
        @JsonProperty("trans_DATE")
        public String trans_DATE;


        @Column(name = "hatch_DATE", type = String.class)
        @JsonProperty("hatch_DATE")
        public String hatch_DATE;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "egg_PRODUCTION_AGE", type = String.class)
        @JsonProperty("egg_PRODUCTION_AGE")
        public String egg_PRODUCTION_AGE;

        @Column(name = "start_DATE", type = String.class)
        @JsonProperty("start_DATE")
        public String start_DATE;
        @Column(name = "end_DATE", type = String.class)
        @JsonProperty("end_DATE")
        public String end_DATE;

        @Column(name = "op_MALE", type = String.class)
        @JsonProperty("op_MALE")
        public String op_MALE;
        @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("op_FEMALE")
        public String op_FEMALE;

        @Column(name = "cl_MALE", type = String.class)
        @JsonProperty("cl_MALE")
        public String cl_MALE;

        @Column(name = "cl_FEMALE", type = String.class)
        @JsonProperty("cl_FEMALE")
        public String cl_FEMALE;
        @Column(name = "wk_OP_MALE", type = String.class)
        @JsonProperty("wk_OP_MALE")
        public String wk_OP_MALE;


        @Column(name = "wk_OP_FEMALE", type = String.class)
        @JsonProperty("wk_OP_FEMALE")
        public String wk_OP_FEMALE;
        @Column(name = "prev_START_DATE", type = String.class)
        @JsonProperty("prev_START_DATE")
        public String prev_START_DATE;

        @Column(name = "prev_END_DATE", type = String.class)
        @JsonProperty("prev_END_DATE")
        public String prev_END_DATE;

        @Column(name = "egg_STD", type = String.class)
        @JsonProperty("egg_STD")
        public String egg_STD;
        @Column(name = "gps", type = String.class)
        @JsonProperty("gps")
        public String gps;

        @Column(name = "batch_STOCKM", type = String.class)
        @JsonProperty("batch_STOCKM")
        public String batch_STOCKM;
        @Column(name = "batch_STOCKF", type = String.class)
        @JsonProperty("batch_STOCKF")
        public String batch_STOCKF;

        @Column(name = "flagbatchtailing", type = String.class)
        @JsonProperty("flagbatchtailing")
        public String flagbatchtailing;
        @Column(name = "fm_AGE", type = String.class)
        @JsonProperty("fm_AGE")
        public String fm_AGE;

        @Column(name = "latitude", type = String.class)
        @JsonProperty("latitude")
        public String latitude;

        @Column(name = "longitude", type = String.class)
        @JsonProperty("longitude")
        public String longitude;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;


        @Column(name = "uniformity", type = String.class)
        @JsonProperty("uniformity")
        public String uniformity;
        @Column(name = "flock_LIQUID", type = String.class)
        @JsonProperty("flock_LIQUID")
        public String flock_LIQUID;

        @Column(name = "cull_REASON", type = String.class)
        @JsonProperty("cull_REASON")
        public String cull_REASON;
    }
    public static class shedmaster{
        @Column(name = "inventory_LOCATION_ID",type = String.class)
        @JsonProperty("inventory_LOCATION_ID")
        public String inventory_LOCATION_ID;

        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "location",type = String.class)
        @JsonProperty("location")
        public String location;

        @Column(name = "loc_TYPE",type = String.class)
        @JsonProperty("loc_TYPE")
        public String loc_TYPE;

        @Column(name = "last_ENTRY_DATE",type = String.class)
        @JsonProperty("last_ENTRY_DATE")
        public String last_ENTRY_DATE;
        @Column(name = "flock",type = String.class)
        @JsonProperty("flock")
        public String flock;
        @Column(name = "last_FEED_ENTRY_DATE",type = String.class)
        @JsonProperty("last_FEED_ENTRY_DATE")
        public String last_FEED_ENTRY_DATE;
    }
    public static class feedstock{
        @Column(name = "item_TYPE",type = String.class)
        @JsonProperty("item_TYPE")
        public String item_TYPE;

        @Column(name = "organization_ID",type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;

        @Column(name = "subinventory_CODE",type = String.class)
        @JsonProperty("subinventory_CODE")
        public String subinventory_CODE;

        @Column(name = "inventory_ITEM_ID",type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "item_GROUP",type = String.class)
        @JsonProperty("item_GROUP")
        public String item_GROUP;
        @Column(name = "item_CATEGORY",type = String.class)
        @JsonProperty("item_CATEGORY")
        public String item_CATEGORY;
        @Column(name = "item_CODE",type = String.class)
        @JsonProperty("item_CODE")
        public String item_CODE;

        @Column(name = "item_DESCRIPTION",type = String.class)
        @JsonProperty("item_DESCRIPTION")
        public String item_DESCRIPTION;

        @Column(name = "primary_UOM_CODE",type = String.class)
        @JsonProperty("primary_UOM_CODE")
        public String primary_UOM_CODE;

        @Column(name = "primary_TRANSACTION_QUANTITY",type = String.class)
        @JsonProperty("primary_TRANSACTION_QUANTITY")
        public String primary_TRANSACTION_QUANTITY;

        @Column(name = "secondary_UOM_CODE",type = String.class)
        @JsonProperty("secondary_UOM_CODE")
        public String secondary_UOM_CODE;
        @Column(name = "secondary_TRANSACTION_QUANTITY",type = String.class)
        @JsonProperty("secondary_TRANSACTION_QUANTITY")
        public String secondary_TRANSACTION_QUANTITY;
        @Column(name = "AGE",type = String.class)
        @JsonProperty("AGE")
        public String AGE;
    }
    public static class medistocks{
        @Column(name = "item_TYPE",type = String.class)
        @JsonProperty("item_TYPE")
        public String item_TYPE;

        @Column(name = "organization_ID",type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;

        @Column(name = "subinventory_CODE",type = String.class)
        @JsonProperty("subinventory_CODE")
        public String subinventory_CODE;

        @Column(name = "inventory_ITEM_ID",type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "item_GROUP",type = String.class)
        @JsonProperty("item_GROUP")
        public String item_GROUP;
        @Column(name = "item_CATEGORY",type = String.class)
        @JsonProperty("item_CATEGORY")
        public String item_CATEGORY;
        @Column(name = "item_CODE",type = String.class)
        @JsonProperty("item_CODE")
        public String item_CODE;

        @Column(name = "item_DESCRIPTION",type = String.class)
        @JsonProperty("item_DESCRIPTION")
        public String item_DESCRIPTION;

        @Column(name = "primary_UOM_CODE",type = String.class)
        @JsonProperty("primary_UOM_CODE")
        public String primary_UOM_CODE;

        @Column(name = "primary_TRANSACTION_QUANTITY",type = String.class)
        @JsonProperty("primary_TRANSACTION_QUANTITY")
        public String primary_TRANSACTION_QUANTITY;

        @Column(name = "secondary_UOM_CODE",type = String.class)
        @JsonProperty("secondary_UOM_CODE")
        public String secondary_UOM_CODE;
        @Column(name = "secondary_TRANSACTION_QUANTITY",type = String.class)
        @JsonProperty("secondary_TRANSACTION_QUANTITY")
        public String secondary_TRANSACTION_QUANTITY;
        @Column(name = "AGE",type = String.class)
        @JsonProperty("AGE")
        public String AGE;
    }
    public static class vaccinestock{
        @Column(name = "item_TYPE",type = String.class)
        @JsonProperty("item_TYPE")
        public String item_TYPE;

        @Column(name = "organization_ID",type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;

        @Column(name = "subinventory_CODE",type = String.class)
        @JsonProperty("subinventory_CODE")
        public String subinventory_CODE;

        @Column(name = "inventory_ITEM_ID",type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "item_GROUP",type = String.class)
        @JsonProperty("item_GROUP")
        public String item_GROUP;
        @Column(name = "item_CATEGORY",type = String.class)
        @JsonProperty("item_CATEGORY")
        public String item_CATEGORY;
        @Column(name = "item_CODE",type = String.class)
        @JsonProperty("item_CODE")
        public String item_CODE;

        @Column(name = "item_DESCRIPTION",type = String.class)
        @JsonProperty("item_DESCRIPTION")
        public String item_DESCRIPTION;

        @Column(name = "primary_UOM_CODE",type = String.class)
        @JsonProperty("primary_UOM_CODE")
        public String primary_UOM_CODE;

        @Column(name = "primary_TRANSACTION_QUANTITY",type = String.class)
        @JsonProperty("primary_TRANSACTION_QUANTITY")
        public String primary_TRANSACTION_QUANTITY;

        @Column(name = "secondary_UOM_CODE",type = String.class)
        @JsonProperty("secondary_UOM_CODE")
        public String secondary_UOM_CODE;
        @Column(name = "secondary_TRANSACTION_QUANTITY",type = String.class)
        @JsonProperty("secondary_TRANSACTION_QUANTITY")
        public String secondary_TRANSACTION_QUANTITY;
        @Column(name = "AGE",type = String.class)
        @JsonProperty("AGE")
        public String AGE;
    }
    public static class gppsperformance{
        @Column(name = "flock",type = String.class)
        @JsonProperty("flock")
        public String flock;

        @Column(name = "p_CODE",type = String.class)
        @JsonProperty("p_CODE")
        public String p_CODE;

        @Column(name = "age",type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "hhm",type = String.class)
        @JsonProperty("hhm")
        public String hhm;

        @Column(name = "hhf",type = String.class)
        @JsonProperty("hhf")
        public String hhf;
        @Column(name = "op_STK_F",type = String.class)
        @JsonProperty("op_STK_F")
        public String op_STK_F;
        @Column(name = "op_STK_M",type = String.class)
        @JsonProperty("op_STK_M")
        public String op_STK_M;

        @Column(name = "cl_STK_F",type = String.class)
        @JsonProperty("cl_STK_F")
        public String cl_STK_F;

        @Column(name = "cl_STK_M",type = String.class)
        @JsonProperty("cl_STK_M")
        public String cl_STK_M;

        @Column(name = "fmort",type = String.class)
        @JsonProperty("fmort")
        public String fmort;

        @Column(name = "cum_FMORT",type = String.class)
        @JsonProperty("cum_FMORT")
        public String cum_FMORT;
        @Column(name = "fmort_PER",type = String.class)
        @JsonProperty("fmort_PER")
        public String fmort_PER;

        @Column(name = "cum_FMORT_PER",type = String.class)
        @JsonProperty("cum_FMORT_PER")
        public String cum_FMORT_PER;

        @Column(name = "mmort",type = String.class)
        @JsonProperty("mmort")
        public String mmort;

        @Column(name = "cum_MMORT",type = String.class)
        @JsonProperty("cum_MMORT")
        public String cum_MMORT;

        @Column(name = "mmort_PER",type = String.class)
        @JsonProperty("mmort_PER")
        public String mmort_PER;

        @Column(name = "cum_MMORT_PER",type = String.class)
        @JsonProperty("cum_MMORT_PER")
        public String cum_MMORT_PER;
        @Column(name = "fcull",type = String.class)
        @JsonProperty("fcull")
        public String fcull;
        @Column(name = "mcull",type = String.class)
        @JsonProperty("mcull")
        public String mcull;

        @Column(name = "cull_PER_MA",type = String.class)
        @JsonProperty("cull_PER_MA")
        public String cull_PER_MA;

        @Column(name = "cum_DEPL_FE_NO",type = String.class)
        @JsonProperty("cum_DEPL_FE_NO")
        public String cum_DEPL_FE_NO;

        @Column(name = "cum_DEPL_FE_PER",type = String.class)
        @JsonProperty("cum_DEPL_FE_PER")
        public String cum_DEPL_FE_PER;

        @Column(name = "cum_DEPL_MA_NO",type = String.class)
        @JsonProperty("cum_DEPL_MA_NO")
        public String cum_DEPL_MA_NO;
        @Column(name = "cum_DEPL_MA_PER",type = String.class)
        @JsonProperty("cum_DEPL_MA_PER")
        public String cum_DEPL_MA_PER;

        @Column(name = "t_OUT_M",type = String.class)
        @JsonProperty("t_OUT_M")
        public String t_OUT_M;

        @Column(name = "t_OUT_F",type = String.class)
        @JsonProperty("t_OUT_F")
        public String t_OUT_F;

        @Column(name = "t_IN_M",type = String.class)
        @JsonProperty("t_IN_M")
        public String t_IN_M;

        @Column(name = "t_IN_F",type = String.class)
        @JsonProperty("t_IN_F")
        public String t_IN_F;
        @Column(name = "f_FEED_GMS_STD",type = String.class)
        @JsonProperty("f_FEED_GMS_STD")
        public String f_FEED_GMS_STD;
        @Column(name = "f_FEED_GMS_ACT",type = String.class)
        @JsonProperty("f_FEED_GMS_ACT")
        public String f_FEED_GMS_ACT;

        @Column(name = "m_FEED_GMS_STD",type = String.class)
        @JsonProperty("m_FEED_GMS_STD")
        public String m_FEED_GMS_STD;

        @Column(name = "m_FEED_GMS_ACT",type = String.class)
        @JsonProperty("m_FEED_GMS_ACT")
        public String m_FEED_GMS_ACT;
        @Column(name = "cum_M_FEED_GMS_STD",type = String.class)
        @JsonProperty("cum_M_FEED_GMS_STD")
        public String cum_M_FEED_GMS_STD;
        @Column(name = "cum_M_FEED_GMS_ACT",type = String.class)
        @JsonProperty("cum_M_FEED_GMS_ACT")
        public String cum_M_FEED_GMS_ACT;

        @Column(name = "cum_F_FEED_GMS_STD",type = String.class)
        @JsonProperty("cum_F_FEED_GMS_STD")
        public String cum_F_FEED_GMS_STD;

        @Column(name = "cum_F_FEED_GMS_ACT",type = String.class)
        @JsonProperty("cum_F_FEED_GMS_ACT")
        public String cum_F_FEED_GMS_ACT;

        @Column(name = "tot_EGG_ACT",type = String.class)
        @JsonProperty("tot_EGG_ACT")
        public String tot_EGG_ACT;

        @Column(name = "hatch_EGG",type = String.class)
        @JsonProperty("hatch_EGG")
        public String hatch_EGG;
        @Column(name = "hd_PER_STD",type = String.class)
        @JsonProperty("hd_PER_STD")
        public String hd_PER_STD;

        @Column(name = "hd_PER_ACT",type = String.class)
        @JsonProperty("hd_PER_ACT")
        public String hd_PER_ACT;

        @Column(name = "egg_SEL_STD",type = String.class)
        @JsonProperty("egg_SEL_STD")
        public String egg_SEL_STD;

        @Column(name = "egg_SEL_ACT",type = String.class)
        @JsonProperty("egg_SEL_ACT")
        public String egg_SEL_ACT;

        @Column(name = "hcthabty_PCT_STD",type = String.class)
        @JsonProperty("hcthabty_PCT_STD")
        public String hcthabty_PCT_STD;
        @Column(name = "hcthabty_PCT_ACT",type = String.class)
        @JsonProperty("hcthabty_PCT_ACT")
        public String hcthabty_PCT_ACT;

        @Column(name = "cpp_STD",type = String.class)
        @JsonProperty("cpp_STD")
        public String cpp_STD;

        @Column(name = "cpp_ACT",type = String.class)
        @JsonProperty("cpp_ACT")
        public String cpp_ACT;
        @Column(name = "cum_CPP_ACT",type = String.class)
        @JsonProperty("cum_CPP_ACT")
        public String cum_CPP_ACT;

        @Column(name = "cum_CPP_STD",type = String.class)
        @JsonProperty("cum_CPP_STD")
        public String cum_CPP_STD;

        @Column(name = "cum_HE_PER_HH_STD",type = String.class)
        @JsonProperty("cum_HE_PER_HH_STD")
        public String cum_HE_PER_HH_STD;

        @Column(name = "cum_HHHE",type = String.class)
        @JsonProperty("cum_HHHE")
        public String cum_HHHE;

        @Column(name = "act_WT_FEMALE",type = String.class)
        @JsonProperty("act_WT_FEMALE")
        public String act_WT_FEMALE ;
        @Column(name = "female_WEIGHT_STD",type = String.class)
        @JsonProperty("female_WEIGHT_STD")
        public String female_WEIGHT_STD;

        @Column(name = "act_WT_MALE",type = String.class)
        @JsonProperty("act_WT_MALE")
        public String act_WT_MALE;

        @Column(name = "male_WEIGHT_STD",type = String.class)
        @JsonProperty("male_WEIGHT_STD")
        public String male_WEIGHT_STD;

        @Column(name = "egg_WEIGHT_STD",type = String.class)
        @JsonProperty("egg_WEIGHT_STD")
        public String egg_WEIGHT_STD ;
        @Column(name = "egg_WEIGHT_ACT",type = String.class)
        @JsonProperty("egg_WEIGHT_ACT")
        public String egg_WEIGHT_ACT;

        @Column(name = "mort_STD",type = String.class)
        @JsonProperty("mort_STD")
        public String mort_STD;

        @Column(name = "cum_MORT_STD",type = String.class)
        @JsonProperty("cum_MORT_STD")
        public String cum_MORT_STD;

        @Column(name = "egg_MASS_ACT",type = String.class)
        @JsonProperty("egg_MASS_ACT")
        public String egg_MASS_ACT ;
        @Column(name = "egg_MASS_STD",type = String.class)
        @JsonProperty("egg_MASS_STD")
        public String egg_MASS_STD;

        @Column(name = "fe_BDWT_GL",type = String.class)
        @JsonProperty("fe_BDWT_GL")
        public String fe_BDWT_GL ;
        @Column(name = "ma_BDWT_GL",type = String.class)
        @JsonProperty("ma_BDWT_GL")
        public String ma_BDWT_GL;
    }
    public static class dailyentryconsumptiondata{
        @Column(name = "tr_TYPE",type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;

        @Column(name = "tr_TYPE_ID",type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "bird_TYPE",type = String.class)
        @JsonProperty("bird_TYPE")
        public String bird_TYPE;
        @Column(name = "order_ID",type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "location_ID",type = String.class)
        @JsonProperty("location_ID")
        public String location_ID;
        @Column(name = "invtransdate",type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "invtranstype",type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription",type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;

        @Column(name = "invtransqty",type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty ;
        @Column(name = "invtransuom",type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;

    }

    public static class dailyentrymst{
        @Column(name = "bird_TYPE",type = String.class)
        @JsonProperty("bird_TYPE")
        public String bird_TYPE;
        @Column(name = "tr_TYPE",type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;
        @Column(name = "tr_TYPE_ID",type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;
        @Column(name = "order_ID",type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;
        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;
        @Column(name = "location_ID",type = String.class)
        @JsonProperty("location_ID")
        public String location_ID;
        @Column(name = "invtranstype",type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription",type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;

        @Column(name = "invtransqty",type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty;

        @Column(name = "invtransuom",type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;

        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "invtransdate",type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

//        @Column(name = "created_date",type = String.class)
//        @JsonProperty("created_date")
//        public String created_date;


    }

    public static class dailyentryproductiondata{
        @Column(name = "tr_TYPE",type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;

        @Column(name = "tr_TYPE_ID",type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "bird_TYPE",type = String.class)
        @JsonProperty("bird_TYPE")
        public String bird_TYPE;
        @Column(name = "order_ID",type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "location_ID",type = String.class)
        @JsonProperty("location_ID")
        public String location_ID;
        @Column(name = "invtransdate",type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "invtranstype",type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription",type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;

        @Column(name = "invtransqty",type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty ;
        @Column(name = "invtransuom",type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;

    }
    public static class dailyentrylivebirddata{
        @Column(name = "tr_TYPE",type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;

        @Column(name = "tr_TYPE_ID",type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "bird_TYPE",type = String.class)
        @JsonProperty("bird_TYPE")
        public String bird_TYPE;
        @Column(name = "order_ID",type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "location_ID",type = String.class)
        @JsonProperty("location_ID")
        public String location_ID;
        @Column(name = "invtransdate",type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "invtranstype",type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription",type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;

        @Column(name = "invtransqty",type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty ;
        @Column(name = "invtransuom",type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;
    }
    public static class dailyentrytransferin{
        @Column(name = "tr_TYPE",type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;

        @Column(name = "tr_TYPE_ID",type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "bird_TYPE",type = String.class)
        @JsonProperty("bird_TYPE")
        public String bird_TYPE;
        @Column(name = "order_ID",type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "location_ID",type = String.class)
        @JsonProperty("location_ID")
        public String location_ID;
        @Column(name = "invtransdate",type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "invtranstype",type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription",type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;

        @Column(name = "invtransqty",type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty ;
        @Column(name = "invtransuom",type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;
    }
    public static class dailyentrytransferout{
        @Column(name = "tr_TYPE",type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;

        @Column(name = "tr_TYPE_ID",type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "bird_TYPE",type = String.class)
        @JsonProperty("bird_TYPE")
        public String bird_TYPE;
        @Column(name = "order_ID",type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_ID",type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "location_ID",type = String.class)
        @JsonProperty("location_ID")
        public String location_ID;
        @Column(name = "invtransdate",type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "invtranstype",type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription",type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;

        @Column(name = "invtransqty",type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty ;
        @Column(name = "invtransuom",type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;

        @Column(name = "vehicleNo",type = String.class)
        @JsonProperty("vehicleNo")
        public String vehicleNo;
    }
    public static class farmerservicecharge {
        @Column(name = "invoice_ID",type = String.class)
        @JsonProperty("invoice_ID")
        public String invoice_ID;

        @Column(name = "parent_BRANCH_NAME",type = String.class)
        @JsonProperty("parent_BRANCH_NAME")
        public String parent_BRANCH_NAME;

        @Column(name = "farm_NAME",type = String.class)
        @JsonProperty("farm_NAME")
        public String farm_NAME;
        @Column(name = "sc_NO",type = String.class)
        @JsonProperty("sc_NO")
        public String sc_NO ;

        @Column(name = "sc_DATE",type = String.class)
        @JsonProperty("sc_DATE")
        public String sc_DATE;

        @Column(name = "farm_CODE",type = String.class)
        @JsonProperty("farm_CODE")
        public String farm_CODE;
        @Column(name = "period",type = String.class)
        @JsonProperty("period")
        public String period;

        @Column(name = "farm_TYPE",type = String.class)
        @JsonProperty("farm_TYPE")
        public String farm_TYPE;

        @Column(name = "hen_HOUSED_BIRDS",type = String.class)
        @JsonProperty("hen_HOUSED_BIRDS")
        public String hen_HOUSED_BIRDS;

        @Column(name = "hhhe_TRANS",type = String.class)
        @JsonProperty("hhhe_TRANS")
        public String hhhe_TRANS;

        @Column(name = "age",type = String.class)
        @JsonProperty("age")
        public String age ;
        @Column(name = "sc_RATE",type = String.class)
        @JsonProperty("sc_RATE")
        public String sc_RATE;

        @Column(name = "addnl_SC_RATE",type = String.class)
        @JsonProperty("addnl_SC_RATE")
        public String addnl_SC_RATE;

        @Column(name = "sc_AMT",type = String.class)
        @JsonProperty("sc_AMT")
        public String sc_AMT;

        @Column(name = "addnl_SC_AMT",type = String.class)
        @JsonProperty("addnl_SC_AMT")
        public String addnl_SC_AMT;

        @Column(name = "cpp_INCENTIVE",type = String.class)
        @JsonProperty("cpp_INCENTIVE")
        public String cpp_INCENTIVE;

        @Column(name = "net_AMT",type = String.class)
        @JsonProperty("net_AMT")
        public String net_AMT ;
        @Column(name = "net_CREDIT_AMT",type = String.class)
        @JsonProperty("net_CREDIT_AMT")
        public String net_CREDIT_AMT;

        @Column(name = "tds_LESS",type = String.class)
        @JsonProperty("tds_LESS")
        public String tds_LESS;

        @Column(name = "ret_AMT",type = String.class)
        @JsonProperty("ret_AMT")
        public String ret_AMT;

        @Column(name = "ccp_INC_DED",type = String.class)
        @JsonProperty("ccp_INC_DED")
        public String ccp_INC_DED;

        @Column(name = "other_DEDUCT",type = String.class)
        @JsonProperty("other_DEDUCT")
        public String other_DEDUCT;

        @Column(name = "net_DEDEC",type = String.class)
        @JsonProperty("net_DEDEC")
        public String net_DEDEC;

        @Column(name = "payable_AMT",type = String.class)
        @JsonProperty("payable_AMT")
        public String payable_AMT ;
        @Column(name = "payment_DATE",type = String.class)
        @JsonProperty("payment_DATE")
        public String payment_DATE;
    }
    public static class eggcollectionsync{
        @Column(name = "trans_ID",type = String.class)
        @JsonProperty("trans_ID")
        public String trans_ID;

        @Column(name = "trans_DET_LINE_ID",type = String.class)
        @JsonProperty("trans_DET_LINE_ID")
        public String trans_DET_LINE_ID;

        @Column(name = "ps_ORGANIZATION_ID",type = String.class)
        @JsonProperty("ps_ORGANIZATION_ID")
        public String ps_ORGANIZATION_ID ;
        @Column(name = "hat_ORGANIZATION_ID",type = String.class)
        @JsonProperty("hat_ORGANIZATION_ID")
        public String hat_ORGANIZATION_ID;
    }
    public static class servicechargeheader{
        @Column(name = "flock_CODE",type = String.class)
        @JsonProperty("flock_CODE")
        public String flock_CODE;

        @Column(name = "farm_CODE",type = String.class)
        @JsonProperty("farm_CODE")
        public String farm_CODE;

        @Column(name = "farm_NAME",type = String.class)
        @JsonProperty("farm_NAME")
        public String farm_NAME;
        @Column(name = "sc_NO",type = String.class)
        @JsonProperty("sc_NO")
        public String sc_NO ;

        @Column(name = "sc_DATE",type = String.class)
        @JsonProperty("sc_DATE")
        public String sc_DATE;


        @Column(name = "period",type = String.class)
        @JsonProperty("period")
        public String period;
        @Column(name = "from_PERIOD",type = String.class)
        @JsonProperty("from_PERIOD")
        public String from_PERIOD;
        @Column(name = "to_PERIOD",type = String.class)
        @JsonProperty("to_PERIOD")
        public String to_PERIOD;

        @Column(name = "hen_HOUSED_BIRDS",type = String.class)
        @JsonProperty("hen_HOUSED_BIRDS")
        public String hen_HOUSED_BIRDS;

        @Column(name = "farm_TYPE",type = String.class)
        @JsonProperty("farm_TYPE")
        public String farm_TYPE;

        @Column(name = "no_OF_HE_EGG_TRANS",type = String.class)
        @JsonProperty("no_OF_HE_EGG_TRANS")
        public String no_OF_HE_EGG_TRANS ;
        @Column(name = "no_OF_TE_EGG_TRANS",type = String.class)
        @JsonProperty("no_OF_TE_EGG_TRANS")
        public String no_OF_TE_EGG_TRANS;

        @Column(name = "rej_RATE",type = String.class)
        @JsonProperty("rej_RATE")
        public String rej_RATE;

        @Column(name = "sc_for_HE_coll",type = String.class)
        @JsonProperty("sc_for_HE_coll")
        public String sc_for_HE_coll;

        @Column(name = "sc_for_tE_coll",type = String.class)
        @JsonProperty("sc_for_tE_coll")
        public String sc_for_tE_coll;

        @Column(name = "he_NO",type = String.class)
        @JsonProperty("he_NO")
        public String he_NO;

        @Column(name = "he_PCT",type = String.class)
        @JsonProperty("he_PCT")
        public String he_PCT ;
        @Column(name = "sc_RATE",type = String.class)
        @JsonProperty("sc_RATE")
        public String sc_RATE;

        @Column(name = "up_SC_RATE",type = String.class)
        @JsonProperty("up_SC_RATE")
        public String up_SC_RATE;

        @Column(name = "hatch_DATE",type = String.class)
        @JsonProperty("hatch_DATE")
        public String hatch_DATE;

        @Column(name = "age",type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "retamt",type = String.class)
        @JsonProperty("retamt")
        public String retamt;

        @Column(name = "he_SAMPLE_DATE",type = String.class)
        @JsonProperty("he_SAMPLE_DATE")
        public String he_SAMPLE_DATE;

        @Column(name = "he_SAMPLE_QTY",type = String.class)
        @JsonProperty("he_SAMPLE_QTY")
        public String he_SAMPLE_QTY ;
        @Column(name = "he_UPTO_SAM_QTY",type = String.class)
        @JsonProperty("he_UPTO_SAM_QTY")
        public String he_UPTO_SAM_QTY;

        @Column(name = "account_NAME",type = String.class)
        @JsonProperty("account_NAME")
        public String account_NAME;

        @Column(name = "bank_NAME",type = String.class)
        @JsonProperty("bank_NAME")
        public String bank_NAME ;
        @Column(name = "account_NUM",type = String.class)
        @JsonProperty("account_NUM")
        public String account_NUM;

        @Column(name = "up_NO_OF_HE_EGG_TRANS",type = String.class)
        @JsonProperty("up_NO_OF_HE_EGG_TRANS")
        public String up_NO_OF_HE_EGG_TRANS;

        @Column(name = "up_NO_OF_TE_EGG_TRANS",type = String.class)
        @JsonProperty("up_NO_OF_TE_EGG_TRANS")
        public String up_NO_OF_TE_EGG_TRANS;

        @Column(name = "up_SC_FOR_HE_COLL",type = String.class)
        @JsonProperty("up_SC_FOR_HE_COLL")
        public String up_SC_FOR_HE_COLL ;
        @Column(name = "up_SC_FOR_TE_COLL",type = String.class)
        @JsonProperty("up_SC_FOR_TE_COLL")
        public String up_SC_FOR_TE_COLL;

        @Column(name = "up_RETAMT",type = String.class)
        @JsonProperty("up_RETAMT")
        public String up_RETAMT;

        @Column(name = "other_DEDUCTION_CAL",type = String.class)
        @JsonProperty("other_DEDUCTION_CAL")
        public String other_DEDUCTION_CAL;

        @Column(name = "sc_AMT",type = String.class)
        @JsonProperty("sc_AMT")
        public String sc_AMT ;

        @Column(name = "tds",type = String.class)
        @JsonProperty("tds")
        public String tds;

        @Column(name = "addnl_SC_AMT",type = String.class)
        @JsonProperty("addnl_SC_AMT")
        public String addnl_SC_AMT;

        @Column(name = "addnl_SC_RATE",type = String.class)
        @JsonProperty("addnl_SC_RATE")
        public String addnl_SC_RATE ;

        @Column(name = "upto_ADDNL_SC_AMT",type = String.class)
        @JsonProperty("upto_ADDNL_SC_AMT")
        public String upto_ADDNL_SC_AMT;

    }
    public static class Eggcollectionhdr{


        @Column(name = "TRANS_ID",type = BigDecimal.class)
        @JsonProperty("TRANS_ID")
        public BigDecimal TRANS_ID;

        @Column(name = "TRANS_DATE",type = String.class)
        @JsonProperty("TRANS_DATE")
        public String TRANS_DATE ;
        @Column(name = "LEDGER_ID",type = BigDecimal.class)
        @JsonProperty("LEDGER_ID")
        public BigDecimal LEDGER_ID;

        @Column(name = "PS_ORG_ID",type = BigDecimal.class)
        @JsonProperty("PS_ORG_ID")
        public BigDecimal PS_ORG_ID;

        @Column(name = "UOM",type = String.class)
        @JsonProperty("UOM")
        public String UOM;

        @Column(name = "DELIVERY_TO",type = String.class)
        @JsonProperty("DELIVERY_TO")
        public String DELIVERY_TO;

        @Column(name = "PLANNED_QUANTITY",type = BigDecimal.class)
        @JsonProperty("PLANNED_QUANTITY")
        public BigDecimal PLANNED_QUANTITY;

        @Column(name = "ALLOCATED_QUANTITY",type = BigDecimal.class)
        @JsonProperty("ALLOCATED_QUANTITY")
        public BigDecimal ALLOCATED_QUANTITY;

        @Column(name = "PENDING_QUANTITY",type = BigDecimal.class)
        @JsonProperty("PENDING_QUANTITY")
        public BigDecimal PENDING_QUANTITY ;
        @Column(name = "CREATED_BY",type = BigDecimal.class)
        @JsonProperty("CREATED_BY")
        public BigDecimal CREATED_BY;

        @Column(name = "CREATION_DATE",type = String.class)
        @JsonProperty("CREATION_DATE")
        public String CREATION_DATE;

        @Column(name = "LAST_UPDATED_BY",type = BigDecimal.class)
        @JsonProperty("LAST_UPDATED_BY")
        public BigDecimal LAST_UPDATED_BY ;
        @Column(name = "LAST_UPDATED_DATE",type = String.class)
        @JsonProperty("LAST_UPDATED_DATE")
        public String LAST_UPDATED_DATE;

        @Column(name = "HATCHERY_ID",type = BigDecimal.class)
        @JsonProperty("HATCHERY_ID")
        public BigDecimal HATCHERY_ID;

        @Column(name = "VEHICLE_NO",type = String.class)
        @JsonProperty("VEHICLE_NO")
        public String VEHICLE_NO;

        @Column(name = "TRANSFER_ENTRY_FLAG",type = String.class)
        @JsonProperty("TRANSFER_ENTRY_FLAG")
        public String TRANSFER_ENTRY_FLAG ;

        @Column(name = "Pending_quantity",type = String.class)
        @JsonProperty("Pending_quantity")
        public String Pending_quantity;

    }
    public static class EggCollectionLines{
        @Column(name = "TRANS_ID",type = BigDecimal.class)
        @JsonProperty("TRANS_ID")
        public BigDecimal TRANS_ID;

        @Column(name = "TRANS_LINE_ID",type = BigDecimal.class)
        @JsonProperty("TRANS_LINE_ID")
        public BigDecimal TRANS_LINE_ID;

        @Column(name = "PS_ORGANIZATION_ID",type = BigDecimal.class)
        @JsonProperty("PS_ORGANIZATION_ID")
        public BigDecimal PS_ORGANIZATION_ID ;
        @Column(name = "SHED_TYPE",type = String.class)
        @JsonProperty("SHED_TYPE")
        public String SHED_TYPE;

        @Column(name = "MALE_BREED",type = String.class)
        @JsonProperty("MALE_BREED")
        public String MALE_BREED;

        @Column(name = "FEMALE_BREED",type = String.class)
        @JsonProperty("FEMALE_BREED")
        public String FEMALE_BREED;

        @Column(name = "LOCATION",type = String.class)
        @JsonProperty("LOCATION")
        public String LOCATION;

        @Column(name = "LOT_NUMBER",type = String.class)
        @JsonProperty("LOT_NUMBER")
        public String LOT_NUMBER;

        @Column(name = "BIRD_AGE",type = BigDecimal.class)
        @JsonProperty("BIRD_AGE")
        public BigDecimal BIRD_AGE;

        @Column(name = "INVENTORY_ITEM_ID",type = BigDecimal.class)
        @JsonProperty("INVENTORY_ITEM_ID")
        public BigDecimal INVENTORY_ITEM_ID ;
        @Column(name = "LAY_DATE",type = String.class)
        @JsonProperty("LAY_DATE")
        public String LAY_DATE;

        @Column(name = "LAY_DAYS",type = BigDecimal.class)
        @JsonProperty("LAY_DAYS")
        public BigDecimal LAY_DAYS;

        @Column(name = "STOCK_QTY",type = BigDecimal.class)
        @JsonProperty("STOCK_QTY")
        public BigDecimal STOCK_QTY ;
        @Column(name = "ALLOC_QTY",type = String.class)
        @JsonProperty("ALLOC_QTY")
        public String ALLOC_QTY;

        @Column(name = "PEND_QTY",type = BigDecimal.class)
        @JsonProperty("PEND_QTY")
        public BigDecimal PEND_QTY;

        @Column(name = "CREATED_BY",type = String.class)
        @JsonProperty("CREATED_BY")
        public String CREATED_BY;

        @Column(name = "CREATION_DATE",type = String.class)
        @JsonProperty("CREATION_DATE")
        public String CREATION_DATE ;

        @Column(name = "LAST_UPDATED_BY",type = BigDecimal.class)
        @JsonProperty("LAST_UPDATED_BY")
        public BigDecimal LAST_UPDATED_BY;

        @Column(name = "LAST_UPDATED_DATE",type = String.class)
        @JsonProperty("LAST_UPDATED_DATE")
        public String LAST_UPDATED_DATE;

        @Column(name = "FLOCK_NUMBER",type = String.class)
        @JsonProperty("FLOCK_NUMBER")
        public String FLOCK_NUMBER;

        @Column(name = "TRANSFER_ENTRY_FLAG",type = String.class)
        @JsonProperty("TRANSFER_ENTRY_FLAG")
        public String TRANSFER_ENTRY_FLAG ;
    }
    public static class EggCollectionDtls{
        @Column(name = "TRANS_ID",type = BigDecimal.class)
        @JsonProperty("TRANS_ID")
        public BigDecimal TRANS_ID;

        @Column(name = "TRANS_LINE_ID",type = BigDecimal.class)
        @JsonProperty("TRANS_LINE_ID")
        public BigDecimal TRANS_LINE_ID;

        @Column(name = "TRANS_DET_LINE_ID",type = BigDecimal.class)
        @JsonProperty("TRANS_DET_LINE_ID")
        public BigDecimal TRANS_DET_LINE_ID ;
        @Column(name = "HAT_ORGANIZATION_ID",type = BigDecimal.class)
        @JsonProperty("HAT_ORGANIZATION_ID")
        public BigDecimal HAT_ORGANIZATION_ID;

        @Column(name = "SETTING_DATE",type = String.class)
        @JsonProperty("SETTING_DATE")
        public String SETTING_DATE;

        @Column(name = "ALLOC_QTY_BOX",type = BigDecimal.class)
        @JsonProperty("ALLOC_QTY_BOX")
        public BigDecimal ALLOC_QTY_BOX;

        @Column(name = "ALLOC_QTY_NOS",type = BigDecimal.class)
        @JsonProperty("ALLOC_QTY_NOS")
        public BigDecimal ALLOC_QTY_NOS;

        @Column(name = "MODE_OF_TRANSPORT",type = String.class)
        @JsonProperty("MODE_OF_TRANSPORT")
        public String MODE_OF_TRANSPORT;

        @Column(name = "VEHICLE_TYPE",type = String.class)
        @JsonProperty("VEHICLE_TYPE")
        public String VEHICLE_TYPE;

        @Column(name = "VEHICLE_NO",type = String.class)
        @JsonProperty("VEHICLE_NO")
        public String VEHICLE_NO ;
        @Column(name = "TRANSPORTER_NAME",type = String.class)
        @JsonProperty("TRANSPORTER_NAME")
        public String TRANSPORTER_NAME;

        @Column(name = "PACKING_TYPE",type = String.class)
        @JsonProperty("PACKING_TYPE")
        public String PACKING_TYPE;

        @Column(name = "CREATION_DATE",type = String.class)
        @JsonProperty("CREATION_DATE")
        public String CREATION_DATE ;
        @Column(name = "LAST_UPDATED_BY",type = String.class)
        @JsonProperty("LAST_UPDATED_BY")
        public String LAST_UPDATED_BY;

        @Column(name = "LAST_UPDATED_DATE",type = Date.class)
        @JsonProperty("LAST_UPDATED_DATE")
        public Date LAST_UPDATED_DATE;

        @Column(name = "TRANSFER_ENTRY_FLAG",type = String.class)
        @JsonProperty("TRANSFER_ENTRY_FLAG")
        public String TRANSFER_ENTRY_FLAG;

        @Column(name = "TRANS_QTY",type = BigDecimal.class)
        @JsonProperty("TRANS_QTY")
        public BigDecimal TRANS_QTY ;

        @Column(name = "DESPATCH_DATE",type = String.class)
        @JsonProperty("DESPATCH_DATE")
        public String DESPATCH_DATE;

    }
}

