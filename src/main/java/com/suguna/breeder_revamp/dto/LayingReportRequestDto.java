package com.suguna.breeder_revamp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LayingReportRequestDto {

    private Integer ledger;
    private Integer regionId;
    private String plantCode;
    private String flock;
    private Integer fromAge;
    private Integer toAge;
}
