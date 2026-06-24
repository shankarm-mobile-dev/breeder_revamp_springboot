package com.suguna.breeder_revamp.manure.services.implementations;

import com.suguna.breeder_revamp.manure.constants.Constants;
import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.FarmDto;
import com.suguna.breeder_revamp.manure.models.Farms;
import com.suguna.breeder_revamp.manure.services.interfaces.FarmServices;
import jakarta.persistence.EntityManager;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmServicesImpl implements FarmServices {

    @Autowired
    EntityManager entityManager;

    /**
     * Retrieves farm information based on the provided employee code and device ID.
     *
     * @param empCode  The employee code.
     * @param deviceId The device ID.
     * @return ResponseEntity containing the API response with the farm information.
     */
    @Override
    public ResponseEntity<APIResponseList<FarmDto>> getFarmsByEmpCodeAndDeviceId(String empCode, String deviceId) {
        APIResponseList<FarmDto> farmDtoAPIResponse = new APIResponseList<>();
        try {

            List<Farms> farmsList = entityManager.createNativeQuery("SELECT C.LOCATION_NAME,B.USER_NAME AS EMP_CODE,B.ORGANIZATION_ID,C.ZONE_NAME,C.REGION_ID,C.REGION_CODE,C.BRANCH_ID,C.BRANCH_NAME,C.BRANCH_SHORT_NAME FROM SUG_MAI_ORG_ACCESS B, SUG_ORGANIZATION_MV C\n" +
                    " WHERE 1 = 1\n" +
                    " AND B.ORGANIZATION_ID = C.BRANCH_ID\n" +
                    " AND B.STATUS = 'A'\n" +
                    " AND B.USER_NAME = ?1")
                    .setParameter(1,empCode)
                    .unwrap(org.hibernate.query.NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(Farms.class))
                    .getResultList();

//            List<Farms> farmsList = entityManager.createNativeQuery("SELECT B.LOCATION_NAME, A.EMP_CODE, A.ORGANIZATION_ID, B.ZONE_NAME, B.REGION_ID, B.REGION_CODE, B.REGION, B.BRANCH_ID, B.BRANCH_NAME, B.Branch_Short_Name FROM SUG.SUG_MAI_DEVICEDATA A, SUG_ORGANIZATION_MV B WHERE 1 = 1 AND A.APPLICATION = 'BREEDER' AND A.ORGANIZATION_ID = B.BRANCH_ID AND A.EMP_CODE = ?1 AND A.STATUS = 'A' AND A.DEVICE_ID = ?2 GROUP BY B.LOCATION_NAME, A.EMP_CODE, A.ORGANIZATION_ID, B.ZONE_NAME, B.REGION_ID, B.REGION_CODE, B.REGION, B.BRANCH_ID, B.BRANCH_NAME, B.Branch_Short_Name")
//                    .setParameter(1, empCode)
//                    .setParameter(2, deviceId)
//                    .unwrap(org.hibernate.query.NativeQuery.class)
//                    .setResultTransformer(Transformers.aliasToBean(Farms.class))
//                    .getResultList();



            if (farmsList.isEmpty()) {
                farmDtoAPIResponse.setMessage("Not Found");
                farmDtoAPIResponse.setStatus(Constants.FAILURE);
                farmDtoAPIResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(farmDtoAPIResponse, HttpStatus.NOT_FOUND);
            }

            //Farms farms = farmsList.get(0);
            farmDtoAPIResponse.setMessage("Found");
            farmDtoAPIResponse.setStatus(Constants.SUCCESS);
            farmDtoAPIResponse.setStatusCode(HttpStatus.OK.value());
            farmDtoAPIResponse.setData(convertListToDto(farmsList));
            return new ResponseEntity<>(farmDtoAPIResponse, HttpStatus.OK);

        } catch (Exception e) {
            farmDtoAPIResponse.setMessage("Internal Server Error " + e.getMessage());
            farmDtoAPIResponse.setStatus(Constants.FAILURE);
            farmDtoAPIResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(farmDtoAPIResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<FarmDto> convertListToDto(List<Farms> farmsList)
    {
        List<FarmDto> farmDtos = new ArrayList<>();
        for(Farms farms:farmsList)
        {
            farmDtos.add(convertEntityToDto(farms));
        }
        return farmDtos;
    }



    /**
     * Converts a Farms entity to a FarmDto.
     *
     * @param farms The Farms entity to be converted.
     * @return The converted FarmDto.
     */
    private FarmDto convertEntityToDto(Farms farms){
        FarmDto farmDto = new FarmDto();
        farmDto.setBranchId(Integer.parseInt(String.valueOf(farms.getBRANCH_ID())));
        farmDto.setBranchName(farms.getBRANCH_NAME());
        farmDto.setEmpCode(farms.getEMP_CODE());
        farmDto.setLocationName(farms.getLOCATION_NAME());
        farmDto.setOrganizationId(Integer.parseInt(String.valueOf(farms.getORGANIZATION_ID())));
        farmDto.setRegionCode(farms.getREGION_CODE());
        farmDto.setRegion(farms.getREGION());
        farmDto.setZoneName(farms.getZONE_NAME());
        farmDto.setRegionId(Integer.parseInt(String.valueOf(farms.getREGION_ID())));
        farmDto.setBranchShortName(farms.getBRANCH_SHORT_NAME());
        return farmDto;
    }
}
