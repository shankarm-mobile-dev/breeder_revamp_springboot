package com.suguna.breeder_revamp.dto;

import lombok.Data;

@Data
public class BodyWeightReportRequestDto {
    private Integer ledger;
    private Integer regionId;
    private String plantCode;
    private String flock;
}
