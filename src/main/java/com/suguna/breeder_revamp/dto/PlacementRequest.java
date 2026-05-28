package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacementRequest <T>{
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
    @JsonProperty("data")
    T data;

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

    }
}
