package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

public class MedicineScheduleDto {

    @Column(name = "item_TYPE",type = String.class)
    @JsonProperty("item_TYPE")
    public String item_TYPE;

    @Column(name = "organization_ID",type = String.class)
    @JsonProperty("organization_ID")
    public String organization_ID;

    @Column(name = "subinventory_CODE",type = String.class)
    @JsonProperty("subinventory_CODE")
    public String subinventory_CODE;

    @Column(name = "inventory_ITEM_ID",type = String.class)
    @JsonProperty("inventory_ITEM_ID")
    public String inventory_ITEM_ID;

    @Column(name = "item_GROUP",type = String.class)
    @JsonProperty("item_GROUP")
    public String item_GROUP;
    @Column(name = "item_CATEGORY",type = String.class)
    @JsonProperty("item_CATEGORY")
    public String item_CATEGORY;
    @Column(name = "item_CODE",type = String.class)
    @JsonProperty("item_CODE")
    public String item_CODE;

    @Column(name = "item_DESCRIPTION",type = String.class)
    @JsonProperty("item_DESCRIPTION")
    public String item_DESCRIPTION;

    @Column(name = "primary_UOM_CODE",type = String.class)
    @JsonProperty("primary_UOM_CODE")
    public String primary_UOM_CODE;

    @Column(name = "primary_TRANSACTION_QUANTITY",type = String.class)
    @JsonProperty("primary_TRANSACTION_QUANTITY")
    public String primary_TRANSACTION_QUANTITY;

    @Column(name = "secondary_UOM_CODE",type = String.class)
    @JsonProperty("secondary_UOM_CODE")
    public String secondary_UOM_CODE;
    @Column(name = "secondary_TRANSACTION_QUANTITY",type = String.class)
    @JsonProperty("secondary_TRANSACTION_QUANTITY")
    public String secondary_TRANSACTION_QUANTITY;
}
