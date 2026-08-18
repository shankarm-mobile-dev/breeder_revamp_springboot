package com.suguna.breeder_revamp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LayingReportRequest {

        private Integer ledger;
        private Integer regionId;
        private String plantCode;
        private String flock;
        private Integer fromAge;
        private Integer toAge;

       /* public Object getLedger() {
    }

    public Object getRegionId() {
    }

    public Object getPlantCode() {
    }

    public Object getFlock() {
    }

    public Object getFromAge() {
    }

    public Object getToAge() {
    }*/
}
