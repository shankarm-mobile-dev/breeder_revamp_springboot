package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_BODY_WT",schema = "SUG")
public class SugCVBodyWeightModels {

    long DEVICE_ID;
    String EMP_CODE;
    long BRANCH_ID;
    long INVENTORY_LOCATION_ID;
    String LOCATION;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_body_wt")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_BODY_WT_S", allocationSize = 1, name = "id_seq_gpps_body_wt")
    long TXN_HEADER_ID;
    Date TXN_DATE;
    String BIRD_TYPE;
    BigDecimal MIN_WEIGHT;
    BigDecimal MAX_WEIGHT;
    BigDecimal INCREMENT_VALUE;
    long AGE;
    BigDecimal STD_BODYWT;
    BigDecimal ACT_BODYWT;
    BigDecimal CV;
    BigDecimal BELOW_STD;
    BigDecimal ABOVE_STD;
    BigDecimal WITHIN_STD;
    Date ENTRY_CREATION_DATE;
    String POSTED_FLAG;
    Date CREATED_DATE;
    BigDecimal MOST_ABOVE_STD;
    BigDecimal MOST_BELOW_STD;
    String GRADING_NO;
    String FLOCK_NO;
    String LINE_NO;
    String STATUS;
    long PHYSICAL_SHED_NO;
}
