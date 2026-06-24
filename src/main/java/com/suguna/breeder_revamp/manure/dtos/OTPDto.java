/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 7/3/2025
 */

package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OTPDto {
    @JsonProperty("user_name")
    String userName;
    @JsonProperty("source")
    String source;
    @JsonProperty("user_type")
    String userType;
    @JsonProperty("user_id")
    String userId;

    String otp;

    String message;

    @JsonProperty("mobile_number")
    String mobileNumber;

    @JsonProperty("customer_acc_site_id")
    String custAccSiteId;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustAccSiteId() {
        return custAccSiteId;
    }

    public void setCustAccSiteId(String custAccSiteId) {
        this.custAccSiteId = custAccSiteId;
    }

    @JsonProperty("order_ref_number")
    String orderRefNumber;

    public String getOrderRefNumber() {
        return orderRefNumber;
    }

    public void setOrderRefNumber(String orderRefNumber) {
        this.orderRefNumber = orderRefNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
