/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 8/7/2024
 */
package com.suguna.breeder_revamp.manure.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_OTP", schema = "SUG")
public class OTPModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(sequenceName = "SUG_MAI_OTP_S", allocationSize = 1, name = "id_seq")
    Long SEQ;
    String MOBILE_NO;
    Date CREATION_DATE;
    Date EXPIRY_DATE;
    String APPLICATION;
    String TYPE;
    @Column(name = "OTP")
    String otp;
    String LOGIN_NAME;
    @Column(name = "JOB_ID")
    String orderRefNumber;

    public Long getSEQ() {
        return SEQ;
    }

    public void setSEQ(Long SEQ) {
        this.SEQ = SEQ;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public Date getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(Date CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public Date getEXPIRY_DATE() {
        return EXPIRY_DATE;
    }

    public void setEXPIRY_DATE(Date EXPIRY_DATE) {
        this.EXPIRY_DATE = EXPIRY_DATE;
    }

    public String getAPPLICATION() {
        return APPLICATION;
    }

    public void setAPPLICATION(String APPLICATION) {
        this.APPLICATION = APPLICATION;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }



    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOrderRefNumber() {
        return orderRefNumber;
    }

    public void setOrderRefNumber(String orderRefNumber) {
        this.orderRefNumber = orderRefNumber;
    }
}
