package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        responseDto.setResult(farmService.getShedDetails(branchRequest.getBranchID()));
        return responseDto;
    }
}
