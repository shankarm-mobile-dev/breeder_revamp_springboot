package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.components.FileStorageService;

import com.suguna.breeder_revamp.dto.ShedReadyDto;
import com.suguna.breeder_revamp.dto.ShedReadyLineDto;
import com.suguna.breeder_revamp.dto.ShedReadyResponseDto;
import com.suguna.breeder_revamp.entities.FeedbackMaster;
import com.suguna.breeder_revamp.entities.ShedReadyHeader;
import com.suguna.breeder_revamp.entities.ShedReadyLines;
import com.suguna.breeder_revamp.enums.FileStorageCategory;
import com.suguna.breeder_revamp.repositories.FeedMstRepositories;

import com.suguna.breeder_revamp.repositories.ShedReadyHeaderRepositories;
import com.suguna.breeder_revamp.repositories.ShedReadyLineRepositories;
import com.suguna.breeder_revamp.response.ApiResponseList;
import com.suguna.breeder_revamp.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShedReadyServiceImpl  implements ShedReadyService{
    @Autowired
    FeedMstRepositories feedMstRepositories;
    @Autowired
    ShedReadyHeaderRepositories shedReadyHeaderRepositories;
    @Autowired
    ShedReadyLineRepositories shedReadyLineRepositories;
    @Autowired
    FileStorageService fileStorageService;

    @Override
    public ResponseEntity<ApiResponseList<ShedReadyDto>> getShedReadyQuestion(String farmCode, String feedbackRef, String language) {
    // Query the repository (DB/view) using the provided filters
    List<FeedbackMaster> feedbackMasterList = feedMstRepositories.findByFeedbackRefAndLanguage(feedbackRef, language);

    // Current behavior: 404 if nothing found
        if (feedbackMasterList.isEmpty()) {
        return Response.buildSingleResponseList("Failure", HttpStatus.NOT_FOUND, "Not Found", null);
    }

    // Map entities -> DTOs
    List<ShedReadyDto> feedbackMstDtoList = new ArrayList<>();

    ShedReadyHeader shedReadyHeaders = shedReadyHeaderRepositories
            .findByFarmCodeAndFarmerStatus(farmCode, "NO");

        if (shedReadyHeaders == null) {
        shedReadyHeaders = shedReadyHeaderRepositories.findByFarmCodeAndFarmerStatusAndManagerStatus(farmCode, "YES", "NO");
        if(shedReadyHeaders == null)
        {
            shedReadyHeaders = shedReadyHeaderRepositories.findByFarmCodeAndFarmerStatusAndManagerStatus(farmCode, "YES", "YES");
        }
    }

        for (FeedbackMaster feedbackMaster : feedbackMasterList) {
        boolean isSubmitted = false;
        Date submittedDate = null;
        String status = "";
        String remarks = "";
        if (shedReadyHeaders != null) {
            ShedReadyLines shedReadyLines = shedReadyLineRepositories.findByTransIdAndActivityId(shedReadyHeaders.getTransId(), feedbackMaster.getQuestionId());
            if (shedReadyLines != null) {
                isSubmitted = true;
                submittedDate = shedReadyLines.getCreationDate();
                status = (shedReadyLines.getManagerStatus().equals("NO") ? "PENDING" : (shedReadyLines.getManagerStatus().equals("A") ? "APPROVED" : "REJECTED"));
                remarks = shedReadyLines.getManagerComments();
            }
        }

        feedbackMstDtoList.add(
                ShedReadyDto.builder()
                        .ledgerId(feedbackMaster.getLedgerId())
                        .categoryId(feedbackMaster.getCategoryId())
                        .category(feedbackMaster.getCategory())
                        .userType(feedbackMaster.getUserType())
                        .feedbackRef(feedbackMaster.getFeedbackRef())
                        .question(feedbackMaster.getQuestion())
                        .questionId(feedbackMaster.getQuestionId())
                        .questionSeq(feedbackMaster.getQuestionSeq())
                        .submitted(isSubmitted)
                        .submittedDate(submittedDate)
                        .status(status)
                        .remarks(remarks)
                        .build()
        );
    }

    String message = "Found";
        if (shedReadyHeaders != null && shedReadyHeaders.getFarmerStatus().equals("YES") && shedReadyHeaders.getManagerStatus().equals("NO"))
    message = "Waiting for Manager Confirmation";
    // Success response with list
        return Response.buildSingleResponseList("Success", HttpStatus.OK, message, feedbackMstDtoList);
}

    @Transactional
    public ResponseEntity<ShedReadyResponseDto<Object>> saveShedReadyLine(ShedReadyLineDto shedReadyLineDto, MultipartFile imageFile) {

        List<FeedbackMaster> feedbackMasterList = feedMstRepositories.findByFeedbackRefAndLanguage("BREEDER_SHED_ACTIVITY", "en");
        //List<FeedbackMaster> feedbackMaster = feedbackMstRepositories.findByFeedbackRefAndLanguageAndQuestionId("FARMER_SHED_ACTIVITY", "en", Integer.parseInt(String.valueOf(shedReadyLineDto.getActivityId())));
        if (feedbackMasterList.isEmpty())
            return Response.buildSingleResponse("Failure", HttpStatus.NOT_FOUND, "Question Not Found", null);

        boolean isQuestionAvailable = false;
        for (FeedbackMaster data : feedbackMasterList) {
            if (data.getQuestionId() == shedReadyLineDto.getActivityId()) {
                isQuestionAvailable = true;
                break;
            }
        }

        if (!isQuestionAvailable) {
            return Response.buildSingleResponse("Failure", HttpStatus.NOT_FOUND, "Question Not Found", null);
        }

        // Check if header exists (your business logic seems to check YES, but your comment says NO)
        // Adjusted based on your code (find by YES)
        ShedReadyHeader shedReadyHeaders = shedReadyHeaderRepositories
                .findByFarmCodeAndFarmerStatus(shedReadyLineDto.getFarmCode(), "NO");

        Long transId;
        if (shedReadyHeaders != null) {
            transId = shedReadyHeaders.getTransId();
        } else {
            // Create new header
            ShedReadyHeader shedHeaders = ShedReadyHeader.builder()
                    .orgId(shedReadyLineDto.getOrgId())
                    .farmCode(shedReadyLineDto.getFarmCode())
                    .farmerStatus("NO")
                    .postedFlag("N")
                    .managerStatus("NO")
                    .creationDate(new Date())
                    .build();
            ShedReadyHeader optHeaders = shedReadyHeaderRepositories.save(shedHeaders);
            transId = optHeaders.getTransId();
        }

        // Save image if present
        String imageUrl = null;
        String imageOriginalName = null;
        String imageContentType = null;
        Long imageSizeBytes = null;

        String voiceUrl = null;

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                imageUrl = fileStorageService.saveImage(imageFile, shedReadyLineDto.getFarmCode(), shedReadyLineDto.getActivityId(), FileStorageCategory.SHED_READY);
                imageOriginalName = imageFile.getOriginalFilename();
                imageContentType = imageFile.getContentType();
                imageSizeBytes = imageFile.getSize();
            }




        } catch (IOException | IllegalArgumentException ex) {
            // Decide whether to fail the whole transaction or continue without image
            // Here we fail to keep data consistent
            return Response.buildSingleResponse("Failed", HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        }
        ShedReadyLines shedReadyLines = shedReadyLineRepositories.findByTransIdAndActivityId(transId, shedReadyLineDto.getActivityId());
        if (shedReadyLines == null) {
            shedReadyLines = ShedReadyLines.builder()
                    .transId(transId)
                    .activityId(shedReadyLineDto.getActivityId())
                    .farmerStatus("YES")
                    .managerStatus("NO")
                    .creationDate(new Date())
                    .updationDate(new Date())
                    .imagePath(imageUrl)
                    .audioPath(voiceUrl)
                    .remarks(shedReadyLineDto.getRemarks())
                    .build();
        } else {
            shedReadyLines.setUpdationDate(new Date());
            shedReadyLines.setFarmerStatus("YES");
            shedReadyLines.setManagerStatus("NO");
            shedReadyLines.setRemarks(shedReadyLineDto.getRemarks());
        }
        ShedReadyLines savedLine = shedReadyLineRepositories.save(shedReadyLines);

        // Prepare response DTO
        ShedReadyLineDto responseDto = new ShedReadyLineDto();
        responseDto.setOrgId(shedReadyLineDto.getOrgId());
        responseDto.setFarmCode(shedReadyLineDto.getFarmCode());
        responseDto.setActivityId(savedLine.getActivityId());
        responseDto.setImagePath(savedLine.getImagePath());

        List<ShedReadyLines> lines = shedReadyLineRepositories.findByTransId(transId);
        if (lines.size() == feedbackMasterList.size()) {
            Optional<ShedReadyHeader> headers = shedReadyHeaderRepositories.findById(transId);
            if (headers.isPresent()) {
                ShedReadyHeader shedReadyHeaders1 = headers.get();
                shedReadyHeaders1.setFarmerStatus("YES");
                shedReadyHeaderRepositories.save(shedReadyHeaders1);
            }
        }

        return Response.buildSingleResponse("Success", HttpStatus.OK, "Saved", responseDto);

    }

    @Override
    public ResponseEntity<ApiResponseList<ShedReadyLineDto>> fetchShedReadyLine(String farmCode) {

        List<FeedbackMaster> feedbackMasterList = feedMstRepositories.findByFeedbackRefAndLanguage("BREEDER_SHED_ACTIVITY", "en");
        //List<FeedbackMaster> feedbackMaster = feedbackMstRepositories.findByFeedbackRefAndLanguageAndQuestionId("FARMER_SHED_ACTIVITY", "en", Integer.parseInt(String.valueOf(shedReadyLineDto.getActivityId())));
        if (feedbackMasterList.isEmpty())
            return Response.buildSingleResponseList("Failure", HttpStatus.NOT_FOUND, "Question Not Found", null);

        ShedReadyHeader shedReadyHeaders = shedReadyHeaderRepositories
                .findByFarmCodeAndFarmerStatus(farmCode, "NO");

        Long transId;
        if (shedReadyHeaders == null) {

            shedReadyHeaders = shedReadyHeaderRepositories.findByFarmCodeAndFarmerStatusAndManagerStatus(farmCode, "YES", "NO");
            if (shedReadyHeaders == null) {
                shedReadyHeaders = shedReadyHeaderRepositories.findByFarmCodeAndFarmerStatusAndManagerStatus(farmCode, "YES", "YES");
                if (shedReadyHeaders == null)
                    return Response.buildSingleResponseList("Failure", HttpStatus.NOT_FOUND, "No Entries Found", null);
            }
        }
        transId = shedReadyHeaders.getTransId();
        List<ShedReadyLines> lines = shedReadyLineRepositories.findByTransId(transId);
        List<ShedReadyLineDto> dtoList = new ArrayList<>();
        for (ShedReadyLines data : lines) {
            dtoList.add(
                    ShedReadyLineDto.builder()
                            .orgId(shedReadyHeaders.getOrgId())
                            .activityId(data.getActivityId())
                            .imagePath(data.getImagePath())
                            .transId(data.getTransId())
                            .transLineId(data.getTransLineId())
                            .farmerStatus(data.getFarmerStatus())
                            .managerStatus(data.getManagerStatus())
                            .farmCode(shedReadyHeaders.getFarmCode())
                            .build()
            );
        }

        return Response.buildSingleResponseList("Success", HttpStatus.OK, "Found", dtoList);
    }
}
