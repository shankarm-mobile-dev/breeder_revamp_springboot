package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ManagerLoginDto {

    @JsonProperty("STATUS")
    String status;
    @JsonProperty("STATUSCODE")
    String statuscode;
    @JsonProperty("MESSAGE")
    String message;

    @JsonProperty("USER_DETAILS")
    UserDetailsSetResultDto user_details;

    @Getter
    @Setter
    public static class UserDetailsSetResultDto {
        @Column(name = "branch_ID" )
        @JsonProperty("branch_ID")
        long branchID;

        @Column(name = "branch_CODE")
        @JsonProperty("branch_CODE")
        String branchCode;

        @Column(name = "branch_NAME")
        @JsonProperty("branch_NAME")
        String branchName;

        @Column(name = "EMP_CODE" )
        @JsonProperty("EMP_CODE")
        String EmpCode;

        @Column(name = "user_TYPE")
        @JsonProperty("user_TYPE")
        String UserType;

        @Column(name = "MPIN" )
        @JsonProperty("MPIN")
        String mPIN;

    }
}