/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 7/3/2025
 */

package com.suguna.breeder_revamp.manure;


import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.dtos.OTPDto;
import com.suguna.breeder_revamp.manure.services.implementations.OtpServiceImpl;
import com.suguna.breeder_revamp.manure.services.interfaces.OtpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manure/otp")
public class OTPController {

    OtpServices otpServices;
    @Autowired
    OTPController(OtpServiceImpl otpServices)
    {
        this.otpServices = otpServices;
    }

    @GetMapping("/check")
    public ResponseEntity<APIResponse<?>> checkOTP(@RequestParam(name = "mobile_number", required = true, defaultValue = "") String mobile_number,
                                                   @RequestParam(name = "otp", required = true, defaultValue = "") String otp,
                                                   @RequestParam(name = "qty", required = true, defaultValue = "") String qty
                                                   ){
        return otpServices.checkOTP(mobile_number,qty,otp);

    }
    @PostMapping("/")
    public ResponseEntity<APIResponse<?>> sendOTP(OTPDto otpDto){
        return otpServices.sendOTP(otpDto);
    }

    @GetMapping("/verify")
    public ResponseEntity<APIResponse<?>> verifyOTP(@RequestParam(name = "order_ref_number", required = true, defaultValue = "") String order_ref_number,
                                                    @RequestParam(name = "otp", required = true, defaultValue = "") String otp){
        return otpServices.verifyOTP(order_ref_number,otp);
    }

}
