package com.suguna.breeder_revamp.model;

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
    String OTP;
    String LOGIN_NAME;
    String JOB_ID;
}
