package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugMaiBreederVehicleGateinout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreederVehicleGateInOutRepository extends JpaRepository<SugMaiBreederVehicleGateinout, Long> {

    Optional<SugMaiBreederVehicleGateinout> findByGateInIdAndFromGateOutDateIsNull(Long gateInId);

    @Query(value = """
            SELECT *
              FROM SUG.SUG_MAI_BREEDER_VEHICLE_GATEINOUT
             WHERE TO_BRANCH_ID = :branchId
               AND FROM_GATE_OUT_DATE IS NOT NULL
               AND TO_GATE_IN_DATE IS NULL
               AND (IN_STATUS IS NULL OR TRIM(IN_STATUS) = '')
             ORDER BY FROM_GATE_OUT_DATE DESC
            """, nativeQuery = true)
    List<SugMaiBreederVehicleGateinout> findPendingReceivingFarmGateIn(@Param("branchId") Long branchId);

    @Query(value = """
            SELECT *
              FROM SUG.SUG_MAI_BREEDER_VEHICLE_GATEINOUT
             WHERE TO_BRANCH_ID = :branchId
               AND TO_GATE_IN_DATE IS NOT NULL
               AND TO_GATE_OUT_DATE IS NULL
               AND UPPER(TRIM(IN_STATUS)) = 'Y'
             ORDER BY TO_GATE_IN_DATE DESC
            """, nativeQuery = true)
    List<SugMaiBreederVehicleGateinout> findPendingToGateOut(@Param("branchId") Long branchId);

    List<SugMaiBreederVehicleGateinout> findByFromBranchIdOrderByFromGateInDateDesc(Long fromBranchId);

    List<SugMaiBreederVehicleGateinout> findByFromBranchIdAndFromGateOutDateIsNullOrderByFromGateInDateDesc(Long fromBranchId);

    List<SugMaiBreederVehicleGateinout> findByToBranchIdOrderByToGateInDateDesc(Long toBranchId);

    List<SugMaiBreederVehicleGateinout> findByToBranchIdAndToGateOutDateIsNullOrderByToGateInDateDesc(Long toBranchId);
}
