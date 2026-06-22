package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugMaiGppsItemAllocation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugMaiGppsItemAllocationRepositories extends JpaRepository<SugMaiGppsItemAllocation,Long> {
    @Query(value = "update SUG_MAI_GPPS_ITEM_ALLOCATION a set a.allocate_status='Y' where a.TRANS_ID = :trans_id ", nativeQuery = true)
    @Modifying
    @Transactional
    int updateentry(@Param("trans_id") String trans_id);
}
