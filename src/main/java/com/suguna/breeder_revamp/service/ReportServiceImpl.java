package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.ReportResultDto;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    EntityManager entityManager;

    @Override
    public ReportResultDto COOLROOMSTOCKS(String branch_ID) throws SQLException {
        ReportResultDto reportResultDto=new ReportResultDto();
        ReportResultDto.coolroomstock appinfo = new ReportResultDto.coolroomstock();
        ArrayList<ReportResultDto.coolroomstock> Result = new ArrayList<ReportResultDto.coolroomstock>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getcoolroomstocks");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,ReportResultDto.coolroomstock.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        reportResultDto.setCoolroommst(Result);
        return reportResultDto;
    }
}
