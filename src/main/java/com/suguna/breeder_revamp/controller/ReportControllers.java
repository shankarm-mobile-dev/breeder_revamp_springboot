package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.ReportDto;
import com.suguna.breeder_revamp.dto.ReportResultDto;
import com.suguna.breeder_revamp.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Tag(name = "Report APIs", description = "All reporting related endpoints")
@RestController
@RequestMapping("/api/report")
public class ReportControllers {
    @Autowired
    ReportService reportService;

    public ReportControllers(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Cool Room Stock Report")
    @GetMapping("/getcoolroom/{branch_ID}")
    public ReportResultDto COOLROOMSTOCKS(@PathVariable String branch_ID) throws Exception{
        return reportService.COOLROOMSTOCKS(branch_ID);
    }

    @Operation(summary = "Daily Monitoring Report")
    @GetMapping("/getDailyMonitoring/{branch_ID}")
    public ReportResultDto DAILYMONITORING(@PathVariable String branch_ID) throws Exception{
        return reportService.DAILYMONITORING(branch_ID);
    }

    @Operation(summary = "GPPS Candling Report")
    @GetMapping("/getGppsCandlingReport/{branch_ID}")
    public ReportResultDto GPPSCANDLINGREPORT(@PathVariable String branch_ID) throws Exception{
        return reportService.GPPSCANDLINGREPORT(branch_ID);
    }

    @Operation(summary = "GPPS Hatching Report")
    @GetMapping("/getGppsHatchingReport/{branch_ID}")
    public ReportResultDto GPPSHATCHINGREPORT(@PathVariable String branch_ID) throws Exception{
        return reportService.GPPSHATCHINGREPORT(branch_ID);
    }

    @Operation(summary = "GPPS Hatching Age Wise Report")
    @GetMapping("/getGppsHatchingReportAgewise/{branch_ID}")
    public ReportResultDto GPPSHATCHINGREPORTAGEWISE(@PathVariable String branch_ID) throws Exception{
        return reportService.GPPSHATCHINGREPORTAGEWISE(branch_ID);
    }

    @Operation(summary = "Get Egg Grading Report")
    @GetMapping("/getEggGradingReport/{branch_ID}")
    public ReportResultDto EGGGRADINGREPORTS(@PathVariable String branch_ID) throws Exception{
        return reportService.EGGGRADINGREPORTS(branch_ID);
    }

    @Operation(summary = "Get Feed Stock")
    @GetMapping("/feedstock/{branchid}")
    public ArrayList<ReportDto.feedStock> getFeedstock(@PathVariable String branchid) throws Exception{
        return reportService.getFeedstock(branchid);
    }

    @Operation(summary = "Egg Unboxing Report")
    @GetMapping("/eggUnboxing/{branchId}/{fromMonth}/{toMonth}")
    public ReportResultDto EGGUNBOXINGREPORT(@PathVariable String branchId, @PathVariable String fromMonth, @PathVariable String toMonth) throws Exception {
        return reportService.EGGUNBOXINGREPORT(branchId, fromMonth, toMonth);
    }

    @Operation(summary = "Get GPPS Performance")
    @GetMapping("/getgppsperformance/{branch_code}")
    public ReportDto getGppsperformance(@PathVariable String branch_code) throws Exception{
        return reportService.getGppsperformance(branch_code);
    }
}
