package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.entities.ShedReadyHeader;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ShedReadyHeaderRepositories  extends JpaRepository<ShedReadyHeader,Long> {



        @Query
        ShedReadyHeader findByFarmCodeAndFarmerStatusAndShedCode(String farmCode, String farmerStatus, String shedCode);

        @Query
        ShedReadyHeader findByFarmCode(String farmCode);

        @Query
        ShedReadyHeader findByFarmCodeAndFarmerStatusAndManagerStatusAndShedCode(String farmCode,String farmerStatus,String managerStatus, String shedCode);

        @Query(value = "update Sug_Mai_Gpps_Shed_Ready_Header a set a.FARMER_ACKNOWLEDGE='YES' where a.SHED_CODE = :shed_no and a.FARM_CODE = :farm_code and a.FARMER_STATUS = 'YES'", nativeQuery = true)
        @Modifying
        @Transactional
        int updateFarmerStatus( @Param("shed_no") String shed_no, @Param("farm_code") String farm_code);
}
