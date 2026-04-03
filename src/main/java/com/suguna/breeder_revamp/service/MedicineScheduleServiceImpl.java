package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.FlockDto;
import com.suguna.breeder_revamp.dto.MedicineScheduleDto;
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
public class MedicineScheduleServiceImpl implements MedicineScheduleService {
    @Autowired
    EntityManager entityManager;
    @Override
    public ArrayList<MedicineScheduleDto> getMedicineSchedule(String branch_ID) throws SQLException {
        MedicineScheduleDto appinfo = new MedicineScheduleDto();
        ArrayList<MedicineScheduleDto> Result = new ArrayList<MedicineScheduleDto>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(" sug_mai_gpps_mob_pkg.getmedicineschedule");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();
        while (resultSet.next()) {
            MedicineScheduleDto pojo = null;
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet, MedicineScheduleDto.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }

    @Override
    public ArrayList<MedicineScheduleDto> getVaccineSchedule(String branch_ID) throws SQLException {
        MedicineScheduleDto appinfo = new MedicineScheduleDto();
        ArrayList<MedicineScheduleDto> Result = new ArrayList<MedicineScheduleDto>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(" sug_mai_gpps_mob_pkg.getvaccineschedule");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();
        while (resultSet.next()) {
            MedicineScheduleDto pojo = null;
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet, MedicineScheduleDto.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }

    @Override
    public ArrayList<FlockDto> getFlock(String branch_ID) throws SQLException {
        FlockDto appinfo = new FlockDto();
        ArrayList<FlockDto> Result = new ArrayList<FlockDto>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(" sug_mai_gpps_mob_pkg.getshedmaster");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();
        while (resultSet.next()) {
            MedicineScheduleDto pojo = null;
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet, FlockDto.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }


}
