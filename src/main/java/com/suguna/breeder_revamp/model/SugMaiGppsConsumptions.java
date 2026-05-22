package com.suguna.breeder_revamp.model;


import com.suguna.breeder_revamp.dto.SugMaiGppsConsumptionsID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_CONSUMPTIONS", schema = "SUG")
@IdClass(SugMaiGppsConsumptionsID.class)
public class SugMaiGppsConsumptions {
    @Id
    String FARM_CODE;
    @Id
    String FLOCK_ID;
    @Id
    String SHED_CODE;
    @Id
    Long BATCH_ID;
    @Id
    String LINE_NO;
    @Id
    String TXN_TYPE;
    @Id
    Long ITEM_ID;
    @Id
    String SEX;
    @Id
    Date TXN_FROM;
    @Id
    Long AGE;
    @Id
    String GRADE;
    Long QTY;
    String UOM;
    String CREATED_BY;
    Date CREATION_DATE;
    String STATUS;
    BigDecimal WEIGHT;
    String REMARK;
    String REASON;
}
