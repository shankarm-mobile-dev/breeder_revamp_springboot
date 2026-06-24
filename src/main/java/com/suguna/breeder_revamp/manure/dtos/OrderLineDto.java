/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 6/3/2025
 */

package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OrderLineDto {
    @JsonProperty("line_id")
    Long lineId;
    @JsonProperty("item_id")
    Long itemID;
    @JsonProperty("qty")
    String qty;
    @JsonProperty("rate")
    String rate;
    @JsonProperty("primary_uom_code")
    String primaryUOMCode;
    @JsonProperty("created_by")
    String createdBy;
    @JsonProperty("creation_date")
    Date creationDate;
    @JsonProperty("last_updated_date")
    Date lastUpdatedDate;
    @JsonProperty("status")
    int status;
    @JsonProperty("price_list_rate")
    String priceListRate;

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPrimaryUOMCode() {
        return primaryUOMCode;
    }

    public void setPrimaryUOMCode(String primaryUOMCode) {
        this.primaryUOMCode = primaryUOMCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPriceListRate() {
        return priceListRate;
    }

    public void setPriceListRate(String priceListRate) {
        this.priceListRate = priceListRate;
    }
}
