package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerLoginDto {

    @JsonProperty("status")
    String status;
    @JsonProperty("statusCode")
    int statusCode;
    @JsonProperty("message")
    String message;

    @JsonProperty("data")
    UserDetailsSetResultDto user_details;

    @Getter
    @Setter
    public static class UserDetailsSetResultDto {
        @Column(name = "branch_ID" , type = long.class)
        @JsonProperty("branchId")
        long branchID;

        @Column(name = "branch_CODE", type = String.class)
        @JsonProperty("branchCode")
        String branchCode;

        @Column(name = "branch_NAME", type = String.class)
        @JsonProperty("branchName")
        String branchName;

        @Column(name = "EMP_CODE" , type = String.class)
        @JsonProperty("empCode")
        String EmpCode;

        @Column(name = "user_TYPE", type = String.class)
        @JsonProperty("userType")
        String UserType;

        @Column(name = "MPIN" , type = Integer.class)
        @JsonProperty("mPin")
        Integer mPIN;

    }
}