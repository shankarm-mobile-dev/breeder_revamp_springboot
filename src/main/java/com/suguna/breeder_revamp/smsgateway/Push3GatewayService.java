/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway;



import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.ResponseResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
//@Primary
public class Push3GatewayService implements SMSGateway {

    private RestTemplate restTemplate;

    @Autowired
    Push3GatewayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public ResponseDto<ResponseResultDto> sendSMS(String mobileNumber, String message) {

        String messages = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String url = "https://push3.maccesssmspush.com/servlet/com.aclwireless.pushconnectivity.listeners.TextListener?userId=sugunalt&pass=sugunalt06&appid=sugunalt&subappid=sugunalt&contenttype=1&to="+mobileNumber+"&from=SUGUNA&text="+messages+"&selfid=true&alert=1&dlrreq=true";
        System.out.println("SMS URL " + url);
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("OTP Response " + response);
        ResponseDto<ResponseResultDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(7);
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;
    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS_2(String mobileNumber, String message) {
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
        ResponseDto<ResponseResultDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(7);
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;
    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS_BD(String mobileNumber, String message) {
        return null;
    }
}
