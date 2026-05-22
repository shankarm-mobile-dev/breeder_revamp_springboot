package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter

@Entity
@Table(name = "sug_mai_gpps_item_consumption")
public class SaveSugNaterialConsumptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_gpps_hdr_seq")
    @SequenceGenerator(

            sequenceName = "SUG_GPPS_ITEM_CONSUMPTION_S",
            allocationSize = 1,
            name = "inventory_gpps_hdr_seq"
    )
    @Column(name = "TRANS_ID")
    private BigDecimal TRANS_ID;

    @Column(name = "TRANS_DATE")
    private Date TRANS_DATE;

    @Column(name = "BRANCH_ID")
    private BigDecimal BRANCH_ID;

    @Column(name = "TRANS_TYPE")
    private String TRANS_TYPE;

    @Column(name = "INVENTORY_ITEM_ID")
    private BigDecimal INVENTORY_ITEM_ID;

    @Column(name = "INVENTORY_ITEM_CODE")
    private String INVENTORY_ITEM_CODE;

    @Column(name = "ITEM_DESCRIPTION")
    private String ITEM_DESCRIPTION;

    @Column(name = "STK_QTY")
    private BigDecimal STK_QTY;

    @Column(name = "QUANTITY")
    private BigDecimal QUANTITY;

    @Column(name = "UOM")
    private String UOM;

    @Column(name = "POSTED_FLAG")
    private String POSTED_FLAG;

    @Column(name = "ENTRY_CREATION_DATE")
    private Date ENTRY_CREATION_DATE;

    @Column(name = "CREATION_DATE")
    private Date CREATION_DATE;

    @Column(name = "ERROR_MSG")
    private String ERROR_MSG;

    @Column(name = "INVENTORY_LOCATION_ID")
    private BigDecimal INVENTORY_LOCATION_ID;

    @Column(name = "FOR_LTR_WATER")
    private BigDecimal FOR_LTR_WATER;

    @Column(name = "ADVISED_BY")
    private String ADVISED_BY;

    @Column(name = "ISSUED_BY")
    private String ISSUED_BY;
}
