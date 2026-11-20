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
            SELECT DISTINCT
                   a.GATE_IN_ID,
                   a.FROM_BRANCH_ID,
                   a.VEHICLE_NO,
                   a.DRIVER_NAME,
                   a.DRIVER_MOBILE_NO,
                   a.PURPOSE,
                   a.FROM_GATE_IN_DATE,
                   a.FROM_GATE_OUT_DATE,
                   a.FROM_CREATED_BY,
                   a.FROM_CREATED_DATE,
                   a.FROM_UPDATED_BY,
                   a.FROM_UPDATED_DATE,
                   a.TO_GATE_IN_DATE,
                   a.TO_GATE_OUT_DATE,
                   a.TO_BRANCH_ID,
                   a.TO_FARM_CREATED_BY,
                   a.TO_FARM_CREATED_DATE,
                   a.TO_FARM_UPDATED_BY,
                   a.TO_FARM_UPDATED_DATE,
                   a.SHIPMENT_NO,
                   a.ORDER_NO,
                   a.IN_STATUS
              FROM SUG.SUG_MAI_BREEDER_VEHICLE_GATEINOUT a
             WHERE a.TO_BRANCH_ID = :branchId
               AND a.FROM_GATE_OUT_DATE IS NOT NULL
               AND a.TO_GATE_IN_DATE IS NULL
               AND (
                     a.IN_STATUS IS NULL
                     OR TRIM(a.IN_STATUS) = ''
                   )
               AND EXISTS (
                     SELECT 1
                       FROM SUG.SUG_MAI_GPPS_TRANS_HDR b,
                            SUG.SUG_MAI_GPPS_TRANS_DTL c
                      WHERE a.GATE_IN_ID = b.PLAN_DTL_ID
                        AND b.TXN_HEADER_ID = c.TXN_HEADER_ID
                        AND (
                              (c.txn_type != 'BIRD' AND b.to_manager = 'Y')
                              OR (c.txn_type = 'BIRD' AND b.to_manager IS NULL)
                            )
                   )
             ORDER BY a.FROM_GATE_OUT_DATE DESC
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
