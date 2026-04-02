/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway;


import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.ResponseResultDto;

/**
 * SMS Gateway Interface
 */
public interface SMSGateway {
    ResponseDto<ResponseResultDto> sendSMS(String mobileNumber, String message);

    ResponseDto<ResponseResultDto> sendSMS_2(String mobileNumber,String message);

    ResponseDto<ResponseResultDto> sendSMS_BD(String mobileNumber,String message);
}
