package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.domain.Persistable;

import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "SUG_MAI_BREEDER_DAILY_ENTRY", schema = "SUG")
public class SugMaiBreederDailyEntryModel implements Persistable<Long> {
    long TXN_ID;
    @Id
    Long REPORT_ID;
    @Transient
    private boolean newEntity = true;
    long DEVICE_ID;
    long BRANCH_ID;
    String BRANCH_CODE;
    String LOCATION_CODE;
    Long INVENTORY_LOCATION_ID;
    String EMP_CODE;
    String TXN_TYPE;
    Date TXN_DATE;
    String BATCH_NO;
    Long BATCH_ID;
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
    Long INVENTORY_ITEM_ID;
    String INVENTORY_DESC;
    String TRANS_UOM;
    float STOCK_QTY;
    float PRIMARY_QTY;
    float SECONDARY_QTY;
    String REASON;
    String ADJ_TYPE;
    String VACC_METHOD;
    Long COLLECTION_NO;
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
    String POSTED_FLAG;
    Date CREATED_DATE;

    @Override
    public Long getId() {
        return REPORT_ID;
    }

    @Override
    public boolean isNew() {
        return newEntity;
    }

    @PostPersist
    @PostLoad
    void markNotNew() {
        this.newEntity = false;
    }
}
