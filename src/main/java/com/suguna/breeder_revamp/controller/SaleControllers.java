package com.suguna.breeder_revamp.controller;


import com.suguna.breeder_revamp.dto.*;
import com.suguna.breeder_revamp.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/sales")
public class SaleControllers {
    @Autowired
    SaleServiceImpl saleService;
    @GetMapping("/customerdetails/{branch_ID}")
    public ArrayList<SaleResultDto.customerdetails>CUSTOMERDETAILS(@PathVariable String branch_ID)throws Exception{
        return saleService.CUSTOMERDETAILS(branch_ID);
    }
    @GetMapping("/vehicleno/{regionid}")
    public ArrayList<SaleResultDto.vehicleno>VEHICLENOS(@PathVariable String regionid)throws Exception{
        return saleService.VEHICLENOS(regionid);
    }
    @GetMapping("/orderdetails/{branch_ID}/{timestring}")
    public ArrayList<SaleResultDto.orderdetails>ORDERDETAILS(@PathVariable String branch_ID,@PathVariable String timestring)throws Exception{
        return saleService.ORDERDETAILS(branch_ID ,timestring);
    }
    @GetMapping("/onhandculleggstock/{branch_ID}")
    public ArrayList<SaleResultDto.onhandculleggstock>ONHANDCULLEGGSTOCKS(@PathVariable String branch_ID)throws Exception{
        return saleService.ONHANDCULLEGGSTOCKS(branch_ID );
    }
    @GetMapping("/despatchtime/{regionid}")
    public ArrayList<SaleResultDto.despatchtime>DESPATCHTIMES(@PathVariable String regionid)throws Exception{
        return saleService.DESPATCHTIMES(regionid);
    }
    @PostMapping("/saveEggCullsSalesEntry")
    public ResponseDto getDeliveryHeader(@RequestBody deliveryHeaderDto entry) throws Exception{
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Success");
        responseDto.setStatusCode(200);
        responseDto.setStatus("Success");
        String response = "";
        // if (branchRequest.getActivityName().equalsIgnoreCase("LIVE BIRD OBSERVATION")) {
        responseDto.setResult(saleService.getDeliveryHeader(entry));
        //}
        return responseDto;

    }
    @PostMapping("/deliverylines")
    public String getDeliveryLines(@RequestBody ArrayList<deliveryLinesDto> entry) throws Exception{
        return saleService.getDeliveryLines(entry);
    }
    @PostMapping("/deliverylotdetails")
    public String getDeliveryLotDetails(@RequestBody ArrayList<deliveryLotDetailsDto> entry) throws Exception{
        return saleService.getDeliveryLotDetails(entry);
    }
}
