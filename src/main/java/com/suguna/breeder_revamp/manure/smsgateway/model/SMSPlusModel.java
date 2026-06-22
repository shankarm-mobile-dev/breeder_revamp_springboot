/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.manure.smsgateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSPlusModel {
    @JsonProperty("api_token")
    String apiToken;
    @JsonProperty("sid")
    String sId;
    @JsonProperty("sms")
    String message;
    @JsonProperty("msisdn")
    String msisdn;
    @JsonProperty("csms_id")
    String csmsId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCsmsId() {
        return csmsId;
    }

    public void setCsmsId(String csmsId) {
        this.csmsId = csmsId;
    }
}
