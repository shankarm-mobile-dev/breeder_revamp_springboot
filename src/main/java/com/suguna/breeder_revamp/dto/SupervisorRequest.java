package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SupervisorRequest {
    @JsonProperty("userCode")
    String userCode;
    @JsonProperty("password")
    String password;
    @JsonProperty("deviceID")
    String deviceID;
    @JsonProperty("userType")
    String userType;
    @JsonProperty("mPin")
    String mPin;
    @JsonProperty("mode")
    String mode;
    @JsonProperty("otp")
    String otp;
    @JsonProperty("mobileNumber")
    String mobileNumber;
    @JsonProperty("branchId")
    String branchId;
    @JsonProperty("existingCode")
    String existingCode;
    @JsonProperty("shedNo")
    ArrayList<String> shedNo;
}
