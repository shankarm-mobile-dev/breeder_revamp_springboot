package com.suguna.breeder_revamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class BranchUser {
    @Column(name = "BRANCH_ID" , type = long.class)
    @JsonProperty("branchId")
    long branchID;
    @Column(name = "BRANCH_CODE" , type = String.class)
    @JsonProperty("branchCode")
    String branchCode;
    @Column(name = "BRANCH_NAME" , type = String.class)
    @JsonProperty("branchName")
    String branchName;
    @Column(name = "LOCATION_NAME" , type = String.class)
    @JsonProperty("locationName")
    String locationName;
    @Column(name = "FARM_TYPE" , type = String.class)
    @JsonProperty("farmType")
    String farmType;


    @JsonProperty("userDetails")
    ArrayList<RegisteredBranchUser> userDetails;

    @JsonProperty("branchUserDetails")
    ArrayList<SupervisorDetails> branchUserDetails;

    @Getter
    @Setter
    public static class RegisteredBranchUser {
        @Column(name = "EMP_CODE" , type = String.class)
        @JsonProperty("empCode")
        String empCode;
        @Column(name = "EMP_NAME" , type = String.class)
        @JsonProperty("empName")
        String empName;
        @Column(name = "EMP_ID" , type = long.class)
        @JsonProperty("empID")
        long empID;
        @Column(name = "DEVICE_ID" , type = long.class)
        @JsonProperty("deviceID")
        long deviceID;
        @Column(name = "DEVICE_INFO" , type = String.class)
        @JsonProperty("deviceInfo")
        String deviceInfo;
        @Column(name = "USER_TYPE" , type = String.class)
        @JsonProperty("userType")
        String userType;
        @Column(name = "MOBILE_NUMBER" , type = String.class)
        @JsonProperty("mobileNumber")
        String mobileNumber;
    }
    @Getter
    @Setter
    public static class SupervisorDetails {
        @Column(name = "EMP_NO" , type = String.class)
        @JsonProperty("empNo")
        String empNo;
        @Column(name = "NAME" , type = String.class)
        @JsonProperty("name")
        String name;
        @Column(name = "REGION_CODE" , type = String.class)
        @JsonProperty("regionCode")
        String regionCode;
        @Column(name = "BRANCH_CODE" , type = String.class)
        @JsonProperty("branchCode")
        String branchCode;
        @Column(name = "BRANCH_ID" , type = String.class)
        @JsonProperty("branchId")
        String branchId;
        @Column(name = "BRANCH_NAME" , type = String.class)
        @JsonProperty("branchName")
        String branchName;
        @Column(name = "JOB" , type = String.class)
        @JsonProperty("job")
        String job;
        @Column(name = "FARM_TYPE" , type = String.class)
        @JsonProperty("farmType")
        String farmType;
        @Column(name = "MOBILE_NUMBER" , type = String.class)
        @JsonProperty("mobileNumber")
        String mobileNumber;
        @JsonProperty("shedNo")
        ArrayList<String> shedNo;
    }

    @Getter
    @Setter
    public static class ShedDetails {
        @Column(name = "FLOCK_ID" , type = String.class)
        @JsonProperty("flockID")
        String flockID;
        @Column(name = "AGE" , type = long.class)
        @JsonProperty("age")
        long age;
        @Column(name = "SHED_NO" , type = String.class)
        @JsonProperty("shedNo")
        String shedNo;
        @Column(name = "MALE_QTY" , type = long.class)
        @JsonProperty("maleQty")
        long maleQty;
        @Column(name = "FEMALE_QTY" , type = long.class)
        @JsonProperty("femaleQty")
        long femaleQty;
        @Column(name = "BATCH_NUMBER" , type = String.class)
        @JsonProperty("batchNumber")
        String batchNumber;
        @Column(name = "BATCH_STATUS" , type = String.class)
        @JsonProperty("batchStatus")
        String batchStatus;
        @Column(name = "BATCH_ID" , type = String.class)
        @JsonProperty("batchId")
        String batchId;
        @JsonProperty("opMaleFeedStandard")
        String opMaleFeedStandard;
        // @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("opFemaleFeedStandard")
        String opFemaleFeedStandard;
        @JsonProperty("opMaleWeightStandard")
        String opMaleWeightStandard;
        // @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("opFemaleWeightStandard")
        String opFemaleWeightStandard;
        @Column(name = "bodyWeightDeviation", type = String.class)
        @JsonProperty("bodyWeightDeviation")
        String bodyWeightDeviation;
        @Column(name = "bodyWeightPercentage", type = String.class)
        @JsonProperty("bodyWeightPercentage")
        String bodyWeightPercentage;
    }

    @Getter
    @Setter
    public static class ShedLineDetails {
        @Column(name = "FLOCK_ID" , type = String.class)
        @JsonProperty("flockID")
        String flockID;
        @Column(name = "AGE" , type = long.class)
        @JsonProperty("age")
        long age;
        @Column(name = "SHED_NO" , type = String.class)
        @JsonProperty("shedNo")
        String shedNo;
        @Column(name = "MALE_QTY" , type = long.class)
        @JsonProperty("maleQty")
        long maleQty;
        @Column(name = "FEMALE_QTY" , type = long.class)
        @JsonProperty("femaleQty")
        long femaleQty;
        @Column(name = "BATCH_NUMBER" , type = String.class)
        @JsonProperty("batchNumber")
        String batchNumber;
        @Column(name = "BATCH_STATUS" , type = String.class)
        @JsonProperty("batchStatus")
        String batchStatus;
        @Column(name = "BATCH_ID" , type = String.class)
        @JsonProperty("batchId")
        String batchId;
        @Column(name = "LINE_NO" , type = String.class)
        @JsonProperty("lineNo")
        String lineNo;
        @Column(name = "GRADE" , type = String.class)
        @JsonProperty("grade")
        String grade;
    }

   /* @Getter
    @Setter
    public static class ShedDetails {
        @Column(name = "FLOCK_ID", type = String.class)
        @JsonProperty("flockID")
        String flockID;
        @Column(name = "AGE", type = long.class)
        @JsonProperty("age")
        long age;
    }*/
        @Getter
        @Setter
        public static class DailyFlockEntryDetails {
            @Column(name = "ACTIVITY_NAME", type = String.class)
            @JsonProperty("activityName")
            String activityName;
            @Column(name = "PERIOD_TYPE", type = String.class)
            @JsonProperty("periodType")
            String periodType;
            @Column(name = "OPRN_ID", type = String.class)
            @JsonProperty("activityId")
            String activityId;
            @Column(name = "COMPLETED_STATUS", type = String.class)
            @JsonProperty("completedStatus")
            String completedStatus;
        }

    @Getter
    @Setter
    public static class ObservationCategory {
        @Column(name = "CATEGORY", type = String.class)
        @JsonProperty("category")
        String category;
        @Column(name = "CATEGORY_ID", type = String.class)
        @JsonProperty("categoryId")
        String categoryId;

        @JsonProperty("question")
        ArrayList<ObservationCategoryDetails> question;
    }
    @Getter
    @Setter
    public static class ObservationCategoryDetails {
        @Column(name = "OBSERVATION_DESCRIPTION", type = String.class)
        @JsonProperty("observationDescription")
        String category;
        @Column(name = "OBSERVATION_ID", type = String.class)
        @JsonProperty("observationId")
        String categoryId;
    }
    @Getter
    @Setter
    public static class ShedWiseFeedBirdsDetails {
        @JsonProperty("birdType")
        String birdType;
        @JsonProperty("feedDetails")
        ArrayList<ShedWiseFeedDetails> feedDetails;
    }
    @Getter
    @Setter
    public static class ShedWiseFeedDetails {
        @Column(name = "BIRD_TYPE", type = String.class)
        @JsonProperty("birdType")
        String birdType;
        @Column(name = "PER_BIRD_FEED", type = String.class)
        @JsonProperty("perBirdFeed")
        String perBirdFeed;
        @Column(name = "BIRDS_NO", type = String.class)
        @JsonProperty("birdsNo")
        String birdsNo;
        @Column(name = "GRADE", type = String.class)
        @JsonProperty("grade")
        String grade;
        @Column(name = "TOTAL_FEED_ALLOCATED", type = String.class)
        @JsonProperty("totalFeedAllocated")
        String totalFeedAllocated;

    }



    @Getter
    @Setter
    public static class ShedWiseBirdsDetails {
        @Column(name = "BIRD_TYPE", type = String.class)
        @JsonProperty("birdType")
        String birdType;

        @Column(name = "BIRDS_NO", type = String.class)
        @JsonProperty("birdsNo")
        String birdsNo;

    }

    @Getter
    @Setter
    public static class EggDetails {
        @JsonProperty("EggItemDetails")
        ArrayList<EggItemDetails> EggItemDetails;
        @JsonProperty("EggCollectedDetails")
        ArrayList<EggCollectionDetails> EggCollectedDetails;
    }
    @Getter
    @Setter
    public static class EggItemDetails {
        @Column(name = "inventory_ITEM_ID", type = String.class)
        @JsonProperty("inventoryItemID")
        String inventoryItemID;

        @Column(name = "item_CODE", type = String.class)
        @JsonProperty("itemCode")
        String itemCode;

        @Column(name = "item_DESCRIPTION", type = String.class)
        @JsonProperty("itemDescription")
        String itemDescription;

        @Column(name = "primary_UOM", type = String.class)
        @JsonProperty("primaryUom")
        String primaryUom;

        @Column(name = "seq_NO", type = String.class)
        @JsonProperty("seqNO")
        String seqNO;

        @Column(name = "collected_Eggs", type = String.class)
        @JsonProperty("collectedEggs")
        String collectedEggs;

    }

    @Getter
    @Setter
    public static class EggCollectionDetails {
        @Column(name = "ROWNUM", type = String.class)
        @JsonProperty("seqNo")
        String seqNo;
        @Column(name = "QTY", type = String.class)
        @JsonProperty("quantity")
        String quantity;
        @Column(name = "COLLETED_TIME", type = String.class)
        @JsonProperty("collectedTime")
        String collectedTime;
        @Column(name = "STATUS", type = String.class)
        @JsonProperty("status")
        String status;
        @Column(name = "ROWID", type = String.class)
        @JsonProperty("rowID")
        String rowID;
    }

    @Getter
    @Setter
    public static class FeedAllocationDetails {
        @JsonProperty("FarmFlockDetails")
        ArrayList<FarmFlockDetails> FarmFlockDetails;
        @JsonProperty("GardeMstDetails")
        ArrayList<GardeMstDetails> GardeMstDetails;
    }

    @Getter
    @Setter
    public static class FarmFlockDetails {
        @Column(name = "batch_ID", type = String.class)
        @JsonProperty("batchID")
        String batchID;
        @Column(name = "batch_NO", type = String.class)
        @JsonProperty("batchNO")
        String batchNO;
        @Column(name = "flock", type = String.class)
        @JsonProperty("flock")
        String flock;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        String age;
        @Column(name = "start_DATE", type = String.class)
        @JsonProperty("startDate")
        String startDate;
        @Column(name = "end_DATE", type = String.class)
        @JsonProperty("endDate")
        String endDate;
        @Column(name = "op_MALE", type = String.class)
        @JsonProperty("opMale")
        String opMale;
        @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("opFemale")
        String opFemale;
        //@Column(name = "op_MALE", type = String.class)
        @JsonProperty("opMaleFeedStandard")
        String opMaleFeedStandard;
       // @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("opFemaleFeedStandard")
        String opFemaleFeedStandard;
        @JsonProperty("opMaleWeightStandard")
        String opMaleWeightStandard;
        // @Column(name = "op_FEMALE", type = String.class)
        @JsonProperty("opFemaleWeightStandard")
        String opFemaleWeightStandard;

        @JsonProperty("farmFlockDetails")
        ArrayList<FarmFlockPreviousDetails> farmFlockDetails;
    }

    @Getter
    @Setter
    public static class GardeMstDetails {
        @Column(name = "CODE", type = String.class)
        @JsonProperty("gradeNo")
        String gradeNo;
        @Column(name = "NAME", type = String.class)
        @JsonProperty("gradeName")
        String gradeName;
    }

    @Getter
    @Setter
    public static class CullDetails {
        @JsonProperty("FarmFlockDetails")
        ArrayList<FarmFlockDetails> FarmFlockDetails;
        @JsonProperty("CullsReasonDetails")
        ArrayList<CullsReasonDetails> CullsReasonDetails;
    }

    @Getter
    @Setter
    public static class CullsReasonDetails {
        @Column(name = "type", type = String.class)
        @JsonProperty("type")
        String type;
        @Column(name = "lookup_CODE", type = String.class)
        @JsonProperty("lookupCode")
        String lookupCode;
        @Column(name = "meaning", type = String.class)
        @JsonProperty("meaning")
        String meaning;
    }

    @Getter
    @Setter
    public static class DestroyDetails {
        @JsonProperty("FarmFlockDetails")
        ArrayList<FarmFlockDetails> FarmFlockDetails;
        @JsonProperty("CullsReasonDetails")
        ArrayList<CullsReasonDetails> CullsReasonDetails;
    }

    @Getter
    @Setter
    public static class MortalityPmlDetails {
        @JsonProperty("FarmFlockDetails")
        ArrayList<FarmFlockDetails> FarmFlockDetails;
        @JsonProperty("MortalityPmlDetails")
        ArrayList<CullsReasonDetails> CullsReasonDetails;
    }

    @Getter
    @Setter
    public static class ExcessShortageDetails {
        @JsonProperty("FarmFlockDetails")
        ArrayList<FarmFlockDetails> FarmFlockDetails;
        @JsonProperty("ExcessShortageDetails")
        ArrayList<CullsReasonDetails> CullsReasonDetails;
    }

    @Getter
    @Setter
    public static class PlacementInfoDetails {
        /*@Column(name = "TOTAL_BIRDS_ALLOCATE", type = String.class)
        @JsonProperty("TOTAL_BIRDS_ALLOCATE")
        String totalBirdsAllocate;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "ALLOCATE_PER", type = String.class)
        @JsonProperty("ALLOCATE_PER")
        String allocatePer;
        @Column(name = "REMAINING_BIRDS", type = String.class)
        @JsonProperty("REMAINING_BIRDS")
        String remainingBirds;
        @Column(name = "FLOCK_NUMBER", type = String.class)
        @JsonProperty("FLOCK_NUMBER")
        String flockNumber;
        @Column(name = "BATCH_ID", type = String.class)
        @JsonProperty("BATCH_ID")
        String batchId;
        @Column(name = "REPORT_NUM", type = String.class)
        @JsonProperty("REPORT_NUM")
        String reportNum;*/
        @JsonProperty("PlacementInfoDetails")
        ArrayList<PlacementInfoDetails1> PlacementInfoDetails;
        @JsonProperty("PlacementInfoShedDetails")
        ArrayList<PlacementInfoShedDetails> PlacementInfoShedDetails;
    }
    @Getter
    @Setter
    public static class PlacementInfoDetails1 {
        @Column(name = "TOTAL_BIRDS_ALLOCATE", type = String.class)
        @JsonProperty("TOTAL_BIRDS_ALLOCATE")
        String totalBirdsAllocate;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "ALLOCATE_PER", type = String.class)
        @JsonProperty("ALLOCATE_PER")
        String allocatePer;
        @Column(name = "REMAINING_BIRDS", type = String.class)
        @JsonProperty("REMAINING_BIRDS")
        String remainingBirds;
        @Column(name = "FLOCK_NUMBER", type = String.class)
        @JsonProperty("FLOCK_NUMBER")
        String flockNumber;
        @Column(name = "BATCH_ID", type = String.class)
        @JsonProperty("BATCH_ID")
        String batchId;
        @Column(name = "REPORT_NUM", type = String.class)
        @JsonProperty("REPORT_NUM")
        String reportNum;
    }
        @Getter
    @Setter
    public static class PlacementInfoShedDetails {
        @Column(name = "TOTAL_BIRDS_CAPACITY", type = String.class)
        @JsonProperty("TOTAL_BIRDS_CAPACITY")
        String totalBirdsCapacity;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "ALLOCATE_PER", type = String.class)
        @JsonProperty("ALLOCATE_PER")
        String allocatePer;
        @Column(name = "REMAINING_BIRDS", type = String.class)
        @JsonProperty("REMAINING_BIRDS")
        String remainingBirds;
        @Column(name = "SHED_NAME", type = String.class)
        @JsonProperty("SHED_NAME")
        String shedName;
            @Column(name = "FLOCK_ID", type = String.class)
            @JsonProperty("FLOCK_ID")
            String flockID;
        @JsonProperty("PlacementInfoLineDetails")
        ArrayList<PlacementInfoLineDetails> PlacementInfoLineDetails;
    }
    @Getter
    @Setter
    public static class PlacementInfoLineDetails {
        @Column(name = "TOTAL_BIRDS_CAPACITY", type = String.class)
        @JsonProperty("TOTAL_BIRDS_CAPACITY")
        String totalBirdsCapacity;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "ALLOCATE_PER", type = String.class)
        @JsonProperty("ALLOCATE_PER")
        String allocatePer;
        @Column(name = "REMAINING_BIRDS", type = String.class)
        @JsonProperty("REMAINING_BIRDS")
        String remainingBirds;
        @Column(name = "LINE_NAME", type = String.class)
        @JsonProperty("LINE_NAME")
        String lineName;
    }

    @Getter
    @Setter
    public static class DashboardDetails {
        @Column(name = "FLOCK_NUMBER", type = String.class)
        @JsonProperty("FLOCK_NUMBER")
        String flockNumber;
        @Column(name = "TOTAL_BIRDS_ALLOCATE", type = String.class)
        @JsonProperty("TOTAL_BIRDS_ALLOCATE")
        String totalBirdsAllocate;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "HEN_WEEK_PER", type = String.class)
        @JsonProperty("HEN_WEEK_PER")
        String henWeekPer;
        @Column(name = "HEN_WEEK_EGGS", type = String.class)
        @JsonProperty("HEN_WEEK_EGGS")
        String henWeekEggs;
        @Column(name = "FERTILE_PER", type = String.class)
        @JsonProperty("FERTILE_PER")
        String fertilePer;
        @Column(name = "FERTILE_QTY", type = String.class)
        @JsonProperty("FERTILE_QTY")
        String fertileQty;
        @Column(name = "HATCHABILITY_PER", type = String.class)
        @JsonProperty("HATCHABILITY_PER")
        String hatchabilityPer;
        @Column(name = "HATCHABILITY_CHICKS", type = String.class)
        @JsonProperty("HATCHABILITY_CHICKS")
        String hatchabilityChicks;
        @Column(name = "MORTALITY_PER", type = String.class)
        @JsonProperty("MORTALITY_PER")
        String mortalityPer;
        @Column(name = "MORTALITY_BIRDS", type = String.class)
        @JsonProperty("MORTALITY_BIRDS")
        String mortalityBirds;
        @JsonProperty("HenWeekDetails")
        ArrayList<HenWeekDetails> HenWeekDetails;
        @JsonProperty("FertilityDetails")
        ArrayList<FertilityDetails> FertilityDetails;
        @JsonProperty("HatchabilityDetails")
        ArrayList<HatchabilityDetails> HatchabilityDetails;
        @JsonProperty("MortalityDetails")
        ArrayList<MortalityDetails> MortalityDetails;
    }

    @Getter
    @Setter
    public static class HenWeekDetails {
        @Column(name = "WEEK_NUMBER", type = String.class)
        @JsonProperty("WEEK_NUMBER")
        String weekNumber;
        @Column(name = "WEEK_START_DATE", type = String.class)
        @JsonProperty("WEEK_START_DATE")
        String weekStartDate;
        @Column(name = "WEEK_END_DATE", type = String.class)
        @JsonProperty("WEEK_END_DATE")
        String weekEndDate;
        @Column(name = "HEN_WEEK", type = String.class)
        @JsonProperty("HEN_WEEK")
        String henWeek;
    }

    @Getter
    @Setter
    public static class FertilityDetails {
        @Column(name = "WEEK_NUMBER", type = String.class)
        @JsonProperty("WEEK_NUMBER")
        String weekNumber;
        @Column(name = "WEEK_START_DATE", type = String.class)
        @JsonProperty("WEEK_START_DATE")
        String weekStartDate;
        @Column(name = "WEEK_END_DATE", type = String.class)
        @JsonProperty("WEEK_END_DATE")
        String weekEndDate;
        @Column(name = "FERTILITY_WEEK", type = String.class)
        @JsonProperty("FERTILITY_WEEK")
        String fertilityWeek;
    }
    @Getter
    @Setter
    public static class HatchabilityDetails {
        @Column(name = "WEEK_NUMBER", type = String.class)
        @JsonProperty("WEEK_NUMBER")
        String weekNumber;
        @Column(name = "WEEK_START_DATE", type = String.class)
        @JsonProperty("WEEK_START_DATE")
        String weekStartDate;
        @Column(name = "WEEK_END_DATE", type = String.class)
        @JsonProperty("WEEK_END_DATE")
        String weekEndDate;
        @Column(name = "HATCHABILITY", type = String.class)
        @JsonProperty("HATCHABILITY")
        String hatchability;
    }

    @Getter
    @Setter
    public static class MortalityDetails {
        @Column(name = "DAY_NAME", type = String.class)
        @JsonProperty("DAY_NAME")
        String dayName;
        @Column(name = "DAY_OF_MONTH", type = String.class)
        @JsonProperty("DAY_OF_MONTH")
        String dayOfMonth;
        @Column(name = "FULL_DATE", type = String.class)
        @JsonProperty("FULL_DATE")
        String fullDate;
        @Column(name = "MORTALITY", type = String.class)
        @JsonProperty("MORTALITY")
        String mortality;
    }

    @Getter
    @Setter
    public static class StandardDetails {
        @Column(name = "bird_TYPE", type = String.class)
        @JsonProperty("birdType")
        String birdType;
        @Column(name = "line", type = String.class)
        @JsonProperty("line")
        String line;
        @Column(name = "week", type = String.class)
        @JsonProperty("week")
        String week;
        @Column(name = "male_WEIGHT", type = String.class)
        @JsonProperty("maleWeight")
        String maleWeight;
        @Column(name = "female_WEIGHT", type = String.class)
        @JsonProperty("femaleWeight")
        String femaleWeight;
        @Column(name = "male_FEED_PER_WEEK", type = String.class)
        @JsonProperty("maleFeedPerWeek")
        String maleFeedPerWeek;
        @Column(name = "female_FEED_PER_WEEK", type = String.class)
        @JsonProperty("femaleFeedPerWeek")
        String femaleFeedPerWeek;
    }

    @Getter
    @Setter
    public static class FarmFlockPreviousDetails {
        @Column(name = "AGE", type = String.class)
        @JsonProperty("age")
        String age;
        @Column(name = "DATE_FROM", type = String.class)
        @JsonProperty("dateFrom")
        String dateFrom;
        @Column(name = "DATE_TO", type = String.class)
        @JsonProperty("dateTo")
        String dateTo;
        @JsonProperty("gradeWiseDetails")
        ArrayList<FarmFlockPreviousBreakupDetails> gradeWiseDetails;

    }
    @Getter
    @Setter
    public static class FarmFlockPreviousBreakupDetails {
        @Column(name = "AGE", type = String.class)
        @JsonProperty("age")
        String age;
        @Column(name = "DATE_FROM", type = String.class)
        @JsonProperty("dateFrom")
        String dateFrom;
        @Column(name = "DATE_TO", type = String.class)
        @JsonProperty("dateTo")
        String dateTo;
        @Column(name = "GRADE", type = String.class)
        @JsonProperty("grade")
        String grade;
        @Column(name = "MALE_QTY", type = String.class)
        @JsonProperty("maleQty")
        String maleQty;
        @Column(name = "FEMALE_QTY", type = String.class)
        @JsonProperty("femaleQty")
        String femaleQty;

    }

    @Getter
    @Setter
    public static class MedicineScheduleDetails {
        @Column(name = "FLOCK_ID", type = String.class)
        @JsonProperty("flockId")
        String flockId;
        @Column(name = "AGE", type = String.class)
        @JsonProperty("age")
        String age;
        @Column(name = "DATE_FROM", type = String.class)
        @JsonProperty("dateFrom")
        String dateFrom;
        @Column(name = "DATE_TO", type = String.class)
        @JsonProperty("dateTo")
        String dateTo;
        @Column(name = "ITEM_TYPE", type = String.class)
        @JsonProperty("itemType")
        String itemType;
        @Column(name = "SHED_NO", type = String.class)
        @JsonProperty("shedNo")
        String shedNo;
        @Column(name = "ITEM_ID", type = String.class)
        @JsonProperty("itemId")
        String itemId;
        @Column(name = "ITEM_DESC", type = String.class)
        @JsonProperty("itemDesc")
        String itemDesc;
    }

    @Getter
    @Setter
    public static class FarmLogPreviousDetails {
        @Column(name = "ITEM_TYPE", type = String.class)
        @JsonProperty("ItemType")
        String ItemType;
        @Column(name = "CREATION_DATE", type = String.class)
        @JsonProperty("lastEntryDate")
        String lastEntryDate;
        @Column(name = "OPENING_QTY", type = String.class)
        @JsonProperty("OpeningQty")
        String OpeningQty;
        @Column(name = "CLOSING_QTY", type = String.class)
        @JsonProperty("ClosingQty")
        String ClosingQty;
        @Column(name = "QTY", type = String.class)
        @JsonProperty("finalQty")
        String finalQty;
        @Column(name = "UOM", type = String.class)
        @JsonProperty("uom")
        String uom;
        @Column(name = "MALE_COUNT", type = String.class)
        @JsonProperty("maleLabourCount")
        String maleLabourCount;
        @Column(name = "FEMALE_COUNT", type = String.class)
        @JsonProperty("femaleLabourCount")
        String femaleLabourCount;
        @Column(name = "BRANCH_ID", type = String.class)
        @JsonProperty("branchId")
        String branchId;
    }

    @Getter
    @Setter
    public static class SanitizationReasonDetails {
        @Column(name = "type", type = String.class)
        @JsonProperty("type")
        String type;
        @Column(name = "lookup_CODE", type = String.class)
        @JsonProperty("lookupCode")
        String lookupCode;
        @Column(name = "meaning", type = String.class)
        @JsonProperty("meaning")
        String meaning;
        @Column(name = "uom", type = String.class)
        @JsonProperty("uom")
        String uom;
    }

    @Getter
    @Setter
    public static class BodyWeightDeviationDetails {
        @Column(name = "start_age", type = String.class)
        @JsonProperty("startAge")
        String startAge;
        @Column(name = "end_age", type = String.class)
        @JsonProperty("endAge")
        String endAge;
        @Column(name = "deviation", type = String.class)
        @JsonProperty("deviation")
        String deviation;
    }

}
