/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.manure.smsgateway;


import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
//@Primary
public class Push3GatewayServices implements SMSGateway {

    private RestTemplate restTemplate;

    @Autowired
    Push3GatewayServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public APIResponse<?> sendSMS(String mobileNumber, String message) {

        String messages = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String url = "https://push3.maccesssmspush.com/servlet/com.aclwireless.pushconnectivity.listeners.TextListener?userId=sugunalt&pass=sugunalt06&appid=sugunalt&subappid=sugunalt&contenttype=1&to="+mobileNumber+"&from=SUGUNA&text="+messages+"&selfid=true&alert=1&dlrreq=true";
        System.out.println("SMS URL " + url);
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("OTP Response " + response);
        APIResponse<?> responseDto = new APIResponse<>();
        responseDto.setStatusCode(HttpStatus.OK.value());
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;
    }

    @Override
    public APIResponse<?> sendSMS_2(String mobileNumber, String message) {
        String user="suguna"; //your username
        String password = "Suguna456";

        String senderid="SUGUNA";
        String messagetype="N";
        String DReports="Y";
        String messages = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String url = "https://push3.maccesssmspush.com/servlet/com.aclwireless.pushconnectivity.listeners.TextListener?User="+user+"&passwd="+password+"&mobilenumber="+mobileNumber+"&message="+messages+"&sid="+senderid;
        System.out.println("SMS URL " + url);
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("OTP Response " + response);
        APIResponse<?> responseDto = new APIResponse<>();
        responseDto.setStatusCode(HttpStatus.OK.value());
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;
    }

    @Override
    public APIResponse<?> sendSMS_BD(String mobileNumber, String message) {
        return null;
    }
}
