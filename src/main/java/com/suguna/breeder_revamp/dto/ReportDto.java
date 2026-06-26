package com.suguna.breeder_revamp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

import java.util.ArrayList;

public class ReportDto {
    @JsonProperty("candlingReport")
    ArrayList<gppsCandlingreport> candlingReport;

    @JsonProperty("performance")
    ArrayList<gppsPerformanceResultDto> performance;

    @JsonProperty("daywisereport")
    ArrayList<dailyEntryConsumption> daywisereport;

    @JsonProperty("dailyMonitoring")
    ArrayList<dailyMonitoring> dailyMonitoring;

    @JsonProperty("hatchingReport")
    ArrayList<gppsHatchingreport> hatchingReport;
    @JsonProperty("eggGardingReport")
    ArrayList<eggGradingreport> eggGardingReport;
    @JsonProperty("coolroomstock")
    ArrayList<coolRoomstock> coolroomstock;
    @JsonProperty("farmStock")
    ArrayList<StocksResultDto> farmStock;

    @JsonProperty("eggqualitycapturereport")
    ArrayList<eggQualitycapturereport> eggqualitycapturereport;

    public ArrayList<eggQualitycapturereport> getEggqualitycapturereport() {
        return eggqualitycapturereport;
    }

    public void setEggqualitycapturereport(ArrayList<eggQualitycapturereport> eggqualitycapturereport) {
        this.eggqualitycapturereport = eggqualitycapturereport;
    }

    public ArrayList<StocksResultDto> getFarmStock() {
        return farmStock;
    }

    public void setFarmStock(ArrayList<StocksResultDto> farmStock) {
        this.farmStock = farmStock;
    }

    public ArrayList<coolRoomstock> getCoolroomstock() {
        return coolroomstock;
    }

    public void setCoolroomstock(ArrayList<coolRoomstock> coolroomstock) {
        this.coolroomstock = coolroomstock;
    }

    public ArrayList<eggGradingreport> getEggGardingReport() {
        return eggGardingReport;
    }

    public void setEggGardingReport(ArrayList<eggGradingreport> eggGardingReport) {
        this.eggGardingReport = eggGardingReport;
    }

    public ArrayList<gppsHatchingreport> getHatchingReport() {
        return hatchingReport;
    }

    public void setHatchingReport(ArrayList<gppsHatchingreport> hatchingReport) {
        this.hatchingReport = hatchingReport;
    }

    public ArrayList<gppsCandlingreport> getCandlingReport() {
        return candlingReport;
    }

    public void setCandlingReport(ArrayList<gppsCandlingreport> candlingReport) {
        this.candlingReport = candlingReport;
    }

    public ArrayList<dailyMonitoring> getDailyMonitoring() {
        return dailyMonitoring;
    }

    public void setDailyMonitoring(ArrayList<dailyMonitoring> dailyMonitoring) {
        this.dailyMonitoring = dailyMonitoring;
    }

    public ArrayList<dailyEntryConsumption> getDaywisereport() {
        return daywisereport;
    }

    public void setDaywisereport(ArrayList<dailyEntryConsumption> daywisereport) {
        this.daywisereport = daywisereport;
    }

    public ArrayList<gppsPerformanceResultDto> getPerformance() {
        return performance;
    }

    public void setPerformance(ArrayList<gppsPerformanceResultDto> performance) {
        this.performance = performance;
    }



    public static class gppsPerformanceResultDto {
        @Column(name = "parent_BRANCH_CODE", type = String.class)
        @JsonProperty("parent_BRANCH_CODE")
        public String parent_BRANCH_CODE;
        @Column(name = "parent_BRANCH_NAME", type = String.class)
        @JsonProperty("parent_BRANCH_NAME")
        public String parent_BRANCH_NAME;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock ;
        @Column(name = "p_CODE", type = String.class)
        @JsonProperty("p_CODE")
        public String p_CODE;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;
        @Column(name = "calendar_YEAR", type = String.class)
        @JsonProperty("calendar_YEAR")
        public String calendar_YEAR;

        @Column(name = "cal_WEEK", type = String.class)
        @JsonProperty("cal_WEEK")
        public String cal_WEEK;

        @Column(name = "cal_WEEK_START", type = String.class)
        @JsonProperty("cal_WEEK_START")
        public String cal_WEEK_START;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

        @Column(name = "op_STK_F", type = String.class)
        @JsonProperty("op_STK_F")
        public String op_STK_F;
        @Column(name = "op_STK_M", type = String.class)
        @JsonProperty("op_STK_M")
        public String op_STK_M;

        @Column(name = "cl_STK_F", type = String.class)
        @JsonProperty("cl_STK_F")
        public String cl_STK_F;


        @Column(name = "cl_STK_M", type = String.class)
        @JsonProperty("cl_STK_M")
        public String cl_STK_M;
        @Column(name = "fmort", type = String.class)
        @JsonProperty("fmort")
        public String fmort;

        @Column(name = "fmort_PER", type = String.class)
        @JsonProperty("fmort_PER")
        public String fmort_PER;

        @Column(name = "mmort", type = String.class)
        @JsonProperty("mmort")
        public String mmort;
        @Column(name = "mmort_PER", type = String.class)
        @JsonProperty("mmort_PER")
        public String mmort_PER;

        @Column(name = "fcull", type = String.class)
        @JsonProperty("fcull")
        public String fcull;
        @Column(name = "cull_PER_FE", type = String.class)
        @JsonProperty("cull_PER_FE")
        public String cull_PER_FE;

        @Column(name = "mcull", type = String.class)
        @JsonProperty("mcull")
        public String mcull;

        @Column(name = "cull_PER_MA", type = String.class)
        @JsonProperty("cull_PER_MA")
        public String cull_PER_MA;

        @Column(name = "cum_DEPL_FE_NO", type = String.class)
        @JsonProperty("cum_DEPL_FE_NO")
        public String cum_DEPL_FE_NO;


