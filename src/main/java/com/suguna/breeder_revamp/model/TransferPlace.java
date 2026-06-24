package com.suguna.breeder_revamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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

    @Getter
    @Setter
    public static class TransferInHdr {
        @Column(name = "empcode", type = String.class)
        @JsonProperty("empCode")
        String empCode;
        @Column(name = "from_farm_id", type = String.class)
        @JsonProperty("fromFarmId")
        String fromFarmId;
        @Column(name = "from_farm_name", type = String.class)
        @JsonProperty("fromFarmName")
        String fromFarmName;
        @Column(name = "to_farm_id", type = String.class)
        @JsonProperty("toFarmId")
        String toFarmId;
        @Column(name = "txn_header_id", type = String.class)
        @JsonProperty("txnHeaderId")
        String txnHeaderId;
        @Column(name = "transfer_type", type = String.class)
        @JsonProperty("transferType")
        String transferType;
        @Column(name = "txn_date", type = String.class)
        @JsonProperty("txnDate")
        String txnDate;
        @Column(name = "vehicle_no", type = String.class)
        @JsonProperty("vehicleNo")
        String vehicleNo;
        @Column(name = "out_pass_no", type = String.class)
        @JsonProperty("outPassNo")
        String outPassNo;
        @Column(name = "receiver_name", type = String.class)
        @JsonProperty("receiverName")
        String receiverName;
        @Column(name = "transfer_rsn", type = String.class)
        @JsonProperty("transferRsn")
        String transferRsn;
        @Column(name = "location_TYPE", type = String.class)
        @JsonProperty("locationTYPE")
        String locationTYPE;
        @Column(name = "is_uploaded", type = String.class)
        @JsonProperty("isUploaded")
        String isUploaded;
        @Column(name = "is_committed", type = String.class)
        @JsonProperty("isCommitted")
        String isCommitted;

        @JsonProperty("transferInDetails")
        ArrayList<TransferInDetails> transferInDetails;
    }
    @Getter
    @Setter
    public static class TransferInDetails {
        @Column(name = "txn_header_id", type = String.class)
        @JsonProperty("txnHeaderId")
        String txnHeaderId;
        @Column(name = "txn_line_id", type = String.class)
        @JsonProperty("txnLineId")
        String txnLineId;
        @Column(name = "from_farm_id", type = String.class)
        @JsonProperty("fromFarmId")
        String fromFarmId;
        @Column(name = "to_farm_id", type = String.class)
        @JsonProperty("toFarmId")
        String toFarmId;
        @Column(name = "from_inventory_location_id", type = String.class)
        @JsonProperty("fromInventoryLocationId")
        String fromInventoryLocationId;
        @Column(name = "from_inventory_loc_desc", type = String.class)
        @JsonProperty("fromInventoryLocDesc")
        String fromInventoryLocDesc;
        @Column(name = "from_batch_id", type = String.class)
        @JsonProperty("fromBatchId")
        String fromBatchId;
        @Column(name = "to_inventory_location_id", type = String.class)
        @JsonProperty("toInventoryLocationId")
        String toInventoryLocationId;
        @Column(name = "to_batch_id", type = String.class)
        @JsonProperty("toBatchId")
        String toBatchId;
        @Column(name = "txn_type", type = String.class)
        @JsonProperty("txnType")
        String txnType;
        @Column(name = "bird_type", type = String.class)
        @JsonProperty("birdType")
        String birdType;
        @Column(name = "item_id", type = String.class)
        @JsonProperty("itemId")
        String itemId;
        @Column(name = "item_desc", type = String.class)
        @JsonProperty("itemDesc")
        String itemDesc;
        @Column(name = "uom", type = String.class)
        @JsonProperty("uom")
        String uom;
        @Column(name = "stock_qty", type = String.class)
        @JsonProperty("stockQty")
        String stockQty;
        @Column(name = "days", type = String.class)
        @JsonProperty("days")
        String days;
        @Column(name = "age", type = String.class)
        @JsonProperty("age")
        String age;
        @Column(name = "qty", type = String.class)
        @JsonProperty("qty")
        String qty;
        @Column(name = "receiving_qty", type = String.class)
        @JsonProperty("receivingQty")
        String receivingQty;
        @Column(name = "diff_qty", type = String.class)
        @JsonProperty("diffQty")
        String diffQty;
        @Column(name = "lotnumber", type = String.class)
        @JsonProperty("lotNumber")
        String lotNumber;
        @Column(name = "lay_date", type = String.class)
        @JsonProperty("layDate")
        String layDate;
        @Column(name = "breedname", type = String.class)
        @JsonProperty("breedName")
        String breedName;
        @Column(name = "location_TYPE", type = String.class)
        @JsonProperty("locationType")
        String locationType;
        @Column(name = "is_uploaded", type = String.class)
        @JsonProperty("isUploaded")
        String isUploaded;
        @Column(name = "is_committed", type = String.class)
        @JsonProperty("isCommitted")
        String isCommitted;

    }
    }
