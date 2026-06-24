/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 6/3/2025
 */

package com.suguna.breeder_revamp.manure.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SUG_MAI_ORDER_LINE", schema = "SUG")
public class OrderLine {
    //Long HEADER_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_seq")
    @SequenceGenerator(sequenceName = "SUG_MAI_ORDER_LINE_S", allocationSize = 1, name = "line_seq")
    Long LINE_ID;
    Long ITEM_ID;
    BigDecimal QTY;
    String RATE;
    String PRIMARY_UOM_CODE;
    String CREATED_BY;
    Date CREATION_DATE;
    Date LAST_UPDATED_DATE;
    int STATUS;
    String PRICE_LIST_RATE;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEADER_ID")
    private Orders order;

    public Long getLINE_ID() {
        return LINE_ID;
    }

    public void setLINE_ID(Long LINE_ID) {
        this.LINE_ID = LINE_ID;
    }

    public Long getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(Long ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public BigDecimal getQTY() {
        return QTY;
    }

    public void setQTY(BigDecimal QTY) {
        this.QTY = QTY;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getPRIMARY_UOM_CODE() {
        return PRIMARY_UOM_CODE;
    }

    public void setPRIMARY_UOM_CODE(String PRIMARY_UOM_CODE) {
        this.PRIMARY_UOM_CODE = PRIMARY_UOM_CODE;
    }

    public String getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(String CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public Date getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(Date CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public Date getLAST_UPDATED_DATE() {
        return LAST_UPDATED_DATE;
    }

    public void setLAST_UPDATED_DATE(Date LAST_UPDATED_DATE) {
        this.LAST_UPDATED_DATE = LAST_UPDATED_DATE;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getPRICE_LIST_RATE() {
        return PRICE_LIST_RATE;
    }

    public void setPRICE_LIST_RATE(String PRICE_LIST_RATE) {
        this.PRICE_LIST_RATE = PRICE_LIST_RATE;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
