package com.suguna.breeder_revamp.manure.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "SUG_MAI_ORDER_HDR", schema = "SUG")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hdr_seq")
    @SequenceGenerator(sequenceName = "SUG_MAI_ORDER_HDR_S", allocationSize = 1, name = "hdr_seq")
    @Column(name = "HEADER_ID")
    Long headerId;
    @Column(name = "ORDER_REF_NUMBER")
    Long orderRefNumber;
    Long ORG_ID;
    Long SALES_REP_ID;
    Long CUSTOMER_ID;
    Long CUSTOMER_SITE_USE_ID;

    Long CUSTOMER_BILL_TO_ID;
    Long PRICE_LIST_ID;
    String SOURCE;
    String CREATED_BY;

    String VEHICLE_NUMBER;
    Date CREATION_DATE;
    Date LAST_UPDATED_DATE;
    int STATUS;
    String REMARKS;
    Long ORGN_ID;
    String POSTED_FLAG;
    String ERR_MSG;
    String ORDER_TYPE;
    String ORDER_FROM;
    Long DEPO_BRANCH_ID;
    String DC_NO;



//    public Long getOrderRefNumber() {
//        return orderRefNumber;
//    }
//
//    public void setOrderRefNumber(Long orderRefNumber) {
//        this.orderRefNumber = orderRefNumber;
//    }

    public Long getCUSTOMER_BILL_TO_ID() {
        return CUSTOMER_BILL_TO_ID;
    }

    public void setCUSTOMER_BILL_TO_ID(Long CUSTOMER_BILL_TO_ID) {
        this.CUSTOMER_BILL_TO_ID = CUSTOMER_BILL_TO_ID;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String getVEHICLE_NUMBER() {
        return VEHICLE_NUMBER;
    }

    public void setVEHICLE_NUMBER(String VEHICLE_NUMBER) {
        this.VEHICLE_NUMBER = VEHICLE_NUMBER;
    }

    /*public Long getHEADER_ID() {
        return HEADER_ID;
    }

    public void setHEADER_ID(Long HEADER_ID) {
        this.HEADER_ID = HEADER_ID;
    }*/

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public Long getORDER_REF_NUMBER() {
        return orderRefNumber;
    }

    public void setORDER_REF_NUMBER(Long ORDER_REF_NUMBER) {
        this.orderRefNumber = ORDER_REF_NUMBER;
    }

    public Long getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(Long ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public Long getSALES_REP_ID() {
        return SALES_REP_ID;
    }

    public void setSALES_REP_ID(Long SALES_REP_ID) {
        this.SALES_REP_ID = SALES_REP_ID;
    }

    public Long getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(Long CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public Long getCUSTOMER_SITE_USE_ID() {
        return CUSTOMER_SITE_USE_ID;
    }

    public void setCUSTOMER_SITE_USE_ID(Long CUSTOMER_SITE_USE_ID) {
        this.CUSTOMER_SITE_USE_ID = CUSTOMER_SITE_USE_ID;
    }

    public Long getPRICE_LIST_ID() {
        return PRICE_LIST_ID;
    }

    public void setPRICE_LIST_ID(Long PRICE_LIST_ID) {
        this.PRICE_LIST_ID = PRICE_LIST_ID;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
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

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public Long getORGN_ID() {
        return ORGN_ID;
    }

    public void setORGN_ID(Long ORGN_ID) {
        this.ORGN_ID = ORGN_ID;
    }

    public String getPOSTED_FLAG() {
        return POSTED_FLAG;
    }

    public void setPOSTED_FLAG(String POSTED_FLAG) {
        this.POSTED_FLAG = POSTED_FLAG;
    }

    public String getERR_MSG() {
        return ERR_MSG;
    }

    public void setERR_MSG(String ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
    }

    public String getORDER_TYPE() {
        return ORDER_TYPE;
    }

    public void setORDER_TYPE(String ORDER_TYPE) {
        this.ORDER_TYPE = ORDER_TYPE;
    }

    public String getORDER_FROM() {
        return ORDER_FROM;
    }

    public void setORDER_FROM(String ORDER_FROM) {
        this.ORDER_FROM = ORDER_FROM;
    }

    public Long getDEPO_BRANCH_ID() {
        return DEPO_BRANCH_ID;
    }

    public void setDEPO_BRANCH_ID(Long DEPO_BRANCH_ID) {
        this.DEPO_BRANCH_ID = DEPO_BRANCH_ID;
    }

    public String getDC_NO() {
        return DC_NO;
    }

    public void setDC_NO(String DC_NO) {
        this.DC_NO = DC_NO;
    }
}
