package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_TRANS_PLAN_DTL",schema = "SUG")
public class SugMaiGppsTransPlanDtl {
    long TXN_HEADER_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_trans_plan_dtl")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_TRANS_DTL_S", allocationSize = 1, name = "id_seq_gpps_trans_plan_dtl")
    long TXN_LINE_ID;
    String FROM_INVENTORY_LOC_DESC;
    String TO_INVENTORY_LOC_DESC;
    String TXN_TYPE;
    String BIRD_TYPE;
    long ITEM_ID ;
    String ITEM_DESC;
    String UOM;
    BigDecimal STOCK_QTY;
    BigDecimal QTY;
    String FROM_LINE_NAME;
    String TO_LINE_NAME;
    String FROM_SIDE_NAME;
    String TO_SIDE_NAME;

}
