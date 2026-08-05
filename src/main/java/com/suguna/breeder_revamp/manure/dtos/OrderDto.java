package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class OrderDto {
    @JsonProperty("header_id")
    Long headerId;
    @JsonProperty("order_ref_number")
    Long orderRefNumber;
    @JsonProperty("org_id")
    Long orgId;
    @JsonProperty("salesrep_id")
    Long salesRepId;
    @JsonProperty("customer_id")
    Long customerId;
    @JsonProperty("customer_site_use_id")
    Long customerSiteUseId;

    @JsonProperty("customer_acc_site_id")
    Long customerAcctSiteId;

    @JsonProperty("party_site_id")
    Long party_site_id;

    @JsonProperty("customer_bill_to_id")
    Long customerBillToId;
    @JsonProperty("pricelist_id")
    Long priceListId;
    @JsonProperty("source")
    String source;
    @JsonProperty("created_by")
    String createdBy;
    @JsonProperty("creation_date")
    Date creationDate;
    @JsonProperty("last_updated_date")
    Date lastUpdatedDate;
    @JsonProperty("status")
    String status;
    @JsonProperty("status_code")
    String status_code;
    @JsonProperty("remarks")
    String remarks;
    @JsonProperty("orgn_id")
    Long orgnId;
    @JsonProperty("posted_flag")
    String postedFlag;
    @JsonProperty("err_msg")
    String errMessage;
    @JsonProperty("order_type")
    String orderType;
    @JsonProperty("order_from")
    String orderFrom;
    @JsonProperty("depo_branch_id")
    Long depoBranchId;

    @JsonProperty("vehicle_number")
    String vehicleNumber;

    @JsonProperty("status_message")
    String statusMessage;

    public Long getParty_site_id() {
        return party_site_id;
    }

    public void setParty_site_id(Long party_site_id) {
        this.party_site_id = party_site_id;
    }

    public Long getCustomerAcctSiteId() {
        return customerAcctSiteId;
    }

    public void setCustomerAcctSiteId(Long customerAcctSiteId) {
        this.customerAcctSiteId = customerAcctSiteId;
    }

    public Long getCustomerBillToId() {
        return customerBillToId;
    }

    public void setCustomerBillToId(Long customerBillToId) {
        this.customerBillToId = customerBillToId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @JsonProperty("order_line")
    List<OrderLineDto> orderLineDtoList;

    public List<OrderLineDto> getOrderLineDtoList() {
        return orderLineDtoList;
    }

    public void setOrderLineDtoList(List<OrderLineDto> orderLineDtoList) {
        this.orderLineDtoList = orderLineDtoList;
    }

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public Long getOrderRefNumber() {
        return orderRefNumber;
    }

    public void setOrderRefNumber(Long orderRefNumber) {
        this.orderRefNumber = orderRefNumber;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerSiteUseId() {
        return customerSiteUseId;
    }

    public void setCustomerSiteUseId(Long customerSiteUseId) {
        this.customerSiteUseId = customerSiteUseId;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Long priceListId) {
        this.priceListId = priceListId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getOrgnId() {
        return orgnId;
    }

    public void setOrgnId(Long orgnId) {
        this.orgnId = orgnId;
    }

    public String getPostedFlag() {
        return postedFlag;
    }

    public void setPostedFlag(String postedFlag) {
        this.postedFlag = postedFlag;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Long getDepoBranchId() {
        return depoBranchId;
    }

    public void setDepoBranchId(Long depoBranchId) {
        this.depoBranchId = depoBranchId;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
}
