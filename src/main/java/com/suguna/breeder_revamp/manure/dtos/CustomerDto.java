package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDto {

    @JsonProperty("appl_code")
    String applCode;
    @JsonProperty("customer_number")
    String customerNumber;
    @JsonProperty("customer_id")
    String customerId;
    @JsonProperty("customer_acc_site_id")
    String customerAccSiteId;
    @JsonProperty("party_site_id")
    String partySiteId;
    @JsonProperty("customer_name")
    String customerName;
    @JsonProperty("site_use_id")
    String siteUseId;
    @JsonProperty("org_id")
    String orgId;
    @JsonProperty("primary_salesrep_id")
    String primarySalesRepId;
    @JsonProperty("location")
    String location;


    public String getPartySiteId() {
        return partySiteId;
    }

    public void setPartySiteId(String partySiteId) {
        this.partySiteId = partySiteId;
    }

    public String getCustomerAccSiteId() {
        return customerAccSiteId;
    }

    public void setCustomerAccSiteId(String customerAccSiteId) {
        this.customerAccSiteId = customerAccSiteId;
    }

    public String getApplCode() {
        return applCode;
    }

    public void setApplCode(String applCode) {
        this.applCode = applCode;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSiteUseId() {
        return siteUseId;
    }

    public void setSiteUseId(String siteUseId) {
        this.siteUseId = siteUseId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPrimarySalesRepId() {
        return primarySalesRepId;
    }

    public void setPrimarySalesRepId(String primarySalesRepId) {
        this.primarySalesRepId = primarySalesRepId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
