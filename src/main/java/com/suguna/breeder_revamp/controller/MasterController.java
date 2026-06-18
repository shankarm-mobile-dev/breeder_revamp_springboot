package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.MasterResultDto;
import com.suguna.breeder_revamp.service.MasterServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/master")
public class MasterController {
    MasterServices masterServices;

    public MasterController(MasterServices masterServices) {
        this.masterServices = masterServices;
    }

    @GetMapping("/getitemmaster/{branchId}")
    public MasterResultDto getitemmaster( @PathVariable String branchId) throws Exception {
        return masterServices.getitemmaster(branchId);
    }

    @GetMapping("/getFvalueWeight")
    public MasterResultDto getfvalueeight() throws Exception {
        return masterServices.getfvalueeight();
    }



}
