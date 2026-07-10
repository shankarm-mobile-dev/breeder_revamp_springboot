package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_BREEDER_DAILY_ENTRY", schema = "SUG")
public class SugMaiBreederDailyEntryModel {
    long TXN_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_SugMaiBreederDailyEntry")
    @SequenceGenerator(sequenceName = "SUG_MAI_BREEDER_DAILY_ENTRY_S", allocationSize = 1, name = "id_seq_SugMaiBreederDailyEntry")
    long REPORT_ID;
    long DEVICE_ID;
    long BRANCH_ID;
    String BRANCH_CODE;
    String LOCATION_CODE;
    long INVENTORY_LOCATION_ID;
    String EMP_CODE;
    String TXN_TYPE;
    Date TXN_DATE;
    String BATCH_NO;
    long BATCH_ID;
    String FLOCK_NO;
    long AGE;
    String HH;
    long OP_MALE;
    long OP_FEMALE;
    long MORT_MALE;
    long MORT_FEMALE;
    long CULLS_MALE;
    long CULL_FEMALE;
    long EXSH_MALE;
    long EXSH_FEMALE;
    long TRANSFER_MALE;
    long TRANSFER_FEMALE;
    long CL_MALE;
    long CL_FEMALE;
    long TOTAL_EGG;
    String START_TIME;
    String END_TIME;
    float TEMP_MIN;
    float TEMP_MAX;
    String BIRD_TYPE;
    long INVENTORY_ITEM_ID;
    String INVENTORY_DESC;
    String TRANS_UOM;
    float STOCK_QTY;
    float PRIMARY_QTY;
    float SECONDARY_QTY;
    String REASON;
    String ADJ_TYPE;
    String VACC_METHOD;
    long COLLECTION_NO;
    float EGG_WT;
    float BODY_WT;
    float BIRD_CV;
    float LIGTHING_HRS;

    Date ENTRY_CREATION_DATE;

    String REMARKS;
    float CLEANUP_TIME;
    float LATITUDE;
    float LONGITUDE;
    String IMV_DILUENT;
    String GUN_INSEMINATION;
    float DISTANCE;
    float PH_LEVEL;
    float PPM_LEVEL;
    long MTL_REPORT_ID;
    String TXN_CATEGORY;
    String FLOCK_LIQUID;
    String CULL_REASON;
    String ARTIFICIAL_INSEMINATION;
}
