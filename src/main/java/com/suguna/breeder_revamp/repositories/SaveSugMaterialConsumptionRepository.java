package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SaveSugNaterialConsumptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveSugMaterialConsumptionRepository extends JpaRepository<SaveSugNaterialConsumptionModel,Long> {


}
