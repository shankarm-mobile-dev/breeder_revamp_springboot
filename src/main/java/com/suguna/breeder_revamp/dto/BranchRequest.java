package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchRequest {
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
}
