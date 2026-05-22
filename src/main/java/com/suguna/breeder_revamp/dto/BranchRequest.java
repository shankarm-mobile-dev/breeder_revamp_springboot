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

}
