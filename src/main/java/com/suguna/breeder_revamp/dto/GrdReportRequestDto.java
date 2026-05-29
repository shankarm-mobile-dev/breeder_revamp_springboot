package com.suguna.breeder_revamp.dto;

import lombok.Data;

@Data
public class GrdReportRequestDto {
    private Integer ledger;
    private Integer regionId;
    private String plantCode;
    private String flock;
}
