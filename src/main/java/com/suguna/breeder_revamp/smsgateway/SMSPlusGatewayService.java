/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway;



import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.ResponseResultDto;
import com.suguna.breeder_revamp.smsgateway.model.SMSPlusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class SMSPlusGatewayService implements SMSGateway{

    private RestTemplate restTemplate;

    @Autowired
    SMSPlusGatewayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public ResponseDto<ResponseResultDto> sendSMS(String mobileNumber, String message) {
        SMSPlusModel smsPlusModel = new SMSPlusModel();
        smsPlusModel.setApiToken("0d8114c6-5060-4f09-a1c0-1791385067f7");
        smsPlusModel.setMessage(message);
        smsPlusModel.setSId("SUGUNAFOOD");
        smsPlusModel.setCsmsId(mobileNumber);
        smsPlusModel.setMsisdn(mobileNumber);
        String url = "https://smsplus.sslwireless.com/api/v3/send-sms";
        String response = restTemplate.postForObject(url,smsPlusModel,String.class);
        System.out.println("SMS Response =>"+response);
        return null;
    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS_2(String mobileNumber, String message) {
        return null;
    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS_BD(String mobileNumber, String message) {
        return null;
    }
}
