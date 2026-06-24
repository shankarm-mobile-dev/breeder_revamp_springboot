package com.suguna.breeder_revamp.repositories;


import com.suguna.breeder_revamp.model.SugCVBodyWeightDtlModels;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SugCVBodyWeightDtlRepository extends JpaRepository<SugCVBodyWeightDtlModels,Long> {
    @Query(value = "update SUG_MAI_GPPS_BODY_WT_DTL a set a.ALLOCATED_FLAG='Y' where a.branch_id = :branch_id and a.physical_shed_no = :shed_no and a.age = :age and a.grading_no=:grading_no", nativeQuery = true)
    @Modifying
    @Transactional
    int updateentry(@Param("branch_id") String branch_id, @Param("shed_no") String shed_no, @Param("age") String age, @Param("grading_no") String grading_no);
}
