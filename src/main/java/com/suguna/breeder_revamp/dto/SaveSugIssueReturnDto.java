package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class SaveSugIssueReturnDto {
    @JsonProperty("branchId")
    public long branchId;
    @JsonProperty("branchCode")
    public String branchCode;
    @JsonProperty("transType")
    public String transType;
    @JsonProperty("location")
    public String location;
    @JsonProperty("bird_type")
    public String bird_type;
    @JsonProperty("inventoryItemId")
    public long inventoryItemId;
    @JsonProperty("inventoryItemCode")
    public String inventoryItemCode;
    @JsonProperty("description")
    public String description;
    @JsonProperty("transQty")
    public float transQty;
    @JsonProperty("quantity")
    public float quantity;
    @JsonProperty("uom")
    public String uom;
    @JsonProperty("transDate")
    public Date transDate;
    @JsonProperty("inventory_location_id")
    public long inventory_location_id;
    @JsonProperty("batchId")
    public long batchId;
    @JsonProperty("batchNo")
    public String batchNo;
    @JsonProperty("flockNo")
    public String flockNo;
    @JsonProperty("reportId")
    public long reportId;
}
