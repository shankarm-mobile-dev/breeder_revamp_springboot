package com.suguna.breeder_revamp.dto;

import lombok.Data;

@Data
public class BroodGrowRegisterRequestDto {

    private Integer company;
    private Integer regionId;
    private Integer branchId;

    private String fromDate;    // format: yyyy-MM-dd
    private String toDate;

    private String mode;        // Report - "R", Alert - A
}