package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_HOUSING_LINE", schema = "SUG")
public class SugMaiGppsHousingLine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_housing_line")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_HOUSING_LINE_S", allocationSize = 1, name = "id_seq_gpps_housing_line")
    Long TXN_ID;
    String FLOCK_ID;
    Date TXN_DATE;
    String FARM_CODE;
    Long AGE;
    String SHED_NO;
    String LINE_NO;
    String SEX;
    String GRADE;
    Long OP_QTY ;
    Long TXN_QTY;
    Long CL_QTY  ;
    String CREATED_BY;
    Date CREATION_DATE;
    Long BATCH_ID;
    Long BRANCH_ID;
    //String ALLOCATE_STATUS;
    String SIDE;
    }
