package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.dto.SugMaiGppsConsumptionsID;
import com.suguna.breeder_revamp.model.SugMaiGppsConsumptions;
import jakarta.transaction.Transactional;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SugMaiGppsConsumptionsRepositories extends JpaRepository<SugMaiGppsConsumptions, Long> {

    @Query(value = "UPDATE SUG_MAI_GPPS_CONSUMPTIONS a SET a.qty = :qty WHERE  a.rowid=:rowid", nativeQuery = true)
    @Modifying
    @Transactional
    int updateentry(@Param("qty") String qty,@Param("rowid") String rowid);

    @Query(value = "UPDATE SUG_MAI_GPPS_CONSUMPTIONS a SET a.STATUS = 'D' WHERE  a.rowid=:rowid", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteentry(@Param("qty") String qty,@Param("rowid") String rowid);

    @Query(value = "UPDATE SUG_MAI_GPPS_CONSUMPTIONS a SET a.STATUS = 'Y' WHERE a.farm_code =:farm_code and  a.SHED_CODE=:SHED_CODE and TXN_TYPE='EGG COLLECTION' and trunc(a.CREATION_DATE) = trunc(sysdate) and nvl(a.ITEM_ID,0)=0 and nvl(a.STATUS,'N') = 'N' ", nativeQuery = true)
    @Modifying
    @Transactional
    int updatestatus(@Param("qty") String qty,@Param("SHED_CODE") String SHED_CODE,@Param("farm_code") String farm_code);

    @Query(value = "SELECT * FROM SUG_MAI_GPPS_CONSUMPTIONS a WHERE a.FLOCK_ID = :flockId AND a.SHED_CODE = :shedCode AND trunc(nvl(a.TXN_DATE, a.CREATION_DATE)) = trunc(:txnDate) AND nvl(a.TXN_TYPE, 'X') <> 'DAY_CLOSE'", nativeQuery = true)
    List<SugMaiGppsConsumptions> findDayEntriesByFlockAndShedAndTxnDate(@Param("flockId") String flockId,
                                                                        @Param("shedCode") String shedCode,
                                                                        @Param("txnDate") Date txnDate);
}
