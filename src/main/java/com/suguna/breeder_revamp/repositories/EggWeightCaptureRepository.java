package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.dto.EggWeightCaptureDto;
import com.suguna.breeder_revamp.model.SugGppsEggUnboxingModels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EggWeightCaptureRepository extends JpaRepository<SugGppsEggUnboxingModels,Long> {
}
