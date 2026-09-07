package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugMaiGppsTransPlanHdr;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugMaiGppsTransPlanHdrRepository extends JpaRepository<SugMaiGppsTransPlanHdr,Long> {
    @Query(value = "update SUG_MAI_GPPS_TRANS_PLAN_HDR a set a.status=:status where a.TXN_HEADER_ID = :trans_id ", nativeQuery = true)
    @Modifying
    @Transactional
    int updateentry(@Param("trans_id") String trans_id,@Param("status") String status);
}
