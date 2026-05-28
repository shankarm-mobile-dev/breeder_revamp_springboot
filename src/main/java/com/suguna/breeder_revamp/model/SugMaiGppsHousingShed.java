package com.suguna.breeder_revamp.model;

import com.suguna.breeder_revamp.dto.SugMaiGppsConsumptionsID;
import com.suguna.breeder_revamp.dto.SugMaiGppsHousingShedID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_HOUSING_SHED", schema = "SUG")
@IdClass(SugMaiGppsHousingShedID.class)
public class SugMaiGppsHousingShed {
    @Id
    String FLOCK_ID;
    @Id
    Date TXN_DATE;
    String FARM_CODE;
    Long AGE ;
    @Id
    String SHED_NO;
    String SEX;
    Long OP_QTY;
    Long TXN_QTY;
    Long CL_QTY;
    String CREATED_BY;
    Date CREATION_DATE;
    @Id
    Long BATCH_ID;
    @Id
    Long BRANCH_ID;
}
