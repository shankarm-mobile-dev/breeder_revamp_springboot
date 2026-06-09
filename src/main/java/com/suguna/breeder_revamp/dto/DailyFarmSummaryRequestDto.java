package com.suguna.breeder_revamp.dto;

import lombok.Data;

@Data
public class DailyFarmSummaryRequestDto {

    private Integer company;
    private Integer regionId;
    private String branchCode;

    private String fromDate;   // format: yyyy-MM-dd
    private String toDate;

    private String mode;
}

