package com.suguna.breeder_revamp.manure;


import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.CustomerDto;
import com.suguna.breeder_revamp.manure.dtos.ShipToBillToDto;
import com.suguna.breeder_revamp.manure.services.implementations.CustomerServiceImpl;
import com.suguna.breeder_revamp.manure.services.interfaces.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manure/customers")
public class CustomerController {

    CustomerServices customerServices;
    @Autowired
    CustomerController(CustomerServiceImpl customerServices)
    {
        this.customerServices = customerServices;
    }

    @GetMapping("/")
    public ResponseEntity<APIResponseList<CustomerDto>> getCustomersByRegion(@RequestParam(name = "region", required = true, defaultValue = "") String region,
                                                                             @RequestParam(name = "application", required = true, defaultValue = "") String application)
    {
        return customerServices.getCustomersRegionAndAppCategory(region,application);
    }

    @GetMapping("/ship_to_bill_to")
    public ResponseEntity<APIResponseList<ShipToBillToDto>> getCustomerShipToBillTo(@RequestParam(name = "customer_id", required = true, defaultValue = "") String customer_id,
                                                                                    @RequestParam(name = "region", required = true, defaultValue = "") String region)
    {
        return customerServices.getShipToBillTo(customer_id,region);
    }

}
