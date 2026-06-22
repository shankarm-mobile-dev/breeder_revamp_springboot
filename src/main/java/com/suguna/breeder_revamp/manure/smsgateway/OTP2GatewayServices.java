/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 9/7/2024
 */

package com.suguna.breeder_revamp.manure.smsgateway;


import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
@Primary
public class OTP2GatewayServices implements SMSGateway {
    private RestTemplate restTemplate;

    @Autowired
    OTP2GatewayServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public APIResponse<?> sendSMS(String mobileNumber, String message) {
        APIResponse<?> responseDto = new APIResponse<>();

            String url = "https://otp2.aclgateway.com/OTP_ACL_Web/OtpRequestListener" +
                    "?enterpriseid=sugunaotp" +
                    "&subEnterpriseid=sugunaotp" +
                    "&pusheid=sugunaotp" +
                    "&pushepwd=suguna_7" +
                    "&contenttype=1" +
                    "&msisdn=" + mobileNumber +
                    "&msgtext=" + message +
                    "&sender=SUGUNA" +
                    "&language=EN";

            System.out.println("SMS URL: " + url);

            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.TEXT_HTML));

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Create RestTemplate with String message converter
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));

            // Make the request
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            // Log and return success
            String responseBody = response.getBody();
            System.out.println("OTP Response: " + responseBody);
            responseDto.setStatusCode(HttpStatus.OK.value());
            responseDto.setStatus("OTP Generated & Sent");
            responseDto.setMessage("OTP Generated & Sent");

        return responseDto;
    }

    /*@Override
    public APIResponse<?> sendSMS(String mobileNumber, String message) {
        try {
            String url = "https://otp2.aclgateway.com/OTP_ACL_Web/OtpRequestListener?enterpriseid=sugunaotp&subEnterpriseid=sugunaotp&pusheid=sugunaotp&pushepwd=suguna_7&contenttype=1&msisdn=" + mobileNumber + "&msgtext=" + message + "&sender=SUGUNA&language=EN";
            System.out.println("SMS URL " + url);
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("OTP Response " + response);

            APIResponse<?> responseDto = new APIResponse<>();
            responseDto.setStatusCode(HttpStatus.OK.value());
            responseDto.setStatus("OTP Generated & Sent");
            responseDto.setMessage("OTP Generated & Sent");
            return responseDto;
        }
        catch (Exception e)
        {
            System.out.println("Error in OTP "+e.getMessage());
            APIResponse<?> responseDto = new APIResponse<>();
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setStatus("OTP Generated & Sent");
            responseDto.setMessage("OTP Generated & Sent");
            return responseDto;
        }

    }*/

    @Override
    public APIResponse<?> sendSMS_2(String mobileNumber, String message) {
        return null;
    }

    @Override
    public APIResponse<?> sendSMS_BD(String mobileNumber, String message) {
        com.suguna.breeder_revamp.manure.smsgateway.model.SMSPlusModel smsPlusModel = new com.suguna.breeder_revamp.manure.smsgateway.model.SMSPlusModel();
        smsPlusModel.setApiToken("0d8114c6-5060-4f09-a1c0-1791385067f7");
        smsPlusModel.setMessage(message);
        smsPlusModel.setsId("SUGUNAFOOD");
        smsPlusModel.setCsmsId(mobileNumber);
        smsPlusModel.setMsisdn(mobileNumber);
        String url = "https://smsplus.sslwireless.com/api/v3/send-sms";
        String response = restTemplate.postForObject(url, smsPlusModel, String.class);
        System.out.println("SMS Response =>" + response);
        APIResponse<?> responseDto = new APIResponse<>();
        responseDto.setStatusCode(HttpStatus.OK.value());
        responseDto.setStatus("OTP Generated & Sent");
        responseDto.setMessage("OTP Generated & Sent");
        return responseDto;
    }
}
