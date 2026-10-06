package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.FarmCardReportRequestDto;
import com.suguna.breeder_revamp.dto.FarmCardReportRowDto;
import com.suguna.breeder_revamp.dto.FramCardTransactionDetailsDto;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FarmCardReportServiceImpl implements FarmCardReportService {

    private static final String PKG_GET_FARM_CARD = "sug.SUG_MAI_GPPS_MOB_PKG.get_farm_card_rpt";

    private final EntityManager entityManager;

    @Override
    public ArrayList<FarmCardReportRowDto> getFarmCardReport(FarmCardReportRequestDto request) throws SQLException {
        ArrayList<FarmCardReportRowDto> rows = new ArrayList<>();
        if (request == null) {
            return rows;
        }

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.get_farm_card_rpt");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter(1, request.getFlockNo());
        storedProcedureQuery.setParameter(2, request.getShedNo());
        storedProcedureQuery.setParameter(3, request.getDate());
        storedProcedureQuery.execute();

        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);
        while (resultSet != null && resultSet.next()) {
            rows.add(ResultSetMapper.mapResultSetToObject(resultSet, FarmCardReportRowDto.class));
        }
        return rows;
    }
    public ArrayList<FramCardTransactionDetailsDto> getInOutDetails(FarmCardReportRequestDto request) throws SQLException {
        ArrayList<FramCardTransactionDetailsDto> rows = new ArrayList<>();
        if (request == null) {
            return rows;
        }
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getInOutFarmTransactions");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter(1, request.getBranchId());
        storedProcedureQuery.setParameter(2, request.getDate());
        storedProcedureQuery.execute();
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);
        while (resultSet != null && resultSet.next()) {
            rows.add(ResultSetMapper.mapResultSetToObject(resultSet, FramCardTransactionDetailsDto.class));
        }
        return rows;
    }

}
