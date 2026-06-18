package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class deliveryLinesDto {
    @JsonProperty("delvtransid")
    public BigDecimal delvtransid;

    @JsonProperty("delvtranslineid")
    public BigDecimal delvtranslineid;

    @JsonProperty("ordernumber")
    public String  ordernumber;

    @JsonProperty("oeorderheaderid")
    public BigDecimal oeorderheaderid;

    @JsonProperty("oeorderlineid")
    public BigDecimal oeorderlineid;

    @JsonProperty("inventoryitemid")
    public BigDecimal inventoryitemid;

    @JsonProperty("orderuom")
    public String orderuom;

    @JsonProperty("orderedqty")
    public BigDecimal orderedqty;

    @JsonProperty("orderedqty2")
    public BigDecimal orderedqty2;

    @JsonProperty("shippedqty")
    public BigDecimal shippedqty;

    @JsonProperty("shippedqty2")
    public BigDecimal  shippedqty2;

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

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public BigDecimal getOeorderheaderid() {
        return oeorderheaderid;
    }

    public void setOeorderheaderid(BigDecimal oeorderheaderid) {
        this.oeorderheaderid = oeorderheaderid;
    }

    public BigDecimal getOeorderlineid() {
        return oeorderlineid;
    }

    public void setOeorderlineid(BigDecimal oeorderlineid) {
        this.oeorderlineid = oeorderlineid;
    }

    public BigDecimal getInventoryitemid() {
        return inventoryitemid;
    }

    public void setInventoryitemid(BigDecimal inventoryitemid) {
        this.inventoryitemid = inventoryitemid;
    }

    public String getOrderuom() {
        return orderuom;
    }

    public void setOrderuom(String orderuom) {
        this.orderuom = orderuom;
    }

    public BigDecimal getOrderedqty() {
        return orderedqty;
    }

    public void setOrderedqty(BigDecimal orderedqty) {
        this.orderedqty = orderedqty;
    }

    public BigDecimal getOrderedqty2() {
        return orderedqty2;
    }

    public void setOrderedqty2(BigDecimal orderedqty2) {
        this.orderedqty2 = orderedqty2;
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
}
