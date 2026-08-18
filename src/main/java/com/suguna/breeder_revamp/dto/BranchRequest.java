package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BranchRequest<T> {

    @JsonProperty("userCode")
    String userCode;
    @JsonProperty("deviceID")
    String deviceID;
    @JsonProperty("userType")
    String userType;
    @JsonProperty("branchID")
    String branchID;
    @JsonProperty("hatcheryID")
    String hatcheryID;
    @JsonProperty("shedNo")
    String shedNo;
    @JsonProperty("flockID")
    String flockID;
    @JsonProperty("batchID")
    String batchID;
    @JsonProperty("activityName")
    String activityName;
    @JsonProperty("age")
    String age;
    @JsonProperty("startDate")
    String startDate;
    @JsonProperty("endDate")
    String endDate;
    @JsonProperty("preparedBy")
    String preparedBy;
    @JsonProperty("transDate")
    String transDate;
    @JsonProperty("phLevel")
    String phLevel;
    @JsonProperty("pmLevel")
    String pmLevel;
    @JsonProperty("entryDate")
    String entryDate;
    @JsonProperty("LATITUDE")
    String latitude;
    @JsonProperty("LONGITUDE")
    String longitude;
    @JsonProperty("data")
    T data;

    @Getter
    @Setter
    public static class SugFeedDetails
    {
        @JsonProperty("birdType")
        String birdType;
        @JsonProperty("totalActualFeed")
        String totalActualFeed;
        @JsonProperty("grade")
        String grade;
    }

    @Getter
    @Setter
    public static class SugMortalityDetails
    {
        @JsonProperty("birdType")
        String birdType;
        @JsonProperty("totalBirds")
        String totalBirds;
    }

    @Getter
    @Setter
    public static class SugEggCollectionDetails
    {
        @JsonProperty("itemID")
        String itemID;
        @JsonProperty("Quantity")
        String quantity;
        @JsonProperty("Mode")
        String mode;
        @JsonProperty("rowId")
        String rowId;

    }

    @Getter
    @Setter
    public static class SugFeedAllocationDetails
    {
        @JsonProperty("Female")
        ArrayList<FeedAllocationDetails> Female;
        @JsonProperty("Male")
        ArrayList<FeedAllocationDetails> Male;

        @Getter
        @Setter
        public static class FeedAllocationDetails
        {
            @JsonProperty("grade")
            String grade;
            @JsonProperty("quantity")
            String quantity;
        }

    }
    @Getter
    @Setter
    public static class SugCullingDetails {
        @JsonProperty("Reason")
        String Reason;
        @JsonProperty("FemaleBirdsCount")
        String FemaleBirdsCount;
        @JsonProperty("FemaleBirdsWeight")
        String FemaleBirdsWeight;
        @JsonProperty("MaleBirdsCount")
        String MaleBirdsCount;
        @JsonProperty("MaleBirdsWeight")
        String MaleBirdsWeight;
        @JsonProperty("Type")
        String Type;
    }

    @Getter
    @Setter
    public static class SugDestroyDetails {
        @JsonProperty("Reason")
        String Reason;
        @JsonProperty("Remark")
        String Remark;
    }
    @Getter
    @Setter
    public static class MedicineAllocationDetails
    {
        @JsonProperty("itemType")
        String itemType;
        @JsonProperty("itemId")
        String itemId;
        @JsonProperty("quantity")
        String quantity;
        @JsonProperty("Uom")
        String Uom;
        @JsonProperty("intakeMode")
        String intakeMode;
        @JsonProperty("flockID")
        String flockID;
        @JsonProperty("batchID")
        String batchID;
        @JsonProperty("startDate")
        String startDate;
        @JsonProperty("endDate")
        String endDate;
        @JsonProperty("shedNo")
        String shedNo;
        @JsonProperty("preparedBy")
        String preparedBy;
        @JsonProperty("age")
        String age;
    }

    @Getter
    @Setter
    public static class FarmLogDetails
    {
        @JsonProperty("itemType")
        String itemType;
        @JsonProperty("maleCount")
        String maleCount;
        @JsonProperty("femaleCount")
        String femaleCount;
        @JsonProperty("Uom")
        String Uom;
        @JsonProperty("opening")
        String opening;
        @JsonProperty("closing")
        String closing;
        @JsonProperty("total")
        String total;
    }
    @Getter
    @Setter
    public static class SugWeekBirdDetails
    {
        @JsonProperty("reasonType")
        String reasonType;
        @JsonProperty("femaleQty")
        String femaleQty;
        @JsonProperty("maleQty")
        String maleQty;
        @JsonProperty("lineNo")
        String lineNo;

    }


    @Getter
    @Setter
    public static class SanitizationEntryDetails
    {
        @JsonProperty("itemType")
        String itemType;
        @JsonProperty("itemCode")
        String itemCode;
        @JsonProperty("itemName")
        String itemName;
        @JsonProperty("uom")
        String uom;
        @JsonProperty("flockID")
        String flockID;
        @JsonProperty("quantity")
        String quantity;
        @JsonProperty("batchID")
        String batchID;
        @JsonProperty("forLtrWater")
        String forLtrWater;
    }

    @Getter
    @Setter
    public static class EggWeightReadingDetails
    {
        @JsonProperty("transDate")
        String transDate;
        @JsonProperty("flockID")
        String flockID;
        @JsonProperty("batchID")
        String batchID;
        @JsonProperty("noOfEgg")
        String noOfEgg;
        @JsonProperty("emptyTray")
        String emptyTray;
        @JsonProperty("totalTray")
        String totalTray;
        @JsonProperty("netEggWeight")
        String netEggWeight;
        @JsonProperty("averageEggWeight")
        String averageEggWeight;
    }
    @Getter
    @Setter
    public static class SugCloseDetails {
        @JsonProperty("tempMin")
        String tempMin;
        @JsonProperty("tempMax")
        String tempMax;
        @JsonProperty("lightStartTime")
        String lightStartTime;
        @JsonProperty("lightEndTime")
        String lightEndTime;
        @JsonProperty("sanitizationStartTime")
        String sanitizationStartTime;
        @JsonProperty("sanitizationEndTime")
        String sanitizationEndTime;
        @JsonProperty("Remarks")
        String Remarks;
    }
    @Getter
    @Setter
    public static class SugMedicineVaccineDetails
    {
        @JsonProperty("qty")
        String qty;
        @JsonProperty("itemId")
        String itemId;
        @JsonProperty("transId")
        String transId;
        @JsonProperty("uom")
        String uom;
        @JsonProperty("itemType")
        String itemType;
    }
    @Getter
    @Setter
    public static class SugEggQualityCaptureDetails
    {
        @JsonProperty("parent_branch_id")
        String parent_branch_id;
        @JsonProperty("parent_branch_name")
        String parent_branch_name;
        @JsonProperty("branch_id")
        String branch_id;
        @JsonProperty("branch_name")
        String branch_name;
        @JsonProperty("flock")
        String flock;
        @JsonProperty("breed")
        String breed;
        @JsonProperty("trans_date")
        String trans_date;
        @JsonProperty("noofsampleegg")
        String noofsampleegg;
        @JsonProperty("fertile")
        String fertile;
        @JsonProperty("infertile")
        String infertile;
        @JsonProperty("preincubation")
        String preincubation;
        @JsonProperty("yolkmottling")
        String yolkmottling;
        @JsonProperty("meatspot")
        String meatspot;
        @JsonProperty("bloodspot")
        String bloodspot;
    }
    @Getter
    @Setter
    public static class SugIfftApprovalDetails {
        @JsonProperty("header_id")
        String header_id;
        @JsonProperty("outpass_no")
        String outpass_no;
        @JsonProperty("date")
        String date;
        @JsonProperty("status")
        String status;
        @JsonProperty("in_or_out")
        String in_or_out;
        @JsonProperty("remarks")
        String remarks;
    }
    @Getter
    @Setter
    public static class SugPlanUpdateDetails {
        @JsonProperty("PLAN_DTL_ID")
        String PLAN_DTL_ID;
        @JsonProperty("ACTUAL_ARRIVAL_DATE")
        String ACTUAL_ARRIVAL_DATE;
        @JsonProperty("ACTUAL_ARRIVAL_IMAGE")
        String ACTUAL_ARRIVAL_IMAGE;
    }
}
