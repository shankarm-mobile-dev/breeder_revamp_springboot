package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.ReportDto;
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

    @Override
    public ReportResultDto DAILYMONITORING(String branch_ID) throws SQLException{
        ReportResultDto reportResultDto=new ReportResultDto();
        ReportResultDto.dailymonitoring appinfo = new ReportResultDto.dailymonitoring();
        ArrayList<ReportResultDto.dailymonitoring> Result = new ArrayList<ReportResultDto.dailymonitoring>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getdailymonitoring");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,ReportResultDto.dailymonitoring.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        reportResultDto.setDailymonitoringmst(Result);
        return reportResultDto;
    }

    public ReportResultDto GPPSCANDLINGREPORT(String branch_ID) throws SQLException{
        ReportResultDto reportResultDto=new ReportResultDto();
        ReportResultDto.gppscandlingreport appinfo = new ReportResultDto.gppscandlingreport();
        ArrayList<ReportResultDto.gppscandlingreport> Result = new ArrayList<ReportResultDto.gppscandlingreport>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getgppscandlingreport");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,ReportResultDto.gppscandlingreport.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        reportResultDto.setCandlingreportmst(Result);
        return reportResultDto;
    }

    public ReportResultDto GPPSHATCHINGREPORT(String branch_ID) throws SQLException{
        ReportResultDto reportResultDto=new ReportResultDto();
        ReportResultDto.gppshatchingreport appinfo = new ReportResultDto.gppshatchingreport();
        ArrayList<ReportResultDto.gppshatchingreport> Result = new ArrayList<ReportResultDto.gppshatchingreport>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getgppshatchingreport");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,ReportResultDto.gppshatchingreport.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        reportResultDto.setHatchingreportmst(Result);
        return reportResultDto;
    }

    public ReportResultDto GPPSHATCHINGREPORTAGEWISE(String branch_ID) throws SQLException{
        ReportResultDto reportResultDto=new ReportResultDto();
        ReportResultDto.gppshatchingreport appinfo = new ReportResultDto.gppshatchingreport();
        ArrayList<ReportResultDto.gppshatchingreport> Result = new ArrayList<ReportResultDto.gppshatchingreport>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getgppshatchingreportagewise");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,ReportResultDto.gppshatchingreport.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        reportResultDto.setHatchingreportmst(Result);
        return reportResultDto;
    }

    public ReportResultDto EGGGRADINGREPORTS(String branch_ID) throws SQLException{
        ReportResultDto reportResultDto=new ReportResultDto();
        ReportResultDto.egggradingreport appinfo = new ReportResultDto.egggradingreport();
        ArrayList<ReportResultDto.egggradingreport> Result = new ArrayList<ReportResultDto.egggradingreport>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getegggradingreport");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,ReportResultDto.egggradingreport.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        reportResultDto.setEgggradingreportmst(Result);
        return reportResultDto;
    }

    public ArrayList<ReportDto.feedStock> getFeedstock(String branchid)throws SQLException{
        ReportDto.feedStock api = new ReportDto.feedStock();
        ArrayList<ReportDto.feedStock> Result = new ArrayList<ReportDto.feedStock>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("Sug_Mai_Gppsmgr_Pkg.getfeedstock");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1,branchid);
        System.out.println(branchid);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();


        while (resultSet.next()) {
            try {
                api = ResultSetMapper.mapResultSetToObject(resultSet, ReportDto.feedStock.class);
            } catch (Exception e) {
                throw new RuntimeException();
            }

            Result.add(api);
        }
        return Result;
    }

    @Override
    public ReportResultDto EGGUNBOXINGREPORT(String branchId, String fromMonth, String toMonth) throws SQLException {

        ReportResultDto reportResultDto = new ReportResultDto();
        ReportResultDto.eggunboxing appinfo = new ReportResultDto.eggunboxing();

        ArrayList<ReportResultDto.eggunboxing> result = new ArrayList<>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getEggUnboxingReport");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);

        storedProcedureQuery.setParameter(1, branchId);
        storedProcedureQuery.setParameter(2, fromMonth);
        storedProcedureQuery.setParameter(3, toMonth);

        storedProcedureQuery.execute();

        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

        while (resultSet.next()) {
            appinfo = ResultSetMapper.mapResultSetToObject(resultSet, ReportResultDto.eggunboxing.class);
            result.add(appinfo);
        }
        reportResultDto.setEggunboxingmst(result);

        return reportResultDto;
    }

    public ReportDto getGppsperformance(String branch_code) throws SQLException {
        ReportDto reportDto=new ReportDto();
        ReportDto.gppsPerformanceResultDto api = new ReportDto.gppsPerformanceResultDto();
        ArrayList<ReportDto.gppsPerformanceResultDto> Result = new ArrayList<ReportDto.gppsPerformanceResultDto>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("Sug_Mai_Gppsmgr_Pkg.getgppsperformance");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_code);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                api = ResultSetMapper.mapResultSetToObject(resultSet, ReportDto.gppsPerformanceResultDto.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Result.add(api);
        }
        reportDto.setPerformance(Result);
        return reportDto;
    }
}
