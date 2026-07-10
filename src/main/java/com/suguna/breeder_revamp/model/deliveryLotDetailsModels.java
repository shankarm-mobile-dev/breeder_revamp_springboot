package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SUG_DELIVERY_LOT_DETAILS",schema = "SUG")
public class deliveryLotDetailsModels {

    long DELV_TRANS_ID;
    long DELV_TRANS_LINE_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_sug_delivery_lot_details_s")
    @SequenceGenerator(sequenceName = "sug_delivery_lot_details_s", allocationSize = 1, name = "id_seq_sug_delivery_lot_details_s")
    long DELV_TRANS_LOT_DET_ID;
    String LOT_NUMBER;
    Date RECEIPT_DATE;
    BigDecimal ONHAND_STK_QTY;
    BigDecimal SHIPPED_QTY;
    BigDecimal SHIPPED_QTY2;
    BigDecimal CREATED_BY;
    Date CREATION_DATE;
    BigDecimal LAST_UPDATE_BY;
    Date LAST_UPDATE_DATE;
    String STATUS;
    BigDecimal DELIVERY_DETAIL_ID;
    BigDecimal INVENTORY_LOCATION_ID;
    String SUBINVENTORY_CODE;
    BigDecimal BRANCH_ID;


    public long getDELV_TRANS_ID() {
        return DELV_TRANS_ID;
    }

    public void setDELV_TRANS_ID(long DELV_TRANS_ID) {
        this.DELV_TRANS_ID = DELV_TRANS_ID;
    }

    public long getDELV_TRANS_LINE_ID() {
        return DELV_TRANS_LINE_ID;
    }

    public void setDELV_TRANS_LINE_ID(long DELV_TRANS_LINE_ID) {
        this.DELV_TRANS_LINE_ID = DELV_TRANS_LINE_ID;
    }

    public long getDELV_TRANS_LOT_DET_ID() {
        return DELV_TRANS_LOT_DET_ID;
    }

    public void setDELV_TRANS_LOT_DET_ID(long DELV_TRANS_LOT_DET_ID) {
        this.DELV_TRANS_LOT_DET_ID = DELV_TRANS_LOT_DET_ID;
    }

    public String getLOT_NUMBER() {
        return LOT_NUMBER;
    }

    public void setLOT_NUMBER(String LOT_NUMBER) {
        this.LOT_NUMBER = LOT_NUMBER;
    }

    public Date getRECEIPT_DATE() {
        return RECEIPT_DATE;
    }

    public void setRECEIPT_DATE(Date RECEIPT_DATE) {
        this.RECEIPT_DATE = RECEIPT_DATE;
    }

    public BigDecimal getONHAND_STK_QTY() {
        return ONHAND_STK_QTY;
    }

    public void setONHAND_STK_QTY(BigDecimal ONHAND_STK_QTY) {
        this.ONHAND_STK_QTY = ONHAND_STK_QTY;
    }

    public BigDecimal getSHIPPED_QTY() {
        return SHIPPED_QTY;
    }

    public void setSHIPPED_QTY(BigDecimal SHIPPED_QTY) {
        this.SHIPPED_QTY = SHIPPED_QTY;
    }

    public BigDecimal getSHIPPED_QTY2() {
        return SHIPPED_QTY2;
    }

    public void setSHIPPED_QTY2(BigDecimal SHIPPED_QTY2) {
        this.SHIPPED_QTY2 = SHIPPED_QTY2;
    }

    public BigDecimal getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(BigDecimal CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public Date getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(Date CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public BigDecimal getLAST_UPDATE_BY() {
        return LAST_UPDATE_BY;
    }

    public void setLAST_UPDATE_BY(BigDecimal LAST_UPDATE_BY) {
        this.LAST_UPDATE_BY = LAST_UPDATE_BY;
    }

    public Date getLAST_UPDATE_DATE() {
        return LAST_UPDATE_DATE;
    }

    public void setLAST_UPDATE_DATE(Date LAST_UPDATE_DATE) {
        this.LAST_UPDATE_DATE = LAST_UPDATE_DATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public BigDecimal getDELIVERY_DETAIL_ID() {
        return DELIVERY_DETAIL_ID;
    }

    public void setDELIVERY_DETAIL_ID(BigDecimal DELIVERY_DETAIL_ID) {
        this.DELIVERY_DETAIL_ID = DELIVERY_DETAIL_ID;
    }

    public BigDecimal getINVENTORY_LOCATION_ID() {
        return INVENTORY_LOCATION_ID;
    }

    public void setINVENTORY_LOCATION_ID(BigDecimal INVENTORY_LOCATION_ID) {
        this.INVENTORY_LOCATION_ID = INVENTORY_LOCATION_ID;
    }

    public String getSUBINVENTORY_CODE() {
        return SUBINVENTORY_CODE;
    }

    public void setSUBINVENTORY_CODE(String SUBINVENTORY_CODE) {
        this.SUBINVENTORY_CODE = SUBINVENTORY_CODE;
    }

    public BigDecimal getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(BigDecimal BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }
}
