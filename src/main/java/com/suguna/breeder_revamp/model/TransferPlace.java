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

    @JsonProperty("ShedInfoLineDetails")
    ArrayList<ShedDetailsReport> ShedInfoLineDetails;

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
    @Getter
    @Setter
    public static class ShedDetailsReport {
        @Column(name = "TOTAL_BIRDS_CAPACITY", type = String.class)
        @JsonProperty("TOTAL_BIRDS_CAPACITY")
        String totalBirdsCapacity;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "ALLOCATE_PER", type = String.class)
        @JsonProperty("ALLOCATE_PER")
        String allocatePer;
        @Column(name = "REMAINING_BIRDS", type = String.class)
        @JsonProperty("REMAINING_BIRDS")
        String remainingBirds;
        @Column(name = "SHED_NAME", type = String.class)
        @JsonProperty("SHED_NAME")
        String shedName;
        @Column(name = "FLOCK_ID", type = String.class)
        @JsonProperty("FLOCK_ID")
        String flockID;


        @Column(name = "SHED_REARING_AREA", type = String.class)
        @JsonProperty("SHED_REARING_AREA")
        String shedRearingArea;
        @Column(name = "SHED_TYPE", type = String.class)
        @JsonProperty("SHED_TYPE")
        String shedType;
        @Column(name = "SHED_DIRECTION", type = String.class)
        @JsonProperty("SHED_DIRECTION")
        String shedDirection;
        @Column(name = "SHED_LENGTH", type = String.class)
        @JsonProperty("SHED_LENGTH")
        String shedLength;
        @Column(name = "SHED_BREATH", type = String.class)
        @JsonProperty("SHED_BREATH")
        String shedBreath;
        @Column(name = "SHED_AREA", type = String.class)
        @JsonProperty("SHED_AREA")
        String shedArea;

        @Column(name = "ROOF_TYPE", type = String.class)
        @JsonProperty("ROOF_TYPE")
        String roofType;
        @Column(name = "FLOOR_TYPE", type = String.class)
        @JsonProperty("FLOOR_TYPE")
        String floorType;
        @Column(name = "CAPACITY_BIRDS_F", type = String.class)
        @JsonProperty("CAPACITY_BIRDS_F")
        String capacityBirdsF;
        @Column(name = "CAPACITY_BIRDS_M", type = String.class)
        @JsonProperty("CAPACITY_BIRDS_M")
        String capacityBirdsM;
        @Column(name = "CAPACITY_UOM", type = String.class)
        @JsonProperty("CAPACITY_UOM")
        String capacityUom;
        @Column(name = "LINES_NO", type = String.class)
        @JsonProperty("LINES_NO")
        String linesNo;
        @Column(name = "SL_NO", type = String.class)
        @JsonProperty("SL_NO")
        String slNo;


        @JsonProperty("PlacementInfoLineDetails")
        ArrayList<PlacementInfoLineDetails> PlacementInfoLineDetails;
    }
    @Getter
    @Setter
    public static class PlacementInfoLineDetails {
        @Column(name = "TOTAL_BIRDS_CAPACITY", type = String.class)
        @JsonProperty("TOTAL_BIRDS_CAPACITY")
        String totalBirdsCapacity;
        @Column(name = "FEMALE_NOS", type = String.class)
        @JsonProperty("FEMALE_NOS")
        String femaleNos;
        @Column(name = "MALE_NOS", type = String.class)
        @JsonProperty("MALE_NOS")
        String maleNos;
        @Column(name = "ALLOCATE_PER", type = String.class)
        @JsonProperty("ALLOCATE_PER")
        String allocatePer;
        @Column(name = "REMAINING_BIRDS", type = String.class)
        @JsonProperty("REMAINING_BIRDS")
        String remainingBirds;
        @Column(name = "LINE_NAME", type = String.class)
        @JsonProperty("LINE_NAME")
        String lineName;
        @Column(name = "SIDE", type = String.class)
        @JsonProperty("SIDE")
        String side;
    }
    @Getter
    @Setter
    public static class VehicleGateInDetails {
        @Column(name = "PLAN_ID", type = String.class)
        @JsonProperty("PLAN_ID")
        String PLAN_ID;
        @Column(name = "TRANS_ID", type = String.class)
        @JsonProperty("TRANS_ID")
        String TRANS_ID;
        @Column(name = "TRANSPORTER_NAME", type = String.class)
        @JsonProperty("TRANSPORTER_NAME")
        String TRANSPORTER_NAME;
        @Column(name = "TRANSPORTER_TYPE", type = String.class)
        @JsonProperty("TRANSPORTER_TYPE")
        String TRANSPORTER_TYPE;
        @Column(name = "TRANSPORT_MODE", type = String.class)
        @JsonProperty("TRANSPORT_MODE")
        String TRANSPORT_MODE;
        @Column(name = "VEHICLE_TYPE", type = String.class)
        @JsonProperty("VEHICLE_TYPE")
        String VEHICLE_TYPE;
        @Column(name = "VEHICLE_NO", type = String.class)
        @JsonProperty("VEHICLE_NO")
        String VEHICLE_NO;
        @Column(name = "ARRIVAL_DATE", type = String.class)
        @JsonProperty("ARRIVAL_DATE")
        String ARRIVAL_DATE;
        @Column(name = "TRANS_LINE_ID", type = String.class)
        @JsonProperty("TRANS_LINE_ID")
        String TRANS_LINE_ID;
        @Column(name = "TRANS_DTL_ID", type = String.class)
        @JsonProperty("TRANS_DTL_ID")
        String TRANS_DTL_ID;
        @Column(name = "PLAN_DTL_ID", type = String.class)
        @JsonProperty("PLAN_DTL_ID")
        String PLAN_DTL_ID;
    }
    @Getter
    @Setter
    public static class VehicleGateOutDetails {
        @Column(name = "PLAN_ID", type = String.class)
        @JsonProperty("PLAN_ID")
        String PLAN_ID;
        @Column(name = "TRANS_ID", type = String.class)
        @JsonProperty("TRANS_ID")
        String TRANS_ID;
        @Column(name = "TRANSPORTER_NAME", type = String.class)
        @JsonProperty("TRANSPORTER_NAME")
        String TRANSPORTER_NAME;
        @Column(name = "TRANSPORTER_TYPE", type = String.class)
        @JsonProperty("TRANSPORTER_TYPE")
        String TRANSPORTER_TYPE;
        @Column(name = "TRANSPORT_MODE", type = String.class)
        @JsonProperty("TRANSPORT_MODE")
        String TRANSPORT_MODE;
        @Column(name = "VEHICLE_TYPE", type = String.class)
        @JsonProperty("VEHICLE_TYPE")
        String VEHICLE_TYPE;
        @Column(name = "VEHICLE_NO", type = String.class)
        @JsonProperty("VEHICLE_NO")
        String VEHICLE_NO;
        @Column(name = "ARRIVAL_DATE", type = String.class)
        @JsonProperty("ARRIVAL_DATE")
        String ARRIVAL_DATE;
        @Column(name = "TRANS_LINE_ID", type = String.class)
        @JsonProperty("TRANS_LINE_ID")
        String TRANS_LINE_ID;
        @Column(name = "TRANS_DTL_ID", type = String.class)
        @JsonProperty("TRANS_DTL_ID")
        String TRANS_DTL_ID;
        @Column(name = "PLAN_DTL_ID", type = String.class)
        @JsonProperty("PLAN_DTL_ID")
        String PLAN_DTL_ID;
    }
    @Getter
    @Setter
    public static class HatcheryPlanDetails {
        @Column(name = "PLAN_ID", type = String.class)
        @JsonProperty("PLAN_ID")
        String PLAN_ID;
        @Column(name = "TRANS_ID", type = String.class)
        @JsonProperty("TRANS_ID")
        String TRANS_ID;
        @Column(name = "TRANSPORTER_NAME", type = String.class)
        @JsonProperty("TRANSPORTER_NAME")
        String TRANSPORTER_NAME;
        @Column(name = "TRANSPORTER_TYPE", type = String.class)
        @JsonProperty("TRANSPORTER_TYPE")
        String TRANSPORTER_TYPE;
        @Column(name = "TRANSPORT_MODE", type = String.class)
        @JsonProperty("TRANSPORT_MODE")
        String TRANSPORT_MODE;
        @Column(name = "VEHICLE_TYPE", type = String.class)
        @JsonProperty("VEHICLE_TYPE")
        String VEHICLE_TYPE;
        @Column(name = "VEHICLE_NO", type = String.class)
        @JsonProperty("VEHICLE_NO")
        String VEHICLE_NO;
        @Column(name = "ARRIVAL_DATE", type = String.class)
        @JsonProperty("ARRIVAL_DATE")
        String ARRIVAL_DATE;
        @Column(name = "TRANS_LINE_ID", type = String.class)
        @JsonProperty("TRANS_LINE_ID")
        String TRANS_LINE_ID;
        @Column(name = "TRANS_DTL_ID", type = String.class)
        @JsonProperty("TRANS_DTL_ID")
        String TRANS_DTL_ID;
        @Column(name = "PLAN_DTL_ID", type = String.class)
        @JsonProperty("PLAN_DTL_ID")
        String PLAN_DTL_ID;

        @Column(name = "ALLOC_QTY", type = String.class)
        @JsonProperty("ALLOC_QTY")
        String ALLOC_QTY;
        @Column(name = "STOCK_QTY", type = String.class)
        @JsonProperty("STOCK_QTY")
        String STOCK_QTY;
        @Column(name = "PEND_QTY", type = String.class)
        @JsonProperty("PEND_QTY")
        String PEND_QTY;
        @Column(name = "TRANSPORTER_MODE", type = String.class)
        @JsonProperty("TRANSPORTER_MODE")
        String TRANSPORTER_MODE;
        @Column(name = "LOT_NUMBER", type = String.class)
        @JsonProperty("LOT_NUMBER")
        String LOT_NUMBER;
        @Column(name = "ACTUAL_ARRIVAL_DATE", type = String.class)
        @JsonProperty("ACTUAL_ARRIVAL_DATE")
        String ACTUAL_ARRIVAL_DATE;

        @Column(name = "HATCHERY_NAME", type = String.class)
        @JsonProperty("HATCHERY_NAME")
        String HATCHERY_NAME;

        @Column(name = "HATCHERY_ID", type = String.class)
        @JsonProperty("HATCHERY_ID")
        String HATCHERY_ID;
    }

    @Getter
    @Setter
    public static class TransferPlanDetails {
        @Column(name = "EMPCODE", type = String.class)
        @JsonProperty("EMPCODE")
        String EMPCODE;
        @Column(name = "FROM_FARM_ID", type = String.class)
        @JsonProperty("FROM_FARM_ID")
        String FROM_FARM_ID;
        @Column(name = "FROM_FARM_NAME", type = String.class)
        @JsonProperty("FROM_FARM_NAME")
        String FROM_FARM_NAME;
        @Column(name = "TO_FARM_ID", type = String.class)
        @JsonProperty("TO_FARM_ID")
        String TO_FARM_ID;

        @Column(name = "TO_FARM_NAME", type = String.class)
        @JsonProperty("TO_FARM_NAME")
        String TO_FARM_NAME;
        @Column(name = "TXN_HEADER_ID", type = String.class)
        @JsonProperty("TXN_HEADER_ID")
        String TXN_HEADER_ID;
        @Column(name = "TRANS_TYPE", type = String.class)
        @JsonProperty("TRANS_TYPE")
        String TRANS_TYPE;
        @Column(name = "FLOCK_ID", type = String.class)
        @JsonProperty("FLOCK_ID")
        String FLOCK_ID;
        @Column(name = "TXN_DATE", type = String.class)
        @JsonProperty("TXN_DATE")
        String TXN_DATE;
        @Column(name = "TRANS_REASON", type = String.class)
        @JsonProperty("TRANS_REASON")
        String TRANS_REASON;

        @JsonProperty("TRANS_LINES")
        ArrayList<TransferPlanLineDetails> TRANS_LINES;
    }
    @Getter
    @Setter
    public static class TransferPlanLineDetails {
        @Column(name = "TXN_HEADER_ID", type = String.class)
        @JsonProperty("TXN_HEADER_ID")
        String TXN_HEADER_ID;
        @Column(name = "TXN_LINE_ID", type = String.class)
        @JsonProperty("TXN_LINE_ID")
        String TXN_LINE_ID;
        @Column(name = "FROM_INVENTORY_LOC_DESC", type = String.class)
        @JsonProperty("FROM_INVENTORY_LOC_DESC")
        String FROM_INVENTORY_LOC_DESC;
        @Column(name = "TO_INVENTORY_LOC_DESC", type = String.class)
        @JsonProperty("TO_INVENTORY_LOC_DESC")
        String TO_INVENTORY_LOC_DESC;
        @Column(name = "TXN_TYPE", type = String.class)
        @JsonProperty("TXN_TYPE")
        String TXN_TYPE;
        @Column(name = "BIRD_TYPE", type = String.class)
        @JsonProperty("BIRD_TYPE")
        String BIRD_TYPE;
        @Column(name = "ITEM_ID", type = String.class)
        @JsonProperty("ITEM_ID")
        String ITEM_ID;
        @Column(name = "ITEM_DESC", type = String.class)
        @JsonProperty("ITEM_DESC")
        String ITEM_DESC;
        @Column(name = "UOM", type = String.class)
        @JsonProperty("UOM")
        String UOM;
        @Column(name = "STOCK_QTY", type = String.class)
        @JsonProperty("STOCK_QTY")
        String STOCK_QTY;
        @Column(name = "QTY", type = String.class)
        @JsonProperty("QTY")
        String QTY;

        @Column(name = "FROM_LINE_NAME", type = String.class)
        @JsonProperty("FROM_LINE_NAME")
        String FROM_LINE_NAME;
        @Column(name = "TO_LINE_NAME", type = String.class)
        @JsonProperty("TO_LINE_NAME")
        String TO_LINE_NAME;
        @Column(name = "FROM_SIDE_NAME", type = String.class)
        @JsonProperty("FROM_SIDE_NAME")
        String FROM_SIDE_NAME;
        @Column(name = "TO_SIDE_NAME", type = String.class)
        @JsonProperty("TO_SIDE_NAME")
        String TO_SIDE_NAME;

    }
    @Getter
    @Setter
    public static class EggItemStockDetails {
        @Column(name = "organization_ID", type = String.class)
        @JsonProperty("organization_ID")
        String organization_ID;
        @Column(name = "locator_ID", type = String.class)
        @JsonProperty("locator_ID")
        String locator_ID;
        @Column(name = "inventory_ITEM_ID", type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        String inventory_ITEM_ID;
        @Column(name = "inventory_ITEM_CODE", type = String.class)
        @JsonProperty("inventory_ITEM_CODE")
        String inventory_ITEM_CODE;
        @Column(name = "inventory_ITEM_NAME", type = String.class)
        @JsonProperty("inventory_ITEM_NAME")
        String inventory_ITEM_NAME;
        @Column(name = "lay_DATE", type = String.class)
        @JsonProperty("lay_DATE")
        String lay_DATE;
        @Column(name = "lot_NUMBER", type = String.class)
        @JsonProperty("lot_NUMBER")
        String lot_NUMBER;
        @Column(name = "batchflockno", type = String.class)
        @JsonProperty("batchflockno")
        String batchflockno;
        @Column(name = "breed", type = String.class)
        @JsonProperty("breed")
        String breed;
        @Column(name = "batch_TYPE", type = String.class)
        @JsonProperty("batch_TYPE")
        String batch_TYPE;
        @Column(name = "days", type = String.class)
        @JsonProperty("days")
        String days;
        @Column(name = "qty", type = String.class)
        @JsonProperty("qty")
        String qty;
    }

}
