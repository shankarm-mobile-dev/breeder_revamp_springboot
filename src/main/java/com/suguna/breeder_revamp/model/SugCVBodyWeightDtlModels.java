package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_BODY_WT_DTL",schema = "SUG")
public class SugCVBodyWeightDtlModels {
    String DEVICE_ID;
    String EMP_CODE;
    long BRANCH_ID;
    long INVENTORY_LOCATION_ID;
    long TXN_HEADER_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_body_wt_dtl")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_BODY_WT_DTL_S", allocationSize = 1, name = "id_seq_gpps_body_wt_dtl")
    long TXN_LINE_ID;
    BigDecimal WEIGHT;
    long NO_OF_BIRDS;
    Date ENTRY_CREATION_DATE;
    String POSTED_FLAG;
    Date CREATED_DATE;
    String BIRD_TYPE;
    String LINE_NO;
    String GRADING_NO;
    String FLOCK_NO;
    String PHYSICAL_SHED_NO;
    long AGE;
}
