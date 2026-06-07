package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_FARMLOG", schema = "SUG")
public class SugMaiGppsFarmLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_farmlog")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_FARMLOG_S", allocationSize = 1, name = "id_seq_gpps_farmlog")
    Long TRANS_ID ;
    String FARM_CODE;
    String ITEM_TYPE;
    Long ITEM_ID ;
    BigDecimal OPENING_QTY;
    BigDecimal CLOSING_QTY;
    BigDecimal QTY ;
    String UOM ;
    String CREATED_BY ;
    Date CREATION_DATE ;
    Long BRANCH_ID  ;
    Long MALE_COUNT;
    Long FEMALE_COUNT ;
}
