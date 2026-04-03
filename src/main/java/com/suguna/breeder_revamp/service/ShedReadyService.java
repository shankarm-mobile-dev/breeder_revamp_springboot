package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.ShedReadyDto;
import com.suguna.breeder_revamp.dto.ShedReadyLineDto;
import com.suguna.breeder_revamp.dto.ShedReadyResponseDto;
import com.suguna.breeder_revamp.response.ApiResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ShedReadyService {

    ResponseEntity<ApiResponseList<ShedReadyDto>> getShedReadyQuestion(String farmCode, String feedbackRef, String language);
    ResponseEntity<ShedReadyResponseDto<Object>> saveShedReadyLine(ShedReadyLineDto shedReadyLineDto, MultipartFile imageFile);
    ResponseEntity<ApiResponseList<ShedReadyLineDto>> fetchShedReadyLine(String farmCode);

}