        @Column(name = "cum_DEPL_FE_PER", type = String.class)
        @JsonProperty("cum_DEPL_FE_PER")
        public String cum_DEPL_FE_PER;
        @Column(name = "cum_DEPL_MA_NO", type = String.class)
        @JsonProperty("cum_DEPL_MA_NO")
        public String cum_DEPL_MA_NO;

        @Column(name = "cum_DEPL_MA_PER", type = String.class)
        @JsonProperty("cum_DEPL_MA_PER")
        public String cum_DEPL_MA_PER;

        @Column(name = "t_OUT_M", type = String.class)
        @JsonProperty("t_OUT_M")
        public String t_OUT_M;
        @Column(name = "t_OUT_F", type = String.class)
        @JsonProperty("t_OUT_F")
        public String t_OUT_F;

        @Column(name = "t_IN_M", type = String.class)
        @JsonProperty("t_IN_M")
        public String t_IN_M;
        @Column(name = "t_IN_F", type = String.class)
        @JsonProperty("t_IN_F")
        public String t_IN_F;

        @Column(name = "f_FEED_GMS_STD", type = String.class)
        @JsonProperty("f_FEED_GMS_STD")
        public String f_FEED_GMS_STD;


        @Column(name = "f_FEED_GMS_ACT", type = String.class)
        @JsonProperty("f_FEED_GMS_ACT")
        public String f_FEED_GMS_ACT;

        @Column(name = "m_FEED_GMS_STD", type = String.class)
        @JsonProperty("m_FEED_GMS_STD")
        public String m_FEED_GMS_STD;

        @Column(name = "m_FEED_GMS_ACT", type = String.class)
        @JsonProperty("m_FEED_GMS_ACT")
        public String m_FEED_GMS_ACT;


        @Column(name = "cum_M_FEED_GMS_STD", type = String.class)
        @JsonProperty("cum_M_FEED_GMS_STD")
        public String cum_M_FEED_GMS_STD;
        @Column(name = "cum_M_FEED_GMS_ACT", type = String.class)
        @JsonProperty("cum_M_FEED_GMS_ACT")
        public String cum_M_FEED_GMS_ACT;

        @Column(name = "cum_F_FEED_GMS_STD", type = String.class)
        @JsonProperty("cum_F_FEED_GMS_STD")
        public String cum_F_FEED_GMS_STD;

        @Column(name = "cum_F_FEED_GMS_ACT", type = String.class)
        @JsonProperty("cum_F_FEED_GMS_ACT")
        public String cum_F_FEED_GMS_ACT;
        @Column(name = "tot_EGG_ACT", type = String.class)
        @JsonProperty("tot_EGG_ACT")
        public String tot_EGG_ACT;

        @Column(name = "hatch_EGG", type = String.class)
        @JsonProperty("hatch_EGG")
        public String hatch_EGG;
        @Column(name = "hd_PER_STD", type = String.class)
        @JsonProperty("hd_PER_STD")
        public String hd_PER_STD;

        @Column(name = "hd_PER_ACT", type = String.class)
        @JsonProperty("hd_PER_ACT")
        public String hd_PER_ACT;

        @Column(name = "hd_DIFF", type = String.class)
        @JsonProperty("hd_DIFF")
        public String hd_DIFF;

        @Column(name = "egg_SEL_STD", type = String.class)
        @JsonProperty("egg_SEL_STD")
        public String egg_SEL_STD;
        @Column(name = "egg_SEL_ACT", type = String.class)
        @JsonProperty("egg_SEL_ACT")
        public String egg_SEL_ACT;

        @Column(name = "egg_SEL_DIFF", type = String.class)
        @JsonProperty("egg_SEL_DIFF")
        public String egg_SEL_DIFF;
        @Column(name = "hcthabty_PCT_STD", type = String.class)
        @JsonProperty("hcthabty_PCT_STD")
        public String hcthabty_PCT_STD;

        @Column(name = "hcthabty_PCT_ACT", type = String.class)
        @JsonProperty("hcthabty_PCT_ACT")
        public String hcthabty_PCT_ACT;


        @Column(name = "cpp_STD", type = String.class)
        @JsonProperty("cpp_STD")
        public String cpp_STD;

        @Column(name = "cpp_ACT", type = String.class)
        @JsonProperty("cpp_ACT")
        public String cpp_ACT;

        @Column(name = "cum_CPP_ACT", type = String.class)
        @JsonProperty("cum_CPP_ACT")
        public String cum_CPP_ACT;


        @Column(name = "cum_CPP_STD", type = String.class)
        @JsonProperty("cum_CPP_STD")
        public String cum_CPP_STD;
        @Column(name = "cum_HE_PER_HH_STD", type = String.class)
        @JsonProperty("cum_HE_PER_HH_STD")
        public String cum_HE_PER_HH_STD;

        @Column(name = "cum_HHHE", type = String.class)
        @JsonProperty("cum_HHHE")
        public String cum_HHHE;

        @Column(name = "cum_HHHE_DIFF", type = String.class)
        @JsonProperty("cum_HHHE_DIFF")
        public String cum_HHHE_DIFF ;
        @Column(name = "act_WT_FEMALE", type = String.class)
        @JsonProperty("act_WT_FEMALE")
        public String act_WT_FEMALE;

        @Column(name = "female_WEIGHT_STD", type = String.class)
        @JsonProperty("female_WEIGHT_STD")
        public String female_WEIGHT_STD;
        @Column(name = "fe_BDTWT_DIFF", type = String.class)
        @JsonProperty("fe_BDTWT_DIFF")
        public String fe_BDTWT_DIFF;

        @Column(name = "act_WT_MALE", type = String.class)
        @JsonProperty("act_WT_MALE")
        public String act_WT_MALE;

        @Column(name = "male_WEIGHT_STD", type = String.class)
        @JsonProperty("male_WEIGHT_STD")
        public String male_WEIGHT_STD;
        @Column(name = "ma_BDTWT_DIFF", type = String.class)
        @JsonProperty("ma_BDTWT_DIFF")
        public String ma_BDTWT_DIFF;

