package com.suguna.breeder_revamp.manure.services.interfaces;

import com.suguna.breeder_revamp.manure.dtos.*;
import org.springframework.http.ResponseEntity;

public interface CustomerServices {
    ResponseEntity<APIResponseList<CustomerDto>> getCustomersRegionAndAppCategory(String region,String category);

    ResponseEntity<APIResponseList<ShipToBillToDto>> getShipToBillTo(String customerId, String regionCode);

    String getCustomerMobileNumber(String customerId,String custAccSiteId);

}
