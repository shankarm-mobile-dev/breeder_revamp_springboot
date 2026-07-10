package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SUG_DELIVERY_LINES",schema = "SUG")
public class deliveryLinesModels {

    long DELV_TRANS_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_sug_delivery_lines_s")
    @SequenceGenerator(sequenceName = "sug_delivery_lines_s", allocationSize = 1, name = "id_seq_sug_delivery_lines_s")
    long DELV_TRANS_LINE_ID;
    String ORDER_NUMBER;
    BigDecimal OE_ORDER_HEADER_ID;
    BigDecimal OE_ORDER_LINE_ID;
    BigDecimal INVENTORY_ITEM_ID;
    String ORDER_UOM;
    BigDecimal ORDERED_QTY;
    BigDecimal ORDERED_QTY2;
    BigDecimal SHIPPED_QTY;
    BigDecimal SHIPPED_QTY2;

    BigDecimal CREATED_BY;
    Date CREATION_DATE;
    BigDecimal LAST_UPDATE_BY;
    Date LAST_UPDATE_DATE;
    String STATUS;
    BigDecimal DELIVERY_ID;
    BigDecimal INVENTORY_LOCATION_ID;
    String SUBINVENTORY_CODE;
    String SEX;
    BigDecimal NO_CRATES;
    String VACCINE_PRINT;

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

    public String getORDER_NUMBER() {
        return ORDER_NUMBER;
    }

    public void setORDER_NUMBER(String ORDER_NUMBER) {
        this.ORDER_NUMBER = ORDER_NUMBER;
    }

    public BigDecimal getOE_ORDER_HEADER_ID() {
        return OE_ORDER_HEADER_ID;
    }

    public void setOE_ORDER_HEADER_ID(BigDecimal OE_ORDER_HEADER_ID) {
        this.OE_ORDER_HEADER_ID = OE_ORDER_HEADER_ID;
    }

    public BigDecimal getOE_ORDER_LINE_ID() {
        return OE_ORDER_LINE_ID;
    }

    public void setOE_ORDER_LINE_ID(BigDecimal OE_ORDER_LINE_ID) {
        this.OE_ORDER_LINE_ID = OE_ORDER_LINE_ID;
    }

    public BigDecimal getINVENTORY_ITEM_ID() {
        return INVENTORY_ITEM_ID;
    }

    public void setINVENTORY_ITEM_ID(BigDecimal INVENTORY_ITEM_ID) {
        this.INVENTORY_ITEM_ID = INVENTORY_ITEM_ID;
    }

    public String getORDER_UOM() {
        return ORDER_UOM;
    }

    public void setORDER_UOM(String ORDER_UOM) {
        this.ORDER_UOM = ORDER_UOM;
    }

    public BigDecimal getORDERED_QTY() {
        return ORDERED_QTY;
    }

    public void setORDERED_QTY(BigDecimal ORDERED_QTY) {
        this.ORDERED_QTY = ORDERED_QTY;
    }

    public BigDecimal getORDERED_QTY2() {
        return ORDERED_QTY2;
    }

    public void setORDERED_QTY2(BigDecimal ORDERED_QTY2) {
        this.ORDERED_QTY2 = ORDERED_QTY2;
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

    public BigDecimal getDELIVERY_ID() {
        return DELIVERY_ID;
    }

    public void setDELIVERY_ID(BigDecimal DELIVERY_ID) {
        this.DELIVERY_ID = DELIVERY_ID;
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

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public BigDecimal getNO_CRATES() {
        return NO_CRATES;
    }

    public void setNO_CRATES(BigDecimal NO_CRATES) {
        this.NO_CRATES = NO_CRATES;
    }

    public String getVACCINE_PRINT() {
        return VACCINE_PRINT;
    }

    public void setVACCINE_PRINT(String VACCINE_PRINT) {
        this.VACCINE_PRINT = VACCINE_PRINT;
    }
}