        @Column(name = "egg_WEIGHT_STD", type = String.class)
        @JsonProperty("egg_WEIGHT_STD")
        public String egg_WEIGHT_STD;

        @Column(name = "egg_WEIGHT_ACT", type = String.class)
        @JsonProperty("egg_WEIGHT_ACT")
        public String egg_WEIGHT_ACT ;
        @Column(name = "egg_WEIGHT_DIFF", type = String.class)
        @JsonProperty("egg_WEIGHT_DIFF")
        public String egg_WEIGHT_DIFF;

        @Column(name = "mort_STD", type = String.class)
        @JsonProperty("mort_STD")
        public String mort_STD;
        @Column(name = "cum_MORT_STD", type = String.class)
        @JsonProperty("cum_MORT_STD")
        public String cum_MORT_STD;

        @Column(name = "egg_MASS_ACT", type = String.class)
        @JsonProperty("egg_MASS_ACT")
        public String egg_MASS_ACT;

        @Column(name = "egg_MASS_STD", type = String.class)
        @JsonProperty("egg_MASS_STD")
        public String egg_MASS_STD;

        @Column(name = "cum_HAT_EGG", type = String.class)
        @JsonProperty("cum_HAT_EGG")
        public String cum_HAT_EGG;

        @Column(name = "cum_TOTAL_EGG", type = String.class)
        @JsonProperty("cum_TOTAL_EGG")
        public String cum_TOTAL_EGG;
        @Column(name = "rep_DATE", type = String.class)
        @JsonProperty("rep_DATE")
        public String rep_DATE;

        @Column(name = "cum_MORT_STD_ROW", type = String.class)
        @JsonProperty("cum_MORT_STD_ROW")
        public String cum_MORT_STD_ROW;

        @Column(name = "cum_FMORT_PER", type = String.class)
        @JsonProperty("cum_FMORT_PER")
        public String cum_FMORT_PER ;
        @Column(name = "cum_MMORT_PER", type = String.class)
        @JsonProperty("cum_MMORT_PER")
        public String cum_MMORT_PER;

        @Column(name = "cum_FMORT", type = String.class)
        @JsonProperty("cum_FMORT")
        public String cum_FMORT;
        @Column(name = "cum_MMORT", type = String.class)
        @JsonProperty("cum_MMORT")
        public String cum_MMORT;

        @Column(name = "fe_BDWT_GL", type = String.class)
        @JsonProperty("fe_BDWT_GL")
        public String fe_BDWT_GL;

        @Column(name = "ma_BDWT_GL", type = String.class)
        @JsonProperty("ma_BDWT_GL")
        public String ma_BDWT_GL;
    }

    public static class StocksResultDto{
        @Column(name = "item_TYPE", type = String.class)
        @JsonProperty("item_TYPE")
        public String item_TYPE;
        @Column(name = "organization_ID", type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "subinventory_CODE", type = String.class)
        @JsonProperty("subinventory_CODE")
        public String subinventory_CODE;
        @Column(name = "inventory_ITEM_ID", type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "item_GROUP", type = String.class)
        @JsonProperty("item_GROUP")
        public String item_GROUP;

        @Column(name = "item_CATEGORY", type = String.class)
        @JsonProperty("item_CATEGORY")
        public String item_CATEGORY ;
        @Column(name = "item_CODE", type = String.class)
        @JsonProperty("item_CODE")
        public String item_CODE;

        @Column(name = "item_DESCRIPTION", type = String.class)
        @JsonProperty("item_DESCRIPTION")
        public String item_DESCRIPTION;
        @Column(name = "primary_UOM_CODE", type = String.class)
        @JsonProperty("primary_UOM_CODE")
        public String primary_UOM_CODE;

        @Column(name = "primary_TRANSACTION_QUANTITY", type = String.class)
        @JsonProperty("primary_TRANSACTION_QUANTITY")
        public String primary_TRANSACTION_QUANTITY;

        @Column(name = "secondary_UOM_CODE", type = String.class)
        @JsonProperty("secondary_UOM_CODE")
        public String secondary_UOM_CODE;

