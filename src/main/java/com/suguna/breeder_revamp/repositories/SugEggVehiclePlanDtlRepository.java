package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugEggVehiclePlanDtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface SugEggVehiclePlanDtlRepository extends JpaRepository<SugEggVehiclePlanDtl,Long> {
    @Modifying
    @Transactional
    @Query(value = """
        UPDATE SUG_EGG_VEHICLE_PLAN_DTL
        SET ACTUAL_ARRIVAL_DATE = :arrivalDate,
            ACTUAL_ARRIVAL_IMAGE = :arrivalImage
        WHERE PLAN_DTL_ID = :planDtlId
        """, nativeQuery = true)
    int updateActualArrival(
            @Param("planDtlId") Long planDtlId,
            @Param("arrivalDate") Date arrivalDate,
            @Param("arrivalImage") String arrivalImage);

}
