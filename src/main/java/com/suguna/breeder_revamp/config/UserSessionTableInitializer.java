package com.suguna.breeder_revamp.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserSessionTableInitializer {
    private static final Logger log = LoggerFactory.getLogger(UserSessionTableInitializer.class);

    private final JdbcTemplate jdbcTemplate;

    public UserSessionTableInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void ensureTable() {
        try {
            jdbcTemplate.execute(
                    "BEGIN "
                            + "EXECUTE IMMEDIATE 'CREATE TABLE SUG_MAI_BREEDER_USER_SESSION ("
                            + "USER_CODE VARCHAR2(50) PRIMARY KEY, "
                            + "SESSION_TOKEN VARCHAR2(64) NOT NULL, "
                            + "DEVICE_ID VARCHAR2(100), "
                            + "LOGIN_TIME DATE)'; "
                            + "EXCEPTION WHEN OTHERS THEN "
                            + "IF SQLCODE != -955 THEN RAISE; END IF; "
                            + "END;"
            );
        } catch (Exception e) {
            log.warn("Could not ensure SUG_MAI_BREEDER_USER_SESSION table: {}", e.getMessage());
        }
    }
}
