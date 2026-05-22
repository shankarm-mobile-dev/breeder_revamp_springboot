package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.SugGppsObservationDetails;
import com.suguna.breeder_revamp.model.SugGppsObservationHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugGppsObservationHeaderRepositories extends JpaRepository<SugGppsObservationHeader,Long> {
}
