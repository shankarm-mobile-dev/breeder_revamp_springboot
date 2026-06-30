package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.ReportDto;
import com.suguna.breeder_revamp.dto.ReportResultDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportService {
    ReportResultDto COOLROOMSTOCKS(String branch_ID) throws SQLException;

    ReportResultDto DAILYMONITORING(String branch_ID) throws SQLException;

    ReportResultDto GPPSCANDLINGREPORT(String branch_ID) throws SQLException;

    ReportResultDto GPPSHATCHINGREPORT(String branch_ID) throws SQLException;

    ReportResultDto GPPSHATCHINGREPORTAGEWISE(String branch_ID) throws SQLException;

    ReportResultDto EGGGRADINGREPORTS(String branch_ID) throws SQLException;

    ArrayList<ReportDto.feedStock> getFeedstock(String branchid)throws SQLException;

    ReportResultDto EGGUNBOXINGREPORT(String branchId, String fromMonth, String toMonth) throws SQLException;
}
