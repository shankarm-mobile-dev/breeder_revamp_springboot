/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.manure.smsgateway;


import com.suguna.breeder_revamp.manure.dtos.APIResponse;

/**
 * SMS Gateway Interface
 */
public interface SMSGateway {
    APIResponse<?> sendSMS(String mobileNumber, String message);

    APIResponse<?> sendSMS_2(String mobileNumber,String message);

    APIResponse<?> sendSMS_BD(String mobileNumber,String message);
}
