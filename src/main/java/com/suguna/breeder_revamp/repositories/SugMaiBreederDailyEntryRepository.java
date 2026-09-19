package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugMaiBreederDailyEntryModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugMaiBreederDailyEntryRepository extends JpaRepository<SugMaiBreederDailyEntryModel,Long> {

    @Query(value = "select sug_mai_breeder_daily_entry_s.nextval from dual", nativeQuery = true)
    Number getNextReportId();

    @Query(value = "update SUG_MAI_BREEDER_DAILY_ENTRY a set a.POSTED_FLAG=:status,a.REMARKS=:remarks,a.ENTRY_CREATION_DATE=sysdate where a.REPORT_ID = :trans_id and a.TXN_CATEGORY='RETURN' and a.POSTED_FLAG='P'", nativeQuery = true)
    @Modifying
    @Transactional
    int updateApprovalEntry(@Param("trans_id") String trans_id, @Param("status") String status, @Param("remarks") String remarks);
}
