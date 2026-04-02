/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway.model;

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
}
