/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 7/3/2025
 */

package com.suguna.breeder_revamp.manure.services.implementations;

import com.suguna.breeder_revamp.manure.constants.Constants;
import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.dtos.OTPDto;
import com.suguna.breeder_revamp.manure.models.OTPModel;
import com.suguna.breeder_revamp.manure.models.Orders;
import com.suguna.breeder_revamp.manure.repositories.OrderRepository;
import com.suguna.breeder_revamp.manure.repositories.OtpRepository;
import com.suguna.breeder_revamp.manure.services.interfaces.OtpServices;
import com.suguna.breeder_revamp.manure.smsgateway.SMSGateway;
import com.suguna.breeder_revamp.manure.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OtpServiceImpl implements OtpServices {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    SMSGateway smsGateway;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//    OrderServiceImpl orderService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");


    public boolean isBeforeExpiry(String expiryDateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime expiryLocalDateTime = LocalDateTime.parse(expiryDateStr, formatter);
            ZonedDateTime expiryDate = expiryLocalDateTime.atZone(ZoneId.systemDefault());
            ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.systemDefault());

            System.out.println("Current Date: " + currentDate);
            System.out.println("Expiry Date: " + expiryDate);
            System.out.println("Status: " + currentDate.isBefore(expiryDate));

            return currentDate.isBefore(expiryDate);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return false;
        }
        /*try {
            LocalDateTime expiryDate = LocalDateTime.parse(expiryDateStr, formatter);
            LocalDateTime currentDate = LocalDateTime.now();
            System.out.println("Current Date: " + currentDate);
            System.out.println("Expiry Date: " + expiryDate);
            System.out.println("Status: "+currentDate.isBefore(expiryDate));
            return currentDate.isBefore(expiryDate);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return false;
        }*/
    }

    public APIResponse<?> otpResponse(OTPDto otpDto) {
        try {
            APIResponse<?> apiResponse = new APIResponse<>();
            String mobileNumber = otpDto.getMobileNumber();
            if (mobileNumber == null) {
                apiResponse.setMessage("No Mobile Number Found.-" + otpDto.getOrderRefNumber());
                apiResponse.setStatusCode(0);
                return apiResponse;
            }

            //String otp = Utils.generateOtp();
            OTPModel otpModel = new OTPModel();
            otpModel.setOtp(otpDto.getOtp());
            otpModel.setAPPLICATION(otpDto.getSource());
            otpModel.setCREATION_DATE(new Date());
            otpModel.setEXPIRY_DATE(Utils.addMinutsToDate(1440));
            otpModel.setTYPE("SALES"); // REGISTER,FORGOT
            otpModel.setLOGIN_NAME(otpDto.getUserName());
            otpModel.setMOBILE_NO(mobileNumber);
            otpModel.setOrderRefNumber(otpDto.getOrderRefNumber());
            OTPModel responseOtp = createNewOtp(otpModel);

            if (responseOtp.getSEQ() == null) {
                apiResponse.setMessage("OTP Generation is error due to mobile number might be invalid.-" + otpDto.getOrderRefNumber());
                apiResponse.setStatusCode(1);
                return apiResponse;
            }

            //String sms = "Dear Customer your registration OTP is " + otp + ". Do not share with anyone.-SUGUNA";
            //int ledgerId = customerService.getLedgerId(registrationDto.getUserName());
            String sms = otpDto.getMessage();
            smsGateway.sendSMS(mobileNumber, sms);
            apiResponse.setMessage("OTP is sent.-" + otpDto.getOrderRefNumber());
            apiResponse.setStatusCode(2);
            return apiResponse;
        } catch (Exception e) {
            APIResponse<?> apiResponse = new APIResponse<>();
            apiResponse.setMessage("OTP is error.-" + otpDto.getOrderRefNumber());
            apiResponse.setStatusCode(1);
            return apiResponse;
        }
    }



    @Override
    public ResponseEntity<APIResponse<?>> sendOTP(OTPDto otpDto) {
        APIResponse<?> apiResponse = new APIResponse<>();
        String mobileNumber = customerService.getCustomerMobileNumber(otpDto.getUserId(), otpDto.getCustAccSiteId());
        //System.out.println("Mobile Number" + mobileNumber);
        //mobileNumber = "9944033729";
        if (mobileNumber == null) {
            apiResponse.setMessage("Mobile Number not found");
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        String otp = Utils.generateOtp();
        OTPModel otpModel = new OTPModel();
        otpModel.setOtp(otp);
        otpModel.setAPPLICATION(otpDto.getSource());
        otpModel.setCREATION_DATE(new Date());
        otpModel.setEXPIRY_DATE(Utils.addMinutsToDate(30));
        otpModel.setTYPE("SALES"); // REGISTER,FORGOT
        otpModel.setLOGIN_NAME(otpDto.getUserName());
        otpModel.setMOBILE_NO(mobileNumber);
        otpModel.setOrderRefNumber(otpDto.getOrderRefNumber());
        OTPModel responseOtp = createNewOtp(otpModel);

        if (responseOtp.getSEQ() == null) {
            apiResponse.setMessage("OTP Generation is error due to mobile number might be invalid.-" + otpDto.getOrderRefNumber());
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setStatus(Constants.FAILURE);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        String sms = "Dear Customer your registration OTP is " + otp + ". Do not share with anyone.-SUGUNA";
        //int ledgerId = customerService.getLedgerId(registrationDto.getUserName());
        smsGateway.sendSMS(mobileNumber, sms);
        apiResponse.setMessage("OTP sent to customer registered mobile no. Kindly collect the OTP from customer and enter the same in order status and confirm the order.");
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setStatus(Constants.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<APIResponse<?>> verifyOTP(String orderRefNumber, String otp) {
        APIResponse<?> apiResponse = new APIResponse<>();
        List<OTPModel> otpModelList = otpRepository.findByOrderRefNumberAndOtp(orderRefNumber, otp);
        if(otpModelList.isEmpty())
        {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setMessage("Invalid OTP");
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        OTPModel otpModel = otpModelList.get(0);
        if (otpModel == null) {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setMessage("Invalid OTP");
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        System.out.println("Expiry Date "+otpModel.getEXPIRY_DATE());

        boolean isExpired = isBeforeExpiry((String.valueOf(otpModel.getEXPIRY_DATE())));

        if(!isExpired)
        {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setMessage("Password is Expired. Please click Resend OTP");
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }


        APIResponse<?> status = updateStatus(orderRefNumber,1);
        if(status.getStatus().equals(Constants.FAILURE))
        {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setMessage("Invalid OTP");
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setStatus(Constants.SUCCESS);
        apiResponse.setMessage("OTP Verified Successfully. Order creation & invoice is progress.");
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<?>> checkOTP(String mobileNumber, String qty, String otp) {
        try{
            String message = "Dear Customer, Manure Sales ";
            message += qty + " MT is booked in your account, Share this OTP ";
            message += otp + " to generate the invoice.-SUGUNA";
            smsGateway.sendSMS(mobileNumber,message);
            APIResponse<?> apiResponse = new APIResponse<>();
            apiResponse.setMessage(message);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setStatus(Constants.SUCCESS);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (Exception e)
        {
            APIResponse<?> apiResponse = new APIResponse<>();
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setStatus(Constants.FAILURE);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }

    }

    public APIResponse<?> updateStatus(String orderRefNumber, int status) {
        APIResponse<?> apiResponse = new APIResponse<>();
        Orders orders = orderRepository.findByOrderRefNumber(Long.valueOf(orderRefNumber));
        if(orders == null)
        {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("Invalid data");
            return apiResponse;
        }
        orders.setSTATUS(1);
        orderRepository.save(orders);
        apiResponse.setStatus(Constants.SUCCESS);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Status updated");
        return apiResponse;
    }

    public OTPModel fetchLastOTP(String orderRefNumber)
    {
        OTPModel otpModel = otpRepository.findByOrderRefNumber(orderRefNumber);
        if(otpModel == null)
        {
            return new OTPModel();
        }

        return otpModel;
    }


    /**
     * Create New OTP and Save to the OTPModel Entity
     *
     * @param otpModel
     * @return
     */
    private OTPModel createNewOtp(OTPModel otpModel) {
        return otpRepository.save(otpModel);
    }


}
