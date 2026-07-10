package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="SUG_DELIVERY_HEADER",schema = "SUG")
public class deliveryHeaderModels {

    BigDecimal LEDGER_ID;
    BigDecimal ORG_ID;
    BigDecimal BRANCH_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_sug_delivery_header_s")
    @SequenceGenerator(sequenceName = "sug_delivery_header_s", allocationSize = 1, name = "id_seq_sug_delivery_header_s")
    long DELV_TRANS_ID;
    Date DELIVERY_DATE;
    BigDecimal CUSTOMER_ID;
    String VEHICLE_NO;
    BigDecimal DRIVER_CONTACT_NO;
    BigDecimal SUPERVISOR_CONTACT_NO;
    String TRANSPORTER_NAME;
    String LR_NO;
    Date LR_DATE;
    BigDecimal CREATED_BY;
    Date CREATION_DATE;
    BigDecimal LAST_UPDATE_BY;
    Date LAST_UPDATE_DATE;
    String STATUS;
    String BUYER_PO_NUMBER;
    String SOURCE;
    String TRANS_REF_NUMBER;
    String DRIVER_NAME;
    BigDecimal NO_CRATES;
    String ROUTE;
    BigDecimal NO_PLASTIC_BOX;

    public BigDecimal getLEDGER_ID() {
        return LEDGER_ID;
    }

    public void setLEDGER_ID(BigDecimal LEDGER_ID) {
        this.LEDGER_ID = LEDGER_ID;
    }

    public BigDecimal getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(BigDecimal ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public BigDecimal getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(BigDecimal BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }

    public long getDELV_TRANS_ID() {
        return DELV_TRANS_ID;
    }

    public void setDELV_TRANS_ID(long DELV_TRANS_ID) {
        this.DELV_TRANS_ID = DELV_TRANS_ID;
    }

    public Date getDELIVERY_DATE() {
        return DELIVERY_DATE;
    }

    public void setDELIVERY_DATE(Date DELIVERY_DATE) {
        this.DELIVERY_DATE = DELIVERY_DATE;
    }

    public BigDecimal getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(BigDecimal CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getVEHICLE_NO() {
        return VEHICLE_NO;
    }

    public void setVEHICLE_NO(String VEHICLE_NO) {
        this.VEHICLE_NO = VEHICLE_NO;
    }

    public BigDecimal getDRIVER_CONTACT_NO() {
        return DRIVER_CONTACT_NO;
    }

    public void setDRIVER_CONTACT_NO(BigDecimal DRIVER_CONTACT_NO) {
        this.DRIVER_CONTACT_NO = DRIVER_CONTACT_NO;
    }

    public BigDecimal getSUPERVISOR_CONTACT_NO() {
        return SUPERVISOR_CONTACT_NO;
    }

    public void setSUPERVISOR_CONTACT_NO(BigDecimal SUPERVISOR_CONTACT_NO) {
        this.SUPERVISOR_CONTACT_NO = SUPERVISOR_CONTACT_NO;
    }

    public String getTRANSPORTER_NAME() {
        return TRANSPORTER_NAME;
    }

    public void setTRANSPORTER_NAME(String TRANSPORTER_NAME) {
        this.TRANSPORTER_NAME = TRANSPORTER_NAME;
    }

    public String getLR_NO() {
        return LR_NO;
    }

    public void setLR_NO(String LR_NO) {
        this.LR_NO = LR_NO;
    }

    public Date getLR_DATE() {
        return LR_DATE;
    }

    public void setLR_DATE(Date LR_DATE) {
        this.LR_DATE = LR_DATE;
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

    public String getBUYER_PO_NUMBER() {
        return BUYER_PO_NUMBER;
    }

    public void setBUYER_PO_NUMBER(String BUYER_PO_NUMBER) {
        this.BUYER_PO_NUMBER = BUYER_PO_NUMBER;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    public String getTRANS_REF_NUMBER() {
        return TRANS_REF_NUMBER;
    }

    public void setTRANS_REF_NUMBER(String TRANS_REF_NUMBER) {
        this.TRANS_REF_NUMBER = TRANS_REF_NUMBER;
    }

    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }

    public BigDecimal getNO_CRATES() {
        return NO_CRATES;
    }

    public void setNO_CRATES(BigDecimal NO_CRATES) {
        this.NO_CRATES = NO_CRATES;
    }

    public String getROUTE() {
        return ROUTE;
    }

    public void setROUTE(String ROUTE) {
        this.ROUTE = ROUTE;
    }

    public BigDecimal getNO_PLASTIC_BOX() {
        return NO_PLASTIC_BOX;
    }

    public void setNO_PLASTIC_BOX(BigDecimal NO_PLASTIC_BOX) {
        this.NO_PLASTIC_BOX = NO_PLASTIC_BOX;
    }
}
