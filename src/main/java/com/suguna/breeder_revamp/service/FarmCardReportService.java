package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.FarmCardReportRequestDto;
import com.suguna.breeder_revamp.dto.FarmCardReportRowDto;
import com.suguna.breeder_revamp.dto.FramCardTransactionDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FarmCardReportService {

    ArrayList<FarmCardReportRowDto> getFarmCardReport(FarmCardReportRequestDto request) throws SQLException;
    ArrayList<FramCardTransactionDetailsDto> getInOutDetails(FarmCardReportRequestDto request) throws SQLException;
}
