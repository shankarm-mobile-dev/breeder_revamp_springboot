/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 12/3/2025
 */

package com.suguna.breeder_revamp.manure.dtos;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailsDto {
    @Column(name="ORGID")
    private String ORGID;
    @Column(name="ORDERREFNUMBER")
    private String ORDERREFNUMBER;
    @Column(name="CUSTOMERID")
    private String CUSTOMERID;
    @Column(name="CUSTOMERSITEUSEID")
    private String CUSTOMERSITEUSEID;
    @Column(name="STATUS")
    private String STATUS;
    @Column(name="STATUS_CODE")
    private BigDecimal STATUS_CODE;

    @Column(name = "CURRENTSTATUS")
    private String CURRENTSTATUS;
    @Column(name="LOCATION")
    private String LOCATION;
    @Column(name="SHIPTOLOCATION")
    private String SHIPTOLOCATION;
    @Column(name="BILLTOLOCATION")
    private String BILLTOLOCATION;
    @Column(name="CUSTOMERNAME")
    private String CUSTOMERNAME;

    private List<OrderLineDto> orderLines;

    public List<OrderLineDto> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDto> orderLines) {
        this.orderLines = orderLines;
    }

    public String getORGID() {
        return ORGID;
    }

    public void setORGID(String ORGID) {
        this.ORGID = ORGID;
    }

    public String getCURRENTSTATUS() {
        return CURRENTSTATUS;
    }

    public void setCURRENTSTATUS(String CURRENTSTATUS) {
        this.CURRENTSTATUS = CURRENTSTATUS;
    }

    public String getORDERREFNUMBER() {
        return ORDERREFNUMBER;
    }

    public void setORDERREFNUMBER(String ORDERREFNUMBER) {
        this.ORDERREFNUMBER = ORDERREFNUMBER;
    }

    public String getCUSTOMERID() {
        return CUSTOMERID;
    }

    public void setCUSTOMERID(String CUSTOMERID) {
        this.CUSTOMERID = CUSTOMERID;
    }

    public String getCUSTOMERSITEUSEID() {
        return CUSTOMERSITEUSEID;
    }

    public void setCUSTOMERSITEUSEID(String CUSTOMERSITEUSEID) {
        this.CUSTOMERSITEUSEID = CUSTOMERSITEUSEID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getSHIPTOLOCATION() {
        return SHIPTOLOCATION;
    }

    public void setSHIPTOLOCATION(String SHIPTOLOCATION) {
        this.SHIPTOLOCATION = SHIPTOLOCATION;
    }

    public String getBILLTOLOCATION() {
        return BILLTOLOCATION;
    }

    public void setBILLTOLOCATION(String BILLTOLOCATION) {
        this.BILLTOLOCATION = BILLTOLOCATION;
    }

    public String getCUSTOMERNAME() {
        return CUSTOMERNAME;
    }

    public void setCUSTOMERNAME(String CUSTOMERNAME) {
        this.CUSTOMERNAME = CUSTOMERNAME;
    }

    public BigDecimal getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(BigDecimal STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }
}
