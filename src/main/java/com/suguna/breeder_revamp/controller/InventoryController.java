package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.dto.IssueReturnDto;
import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.SaveSugMaterialConsumptionDto;
import com.suguna.breeder_revamp.service.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryServiceImpl inventoryService;

    @PostMapping("/saveSugMaterialConsumption")
    public ResponseDto SaveSugMaterialConsumption(@RequestBody ArrayList<SaveSugMaterialConsumptionDto > entry) throws Exception{

        ResponseDto responseDto = new ResponseDto();

        String response = "";

        if(response.equalsIgnoreCase("True"))
        {
            responseDto.setMessage("Success");
            responseDto.setStatusCode(200);
            responseDto.setStatus("Success");
            response=inventoryService.SaveSugMaterialConsumption(entry);
        }
        else
        {
            responseDto.setMessage("UnSuccess");
            responseDto.setStatusCode(201);
            responseDto.setStatus("UnSuccess");
            response=inventoryService.SaveSugMaterialConsumption(entry);
        }
        responseDto.setResult(response);
        return responseDto;
    }


    @GetMapping("/consumptionItem/{branch_ID}")
    public ArrayList<IssueReturnDto>GetConsumptionItem(@PathVariable String branch_ID)throws Exception{
        return inventoryService.GetConsumptionItem(branch_ID);}

    @GetMapping("/issueReturn/{branch_ID}")
    public ArrayList<IssueReturnDto>GetIssueReturn(@PathVariable String branch_ID)throws Exception{
        return inventoryService.GetIssueReturn(branch_ID);
    }

    @PostMapping("/SaveSugIssueReturn")
    public ResponseDto SaveSugIssueReturn(@RequestBody ArrayList<IssueReturnDto > entry) throws Exception{

        ResponseDto responseDto = new ResponseDto();

        String response = "";
        response=inventoryService.SaveSugIssueReturn(entry);
        if(response.equalsIgnoreCase("True"))
        {
            responseDto.setMessage("Success");
            responseDto.setStatusCode(200);
            responseDto.setStatus("Success");
            //response=inventoryService.SaveSugIssueReturn(entry);
        }
        else
        {
            responseDto.setMessage("UnSuccess");
            responseDto.setStatusCode(201);
            responseDto.setStatus("UnSuccess");

        }
        responseDto.setResult(response);
        return responseDto;
    }
}
