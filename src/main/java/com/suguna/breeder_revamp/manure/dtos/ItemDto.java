package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto {

    @JsonProperty("appl_code")
    String applCode;
    @JsonProperty("price_list_name")
    String priceListName;
    @JsonProperty("price_list_id")
    String priceListId;
    @JsonProperty("item_id")
    String itemId;
    @JsonProperty("rate")
    String rate;
    @JsonProperty("uom")
    String uom;
    @JsonProperty("item_code")
    String itemCode;
    @JsonProperty("description")
    String description;

    public String getApplCode() {
        return applCode;
    }

    public void setApplCode(String applCode) {
        this.applCode = applCode;
    }

    public String getPriceListName() {
        return priceListName;
    }

    public void setPriceListName(String priceListName) {
        this.priceListName = priceListName;
    }

    public String getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(String priceListId) {
        this.priceListId = priceListId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
