package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.FlockDto;
import com.suguna.breeder_revamp.dto.MedicineScheduleDto;
import com.suguna.breeder_revamp.dto.SaveMedicineScheduleDto;
import com.suguna.breeder_revamp.model.SaveMedicineScheduleModel;
import com.suguna.breeder_revamp.repositories.SaveMedicineScheduleRepository;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class MedicineScheduleServiceImpl implements MedicineScheduleService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    SaveMedicineScheduleRepository saveMedicineScheduleRepository;

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

    @Transactional
    public String saveMedicineSchedule(ArrayList<SaveMedicineScheduleDto>entry){
        String fromdateFormat = "dd-MMM-yyyy";
        String fromdateFormat1 = "yyyy/MM/dd";
        try{
            for(SaveMedicineScheduleDto farmdto:entry){
                SaveMedicineScheduleModel masterModels =new SaveMedicineScheduleModel();
                masterModels.setAge(farmdto.getAge());
                masterModels.setGrade(farmdto.getGrade());
                masterModels.setQty(farmdto.getQty());
                masterModels.setCreationDate(new Date());
                masterModels.setCreatedBy(farmdto.getCreatedBy());
                masterModels.setDateFrom(getTxnDateString(farmdto.getDateFrom(),fromdateFormat));
                masterModels.setDateTo(getTxnDateString(farmdto.getDateFrom(),fromdateFormat));
                masterModels.setUom(farmdto.getUom());
                masterModels.setFlockId(farmdto.getFlockId());
                masterModels.setFarmCode(farmdto.getFarmCode());
                masterModels.setItemType(farmdto.getItemType());
                masterModels.setItemId("");
                masterModels.setAge("");
                 saveMedicineScheduleRepository.save(masterModels);
                return "True";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "False";
    }
    private Date getTxnDateString(String ipdate, String toformate) {
        DateFormat formatter;
        Date date = null;
        try {
            formatter = new SimpleDateFormat(toformate);
            date = formatter.parse(ipdate);

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());

        }
        return date;
    }

}
