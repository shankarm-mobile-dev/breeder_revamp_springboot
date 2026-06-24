package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.SUGMAIGPPSTRANS_HDRDto;
import com.suguna.breeder_revamp.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
}