        @Column(name = "secondary_TRANSACTION_QUANTITY", type = String.class)
        @JsonProperty("secondary_TRANSACTION_QUANTITY")
        public String secondary_TRANSACTION_QUANTITY;

    }
    public static class dailyEntryConsumption{
        @Column(name = "tr_TYPE", type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;
        @Column(name = "tr_TYPE_ID", type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "order_ID", type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "invtransdate", type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;

        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER ;
        @Column(name = "invtranstype", type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription", type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;
        @Column(name = "invtransqty", type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty;

        @Column(name = "invtransuom", type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;

        @Column(name = "send_BRANCH_NAME", type = String.class)
        @JsonProperty("send_BRANCH_NAME")
        public String send_BRANCH_NAME;

        @Column(name = "receiving_BRANCH_NAME", type = String.class)
        @JsonProperty("receiving_BRANCH_NAME")
        public String receiving_BRANCH_NAME;



        @Column(name = "COUNT(1)", type = String.class)
        @JsonProperty("COUNT(1)")
        public String COUNT;



    }
    public static class dailyEntryProduction{
        @Column(name = "tr_TYPE", type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;
        @Column(name = "tr_TYPE_ID", type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "order_ID", type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "invtransdate", type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;


        @Column(name = "invtranstype", type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription", type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;
        @Column(name = "invtransqty", type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty;

        @Column(name = "invtransuom", type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;
        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER ;
        @Column(name = "send_BRANCH_NAME", type = String.class)
        @JsonProperty("send_BRANCH_NAME")
        public String send_BRANCH_NAME;

        @Column(name = "receiving_BRANCH_NAME", type = String.class)
        @JsonProperty("receiving_BRANCH_NAME")
        public String receiving_BRANCH_NAME;
    }
    public static class dailyEntryLiveBird{
        @Column(name = "tr_TYPE", type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;
        @Column(name = "tr_TYPE_ID", type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "order_ID", type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "invtransdate", type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;


        @Column(name = "invtranstype", type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription", type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;
        @Column(name = "invtransqty", type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty;

        @Column(name = "invtransuom", type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;
        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER ;
        @Column(name = "send_BRANCH_NAME", type = String.class)
        @JsonProperty("send_BRANCH_NAME")
        public String send_BRANCH_NAME;

        @Column(name = "receiving_BRANCH_NAME", type = String.class)
        @JsonProperty("receiving_BRANCH_NAME")
        public String receiving_BRANCH_NAME;
    }
    public static class dailyentryTransferin{
        @Column(name = "tr_TYPE", type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;

        @Column(name = "COUNT(1)", type = String.class)
        @JsonProperty("COUNT(1)")
        public String COUNT;
        @Column(name = "tr_TYPE_ID", type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "order_ID", type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "invtransdate", type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;


        @Column(name = "invtranstype", type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription", type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;
        @Column(name = "invtransqty", type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty;

        @Column(name = "invtransuom", type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;
        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER ;
        @Column(name = "send_BRANCH_NAME", type = String.class)
        @JsonProperty("send_BRANCH_NAME")
        public String send_BRANCH_NAME;

        @Column(name = "receiving_BRANCH_NAME", type = String.class)
        @JsonProperty("receiving_BRANCH_NAME")
        public String receiving_BRANCH_NAME;
    }
    public static class dailyEntrytransferout{
        @Column(name = "tr_TYPE", type = String.class)
        @JsonProperty("tr_TYPE")
        public String tr_TYPE;
        @Column(name = "tr_TYPE_ID", type = String.class)
        @JsonProperty("tr_TYPE_ID")
        public String tr_TYPE_ID;

        @Column(name = "order_ID", type = String.class)
        @JsonProperty("order_ID")
        public String order_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "invtransdate", type = String.class)
        @JsonProperty("invtransdate")
        public String invtransdate;


        @Column(name = "invtranstype", type = String.class)
        @JsonProperty("invtranstype")
        public String invtranstype;

        @Column(name = "invdescription", type = String.class)
        @JsonProperty("invdescription")
        public String invdescription;
        @Column(name = "invtransqty", type = String.class)
        @JsonProperty("invtransqty")
        public String invtransqty;

        @Column(name = "invtransuom", type = String.class)
        @JsonProperty("invtransuom")
        public String invtransuom;
        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER ;
        @Column(name = "send_BRANCH_NAME", type = String.class)
        @JsonProperty("send_BRANCH_NAME")
        public String send_BRANCH_NAME;

        @Column(name = "receiving_BRANCH_NAME", type = String.class)
        @JsonProperty("receiving_BRANCH_NAME")
        public String receiving_BRANCH_NAME;
    }
    public static class dailyMonitoring{
        @Column(name = "parent_BRANCH_CODE", type = String.class)
        @JsonProperty("parent_BRANCH_CODE")
        public String parent_BRANCH_CODE;
        @Column(name = "parent_BRANCH_NAME", type = String.class)
        @JsonProperty("parent_BRANCH_NAME")
        public String parent_BRANCH_NAME;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "report_DATE", type = String.class)
        @JsonProperty("report_DATE")
        public String report_DATE ;
        @Column(name = "branch_ID", type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;
        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock;

        @Column(name = "op_FE", type = String.class)
        @JsonProperty("op_FE")
        public String op_FE;

        @Column(name = "op_MA", type = String.class)
        @JsonProperty("op_MA")
        public String op_MA;
        @Column(name = "fmort", type = String.class)
        @JsonProperty("fmort")
        public String fmort;

        @Column(name = "mmort", type = String.class)
        @JsonProperty("mmort")
        public String mmort;
        @Column(name = "fcull", type = String.class)
        @JsonProperty("fcull")
        public String fcull;

        @Column(name = "mcull", type = String.class)
        @JsonProperty("mcull")
        public String mcull;


        @Column(name = "fe_FEED_ACT", type = String.class)
        @JsonProperty("fe_FEED_ACT")
        public String fe_FEED_ACT;
        @Column(name = "m_FEED_ACT", type = String.class)
        @JsonProperty("m_FEED_ACT")
        public String m_FEED_ACT;

        @Column(name = "he", type = String.class)
        @JsonProperty("he")
        public String he;

        @Column(name = "te", type = String.class)
        @JsonProperty("te")
        public String te;
        @Column(name = "je", type = String.class)
        @JsonProperty("je")
        public String je;

        @Column(name = "ce", type = String.class)
        @JsonProperty("ce")
        public String ce;
        @Column(name = "ss", type = String.class)
        @JsonProperty("ss")
        public String ss;

        @Column(name = "we", type = String.class)
        @JsonProperty("we")
        public String we;

        @Column(name = "tot_EGGS", type = String.class)
        @JsonProperty("tot_EGGS")
        public String tot_EGGS ;

        @Column(name = "hday", type = String.class)
        @JsonProperty("hday")
        public String hday;


        @Column(name = "prev_HDAY", type = String.class)
        @JsonProperty("prev_HDAY")
        public String prev_HDAY;
        @Column(name = "hegg_ACT", type = String.class)
        @JsonProperty("hegg_ACT")
        public String hegg_ACT;

        @Column(name = "fw_ACT", type = String.class)
        @JsonProperty("fw_ACT")
        public String fw_ACT;

        @Column(name = "fw_CV", type = String.class)
        @JsonProperty("fw_CV")
        public String fw_CV;

        @Column(name = "mw_ACT", type = String.class)
        @JsonProperty("mw_ACT")
        public String mw_ACT;
        @Column(name = "mw_CV", type = String.class)
        @JsonProperty("mw_CV")
        public String mw_CV;

        @Column(name = "f_FEED_GMS_STD", type = String.class)
        @JsonProperty("f_FEED_GMS_STD")
        public String f_FEED_GMS_STD;

        @Column(name = "m_FEED_GMS_STD", type = String.class)
        @JsonProperty("m_FEED_GMS_STD")
        public String m_FEED_GMS_STD ;

        @Column(name = "he_STD", type = String.class)
        @JsonProperty("he_STD")
        public String he_STD;

        @Column(name = "hediff", type = String.class)
        @JsonProperty("hediff")
        public String hediff;
        @Column(name = "hd_DIFF", type = String.class)
        @JsonProperty("hd_DIFF")
        public String hd_DIFF;

    }
    public static class gppsCandlingreport{
        @Column(name = "branch_ID", type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;

        @Column(name = "report_DATE", type = String.class)
        @JsonProperty("report_DATE")
        public String report_DATE ;
        @Column(name = "ps_FLOCK_REF", type = String.class)
        @JsonProperty("ps_FLOCK_REF")
        public String ps_FLOCK_REF;

        @Column(name = "ps_REGION", type = String.class)
        @JsonProperty("ps_REGION")
        public String ps_REGION;
        @Column(name = "hatchery_NAME", type = String.class)
        @JsonProperty("hatchery_NAME")
        public String hatchery_NAME;

        @Column(name = "loading_DATE", type = String.class)
        @JsonProperty("loading_DATE")
        public String loading_DATE;

        @Column(name = "candling_DATE", type = String.class)
        @JsonProperty("candling_DATE")
        public String candling_DATE;
        @Column(name = "hatch_DATE", type = String.class)
        @JsonProperty("hatch_DATE")
        public String hatch_DATE;

        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "set_EGGS", type = String.class)
        @JsonProperty("set_EGGS")
        public String set_EGGS;


        @Column(name = "infertile_EGGS_PCT", type = String.class)
        @JsonProperty("infertile_EGGS_PCT")
        public String infertile_EGGS_PCT;
        @Column(name = "pre_INCUB_EGGS_PCT", type = String.class)
        @JsonProperty("pre_INCUB_EGGS_PCT")
        public String pre_INCUB_EGGS_PCT;

        @Column(name = "eem_EGGS_PCT", type = String.class)
        @JsonProperty("eem_EGGS_PCT")
        public String eem_EGGS_PCT;

        @Column(name = "std_HATCHABILITY_PCT", type = String.class)
        @JsonProperty("std_HATCHABILITY_PCT")
        public String std_HATCHABILITY_PCT;
        @Column(name = "expected_HAT_PERC", type = String.class)
        @JsonProperty("expected_HAT_PERC")
        public String expected_HAT_PERC;

        @Column(name = "hat_GAP", type = String.class)
        @JsonProperty("hat_GAP")
        public String hat_GAP;
        @Column(name = "lay_FROM_DATE", type = String.class)
        @JsonProperty("lay_FROM_DATE")
        public String lay_FROM_DATE;

        @Column(name = "lay_TO_DATE", type = String.class)
        @JsonProperty("lay_TO_DATE")
        public String lay_TO_DATE;

        @Column(name = "setting_ID", type = String.class)
        @JsonProperty("setting_ID")
        public String setting_ID ;

        @Column(name = "batch_NO", type = String.class)
        @JsonProperty("batch_NO")
        public String batch_NO;


        @Column(name = "region_CD", type = String.class)
        @JsonProperty("region_CD")
        public String region_CD;
    }
    public static class gppsHatchingreport{
        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;
        @Column(name = "farmid", type = String.class)
        @JsonProperty("farmid")
        public String farmid;

        @Column(name = "farmname", type = String.class)
        @JsonProperty("farmname")
        public String farmname;

        @Column(name = "hatchery_NAME", type = String.class)
        @JsonProperty("hatchery_NAME")
        public String hatchery_NAME ;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "batch_CLOSE_DATE", type = String.class)
        @JsonProperty("batch_CLOSE_DATE")
        public String batch_CLOSE_DATE;
        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock;

        @Column(name = "setegg", type = String.class)
        @JsonProperty("setegg")
        public String setegg;

        @Column(name = "std", type = String.class)
        @JsonProperty("std")
        public String std;
        @Column(name = "pullout_PER", type = String.class)
        @JsonProperty("pullout_PER")
        public String pullout_PER;

        @Column(name = "sal_PER", type = String.class)
        @JsonProperty("sal_PER")
        public String sal_PER;
        @Column(name = "inf", type = String.class)
        @JsonProperty("inf")
        public String inf;

        @Column(name = "eem", type = String.class)
        @JsonProperty("eem")
        public String eem;


        @Column(name = "dis", type = String.class)
        @JsonProperty("dis")
        public String dis;
        @Column(name = "gas", type = String.class)
        @JsonProperty("gas")
        public String gas;

        @Column(name = "cull", type = String.class)
        @JsonProperty("cull")
        public String cull;

        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;
        @Column(name = "calendar_YEAR", type = String.class)
        @JsonProperty("calendar_YEAR")
        public String calendar_YEAR;

        @Column(name = "cal_WEEK", type = String.class)
        @JsonProperty("cal_WEEK")
        public String cal_WEEK;
        @Column(name = "cal_WEEK_START", type = String.class)
        @JsonProperty("cal_WEEK_START")
        public String cal_WEEK_START;

    }
    public static class eggGradingreport{
        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;
        @Column(name = "farmid", type = String.class)
        @JsonProperty("farmid")
        public String farmid;

        @Column(name = "farmname", type = String.class)
        @JsonProperty("farmname")
        public String farmname;

        @Column(name = "hatchery_NAME", type = String.class)
        @JsonProperty("hatchery_NAME")
        public String hatchery_NAME ;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock;

        @Column(name = "setting_EGG", type = String.class)
        @JsonProperty("setting_EGG")
        public String setting_EGG;
        @Column(name = "setting_DATE", type = String.class)
        @JsonProperty("setting_DATE")
        public String setting_DATE;

        @Column(name = "trans_QUANTITY", type = String.class)
        @JsonProperty("trans_QUANTITY")
        public String trans_QUANTITY;

        @Column(name = "sales", type = String.class)
        @JsonProperty("sales")
        public String sales;
        @Column(name = "salable_EGG", type = String.class)
        @JsonProperty("salable_EGG")
        public String salable_EGG;

        @Column(name = "rec_EGG", type = String.class)
        @JsonProperty("rec_EGG")
        public String rec_EGG;
        @Column(name = "rejection_EGG", type = String.class)
        @JsonProperty("rejection_EGG")
        public String rejection_EGG;

        @Column(name = "rec_DATE", type = String.class)
        @JsonProperty("rec_DATE")
        public String rec_DATE;


        @Column(name = "mini_TABLE", type = String.class)
        @JsonProperty("mini_TABLE")
        public String mini_TABLE;
        @Column(name = "dirty_TABLE", type = String.class)
        @JsonProperty("dirty_TABLE")
        public String dirty_TABLE;

        @Column(name = "wrinkled_TABLE", type = String.class)
        @JsonProperty("wrinkled_TABLE")
        public String wrinkled_TABLE;

        @Column(name = "set_TABLE", type = String.class)
        @JsonProperty("set_TABLE")
        public String set_TABLE;
        @Column(name = "set_JUMBO", type = String.class)
        @JsonProperty("set_JUMBO")
        public String set_JUMBO;

        @Column(name = "transport_CRACK", type = String.class)
        @JsonProperty("transport_CRACK")
        public String transport_CRACK;
        @Column(name = "setting_CRACK", type = String.class)
        @JsonProperty("setting_CRACK")
        public String setting_CRACK;

        @Column(name = "set_CRACK", type = String.class)
        @JsonProperty("set_CRACK")
        public String set_CRACK;
        @Column(name = "transport_WASTE", type = String.class)
        @JsonProperty("transport_WASTE")
        public String transport_WASTE;

        @Column(name = "transport_LOSS", type = String.class)
        @JsonProperty("transport_LOSS")
        public String transport_LOSS;

        @Column(name = "transport_LOSS_PER", type = String.class)
        @JsonProperty("transport_LOSS_PER")
        public String transport_LOSS_PER;
        @Column(name = "setting_WASTE", type = String.class)
        @JsonProperty("setting_WASTE")
        public String setting_WASTE;

        @Column(name = "set_WASTE", type = String.class)
        @JsonProperty("set_WASTE")
        public String set_WASTE;
        @Column(name = "tot", type = String.class)
        @JsonProperty("tot")
        public String tot;
    }
    public static class coolRoomstock{
        @Column(name = "PARENT_BRANCH_ID", type = String.class)
        @JsonProperty("PARENT_BRANCH_ID")
        public String PARENT_BRANCH_ID;
        @Column(name = "PARENT_BRANCH_NAME", type = String.class)
        @JsonProperty("PARENT_BRANCH_NAME")
        public String PARENT_BRANCH_NAME;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME ;
        @Column(name = "organization_ID", type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;

        @Column(name = "locator_ID", type = String.class)
        @JsonProperty("locator_ID")
        public String locator_ID ;

        @Column(name = "locationname", type = String.class)
        @JsonProperty("locationname")
        public String locationname;
        @Column(name = "inventory_ITEM_ID", type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "description", type = String.class)
        @JsonProperty("description")
        public String description;

        @Column(name = "lay_DATE", type = String.class)
        @JsonProperty("lay_DATE")
        public String lay_DATE ;
        @Column(name = "batchflockno", type = String.class)
        @JsonProperty("batchflockno")
        public String batchflockno;

        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER ;

        @Column(name = "batch_TYPE", type = String.class)
        @JsonProperty("batch_TYPE")
        public String batch_TYPE ;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

        @Column(name = "days", type = String.class)
        @JsonProperty("days")
        public String days ;
        @Column(name = "qty", type = String.class)
        @JsonProperty("qty")
        public String qty;
    }
    public static class eggQualitycapturereport{
        @Column(name = "PARENT_BRANCH_ID", type = String.class)
        @JsonProperty("PARENT_BRANCH_ID")
        public String PARENT_BRANCH_ID;
        @Column(name = "PARENT_BRANCH_NAME", type = String.class)
        @JsonProperty("PARENT_BRANCH_NAME")
        public String PARENT_BRANCH_NAME;

        @Column(name = "branch_id", type = String.class)
        @JsonProperty("branch_id")
        public String branch_id;

        @Column(name = "branch_name", type = String.class)
        @JsonProperty("branch_name")
        public String branch_name ;
        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock;

        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed ;

        @Column(name = "transaction_date", type = String.class)
        @JsonProperty("transaction_date")
        public String transaction_date;
        @Column(name = "no_ofsampleegg", type = String.class)
        @JsonProperty("no_ofsampleegg")
        public String no_ofsampleegg;

        @Column(name = "fertile", type = String.class)
        @JsonProperty("fertile")
        public String fertile;

        @Column(name = "fertile_per", type = String.class)
        @JsonProperty("fertile_per")
        public String fertile_per ;
        @Column(name = "infertile", type = String.class)
        @JsonProperty("infertile")
        public String infertile;

        @Column(name = "infertile_per", type = String.class)
        @JsonProperty("infertile_per")
        public String infertile_per ;

        @Column(name = "pre_incubation", type = String.class)
        @JsonProperty("pre_incubation")
        public String pre_incubation ;
        @Column(name = "preincubation_per", type = String.class)
        @JsonProperty("preincubation_per")
        public String preincubation_per;

        @Column(name = "yolk_mottling", type = String.class)
        @JsonProperty("yolk_mottling")
        public String yolk_mottling ;
        @Column(name = "yolk_mottling_per", type = String.class)
        @JsonProperty("yolk_mottling_per")
        public String yolk_mottling_per;

        @Column(name = "meat_spot", type = String.class)
        @JsonProperty("meat_spot")
        public String meat_spot ;

        @Column(name = "meatspot_per", type = String.class)
        @JsonProperty("meatspot_per")
        public String meatspot_per ;
        @Column(name = "blood_spot", type = String.class)
        @JsonProperty("blood_spot")
        public String blood_spot;

        @Column(name = "bloodspot_per", type = String.class)
        @JsonProperty("bloodspot_per")
        public String bloodspot_per ;

    }
    public static class Syncdata{
        @Column(name = "ledger_ID", type = String.class)
        @JsonProperty("ledger_ID")
        public String ledger_ID;
        @Column(name = "region_ID", type = String.class)
        @JsonProperty("region_ID")
        public String region_ID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME ;
        @Column(name = "branch_ID", type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;

        @Column(name = "inventory_LOCATION_ID", type = String.class)
        @JsonProperty("inventory_LOCATION_ID")
        public String inventory_LOCATION_ID ;

        @Column(name = "shed_NO", type = String.class)
        @JsonProperty("shed_NO")
        public String shed_NO;
        @Column(name = "batch_ID", type = String.class)
        @JsonProperty("batch_ID")
        public String batch_ID;

        @Column(name = "batch_NO", type = String.class)
        @JsonProperty("batch_NO")
        public String batch_NO;

        @Column(name = "batch_TYPE", type = String.class)
        @JsonProperty("batch_TYPE")
        public String batch_TYPE ;
        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock;

        @Column(name = "hatch_DATE", type = String.class)
        @JsonProperty("hatch_DATE")
        public String hatch_DATE ;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age ;
        @Column(name = "op_MALE", type = String.class)
        @JsonProperty("op_MALE")
        public String op_MALE;

        @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("op_FEMALE")
        public String op_FEMALE ;

    }
    public static class farmData{
        @Column(name = "headerid", type = String.class)
        @JsonProperty("headerid")
        public String headerid ;

        @Column(name = "shedno", type = String.class)
        @JsonProperty("shedno")
        public String shedno ;
        @Column(name = "branchid", type = String.class)
        @JsonProperty("branchid")
        public String branchid;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branch_CODE")
        public String branch_CODE ;

        @Column(name = "noofbirds", type = String.class)
        @JsonProperty("noofbirds")
        public String noofbirds ;
    }
    public static class eggProductionreport{
        @Column(name = "branchid", type = String.class)
        @JsonProperty("branchid")
        public String branchid;
        @Column(name = "transdate", type = String.class)
        @JsonProperty("transdate")
        public String transdate;

        @Column(name = "deviceid", type = String.class)
        @JsonProperty("deviceid")
        public String deviceid;

        @Column(name = "farmcode", type = String.class)
        @JsonProperty("farmcode")
        public String farmcode ;
        @Column(name = "shedno", type = String.class)
        @JsonProperty("shedno")
        public String shedno;

        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        public String flock ;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "legno", type = String.class)
        @JsonProperty("legno")
        public String legno ;

        @Column(name = "wingno", type = String.class)
        @JsonProperty("wingno")
        public String wingno ;

        @Column(name = "cageno", type = String.class)
        @JsonProperty("cageno")
        public String cageno ;
        @Column(name = "wingnoleft", type = String.class)
        @JsonProperty("wingnoleft")
        public String wingnoleft;

        @Column(name = "legnoleft", type = String.class)
        @JsonProperty("legnoleft")
        public String legnoleft ;

        @Column(name = "eggprod", type = String.class)
        @JsonProperty("eggprod")
        public String eggprod;
        @Column(name = "eggqty", type = String.class)
        @JsonProperty("eggqty")
        public String eggqty;

        @Column(name = "eggweight", type = String.class)
        @JsonProperty("eggweight")
        public String eggweight;

        @Column(name = "eggcolor", type = String.class)
        @JsonProperty("eggcolor")
        public String eggcolor ;
        @Column(name = "eggtexture", type = String.class)
        @JsonProperty("eggtexture")
        public String eggtexture ;

        @Column(name = "wasteegg", type = String.class)
        @JsonProperty("wasteegg")
        public String wasteegg ;

        @Column(name = "feather", type = String.class)
        @JsonProperty("feather")
        public String feather ;
        @Column(name = "creationdate", type = String.class)
        @JsonProperty("creationdate")
        public String creationdate;

        @Column(name = "devicecreationdate", type = String.class)
        @JsonProperty("devicecreationdate")
        public String devicecreationdate ;

        @Column(name = "createdby", type = String.class)
        @JsonProperty("createdby")
        public String createdby ;
    }
    public static class Birddetails{
        @Column(name = "header_ID", type = String.class)
        @JsonProperty("header_ID")
        public String header_ID;

        @Column(name = "wing_NO", type = String.class)
        @JsonProperty("wing_NO")
        public String wing_NO ;
        @Column(name = "leg_NO", type = String.class)
        @JsonProperty("leg_NO")
        public String leg_NO ;

        @Column(name = "cage_NO", type = String.class)
        @JsonProperty("cage_NO")
        public String cage_NO ;

        @Column(name = "wing_NO_Left", type = String.class)
        @JsonProperty("wing_NO_Left")
        public String wing_NO_Left ;
        @Column(name = "leg_NO_Left", type = String.class)
        @JsonProperty("leg_NO_Left")
        public String leg_NO_Left;

        @Column(name = "created_BY", type = String.class)
        @JsonProperty("created_BY")
        public String created_BY ;

        @Column(name = "creation_DATE", type = String.class)
        @JsonProperty("creation_DATE")
        public String creation_DATE ;
    }
    public static class TotalEntryCount{
        @Column(name = "TOTALCOUNT", type = String.class)
        @JsonProperty("TOTALCOUNT")
        public String TOTALCOUNT ;

        @Column(name = "TOTALPREVCOUNT", type = String.class)
        @JsonProperty("TOTALPREVCOUNT")
        public String TOTALPREVCOUNT ;
    }
    public static class checkifentryexist{
        @Column(name = "TOTALCOUNT", type = String.class)
        @JsonProperty("TOTALCOUNT")
        public String TOTALCOUNT ;
    }
    public static class agecount{
        @Column(name = "TOTALCOUNT", type = String.class)
        @JsonProperty("TOTALCOUNT")
        public String TOTALCOUNT ;
    }
    public static class TotalBirdcount{
        @Column(name = "NO_OF_BIRDS", type = String.class)
        @JsonProperty("NO_OF_BIRDS")
        public String NO_OF_BIRDS ;
    }
    public static class Allagecount{
        @Column(name = "qty ", type = String.class)
        @JsonProperty("qty")
        public String qty ;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age ;
    }
    public static class Isfeedentryexist{
        @Column(name = "TOTALCOUNT", type = String.class)
        @JsonProperty("TOTALCOUNT")
        public String TOTALCOUNT ;
    }
    public static class getReports{
        @Column(name = "TRANS_ID", type = String.class)
        @JsonProperty("TRANS_ID")
        public String TRANS_ID;
        @Column(name = "TRANS_DATE", type = String.class)
        @JsonProperty("TRANS_DATE")
        public String TRANS_DATE;

        @Column(name = "DEVICE_ID", type = String.class)
        @JsonProperty("DEVICE_ID")
        public String DEVICE_ID;

        @Column(name = "FARM_CODE", type = String.class)
        @JsonProperty("FARM_CODE")
        public String FARM_CODE ;
        @Column(name = "SHED_NO", type = String.class)
        @JsonProperty("SHED_NO")
        public String SHED_NO;

        @Column(name = "FLOCK", type = String.class)
        @JsonProperty("FLOCK")
        public String FLOCK;
        @Column(name = "AGE", type = String.class)
        @JsonProperty("AGE")
        public String AGE;

        @Column(name = "LEG_NO", type = String.class)
        @JsonProperty("LEG_NO")
        public String LEG_NO;

        @Column(name = "WING_NO", type = String.class)
        @JsonProperty("WING_NO")
        public String WING_NO;
        @Column(name = "CAGE_NO", type = String.class)
        @JsonProperty("CAGE_NO")
        public String CAGE_NO;

        @Column(name = "EGG_PROD", type = String.class)
        @JsonProperty("EGG_PROD")
        public String EGG_PROD;
        @Column(name = "EGG_QTY", type = String.class)
        @JsonProperty("EGG_QTY")
        public String EGG_QTY;

        @Column(name = "EGG_WEIGHT", type = String.class)
        @JsonProperty("EGG_WEIGHT")
        public String EGG_WEIGHT;


        @Column(name = "EGG_COLOR", type = String.class)
        @JsonProperty("EGG_COLOR")
        public String EGG_COLOR;
        @Column(name = "EGG_TEXTURE", type = String.class)
        @JsonProperty("EGG_TEXTURE")
        public String EGG_TEXTURE;

        @Column(name = "CREATION_DATE", type = String.class)
        @JsonProperty("CREATION_DATE")
        public String CREATION_DATE;

        @Column(name = "CREATED_BY", type = String.class)
        @JsonProperty("CREATED_BY")
        public String CREATED_BY;
        @Column(name = "BRANCH_ID", type = String.class)
        @JsonProperty("BRANCH_ID")
        public String BRANCH_ID;

        @Column(name = "DEVICE_CREATION_DATE", type = String.class)
        @JsonProperty("DEVICE_CREATION_DATE")
        public String DEVICE_CREATION_DATE;
        @Column(name = "WASTE_EGG", type = String.class)
        @JsonProperty("WASTE_EGG")
        public String WASTE_EGG;

        @Column(name = "FEATHER", type = String.class)
        @JsonProperty("FEATHER")
        public String FEATHER;

        @Column(name = "WING_NO_LEFT", type = String.class)
        @JsonProperty("WING_NO_LEFT")
        public String WING_NO_LEFT;

        @Column(name = "LEG_NO_LEFT", type = String.class)
        @JsonProperty("LEG_NO_LEFT")
        public String LEG_NO_LEFT;


        @Column(name = "BODY_WEIGHT", type = String.class)
        @JsonProperty("BODY_WEIGHT")
        public String BODY_WEIGHT;
        @Column(name = "TRANS_TYPE", type = String.class)
        @JsonProperty("TRANS_TYPE")
        public String TRANS_TYPE;

        @Column(name = "FEED_ITEM", type = String.class)
        @JsonProperty("FEED_ITEM")
        public String FEED_ITEM;

        @Column(name = "FEED_CONSUMPTION", type = String.class)
        @JsonProperty("FEED_CONSUMPTION")
        public String FEED_CONSUMPTION;
        @Column(name = "FEED_DATE", type = String.class)
        @JsonProperty("FEED_DATE")
        public String FEED_DATE;

    }
    public static class feedStock{
        @Column(name = "FARMCODE", type = String.class)
        @JsonProperty("FARMCODE")
        public String FARMCODE;

        @Column(name = "ITEM_ID", type = String.class)
        @JsonProperty("ITEM_ID")
        public String ITEM_ID;


        @Column(name = "ITEM_CODE", type = String.class)
        @JsonProperty("ITEM_CODE")
        public String ITEM_CODE;
        @Column(name = "ITEM_DESC", type = String.class)
        @JsonProperty("ITEM_DESC")
        public String ITEM_DESC;

        @Column(name = "QTY_BAGS", type = String.class)
        @JsonProperty("QTY_BAGS")
        public String QTY_BAGS;

        @Column(name = "PER_GMS", type = String.class)
        @JsonProperty("PER_GMS")
        public String PER_GMS;
        @Column(name = "SEGMENT1", type = String.class)
        @JsonProperty("SEGMENT1")
        public String SEGMENT1;

    }
    public static class Appinfo {
        @Column(name = "application_NAME", type = String.class)
        @JsonProperty("application_NAME")
        String application_NAME;

        @Column(name = "application_VERSIONCODE", type = String.class)
        @JsonProperty("application_VERSIONCODE")
        String application_VERSIONCODE;
    }

}
