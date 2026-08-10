package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_TRANS_DTL",schema = "SUG")
public class SugMaiGppsTransDtl {

    BigDecimal DEVICE_ID;
    long TXN_HEADER_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_trans_dtl")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_TRANS_DTL_S", allocationSize = 1, name = "id_seq_gpps_trans_dtl")
    long TXN_LINE_ID;
    BigDecimal FROM_FARM_ID;
    BigDecimal TO_FARM_ID;
    BigDecimal FROM_INVENTORY_LOCATION_ID;
    String FROM_INVENTORY_LOC_DESC;
    BigDecimal FROM_BATCH_ID;
    BigDecimal TO_INVENTORY_LOCATION_ID;
    BigDecimal TO_BATCH_ID;
    String TXN_TYPE;
    String BIRD_TYPE;
    BigDecimal ITEM_ID;
    String ITEM_DESC;
    String UOM;
    BigDecimal STOCK_QTY;
    BigDecimal QTY;
    BigDecimal DAYS;
    BigDecimal RECEIVING_QTY;
    BigDecimal DIFF_QTY;
    Date ENTRY_CREATION_DATE;
    Date CREATED_DATE;
    String POSTED_FLAG;
    BigDecimal AGE;
    String ERROR_MSG;
    String POST_TO_ERP;
    String LOTNUMBER;
    Date LAY_DATE;
    String LOCATION_TYPE;
    String TXN_TIME;
    String BREEDNAME;
    String FROM_LINE_NAME;
    String TO_LINE_NAME;
    String FROM_SIDE_NAME;
    String TO_SIDE_NAME;
}
