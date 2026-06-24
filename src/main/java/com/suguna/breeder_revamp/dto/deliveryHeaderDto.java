package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class deliveryHeaderDto {
    @JsonProperty("ledgerid")
    public BigDecimal ledgerid;

    @JsonProperty("orgid")
    public BigDecimal orgid;

    @JsonProperty("branchid")
    public BigDecimal branchid;

    @JsonProperty("deli_trans_id")
    public BigDecimal deli_trans_id;

    @JsonProperty("deliveryDate")
    public String deliveryDate;

    @JsonProperty("customerid")
    public BigDecimal customerid;

    @JsonProperty("vehicleno")
    public String vehicleno;

    @JsonProperty("trans_ref_number")
    public String trans_ref_number;
    @JsonProperty("createdby")
    public BigDecimal createdby;

    @JsonProperty("creation_date")
    public String creation_date;

    @JsonProperty("lastupdateby")
    public BigDecimal lastupdateby;

    @JsonProperty("status")
    public String status;

    @JsonProperty("source")
    public String source;

    public BigDecimal getLedgerid() {
        return ledgerid;
    }

    public void setLedgerid(BigDecimal ledgerid) {
        this.ledgerid = ledgerid;
    }

    public BigDecimal getOrgid() {
        return orgid;
    }

    public void setOrgid(BigDecimal orgid) {
        this.orgid = orgid;
    }

    public BigDecimal getBranchid() {
        return branchid;
    }

    public void setBranchid(BigDecimal branchid) {
        this.branchid = branchid;
    }

    public BigDecimal getDeli_trans_id() {
        return deli_trans_id;
    }

    public void setDeli_trans_id(BigDecimal deli_trans_id) {
        this.deli_trans_id = deli_trans_id;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getCustomerid() {
        return customerid;
    }

    public void setCustomerid(BigDecimal customerid) {
        this.customerid = customerid;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public BigDecimal getCreatedby() {
        return createdby;
    }

    public void setCreatedby(BigDecimal createdby) {
        this.createdby = createdby;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public BigDecimal getLastupdateby() {
        return lastupdateby;
    }

    public void setLastupdateby(BigDecimal lastupdateby) {
        this.lastupdateby = lastupdateby;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
