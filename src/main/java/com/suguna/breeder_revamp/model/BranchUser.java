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
    }
}
