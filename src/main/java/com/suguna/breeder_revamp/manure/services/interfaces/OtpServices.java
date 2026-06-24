/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 7/3/2025
 */

package com.suguna.breeder_revamp.manure.services.interfaces;

import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.dtos.OTPDto;
import org.springframework.http.ResponseEntity;

public interface OtpServices {
    ResponseEntity<APIResponse<?>> sendOTP(OTPDto otpDto);

    ResponseEntity<APIResponse<?>> verifyOTP(String orderRefNumber,String otp);

    ResponseEntity<APIResponse<?>> checkOTP(String mobileNumber,String qty, String otp);


}
