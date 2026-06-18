package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.dto.SugMaiGppsHousingShedID;
import com.suguna.breeder_revamp.model.SugMaiGppsHousingShed;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugMaiGppsHousingShedRepositories extends JpaRepository<SugMaiGppsHousingShed,SugMaiGppsHousingShedID> {
    @Query(value = "UPDATE Sug_Gpps_Transactions a SET a.SHED_ALLOCATED = 'Y' WHERE  a.REPORT_NUM=:reportNum and a.LOT_NUMBER=:lotNumber and a.PLANT_CODE=:branch_code", nativeQuery = true)
    @Modifying
    @Transactional
    int updateentry(@Param("lotNumber") String lotNumber, @Param("reportNum") String reportNum,@Param("branch_code") String branch_code);
}
