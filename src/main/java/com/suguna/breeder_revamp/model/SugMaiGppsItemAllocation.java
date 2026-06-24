package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_ITEM_ALLOCATION", schema = "SUG")
public class SugMaiGppsItemAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_item_alloc")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_ITEM_ALLOCATION_S", allocationSize = 1, name = "id_seq_gpps_item_alloc")
    Long TRANS_ID ;
    String FARM_CODE ;
    String ITEM_TYPE;
    Long ITEM_ID ;
    String FLOCK_ID ;
    String SEX;
    Long AGE ;
    String GRADE;
    BigDecimal QTY ;
    Date DATE_FROM ;
    Date DATE_TO ;
    String UOM;
    String CREATED_BY ;
    Date CREATION_DATE ;
    Long BRANCH_ID ;
    String SHED_NO ;
    String INTAKE_MODE ;
    String PREPARED_BY;
}
