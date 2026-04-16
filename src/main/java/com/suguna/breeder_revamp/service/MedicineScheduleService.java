package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineScheduleService {

    ArrayList<MedicineScheduleDto> getMedicineSchedule(String branch_ID) throws SQLException;
    ArrayList<MedicineScheduleDto> getVaccineSchedule(String branch_ID) throws SQLException;
    ArrayList<FlockDto> getFlock(String branch_ID) throws SQLException;
    String saveMedicineSchedule(ArrayList<SaveMedicineScheduleDto>entry);
}
