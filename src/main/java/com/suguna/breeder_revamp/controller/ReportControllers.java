package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.ReportResultDto;
import com.suguna.breeder_revamp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportControllers {
    @Autowired
    ReportService reportService;

    public ReportControllers(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/getcoolroom/{branch_ID}")
    public ReportResultDto COOLROOMSTOCKS(@PathVariable String branch_ID) throws Exception{
        return reportService.COOLROOMSTOCKS(branch_ID);
    }
}
