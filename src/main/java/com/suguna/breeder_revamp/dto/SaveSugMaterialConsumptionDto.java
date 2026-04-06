package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class SaveSugMaterialConsumptionDto {
    @JsonProperty("device_id")
    public String device_id;

    @JsonProperty("trans_id")
    public BigDecimal trans_id;

    @JsonProperty("transdate")
    public String transdate;
    @JsonProperty("branch_id")
    public String branch_id;



    @JsonProperty("trans_type")
    public String trans_type;

    @JsonProperty("inventory_item_id")
    public String inventory_item_id;

    @JsonProperty("item_description")
    public String item_description;

    @JsonProperty("stk_qty")
    public BigDecimal stk_qty;

    @JsonProperty("quantity")
    public String quantity;


    @JsonProperty("uom")
    public String uom;

    @JsonProperty("entry_creation_date")
    public String entry_creation_date;

    @JsonProperty("inventory_location_id")
    public BigDecimal inventory_location_id;

    @JsonProperty("for_ltr_water")
    public BigDecimal for_ltr_water;

    @JsonProperty("advised_by")
    public String advised_by;

    @JsonProperty("issued_by")
    public String issued_by;
}
