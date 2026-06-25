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

    @GetMapping("/question/{farmCode}/{feedbackRef}/{language}/{shedCode}")
    public ResponseEntity<ApiResponseList<ShedReadyDto>> getShedReadyQuestion(@PathVariable String farmCode, @PathVariable String feedbackRef, @PathVariable String language,@PathVariable String shedCode){
        return shedReadyServices.getShedReadyQuestion(farmCode,feedbackRef,language,shedCode);
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ShedReadyResponseDto<Object>> saveShedReadyActivity(
            @RequestParam("BRANCH_ID") int orgId,
            @RequestParam("FARM_CODE") String farmCode,
            @RequestParam("ACTIVITY_ID") Long activityId,
            @RequestParam("REMARKS") String remarks,
            @RequestParam(value = "image", required = false) MultipartFile imageFile,
            @RequestParam("SHED_CODE") String shedCode
    ) {
        ShedReadyLineDto shedReadyLineDto = new ShedReadyLineDto();
        shedReadyLineDto.setActivityId(activityId);
        shedReadyLineDto.setOrgId(orgId);
        shedReadyLineDto.setFarmCode(farmCode);
        shedReadyLineDto.setRemarks(remarks);
        shedReadyLineDto.setShedCode(shedCode);
        return shedReadyServices.saveShedReadyLine(shedReadyLineDto, imageFile);
    }

    @GetMapping("/{farm_code}/{shed_code}")
    public ResponseEntity<ApiResponseList<ShedReadyLineDto>> getShedReadyLines(@PathVariable String farm_code,@PathVariable String shed_code)
    {
        return shedReadyServices.fetchShedReadyLine(farm_code,shed_code);
    }
}
