/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Push3SmsModel {
    String appid;
    String userId;
    String pass;
    String contenttype;
    String from;
    String to;
    String alert;
    String selfid;
    String intflag;
    String dpi;
    String dtm;
    String tc;
    String text;
    String n;
}
