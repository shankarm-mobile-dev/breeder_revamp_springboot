package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.EmpModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDataRepository extends JpaRepository<EmpModel,Long> {
}
