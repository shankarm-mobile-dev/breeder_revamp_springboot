package com.suguna.breeder_revamp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

import java.util.ArrayList;

public class ReportResultDto {
    @JsonProperty("dailymonitoringmst")
    ArrayList<dailymonitoring> dailymonitoringmst;
    @JsonProperty("candlingreportmst")
    ArrayList<gppscandlingreport> candlingreportmst;
    @JsonProperty("hatchingreportmst")
    ArrayList<gppshatchingreport> hatchingreportmst;
    @JsonProperty("egggradingreportmst")
    ArrayList<egggradingreport> egggradingreportmst;
    @JsonProperty("coolroommst")
    ArrayList<coolroomstock> coolroommst;
    @JsonProperty("eggunboxingmst")
    ArrayList<eggunboxing> eggunboxingmst;

    public ArrayList<coolroomstock> getCoolroommst() {
        return coolroommst;
    }

    public void setCoolroommst(ArrayList<coolroomstock> coolroommst) {
        this.coolroommst = coolroommst;
    }

    public ArrayList<egggradingreport> getEgggradingreportmst() {
        return egggradingreportmst;
    }

    public void setEgggradingreportmst(ArrayList<egggradingreport> egggradingreportmst) {
        this.egggradingreportmst = egggradingreportmst;
    }

    public ArrayList<gppshatchingreport> getHatchingreportmst() {
        return hatchingreportmst;
    }

    public void setHatchingreportmst(ArrayList<gppshatchingreport> hatchingreportmst) {
        this.hatchingreportmst = hatchingreportmst;
    }

    public ArrayList<gppscandlingreport> getCandlingreportmst() {
        return candlingreportmst;
    }

    public void setCandlingreportmst(ArrayList<gppscandlingreport> candlingreportmst) {
        this.candlingreportmst = candlingreportmst;
    }

    public ArrayList<dailymonitoring> getDailymonitoringmst() {
        return dailymonitoringmst;
    }

    public void setDailymonitoringmst(ArrayList<dailymonitoring> dailymonitoringmst) {
        this.dailymonitoringmst = dailymonitoringmst;
    }

    public ArrayList<eggunboxing> getEggunboxingmst() {return eggunboxingmst;}

    public void setEggunboxingmst(ArrayList<eggunboxing> eggunboxingmst) {
        this.eggunboxingmst = eggunboxingmst;
    }

    public static class dailymonitoring{
        @Column(name = "parent_BRANCH_CODE", type = String.class)
        @JsonProperty("parent_BRANCH_CODE")
        public String parent_BRANCH_CODE;
        @Column(name = "parent_BRANCH_NAME", type = String.class)
        @JsonProperty("parent_BRANCH_NAME")
        public String parent_BRANCH_NAME;

        @Column(name = "report_DATE", type = String.class)
        @JsonProperty("report_DATE")
        public String report_DATE;

        @Column(name = "branch_ID", type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

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
        public String tot_EGGS;

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

    }
    public static class gppscandlingreport{
        @Column(name = "branch_ID", type = String.class)
        @JsonProperty("branch_ID")
        public String branch_ID;
        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branch_NAME")
        public String branch_NAME;

        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;

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
        public String setting_ID;
        @Column(name = "batch_NO", type = String.class)
        @JsonProperty("batch_NO")
        public String batch_NO;

        @Column(name = "region_CD", type = String.class)
        @JsonProperty("region_CD")
        public String region_CD;

    }
    public static class gppshatchingreport{
        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;
        @Column(name = "farmid", type = String.class)
        @JsonProperty("farmid")
        public String farmid ;

        @Column(name = "farmname", type = String.class)
        @JsonProperty("farmname")
        public String farmname;

        @Column(name = "hatchery_NAME", type = String.class)
        @JsonProperty("hatchery_NAME")
        public String hatchery_NAME;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

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

    }
    public static class gppshatchingreportagewise{
        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;
        @Column(name = "farmid", type = String.class)
        @JsonProperty("farmid")
        public String farmid ;

        @Column(name = "farmname", type = String.class)
        @JsonProperty("farmname")
        public String farmname;

