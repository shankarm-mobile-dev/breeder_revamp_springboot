package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.service.BodyWeightReportServiceImpl;
import com.suguna.breeder_revamp.service.GrdReportServiceImpl;
import com.suguna.breeder_revamp.service.LayingReportServiceImpl;
import com.suguna.breeder_revamp.service.BreederBsgReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suguna.breeder_revamp.dto.LayingReportRequestDto;
import com.suguna.breeder_revamp.dto.BreederBsgRequestDto;
import com.suguna.breeder_revamp.dto.BodyWeightReportRequestDto;
import com.suguna.breeder_revamp.dto.GrdReportRequestDto;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    LayingReportServiceImpl layingReportService;

    @Autowired
    BreederBsgReportServiceImpl BreederBsgReportService;

    @Autowired
    BodyWeightReportServiceImpl BodyWeightReportService;

    @Autowired
    GrdReportServiceImpl GrdReportService;

    @PostMapping(value = "/laying", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getLayingReport(@RequestBody LayingReportRequestDto request) {

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

    /**
     *  Digital Flock recorder - breeder Birds shifting Brooding and Growing Report
     * */
    @PostMapping(value = "/bsg", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getBreederBsgReport(@RequestBody BreederBsgRequestDto request) {

        try {
            String html = BreederBsgReportService.getBreederBsgReport(request);

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

    /**
     *   Body-weight Report
     * */
    @PostMapping(value = "/body-weight", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getBodyWeight(@RequestBody BodyWeightReportRequestDto request) {

        try {
            String html = BodyWeightReportService.getBodyWeight(request);

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

    /**
     *   100 GRD Details Report
     * */
    @PostMapping(value = "/grd", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getGrdReport(@RequestBody GrdReportRequestDto request) {

        try {
            String html = GrdReportService.getGrdReport(request);

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
