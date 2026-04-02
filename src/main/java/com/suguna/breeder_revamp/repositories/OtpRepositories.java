package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.OTPModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OtpRepositories extends JpaRepository<OTPModel,Long> {
    @Query(nativeQuery = true,value = "select * from sug_mai_otp t where t.login_name=:userName and t.application=:application  and t.otp=:otp  and t.type=:type and sysdate between t.creation_date and t.expiry_date and rownum=1")
    OTPModel findRecentOtp(@Param("userName") String userName, @Param("application") String application, @Param("otp") String otp, @Param("type") String type);
}
