/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSResponse {
    @JsonProperty("respid")
    String responseId;
    @JsonProperty("accepted")
    boolean status;
    @JsonProperty("error")
    String error;
    String msgid;

}
