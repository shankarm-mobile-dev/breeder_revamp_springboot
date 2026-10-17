package com.suguna.breeder_revamp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_BREEDER_USER_SESSION",schema = "SUG")
public class UserSession {
    @Id
    @Column(name = "USER_CODE", length = 50)
    private String userCode;

    @Column(name = "SESSION_TOKEN", length = 64, nullable = false)
    private String sessionToken;

    @Column(name = "DEVICE_ID", length = 100)
    private String deviceId;

    @Column(name = "LOGIN_TIME")
    private Date loginTime;
}
