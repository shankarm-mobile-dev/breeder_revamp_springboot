package com.suguna.breeder_revamp.controller;

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


        try {
            String html = layingReportService.getLayingReport(request);

            }


        } catch (Exception e) {

        }
    }

}