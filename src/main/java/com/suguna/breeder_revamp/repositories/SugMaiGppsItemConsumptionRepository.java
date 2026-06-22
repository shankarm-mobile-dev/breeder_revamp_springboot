package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugMaiGppsItemConsumption;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugMaiGppsItemConsumptionRepository extends JpaRepository<SugMaiGppsItemConsumption,Long> {

}
