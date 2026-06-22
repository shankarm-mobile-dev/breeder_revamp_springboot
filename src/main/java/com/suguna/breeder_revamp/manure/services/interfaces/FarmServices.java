package com.suguna.breeder_revamp.manure.services.interfaces;

import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.FarmDto;
import org.springframework.http.ResponseEntity;

public interface FarmServices {
    ResponseEntity<APIResponseList<FarmDto>> getFarmsByEmpCodeAndDeviceId(String empCode, String deviceId);

}
