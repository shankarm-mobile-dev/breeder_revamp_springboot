package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.FlockDto;
import com.suguna.breeder_revamp.dto.MedicineScheduleDto;
import com.suguna.breeder_revamp.dto.SaveMedicineScheduleDto;
import com.suguna.breeder_revamp.service.MedicineScheduleService;
import com.suguna.breeder_revamp.service.MedicineScheduleServiceImpl;
import com.suguna.breeder_revamp.service.ShedReadyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/medicineschedule")
public class MedicineController {

    MedicineScheduleService medicineScheduleService;

    @Autowired
    public MedicineController(MedicineScheduleServiceImpl medicineScheduleService) {
        this.medicineScheduleService = medicineScheduleService;
    }


    @GetMapping("/medistock/{branch_ID}")
    public ArrayList<MedicineScheduleDto> getMedicineSchedule( @PathVariable String branch_ID) throws Exception {
        return medicineScheduleService.getMedicineSchedule( branch_ID);
    }

    @GetMapping("/vaccinestock/{branch_ID}")
    public ArrayList<MedicineScheduleDto> getVaccineSchedule( @PathVariable String branch_ID) throws Exception {
        return medicineScheduleService.getVaccineSchedule( branch_ID);
    }

    @GetMapping("/flock/{branch_ID}")
    public ArrayList<FlockDto> getFlock(@PathVariable String branch_ID) throws Exception {
        return medicineScheduleService.getFlock( branch_ID);
    }

    @PostMapping("/save")
    public String saveMedicineSchedule(@RequestBody ArrayList<SaveMedicineScheduleDto>entry) throws Exception{
        return medicineScheduleService.saveMedicineSchedule(entry);
    }
}
