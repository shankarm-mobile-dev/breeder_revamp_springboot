/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.manure.smsgateway;



import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.smsgateway.model.SMSPlusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class SMSPlusGatewayServices implements SMSGateway{

    private RestTemplate restTemplate;

    @Autowired
    SMSPlusGatewayServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public APIResponse<?> sendSMS(String mobileNumber, String message) {
        SMSPlusModel smsPlusModel = new SMSPlusModel();
        smsPlusModel.setApiToken("0d8114c6-5060-4f09-a1c0-1791385067f7");
        smsPlusModel.setMessage(message);
        smsPlusModel.setsId("SUGUNAFOOD");
        smsPlusModel.setCsmsId(mobileNumber);
        smsPlusModel.setMsisdn(mobileNumber);
        String url = "https://smsplus.sslwireless.com/api/v3/send-sms";
        String response = restTemplate.postForObject(url,smsPlusModel,String.class);
        System.out.println("SMS Response =>"+response);
        return null;
    }

    @Override
    public APIResponse<?> sendSMS_2(String mobileNumber, String message) {
        return null;
    }

    @Override
    public APIResponse<?> sendSMS_BD(String mobileNumber, String message) {
        return null;
    }
}
