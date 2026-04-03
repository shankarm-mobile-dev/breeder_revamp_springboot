package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.ShedReadyDto;
import com.suguna.breeder_revamp.dto.ShedReadyLineDto;
import com.suguna.breeder_revamp.dto.ShedReadyResponseDto;
import com.suguna.breeder_revamp.response.ApiResponseList;
import com.suguna.breeder_revamp.service.ShedReadyService;
import com.suguna.breeder_revamp.service.ShedReadyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/shedready")
public class ShedReadyController {

    ShedReadyService shedReadyServices;

    @Autowired
    public ShedReadyController(ShedReadyServiceImpl shedReadyServices) {
        this.shedReadyServices = shedReadyServices;
    }

    @GetMapping("/question/{farmCode}/{feedbackRef}/{language}")
    public ResponseEntity<ApiResponseList<ShedReadyDto>> getFeedbackMaster(@PathVariable String farmCode, @PathVariable String feedbackRef, @PathVariable String language){
        return shedReadyServices.getShedReadyQuestion(farmCode,feedbackRef,language);
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ShedReadyResponseDto<Object>> saveShedReadyActivity(
            @RequestParam("BRANCH_ID") int orgId,
            @RequestParam("FARM_CODE") String farmCode,
            @RequestParam("ACTIVITY_ID") Long activityId,
            @RequestParam("REMARKS") String remarks,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        ShedReadyLineDto shedReadyLineDto = new ShedReadyLineDto();
        shedReadyLineDto.setActivityId(activityId);
        shedReadyLineDto.setOrgId(orgId);
        shedReadyLineDto.setFarmCode(farmCode);
        shedReadyLineDto.setRemarks(remarks);
        return shedReadyServices.saveShedReadyLine(shedReadyLineDto, imageFile);
    }

    @GetMapping("/{farm_code}")
    public ResponseEntity<ApiResponseList<ShedReadyLineDto>> getShedReadyLines(@PathVariable String farm_code)
    {
        return shedReadyServices.fetchShedReadyLine(farm_code);
    }
}
