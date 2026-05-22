package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.entities.ShedReadyLines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShedReadyLineRepositories extends JpaRepository<ShedReadyLines,Long> {

    @Query
    List<ShedReadyLines> findByTransId(long transId);

    @Query
    ShedReadyLines findByTransIdAndActivityId(long transId, long activityId);
}
