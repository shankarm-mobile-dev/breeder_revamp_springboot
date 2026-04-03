package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.entities.FeedbackMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedMstRepositories extends JpaRepository<FeedbackMaster,Integer> {
    @Query
    List<FeedbackMaster> findByFeedbackRefAndLanguage(String feedbackRef, String language);


}
