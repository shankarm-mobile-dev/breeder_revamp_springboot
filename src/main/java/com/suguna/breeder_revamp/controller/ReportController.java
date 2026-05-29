package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.service.LayingReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suguna.breeder_revamp.dto.LayingReportRequest;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    LayingReportServiceImpl layingReportService;

    @PostMapping(value = "/laying", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getLayingReport(@RequestBody LayingReportRequest request) {

        try {
            String html = layingReportService.getLayingReport(request);

            if (html == null || html.isBlank()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body("<h3>No Data Found</h3>");
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/html;charset=UTF-8"))
                    .body(html);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_HTML)
                    .body("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

}
