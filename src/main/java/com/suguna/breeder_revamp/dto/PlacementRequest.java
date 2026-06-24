package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PlacementRequest{
    @JsonProperty("userCode")
    String userCode;
    @JsonProperty("deviceID")
    String deviceID;
        @JsonProperty("shedNo")
    String shedNo;
    @JsonProperty("totalFemaleQty")
    String totalFemaleQty;
    @JsonProperty("totalMaleQty")
    String totalMaleQty;
    @JsonProperty("flockID")
    String flockID;
    @JsonProperty("batchID")
    String batchID;
    @JsonProperty("branchID")
    String branchID;
    @JsonProperty("reportNum")
    String reportNum;
    @JsonProperty("age")
    String age;
    @JsonProperty("data")
    ArrayList<SugLineDetails> data;

    @Getter
    @Setter
    public static class SugLineDetails
    {
        @JsonProperty("lineNo")
        String lineNo;
        @JsonProperty("FemaleBirdsCount")
        String femaleBirdsCount;
        @JsonProperty("MaleBirdsCount")
        String maleBirdsCount;
        @JsonProperty("gradeNo")
        String gradeNo;

    }
}
