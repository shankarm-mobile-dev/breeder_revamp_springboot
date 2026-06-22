package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipToBillToDto {
    @JsonProperty("ship_to_id")
    String shipToId; //SITE_USE_ID
    @JsonProperty("bill_to_id")
    String billToId;
    @JsonProperty("region_code_bl")
    String regionCodeBl;
    @JsonProperty("region_code")
    String regionCode;
    @JsonProperty("bill_to_location")
    String billToLocation;
    @JsonProperty("bill_to_status")
    String billToStatus;
    @JsonProperty("ship_to_location")
    String shipToLocation;
    @JsonProperty("ship_to_status")
    String shipToStatus;

    public String getShipToId() {
        return shipToId;
    }

    public void setShipToId(String shipToId) {
        this.shipToId = shipToId;
    }

    public String getBillToId() {
        return billToId;
    }

    public void setBillToId(String billToId) {
        this.billToId = billToId;
    }

    public String getRegionCodeBl() {
        return regionCodeBl;
    }

    public void setRegionCodeBl(String regionCodeBl) {
        this.regionCodeBl = regionCodeBl;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getBillToLocation() {
        return billToLocation;
    }

    public void setBillToLocation(String billToLocation) {
        this.billToLocation = billToLocation;
    }

    public String getBillToStatus() {
        return billToStatus;
    }

    public void setBillToStatus(String billToStatus) {
        this.billToStatus = billToStatus;
    }

    public String getShipToLocation() {
        return shipToLocation;
    }

    public void setShipToLocation(String shipToLocation) {
        this.shipToLocation = shipToLocation;
    }

    public String getShipToStatus() {
        return shipToStatus;
    }

    public void setShipToStatus(String shipToStatus) {
        this.shipToStatus = shipToStatus;
    }
}
