/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 8/7/2024
 */
package com.suguna.breeder_revamp.manure.repositories;

import com.suguna.breeder_revamp.manure.models.OTPModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<OTPModel,Long> {
    @Query(nativeQuery = true,value = "select * from sug_mai_otp t where t.login_name=:userName and t.application=:application  and t.otp=:otp  and t.type=:type and sysdate between t.creation_date and t.expiry_date")
    OTPModel findRecentOtp(@Param("userName") String userName, @Param("application") String application, @Param("otp") String otp, @Param("type") String type);

    List<OTPModel> findByOrderRefNumberAndOtp(String orderRefNumber, String otp);

    @Query(nativeQuery = true,value = "SELECT * FROM SUG.SUG_MAI_OTP A WHERE A.APPLICATION = 'MANURE_SALES' AND A.JOB_ID = :order_ref_number ORDER BY SEQ DESC FETCH FIRST 1 ROW ONLY")
    OTPModel findByOrderRefNumber(@Param("order_ref_number") String order_ref_number);

}
