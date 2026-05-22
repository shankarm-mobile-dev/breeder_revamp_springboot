package com.suguna.breeder_revamp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SugGppsObservationBatchDTO {
    String FLOCK_NO;
    String LOCATION_CODE;
    String BRANCH_CODE;
    String BATCH_NO;

    public SugGppsObservationBatchDTO(String FLOCK_NO, String LOCATION_CODE, String BRANCH_CODE, String BATCH_NO) {
        this.FLOCK_NO = FLOCK_NO;
        this.LOCATION_CODE = LOCATION_CODE;
        this.BRANCH_CODE = BRANCH_CODE;
        this.BATCH_NO = BATCH_NO;
    }
}
