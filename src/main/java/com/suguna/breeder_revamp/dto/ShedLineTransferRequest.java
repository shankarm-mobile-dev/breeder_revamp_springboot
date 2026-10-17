package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ShedLineTransferRequest {
    @JsonProperty("branchID")
    String branchID;
    @JsonProperty("userCode")
    String userCode;
    @JsonProperty("txnDate")
    String txnDate;
    @JsonProperty("transferReason")
    String transferReason;
    @JsonProperty("fromFarmId")
    String fromFarmId;
    @JsonProperty("lines")
    ArrayList<LineMove> lines;

    @Getter
    @Setter
    public static class LineMove {
        @JsonProperty("flockId")
        String flockId;
        @JsonProperty("sex")
        String sex;
        @JsonProperty("qty")
        String qty;
        @JsonProperty("fromShed")
        String fromShed;
        @JsonProperty("fromLine")
        String fromLine;
        @JsonProperty("fromSide")
        String fromSide;
        @JsonProperty("toShed")
        String toShed;
        @JsonProperty("toLine")
        String toLine;
        @JsonProperty("toSide")
        String toSide;
        @JsonProperty("age")
        String age;
        @JsonProperty("batchId")
        String batchId;
    }
}
