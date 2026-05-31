package com.suguna.breeder_revamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferPlace {
    @Column(name = "from_ORG_ID" , type = long.class)
    @JsonProperty("fromOrgId")
    long fromOrgId;
    @Column(name = "branch_ID" , type = long.class)
    @JsonProperty("branchId")
    long branchId;
    @Column(name = "branch_NAME" , type = String.class)
    @JsonProperty("branchName")
    String branchName;
    @Column(name = "opm_DIVISION" , type = String.class)
    @JsonProperty("opmDivision")
    String opmDivision;
    @Column(name = "cull_FARM" , type = String.class)
    @JsonProperty("cullFarm")
    String cullFarm;
    @Column(name = "hatchery" , type = String.class)
    @JsonProperty("hatcheryName")
    String hatcheryName;
    @Column(name = "ro" , type = String.class)
    @JsonProperty("regionalOffice")
    String regionalOffice;

    @Getter
    @Setter
    public static class EggItemDetails {
        @Column(name = "item_GROUP", type = String.class)
        @JsonProperty("itemGroup")
        String itemGroup;
        @Column(name = "item_CATEGORY", type = String.class)
        @JsonProperty("itemCategory")
        String itemCategory;
        @Column(name = "inventory_ITEM_ID", type = String.class)
        @JsonProperty("inventoryItemId")
        String inventoryItemId;
        @Column(name = "batch_TYPE", type = String.class)
        @JsonProperty("batchType")
        String batchType;
        @Column(name = "item_CODE", type = String.class)
        @JsonProperty("itemCode")
        String itemCode;
        @Column(name = "item_DESCRIPTION", type = String.class)
        @JsonProperty("itemDescription")
        String itemDescription;
        @Column(name = "primary_UOM", type = String.class)
        @JsonProperty("primaryUom")
        String primaryUom;
        @Column(name = "secondary_UOM", type = String.class)
        @JsonProperty("secondaryUom")
        String secondaryUom;
        @Column(name = "organization_ID", type = String.class)
        @JsonProperty("organizationID")
        String organizationID;
        @Column(name = "seq_NO", type = long.class)
        @JsonProperty("seqNo")
        long seqNo;
    }

    @Getter
    @Setter
    public static class FeedItemDetails {
        @Column(name = "item_TYPE", type = String.class)
        @JsonProperty("itemType")
        String itemType;
        @Column(name = "organization_ID", type = long.class)
        @JsonProperty("organizationId")
        long organizationId;
        @Column(name = "subinventory_CODE", type = String.class)
        @JsonProperty("subInventoryCode")
        String subInventoryCode;
        @Column(name = "inventory_ITEM_ID", type = long.class)
        @JsonProperty("inventoryItemId")
        long inventoryItemId;
        @Column(name = "item_GROUP", type = String.class)
        @JsonProperty("itemGroup")
        String itemGroup;

        @Column(name = "item_CATEGORY", type = String.class)
        @JsonProperty("itemCategory")
        String itemCategory;
        @Column(name = "item_CODE", type = String.class)
        @JsonProperty("itemCode")
        String itemCode;
        @Column(name = "item_DESCRIPTION", type = String.class)
        @JsonProperty("itemDescription")
        String itemDescription;
        @Column(name = "primary_UOM_CODE", type = String.class)
        @JsonProperty("primaryUomCode")
        String primaryUomCode;
        @Column(name = "primary_TRANSACTION_QUANTITY", type = String.class)
        @JsonProperty("primaryTransactionQuantity")
        String primaryTransactionQuantity;
        @Column(name = "secondary_UOM_CODE", type = String.class)
        @JsonProperty("secondaryUomCode")
        String secondaryUomCode;
        @Column(name = "secondary_TRANSACTION_QUANTITY", type = String.class)
        @JsonProperty("secondaryTransactionQuantity")
        String secondaryTransactionQuantity;
        @Column(name = "AGE", type = long.class)
        @JsonProperty("age")
        long age;
    }

    @Getter
    @Setter
    public static class MedicineVaccineDetails {
        @Column(name = "item_TYPE", type = String.class)
        @JsonProperty("itemType")
        String itemType;
        @Column(name = "organization_ID", type = long.class)
        @JsonProperty("organizationId")
        long organizationId;
        @Column(name = "subinventory_CODE", type = String.class)
        @JsonProperty("subInventoryCode")
        String subInventoryCode;
        @Column(name = "inventory_ITEM_ID", type = long.class)
        @JsonProperty("inventoryItemId")
        long inventoryItemId;
        @Column(name = "item_GROUP", type = String.class)
        @JsonProperty("itemGroup")
        String itemGroup;

        @Column(name = "item_CATEGORY", type = String.class)
        @JsonProperty("itemCategory")
        String itemCategory;
        @Column(name = "item_CODE", type = String.class)
        @JsonProperty("itemCode")
        String itemCode;
        @Column(name = "item_DESCRIPTION", type = String.class)
        @JsonProperty("itemDescription")
        String itemDescription;
        @Column(name = "primary_UOM_CODE", type = String.class)
        @JsonProperty("primaryUomCode")
        String primaryUomCode;
        @Column(name = "primary_TRANSACTION_QUANTITY", type = String.class)
        @JsonProperty("primaryTransactionQuantity")
        String primaryTransactionQuantity;
        @Column(name = "secondary_UOM_CODE", type = String.class)
        @JsonProperty("secondaryUomCode")
        String secondaryUomCode;
        @Column(name = "secondary_TRANSACTION_QUANTITY", type = String.class)
        @JsonProperty("secondaryTransactionQuantity")
        String secondaryTransactionQuantity;
        @Column(name = "AGE", type = long.class)
        @JsonProperty("age")
        long age;
    }
}
