/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Otp2SmsModel {
    String enterpriseid;
    String subEnterpriseid;
    String pusheid;
    String pushepwd;
    String contenttype;
    String sender;
    String alert;
    String msisdn;
    String intflag;
    String language;
    String msgtext;
    String dpi;
    String dtm;
}
