package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.entities.ShedReadyHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ShedReadyHeaderRepositories  extends JpaRepository<ShedReadyHeader,Long> {



        @Query
        ShedReadyHeader findByFarmCodeAndFarmerStatus(String farmCode, String farmerStatus);

        @Query
        ShedReadyHeader findByFarmCode(String farmCode);

        @Query
        ShedReadyHeader findByFarmCodeAndFarmerStatusAndManagerStatus(String farmCode,String farmerStatus,String managerStatus);


}
