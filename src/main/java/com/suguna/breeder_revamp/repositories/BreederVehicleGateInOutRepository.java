package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.BreederVehicleGateInOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreederVehicleGateInOutRepository extends JpaRepository<BreederVehicleGateInOut, Long> {

    List<BreederVehicleGateInOut> findByBranchIdOrderByGateInDateDesc(Long branchId);

    List<BreederVehicleGateInOut> findByBranchIdAndGateOutDateIsNullOrderByGateInDateDesc(Long branchId);
}