        @Column(name = "hatchery_NAME", type = String.class)
        @JsonProperty("hatchery_NAME")
        public String hatchery_NAME;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

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
    }
    public static class egggradingreport{
        @Column(name = "region_CODE", type = String.class)
        @JsonProperty("region_CODE")
        public String region_CODE;
        @Column(name = "farmid", type = String.class)
        @JsonProperty("farmid")
        public String farmid ;

        @Column(name = "farmname", type = String.class)
        @JsonProperty("farmname")
        public String farmname;

        @Column(name = "hatchery_NAME", type = String.class)
        @JsonProperty("hatchery_NAME")
        public String hatchery_NAME;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        public String age;

        @Column(name = "rec_EGG", type = String.class)
        @JsonProperty("rec_EGG")
        public String rec_EGG;
        @Column(name = "rejection_EGG", type = String.class)
        @JsonProperty("rejection_EGG")
        public String rejection_EGG;

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
        public String set_TABLE ;
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

        @Column(name = "setting_WASTE", type = String.class)
        @JsonProperty("setting_WASTE")
        public String setting_WASTE ;
        @Column(name = "set_WASTE", type = String.class)
        @JsonProperty("set_WASTE")
        public String set_WASTE;

        @Column(name = "tot", type = String.class)
        @JsonProperty("tot")
        public String tot;

    }
    public static class coolroomstock{
        @Column(name = "organization_ID", type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;
        @Column(name = "locator_ID", type = String.class)
        @JsonProperty("locator_ID")
        public String locator_ID;

        @Column(name = "inventory_ITEM_ID", type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "lay_DATE", type = String.class)
        @JsonProperty("lay_DATE")
        public String lay_DATE;
        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "batchflockno", type = String.class)
        @JsonProperty("batchflockno")
        public String batchflockno;

        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        public String breed;

        @Column(name = "batch_TYPE", type = String.class)
        @JsonProperty("batch_TYPE")
        public String batch_TYPE;
        @Column(name = "days", type = String.class)
        @JsonProperty("days")
        public String days;

        @Column(name = "qty", type = String.class)
        @JsonProperty("qty")
        public String qty;

    }
    public static class eggunboxing {

        @Column(name = "TRANS_ID", type = Long.class)
        @JsonProperty("TRANS_ID")
        public Long TRANS_ID;

        @Column(name = "BRANCH_ID", type = Integer.class)
        @JsonProperty("BRANCH_ID")
        public Integer BRANCH_ID;

        @Column(name = "BRANCH_NAME", type = String.class)
        @JsonProperty("BRANCH_NAME")
        public String BRANCH_NAME;

        @Column(name = "UNBOXING_DATE", type = String.class)
        @JsonProperty("UNBOXING_DATE")
        public String UNBOXING_DATE;

        @Column(name = "FLOCK", type = String.class)
        @JsonProperty("FLOCK")
        public String FLOCK;

        @Column(name = "CRACK_EGGS", type = Integer.class)
        @JsonProperty("CRACK_EGGS")
        public Integer CRACK_EGGS;

        @Column(name = "DAMAGE_EGGS", type = Integer.class)
        @JsonProperty("DAMAGE_EGGS")
        public Integer DAMAGE_EGGS;

        @Column(name = "MISSING_EGGS", type = Integer.class)
        @JsonProperty("MISSING_EGGS")
        public Integer MISSING_EGGS;

        @Column(name = "TOTAL_DEFECTED", type = Integer.class)
        @JsonProperty("TOTAL_DEFECTED")
        public Integer TOTAL_DEFECTED;

        @Column(name = "TOTAL_CHECKED", type = Integer.class)
        @JsonProperty("TOTAL_CHECKED")
        public Integer TOTAL_CHECKED;

        @Column(name = "INSPECTOR_NAME", type = String.class)
        @JsonProperty("INSPECTOR_NAME")
        public String INSPECTOR_NAME;

        @Column(name = "REMARKS", type = String.class)
        @JsonProperty("REMARKS")
        public String REMARKS;
    }
}
