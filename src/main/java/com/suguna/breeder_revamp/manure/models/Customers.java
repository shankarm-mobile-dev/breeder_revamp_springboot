package com.suguna.breeder_revamp.manure.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customers {
    String APPL_CODE;
    String CUSTOMER_NUMBER;
    String CUSTOMER_ID;
    String CUSTOMER_NAME;

    String CUST_ACCT_SITE_ID;

    String PARTY_SITE_ID;
    String SITE_USE_ID;
    String ORG_ID;
    String PRIMARY_SALESREP_ID;
    String LOCATION;

    public Customers() {
    }

    public Customers(String APPL_CODE, String CUSTOMER_NUMBER, String CUSTOMER_ID, String CUSTOMER_NAME,String CUST_ACCT_SITE_ID,String PARTY_SITE_ID, String SITE_USE_ID, String ORG_ID, String PRIMARY_SALESREP_ID, String LOCATION) {
        this.APPL_CODE = APPL_CODE;
        this.CUSTOMER_NUMBER = CUSTOMER_NUMBER;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.CUSTOMER_NAME = CUSTOMER_NAME;
        this.CUST_ACCT_SITE_ID = CUST_ACCT_SITE_ID;
        this.PARTY_SITE_ID = PARTY_SITE_ID;
        this.SITE_USE_ID = SITE_USE_ID;
        this.ORG_ID = ORG_ID;
        this.PRIMARY_SALESREP_ID = PRIMARY_SALESREP_ID;
        this.LOCATION = LOCATION;
    }

    public String getPARTY_SITE_ID() {
        return PARTY_SITE_ID;
    }

    public void setPARTY_SITE_ID(String PARTY_SITE_ID) {
        this.PARTY_SITE_ID = PARTY_SITE_ID;
    }

    public String getCUST_ACCT_SITE_ID() {
        return CUST_ACCT_SITE_ID;
    }

    public void setCUST_ACCT_SITE_ID(String CUST_ACCT_SITE_ID) {
        this.CUST_ACCT_SITE_ID = CUST_ACCT_SITE_ID;
    }

    public String getAPPL_CODE() {
        return APPL_CODE;
    }

    public void setAPPL_CODE(String APPL_CODE) {
        this.APPL_CODE = APPL_CODE;
    }

    public String getCUSTOMER_NUMBER() {
        return CUSTOMER_NUMBER;
    }

    public void setCUSTOMER_NUMBER(String CUSTOMER_NUMBER) {
        this.CUSTOMER_NUMBER = CUSTOMER_NUMBER;
    }

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public String getSITE_USE_ID() {
        return SITE_USE_ID;
    }

    public void setSITE_USE_ID(String SITE_USE_ID) {
        this.SITE_USE_ID = SITE_USE_ID;
    }

    public String getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(String ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    public String getPRIMARY_SALESREP_ID() {
        return PRIMARY_SALESREP_ID;
    }

    public void setPRIMARY_SALESREP_ID(String PRIMARY_SALESREP_ID) {
        this.PRIMARY_SALESREP_ID = PRIMARY_SALESREP_ID;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
}
