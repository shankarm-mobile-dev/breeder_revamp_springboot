package com.suguna.breeder_revamp.model;


import com.suguna.breeder_revamp.dto.SugMaiGppsConsumptionsID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_CONSUMPTIONS", schema = "SUG")
//@IdClass(SugMaiGppsConsumptionsID.class)
public class SugMaiGppsConsumptions {

    String FARM_CODE;

    String FLOCK_ID;

    String SHED_CODE;

    Long BATCH_ID;

    String LINE_NO;

    String TXN_TYPE;

    Long ITEM_ID;

    String SEX;

    Date TXN_DATE;

    Long AGE;

    String GRADE;
    Long QTY;
    String UOM;
    String CREATED_BY;
    Date CREATION_DATE;
    String STATUS;
    BigDecimal WEIGHT;
    String REMARK;

    String REASON;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_egg_weight")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_CONSUMPTIONS_S", allocationSize = 1, name = "id_seq_gpps_egg_weight")
    long TRANS_ID;
    double TEMP_MIN;
    double TEMP_MAX;
    String LIGTHING_START_HRS;
    String LIGTHING_END_HRS;
    String SANITIZATION_START_HRS;
    String SANITIZATION_END_HRS;
    double PH_LEVEL;
    double PM_LEVEL;
    String REMARKS;
    Long BRANCH_ID;
}
