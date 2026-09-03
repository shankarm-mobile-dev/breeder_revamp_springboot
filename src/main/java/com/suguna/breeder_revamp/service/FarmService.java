package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.*;
import com.suguna.breeder_revamp.model.BranchUser;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FarmService {
    ArrayList<BranchUser> getBranchUsers(BranchRequest branchRequest);

    ArrayList<BranchUser.SupervisorDetails> getSupervisorDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedDetails> getShedDetails(String branchId,String userType,String userCode);

    ArrayList<BranchUser.ShedLineDetails> getShedLineDetails(String branchID, String shedNo);

    ArrayList<BranchUser.DailyFlockEntryDetails> getDailyEntrySchedule(BranchRequest branchID);

    ArrayList<BranchUser.ObservationCategory> getObservationCategory(BranchRequest branchID);

    String saveObservationDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedWiseFeedBirdsDetails> getshedwise_feeddtls(BranchRequest branchRequest);

    ArrayList<BranchUser.ShedWiseBirdsDetails> getshedwise_birdsdtls(BranchRequest branchRequest);

    String saveFeedDetails(BranchRequest branchRequest, List<MultipartFile> imageFile);

    String saveMortalityDetails(BranchRequest branchRequest, List<MultipartFile> imageFile);

    BranchUser.EggDetails getegg_collectiondtls(BranchRequest branchRequest);

    String saveEggCollectionDetails(BranchRequest branchRequest);

    BranchUser.FeedAllocationDetails getFeedAllocationDetails(String branchID);

    String saveFeedAllocationDetails(BranchRequest branchRequest) ;

    BranchUser.CullDetails getCullsDetails(String branchID);

    String saveCullingDetails(BranchRequest branchRequest);

    BranchUser.DestroyDetails getDestroyDetails(String branchID);

    String saveDestroyDetails(BranchRequest branchRequest);

    BranchUser.MortalityPmlDetails getMortalityPmlDetails(BranchRequest branchRequest);

    BranchUser.ExcessShortageDetails getExcessShortageDetails(String branchID);

    String saveMortalityPmlDetails(BranchRequest branchRequest, List<MultipartFile> imageFile);

    String  saveExcessShortageDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.CullsReasonDetails> getWeekBirdReasonsDetails(String branchID);

    BranchUser.PlacementInfoDetails getPlacementInfo(String branchID);

    ArrayList<BranchUser.DashboardDetails> getDashboardInfo(String branchID, String branchCode, String flockNumber);

    String savePlacementInfoDetails(ArrayList<PlacementRequest> placementRequest);

    String saveMedicineScheduleDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.MedicineScheduleDetails> getMedicineScheduleDetails(String branchID,String flock);

    String saveFarmLogDetails(BranchRequest branchRequest);

    ArrayList<BranchUser.FarmLogPreviousDetails> getFarmLogPreviousDetails(String branchID, String flockID);


    ArrayList<BranchUser.SanitizationReasonDetails> getSanitizationReasonsDetails(String branchID);

    String saveWeekSeperationDetails(BranchRequest branchRequest);

    String saveSanitizationDetails(BranchRequest branchRequest);

    String saveEggWeightDetails(BranchRequest branchRequest);

    String saveCloseEntryDetails(BranchRequest branchRequest);

    String SugCVBodyWeight(ArrayList<SugCVBodyWeightDto> entry);

    ArrayList<BranchUser.BodyWeightDeviationDetails> getBodyWeightRange(String branchID);

    ArrayList<BranchUser.FlockWiseGradingDetails> getFlockWiseGradingDetails(String branchID,String shedNo,String age);
    String saveFlockGradeWiseDetails(ArrayList<PlacementRequest> placementRequest1);
    ArrayList<BranchUser.MedicineScheduleDetails> getDailyMedicineSchedule(String branchID,String shedNo,String date);
    String saveDailyMedicineVaccine(BranchRequest branchRequest, List<MultipartFile> imageFile);
    ArrayList<BranchUser.PlacementInfoShedDetails> getShedReadyshedinfo(String branchID);

    ArrayList<BranchUser.ShedDetailsReport>  getShedDetailsReport(String branchID);

    MasterResultDto getshedready_medicine(String branchId) throws SQLException;

    String saveEggWeightCapture(ArrayList<EggWeightCaptureDto> eggWeightCaptureDto);

    ArrayList<BranchUser.EggWeightCapturePerson> getEggUnboxingPersonDtls(String branchID);

    String saveEggQualityCapture(ArrayList<BranchRequest.SugEggQualityCaptureDetails> branchRequest);

    ArrayList<BranchUser.IfftApprovalHdrDetails>  getIfftApprovalHdrDetails(String branchID);

    String saveIfftApproval(BranchRequest.SugIfftApprovalDetails branchRequest);

    ArrayList<BranchUser.ReasonMaster> getReasonMasterDetails(String branchID);

    String saveOthersEntryDetails(BranchRequest branchRequest);

    ArrayList<BranchUser> getTransportBranch(BranchRequest branchRequest);

    ArrayList<BranchUser.FarmFlockVaccineDetails> getVaccineScheduleDetails(String branchID);
    public FarmResultDto FARMERSERVICECHARGES(String branch_ID )throws SQLException;

}
