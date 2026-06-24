package com.suguna.breeder_revamp.manure;


import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.FarmDto;
import com.suguna.breeder_revamp.manure.services.implementations.FarmServicesImpl;
import com.suguna.breeder_revamp.manure.services.interfaces.FarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manure/farms")
public class FarmsController {

    FarmServices farmServices;

    @Autowired
    FarmsController(FarmServicesImpl farmServices)
    {
        this.farmServices = farmServices;
    }

    @GetMapping("/")
    public ResponseEntity<APIResponseList<FarmDto>> getFarms(@RequestParam(name = "emp_code", required = true, defaultValue = "") String emp_code,
                                                             @RequestParam(name = "device_id", required = true, defaultValue = "") String device_id
                                                         ){
        return farmServices.getFarmsByEmpCodeAndDeviceId(emp_code,device_id);
    }

}
