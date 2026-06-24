package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.ReportResultDto;

import java.sql.SQLException;

public interface ReportService {
    ReportResultDto COOLROOMSTOCKS(String branch_ID) throws SQLException;
}
