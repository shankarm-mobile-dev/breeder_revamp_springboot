/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.smsgateway;



import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.ResponseResultDto;
import com.suguna.breeder_revamp.smsgateway.model.SMSPlusModel;
import com.suguna.breeder_revamp.utils.RestTemplateClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Primary
public class OTP2GatewayService implements SMSGateway {

    @Autowired
    private RestTemplateClass restTemplate;

    @Autowired
    OTP2GatewayService(RestTemplateClass restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS(String mobileNumber, String message) {

        String messages = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String url = "https://otp2.aclgateway.com/OTP_ACL_Web/OtpRequestListener?enterpriseid=sugunaotp&subEnterpriseid=sugunaotp&pusheid=sugunaotp&pushepwd=suguna_7&contenttype=1&msisdn=" + mobileNumber + "&msgtext=" + messages + "&sender=SUGUNA&language=EN";
        System.out.println("SMS URL " + url);
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("OTP Response " + response);

        ResponseDto<ResponseResultDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(7);
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;

        /*String messages = URLEncoder.encode(message, StandardCharsets.UTF_8);
        Otp2SmsModel otp2SmsModel = new Otp2SmsModel();
        otp2SmsModel.setEnterpriseid("sugunaotp");
        otp2SmsModel.setSubEnterpriseid("sugunaotp");
        otp2SmsModel.setPusheid("sugunaotp");
        otp2SmsModel.setPushepwd("suguna_7");
        otp2SmsModel.setContenttype("1");
        otp2SmsModel.setMsisdn(mobileNumber);
        otp2SmsModel.setMsgtext(messages);
        otp2SmsModel.setSender("SUGUNA");
        otp2SmsModel.setLanguage("EN");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(otp2SmsModel);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        SMSResponse otp2Response = restTemplate.postForObject("https://otp2.aclgateway.com/OTP_ACL_Web/otpjsonlistener",otp2SmsModel, SMSResponse.class);
        System.out.println("Response "+otp2Response.getResponseId()+" - "+otp2Response.isStatus());
        return null;*/

    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS_2(String mobileNumber, String message) {
        return null;
    }

    @Override
    public ResponseDto<ResponseResultDto> sendSMS_BD(String mobileNumber, String message) {
        SMSPlusModel smsPlusModel = new SMSPlusModel();
        smsPlusModel.setApiToken("0d8114c6-5060-4f09-a1c0-1791385067f7");
        smsPlusModel.setMessage(message);
        smsPlusModel.setSId("SUGUNAFOOD");
        smsPlusModel.setCsmsId(mobileNumber);
        smsPlusModel.setMsisdn(mobileNumber);
        String url = "https://smsplus.sslwireless.com/api/v3/send-sms";
        String response = restTemplate.postForObject(url,smsPlusModel,String.class);
        System.out.println("SMS Response =>"+response);
        ResponseDto<ResponseResultDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(7);
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;
    }
}
