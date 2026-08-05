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


        @Modifying
        @Transactional
        @Query(value = """
        UPDATE SUG_EGG_VEHICLE_PLAN_DTL
        SET ACTUAL_DEPARTURE_DATE = :departureDate,
            ACTUAL_DEPARTURE_IMAGE = :departureImage
        WHERE PLAN_DTL_ID = :planDtlId
        """, nativeQuery = true)
        int updateActualDeparture(
                @Param("planDtlId") Long planDtlId,
                @Param("departureDate") Date departureDate,
                @Param("departureImage") String departureImage);


        @Modifying
        @Transactional
        @Query(value = """
        UPDATE SUG_EGG_COLL_PLAN_LINES
        SET TRANSFER_ENTRY_FLAG='A'
        WHERE TRANS_LINE_ID IN (
            SELECT d.TRANS_LINE_ID
            FROM SUG_EGG_VEHICLE_PLAN_HDR a,
                 SUG_EGG_VEHICLE_PLAN_DTL b,
                 SUG_EGG_COLL_PLAN_HDR c,
                 SUG_EGG_COLL_PLAN_LINES d,
                 SUG_EGG_COLL_PLAN_LINE_DTL e
            WHERE a.TRANS_ID = b.TRANS_ID
              AND b.TRANS_ID = c.TRANS_ID
              AND b.TRANS_LINE_ID = d.TRANS_LINE_ID
              AND e.TRANS_DET_LINE_ID = b.TRANS_DTL_ID
              AND b.ACTUAL_DEPARTURE_DATE IS NOT NULL
              AND d.TRANSFER_ENTRY_FLAG = 'P'
              AND b.PLAN_DTL_ID = :planDtlId
        )
        AND EXISTS (
            SELECT 1
            FROM SUG_EGG_VEHICLE_PLAN_DTL t
            WHERE t.ACTUAL_DEPARTURE_DATE IS NULL
              AND t.TRANS_LINE_ID IN (
                    SELECT d.TRANS_LINE_ID
                    FROM SUG_EGG_VEHICLE_PLAN_HDR a,
                         SUG_EGG_VEHICLE_PLAN_DTL b,
                         SUG_EGG_COLL_PLAN_HDR c,
                         SUG_EGG_COLL_PLAN_LINES d,
                         SUG_EGG_COLL_PLAN_LINE_DTL e
                    WHERE a.TRANS_ID = b.TRANS_ID
                      AND b.TRANS_ID = c.TRANS_ID
                      AND b.TRANS_LINE_ID = d.TRANS_LINE_ID
                      AND e.TRANS_DET_LINE_ID = b.TRANS_DTL_ID
                      AND b.PLAN_DTL_ID = :planDtlId
              )
        )
        """, nativeQuery = true)
        int updateTransferEntryFlag(@Param("planDtlId") Long planDtlId);

    }
