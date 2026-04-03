package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.FlockDto;
import com.suguna.breeder_revamp.dto.MedicineScheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineScheduleService {

    ArrayList<MedicineScheduleDto> getMedicineSchedule(String branch_ID) throws SQLException;
    ArrayList<MedicineScheduleDto> getVaccineSchedule(String branch_ID) throws SQLException;
    ArrayList<FlockDto> getFlock(String branch_ID) throws SQLException;
}
