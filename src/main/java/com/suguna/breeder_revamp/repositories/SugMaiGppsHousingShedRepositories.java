package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.dto.SugMaiGppsHousingShedID;
import com.suguna.breeder_revamp.model.SugMaiGppsHousingShed;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugMaiGppsHousingShedRepositories extends JpaRepository<SugMaiGppsHousingShed,SugMaiGppsHousingShedID> {
    @Query(value = """
            UPDATE SUG.SUG_GPPS_TRANSACTIONS a
               SET a.SHED_ALLOCATED = 'Y'
             WHERE a.PLANT_CODE = :branch_code
               AND a.LOT_NUMBER = :lotNumber
               AND NVL(a.SHED_ALLOCATED, 'N') = 'N'
               AND (
                    TO_CHAR(a.BATCH_ID) = :reportNum
                    OR EXISTS (
                        SELECT 1
                          FROM GME_BATCH_HEADER h
                         WHERE TO_CHAR(h.BATCH_ID) = :reportNum
                           AND h.ATTRIBUTE1 = a.LOT_NUMBER
                    )
               )
            """, nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    int updateentry(@Param("lotNumber") String lotNumber, @Param("reportNum") String reportNum, @Param("branch_code") String branch_code);
}
