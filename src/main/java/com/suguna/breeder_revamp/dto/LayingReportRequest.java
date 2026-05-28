package com.suguna.breeder_revamp.dto;

import lombok.Data;

@Data
public class LayingReportRequest {

    private Integer ledger;
    private Integer regionId;
    private String plantCode;
    private String flock;
    private Integer fromAge;
    private Integer toAge;
}
