package com.suguna.breeder_revamp.manure.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Farms {
    String LOCATION_NAME;
    String EMP_CODE;
    BigDecimal ORGANIZATION_ID;
    String ZONE_NAME;
    BigDecimal REGION_ID;
    String REGION_CODE;
    String REGION;
    BigDecimal BRANCH_ID;
    String BRANCH_NAME;
    String BRANCH_SHORT_NAME;



    // Default constructor
    public Farms() {}

    // Parameterized constructor
    // Parameterized constructor
    public Farms(String LOCATION_NAME, String EMP_CODE, BigDecimal ORGANIZATION_ID, String ZONE_NAME, BigDecimal REGION_ID, String REGION_CODE, String REGION, BigDecimal BRANCH_ID, String BRANCH_NAME, String BRANCH_SHORT_NAME) {
        this.LOCATION_NAME = LOCATION_NAME;
        this.EMP_CODE = EMP_CODE;
        this.ORGANIZATION_ID = ORGANIZATION_ID;
        this.ZONE_NAME = ZONE_NAME;
        this.REGION_ID = REGION_ID;
        this.REGION_CODE = REGION_CODE;
        this.REGION = REGION;
        this.BRANCH_ID = BRANCH_ID;
        this.BRANCH_NAME = BRANCH_NAME;
        this.BRANCH_SHORT_NAME = BRANCH_SHORT_NAME;
    }

    public String getLOCATION_NAME() {
        return LOCATION_NAME;
    }

    public String getEMP_CODE() {
        return EMP_CODE;
    }

    public BigDecimal getORGANIZATION_ID() {
        return ORGANIZATION_ID;
    }

    public String getZONE_NAME() {
        return ZONE_NAME;
    }

    public BigDecimal getREGION_ID() {
        return REGION_ID;
    }

    public String getREGION_CODE() {
        return REGION_CODE;
    }

    public String getREGION() {
        return REGION;
    }

    public BigDecimal getBRANCH_ID() {
        return BRANCH_ID;
    }

    public String getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public String getBRANCH_SHORT_NAME() {
        return BRANCH_SHORT_NAME;
    }


}

