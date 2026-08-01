package com.suguna.breeder_revamp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suguna.breeder_revamp.dto.*;
import com.suguna.breeder_revamp.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transfer/")
public class TransferController {

    @Autowired
    TransferService transferService;
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/getTransferPlace")
    public ResponseDto getTransferPlace(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getTransferPlace(branchRequest));
        return responseDto;
    }

    @PostMapping("/getEggItemMaster")
    public ResponseDto getEggItemMaster(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getEggItemMaster(branchRequest));
        return responseDto;
    }

    @PostMapping("/getFeedItemMaster")
    public ResponseDto getFeedItemMaster(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getFeedItemMaster(branchRequest));
        return responseDto;
    }

    @PostMapping("/getMedicineVaccineMaster")
    public ResponseDto getMedicineVaccineMaster(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getMedicineVaccineMaster(branchRequest));
        return responseDto;
    }

    @PostMapping("/getTransferInHdr")
    public ResponseDto getTransferInHdr(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getTransferInHdr(branchRequest));
        return responseDto;
    }

    @PostMapping("/saveTransOut")
    public ResponseDto  saveTransOut(@RequestBody ArrayList<SUGMAIGPPSTRANS_HDRDto> entry) throws Exception{
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(transferService.saveTransOut(entry));
        //}
        return responseDto;
    }

    @PostMapping("/saveTransPlan")
    public ResponseDto  saveTransPlan(@RequestBody TransferPlanDto entry) throws Exception{
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(transferService.saveTransPlan(entry));
        //}
        return responseDto;
    }

    @PostMapping("/getTransferPlanPlace")
    public ResponseDto getTransferPlanPlace(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getTransferPlanPlace(branchRequest));
        return responseDto;
    }

    @PostMapping("/getAllBranch")
    public ResponseDto getAllBranch(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getAllBranch(branchRequest));
        return responseDto;
    }

    @PostMapping("/getEggGateInDetails")
    public ResponseDto getEggGateInDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getEggGateInDetails(branchRequest));
        return responseDto;
    }

    @PostMapping("/getEggGateOutDetails")
    public ResponseDto getEggGateOutDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getEggGateOutDetails(branchRequest));
        return responseDto;
    }

    @PostMapping("/getEggHatcheryPlanDetails")
    public ResponseDto getEggHatcheryPlanDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getEggHatcheryPlanDetails(branchRequest));
        return responseDto;
    }

    @PostMapping("/getPlanDetails")
    public ResponseDto getPlanDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getPlanDetails(branchRequest));
        return responseDto;
    }

    @PostMapping("/getEggStockDetails")
    public ResponseDto getEggStockDetails(@RequestBody BranchRequest branchRequest)
    {
        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage("");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        responseDto.setResult(transferService.getEggStockDetails(branchRequest));
        return responseDto;
    }

    @PostMapping(value = "/saveGateInDetails",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto saveGateInDetails(/*@RequestBody BranchRequest branchRequest*/@RequestParam("entryRequest") String branchRequestJson, @RequestParam(value = "image", required = false) List<MultipartFile> imageFile) {
        PlanRequest branchRequest = null;
        try {
            branchRequest = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(branchRequestJson, PlanRequest.class);
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
        responseDto.setResult(transferService.saveGateInDetails(branchRequest,imageFile));
        //}
        return responseDto;
    }

}
