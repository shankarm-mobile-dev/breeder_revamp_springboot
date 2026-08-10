package com.suguna.breeder_revamp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suguna.breeder_revamp.dto.*;
import com.suguna.breeder_revamp.service.FarmService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/farms/")
public class FarmController {

    @Autowired
    FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping("/getBranchUsers")
    public ResponseDto getBranchUsers(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getBranchUsers(branchRequest));
        return responseDto;
    }

    @PostMapping("/getSupervisorDetails")
    public ResponseDto getSupervisorDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getSupervisorDetails(branchRequest));
        return responseDto;
    }

    @PostMapping("/getShedDetails")
    public ResponseDto getShedDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getShedDetails(branchRequest.getBranchID(),branchRequest.getUserType(),branchRequest.getUserCode()));
        return responseDto;
    }

    @PostMapping("/getShedLineDetails")
    public ResponseDto getShedLineDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getShedLineDetails(branchRequest.getBranchID(),branchRequest.getShedNo()));
        return responseDto;
    }

    @PostMapping("/getDailyEntrySchedule")
        public ResponseDto getDailyEntrySchedule(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getDailyEntrySchedule(branchRequest));
        return responseDto;
    }

    @PostMapping("/getDailyEntryScheduleDetails")
    public ResponseDto getDailyEntryScheduleDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        if(branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
            responseDto.setResult(farmService.getObservationCategory(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("FEED")) {
            responseDto.setResult(farmService.getshedwise_feeddtls(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("MORTALITY")) {
            responseDto.setResult(farmService.getshedwise_birdsdtls(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("WEAK BIRD SEPARATION")) {
            responseDto.setResult(farmService.getshedwise_birdsdtls(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("EGG COLLECTION")) {
            responseDto.setResult(farmService.getegg_collectiondtls(branchRequest));
        }
            return responseDto;
    }

    @PostMapping(value = "/saveDailyEntryScheduleDetails",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto saveDailyEntryScheduleDetails(/*@RequestBody BranchRequest branchRequest*/@RequestParam("entryRequest") String branchRequestJson,@RequestParam(value = "image", required = false) List<MultipartFile> imageFile)
    {
        BranchRequest branchRequest = null;
        try {
            branchRequest = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(branchRequestJson, BranchRequest.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error in parsing " + e.getMessage());
            throw new RuntimeException(e);
        }
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response="";
        if(branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
            responseDto.setResult(farmService.saveObservationDetails(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("FEED")) {
            responseDto.setResult(farmService.saveFeedDetails(branchRequest,imageFile));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("MORTALITY")) {
            responseDto.setResult(farmService.saveMortalityDetails(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("EGG COLLECTION")) {
            responseDto.setResult(farmService.saveEggCollectionDetails(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("WEAK BIRD SEPARATION")) {
            responseDto.setResult(farmService.saveWeekSeperationDetails(branchRequest));
        }
        else if(branchRequest.getActivityName().equalsIgnoreCase("MEDICINE/VACCINE")) {
            responseDto.setResult(farmService.saveDailyMedicineVaccine(branchRequest,imageFile));
        }
        responseDto.setResult(farmService.getDailyEntrySchedule(branchRequest));
        return responseDto;
    }

    @PostMapping("/getFeedAllocationDetails")
    public ResponseDto getFeedAllocationDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getFeedAllocationDetails(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/saveFeedAllocationDetails")
    public ResponseDto saveFeedAllocationDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
       // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
            responseDto.setResult(farmService.saveFeedAllocationDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/getCullsDetails")
    public ResponseDto getCullsDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getCullsDetails(branchRequest.getBranchID()));
        return responseDto;
    }

    @PostMapping("/saveCullingDetails")
    public ResponseDto saveCullingDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveCullingDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/getDestroyDetails")
    public ResponseDto getDestroyDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getDestroyDetails(branchRequest.getBranchID()));
        return responseDto;
    }

    @PostMapping("/saveDestroyDetails")
    public ResponseDto saveDestroyDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveDestroyDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/getMortalityPmlDetails")
    public ResponseDto getMortalityPmlDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getMortalityPmlDetails(branchRequest));
        return responseDto;
    }
    @PostMapping(value = "/saveMortalityPmlDetails",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto saveMortalityPmlDetails(/*@RequestBody BranchRequest branchRequest*/@RequestParam("entryRequest") String branchRequestJson,@RequestParam(value = "image", required = false) List<MultipartFile> imageFile) {
        BranchRequest branchRequest = null;
        try {
            branchRequest = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(branchRequestJson, BranchRequest.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error in parsing " + e.getMessage());
            throw new RuntimeException(e);
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveMortalityPmlDetails(branchRequest,imageFile));
        //}
        return responseDto;
    }
    @PostMapping("/getExcessShortageDetails")
    public ResponseDto getExcessShortageDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getExcessShortageDetails(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/saveExcessShortageDetails")
    public ResponseDto saveExcessShortageDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveExcessShortageDetails(branchRequest));
        //}
        return responseDto;
    }
    @PostMapping("/getWeekBirdReasonsDetails")
    public ResponseDto getWeekBirdReasonsDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getWeekBirdReasonsDetails(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/getPlacementInfo")
    public ResponseDto getPlacementInfo(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getPlacementInfo(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/getDashboardInfo")
    public ResponseDto getDashboardInfo(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getDashboardInfo(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/savePlacementInfoDetails")
    public ResponseDto savePlacementInfoDetails(@RequestBody ArrayList<PlacementRequest> placementRequest) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setMessage("Placement Successfully");
            responseDto.setStatusCode(200);
            responseDto.setStatus("Success");
            String response = "";
            // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
            responseDto.setResult(farmService.savePlacementInfoDetails(placementRequest));
            //}
        }
        catch (ExecutionException e) {
            responseDto.setMessage("Placement Save Error :"+e.getMessage());
            responseDto.setStatusCode(500);
            responseDto.setStatus("Success");
            responseDto.setResult("500");
        }
        catch (Exception e) {
            responseDto.setMessage("Placement Save Error :"+e.getMessage());
            responseDto.setStatusCode(500);
            responseDto.setStatus("Success");
            responseDto.setResult("500");

        }
        return responseDto;
    }

    @PostMapping("/saveMedicineScheduleDetails")
    public ResponseDto saveMedicineScheduleDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveMedicineScheduleDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/getMedicineScheduleDetails")
    public ResponseDto getMedicineScheduleDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getMedicineScheduleDetails(branchRequest.getBranchID(),branchRequest.getFlockID()));
        return responseDto;
    }

    @PostMapping("/saveFarmLogDetails")
    public ResponseDto saveFarmLogDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveFarmLogDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/getFarmLogPreviousDetails")
    public ResponseDto getFarmLogPreviousDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getFarmLogPreviousDetails(branchRequest.getBranchID(),branchRequest.getFlockID()));
        return responseDto;
    }

    @PostMapping("/getSanitizationReasonsDetails")
    public ResponseDto getSanitizationReasonsDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getSanitizationReasonsDetails(branchRequest.getBranchID()));
        return responseDto;
    }

    @PostMapping("/saveSanitizationDetails")
    public ResponseDto saveSanitizationDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveSanitizationDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/saveEggWeightDetails")
    public ResponseDto saveEggWeightDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveEggWeightDetails(branchRequest));
        //}
        return responseDto;
    }

    @PostMapping("/saveCloseEntryDetails")
    public ResponseDto saveCloseEntryDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveCloseEntryDetails(branchRequest));
        //}
        return responseDto;
    }
    @PostMapping("/saveCVBodyWeight")
    public ResponseDto saveCVBodyWeight(@RequestBody ArrayList<SugCVBodyWeightDto> branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.SugCVBodyWeight(branchRequest));
        //}
        return responseDto;
    }
    @PostMapping("/getBodyWeightRange")
    public ResponseDto getBodyWeightRange(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getBodyWeightRange(branchRequest.getBranchID()));
        return responseDto;
    }

    @PostMapping("/getFlockWiseGradingDetails")
    public ResponseDto getFlockWiseGradingDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getFlockWiseGradingDetails(branchRequest.getBranchID(),branchRequest.getShedNo(),branchRequest.getAge()));
        return responseDto;
    }

    @PostMapping("/saveFlockGradeWiseDetails")
    public ResponseDto saveFlockGradeWiseDetails(@RequestBody ArrayList<PlacementRequest> placementRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveFlockGradeWiseDetails(placementRequest));
        //}
        return responseDto;
    }

    @PostMapping("/getDailyMedicineSchedule")
    public ResponseDto getDailyMedicineSchedule(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getDailyMedicineSchedule(branchRequest.getBranchID(),branchRequest.getShedNo(),branchRequest.getTransDate()));
        return responseDto;
    }
    @PostMapping("/getShedReadyshedinfo")
    public ResponseDto getShedReadyshedinfo(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getShedReadyshedinfo(branchRequest.getBranchID()));
        return responseDto;
    }

    @PostMapping("/getShedDetailsReport")
    public ResponseDto getShedDetailsReport(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getShedDetailsReport(branchRequest.getBranchID()));
        return responseDto;
    }

    @GetMapping("/getshedready_medicine/{branchId}")
    public MasterResultDto getshedready_medicine(@PathVariable String branchId) throws Exception {
        return farmService.getshedready_medicine(branchId);
    }

    @PostMapping("/saveEggWeightCapture")
    public ResponseDto saveEggWeightCapture(@RequestBody ArrayList<EggWeightCaptureDto> eggWeightCaptureDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveEggWeightCapture(eggWeightCaptureDto));
        //}
        return responseDto;
    }

    @PostMapping("/getEggUnboxingPersonDtls")
    public ResponseDto getEggUnboxingPersonDtls(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getEggUnboxingPersonDtls(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/saveEggQualityCapture")
    public ResponseDto saveEggQualityCapture(@RequestBody ArrayList<BranchRequest.SugEggQualityCaptureDetails> branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveEggQualityCapture(branchRequest));
        //}
        return responseDto;
    }
    @PostMapping("/getIfftApprovalHdrDetails")
    public ResponseDto getIfftApprovalHdrDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getIfftApprovalHdrDetails(branchRequest.getUserCode()));
        return responseDto;
    }

    @PostMapping("/saveIfftApproval")
    public ResponseDto saveIfftApproval(@RequestBody BranchRequest.SugIfftApprovalDetails branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.saveIfftApproval(branchRequest));
        return responseDto;
    }

    @PostMapping("/getReasonMasterDetails")
    public ResponseDto getReasonMasterDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(farmService.getReasonMasterDetails(branchRequest.getBranchID()));
        return responseDto;
    }
    @PostMapping("/saveOthersEntryDetails")
    public ResponseDto saveOthersEntryDetails(@RequestBody BranchRequest branchRequest) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(farmService.saveOthersEntryDetails(branchRequest));
        //}
        return responseDto;
    }
}
