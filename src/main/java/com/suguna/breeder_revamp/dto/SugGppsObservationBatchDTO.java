package com.suguna.breeder_revamp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SugGppsObservationBatchDTO {
    String FLOCK_NO;
    String LOCATION_CODE;
    String BRANCH_CODE;
    String BATCH_NO;
    BigDecimal INVENTORY_LOCATION_ID;
    BigDecimal LEDGER_ID;

    public SugGppsObservationBatchDTO(String FLOCK_NO, String LOCATION_CODE, String BRANCH_CODE, String BATCH_NO,BigDecimal INVENTORY_LOCATION_ID,BigDecimal LEDGER_ID) {
        this.FLOCK_NO = FLOCK_NO;
        this.LOCATION_CODE = LOCATION_CODE;
        this.BRANCH_CODE = BRANCH_CODE;
        this.BATCH_NO = BATCH_NO;
        this.INVENTORY_LOCATION_ID=INVENTORY_LOCATION_ID;
        this.LEDGER_ID=LEDGER_ID;
    }
}
