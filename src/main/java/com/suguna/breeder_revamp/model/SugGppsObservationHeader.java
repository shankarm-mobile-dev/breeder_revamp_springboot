package com.suguna.breeder_revamp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_GPPS_OBSERVATION_HEADER", schema = "SUG")
public class SugGppsObservationHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_obs_header")
    @SequenceGenerator(sequenceName = "SUG_GPPS_OBSERVATION_HEADER_S", allocationSize = 1, name = "id_seq_gpps_obs_header")
    Long TRANS_ID;
    Date TRANS_DATE;
    BigDecimal LEDGER_ID;
    String EMP_CODE;
    Long DEVICE_ID;
    Long BRANCH_ID;
    String BRANCH_CODE;
    String LOCATION_CODE;
    Long INVENTORY_LOCATION_ID;
    String FLOCK_NO;
    Long BATCH_ID;
    Long BATCH_NO;
    Long CREATED_BY;
    Date CREATION_DATE;
    Long LAST_UPDATED_BY;
    Date LAST_UPDATED_DATE;
    String SHED_NO;
}
