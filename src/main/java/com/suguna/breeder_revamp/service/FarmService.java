package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.model.BranchUser;

import java.util.ArrayList;

public interface FarmService {
    ArrayList<BranchUser> getBranchUsers(BranchRequest branchRequest);

    ArrayList<BranchUser.SupervisorDetails> getSupervisorDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedDetails> getShedDetails(String branchId);

    ArrayList<BranchUser.DailyFlockEntryDetails> getDailyEntrySchedule(String branchID);

    ArrayList<BranchUser.ObservationCategory> getObservationCategory(String branchID);

    String saveObservationDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedWiseFeedBirdsDetails> getshedwise_feeddtls(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedWiseBirdsDetails> getshedwise_birdsdtls(BranchRequest branchRequest);

    String saveFeedDetails(BranchRequest branchRequest);

    String saveMortalityDetails(BranchRequest branchRequest);

    BranchUser.EggDetails getegg_collectiondtls(BranchRequest branchRequest);

    String saveEggCollectionDetails(BranchRequest branchRequest);

    BranchUser.FeedAllocationDetails getFeedAllocationDetails(String branchID);

    String saveFeedAllocationDetails(BranchRequest branchRequest) ;

    BranchUser.CullDetails getCullsDetails(String branchID);

    String saveCullingDetails(BranchRequest branchRequest);

    BranchUser.DestroyDetails getDestroyDetails(String branchID);

    String saveDestroyDetails(BranchRequest branchRequest);

    BranchUser.MortalityPmlDetails getMortalityPmlDetails(String branchID);

    BranchUser.ExcessShortageDetails getExcessShortageDetails(String branchID);

    String saveMortalityPmlDetails(BranchRequest branchRequest);

    String saveExcessShortageDetails(BranchRequest branchRequest);

    BranchUser.PlacementInfoDetails getPlacementInfo(String branchID);

    ArrayList<BranchUser.DashboardDetails> getDashboardInfo(String branchID);
}
