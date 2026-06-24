package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class deliveryLotDetailsDto {
    @JsonProperty("delvtransid")
    public BigDecimal delvtransid;

    @JsonProperty("delvtranslineid")
    public BigDecimal delvtranslineid;

    @JsonProperty("deltranslotdets_id")
    public BigDecimal deltranslotdets_id;

    @JsonProperty("lotnumber")
    public String lotnumber;

    @JsonProperty("onhandstkqty")
    public BigDecimal onhandstkqty;

    @JsonProperty("shippedqty")
    public BigDecimal shippedqty;

    @JsonProperty("shippedqty2")
    public BigDecimal shippedqty2;

    @JsonProperty("createdby")
    public BigDecimal createdby;

    @JsonProperty("creation_date")
    public String creation_date;

    @JsonProperty("lastupdateby")
    public BigDecimal lastupdateby;

    @JsonProperty("status")
    public String status;
    @JsonProperty("inventorylocationid")
    public BigDecimal inventorylocationid;

    @JsonProperty("subinventorycode")
    public String subinventorycode;

    @JsonProperty("branchid")
    public BigDecimal branchid;

    public BigDecimal getDelvtransid() {
        return delvtransid;
    }

    public void setDelvtransid(BigDecimal delvtransid) {
        this.delvtransid = delvtransid;
    }

    public BigDecimal getDelvtranslineid() {
        return delvtranslineid;
    }

    public void setDelvtranslineid(BigDecimal delvtranslineid) {
        this.delvtranslineid = delvtranslineid;
    }

    public BigDecimal getDeltranslotdets_id() {
        return deltranslotdets_id;
    }

    public void setDeltranslotdets_id(BigDecimal deltranslotdets_id) {
        this.deltranslotdets_id = deltranslotdets_id;
    }

    public String getLotnumber() {
        return lotnumber;
    }

    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber;
    }

    public BigDecimal getOnhandstkqty() {
        return onhandstkqty;
    }

    public void setOnhandstkqty(BigDecimal onhandstkqty) {
        this.onhandstkqty = onhandstkqty;
    }

    public BigDecimal getShippedqty() {
        return shippedqty;
    }

    public void setShippedqty(BigDecimal shippedqty) {
        this.shippedqty = shippedqty;
    }

    public BigDecimal getShippedqty2() {
        return shippedqty2;
    }

    public void setShippedqty2(BigDecimal shippedqty2) {
        this.shippedqty2 = shippedqty2;
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

    public BigDecimal getInventorylocationid() {
        return inventorylocationid;
    }

    public void setInventorylocationid(BigDecimal inventorylocationid) {
        this.inventorylocationid = inventorylocationid;
    }

    public String getSubinventorycode() {
        return subinventorycode;
    }

    public void setSubinventorycode(String subinventorycode) {
        this.subinventorycode = subinventorycode;
    }

    public BigDecimal getBranchid() {
        return branchid;
    }

    public void setBranchid(BigDecimal branchid) {
        this.branchid = branchid;
    }
}
