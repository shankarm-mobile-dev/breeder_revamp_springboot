package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.*;
import com.suguna.breeder_revamp.response.ApiResponse;
import com.suguna.breeder_revamp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    LayingReportServiceImpl layingReportService;

    @Autowired
    BreederBsgReportServiceImpl breederBsgReportService;

    @Autowired
    BodyWeightReportServiceImpl bodyWeightReportService;

    @Autowired
    GrdReportServiceImpl grdReportService;

    @Autowired
    DailyFarmSummaryServiceImpl dailyFarmSummaryService;

    @Autowired
    BroodGrowRegisterServiceImpl broodGrowRegisterService;

    private ResponseEntity<ApiResponse<String>> buildResponse(String html, String message) {

        if (html == null || html.isBlank()) {
            html = "<h3>No Data Found</h3>";
        }

        ApiResponse<String> response = ApiResponse.<String>builder()
                .status("SUCCESS")
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(html)
                .build();

        return ResponseEntity.ok(response);
    }

    private ResponseEntity<ApiResponse<String>> buildError(Exception e) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status("FAILED")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    // 1. LAYING REPORT
    @PostMapping(value = "/laying", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String>> getLayingReport(
            @RequestBody LayingReportRequestDto request) {

        try {
            String html = layingReportService.getLayingReport(request);
            return buildResponse(html, "Laying report generated");

        } catch (Exception e) {
            return buildError(e);
        }
    }

    // 2. BSG REPORT
    @PostMapping(value = "/bsg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String>> getBreederBsgReport(
            @RequestBody BreederBsgRequestDto request) {

        try {
            String html = breederBsgReportService.getBreederBsgReport(request);
            return buildResponse(html, "BSG report generated");

        } catch (Exception e) {
            return buildError(e);
        }
    }

    // 3. BODY WEIGHT REPORT
    @PostMapping(value = "/bodyWeight", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String>> getBodyWeight(
            @RequestBody BodyWeightReportRequestDto request) {

        try {
            String html = bodyWeightReportService.getBodyWeight(request);
            return buildResponse(html, "Body Weight report generated");

        } catch (Exception e) {
            return buildError(e);
        }
    }

    // 4. GRD REPORT
    @PostMapping(value = "/grd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String>> getGrdReport(
            @RequestBody GrdReportRequestDto request) {

        try {
            String html = grdReportService.getGrdReport(request);
            return buildResponse(html, "GRD report generated");

        } catch (Exception e) {
            return buildError(e);
        }
    }

    // 5. DAILY SUMMARY REPORT (Brooding Growing and Laying)
    @PostMapping(value = "/dailySummary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String>> getDailySummary(
            @RequestBody DailyFarmSummaryRequestDto request) {

        try {
            String html = dailyFarmSummaryService.getDailyFarmSummary(request);
            return buildResponse(html, "Daily Farm Summary report generated");

        } catch (Exception e) {
            return buildError(e);
        }
    }

    // 6. Brooding & Growing Register Report
    @PostMapping(value = "/broodGrowRegister", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<String>> getBroodGrowRegister(
            @RequestBody BroodGrowRegisterRequestDto request) {

        try {
            String html = broodGrowRegisterService.getReport(request);
            return buildResponse(html, "Brooding & Growing Register report generated");

        } catch (Exception e) {
            return buildError(e);
        }
    }
}