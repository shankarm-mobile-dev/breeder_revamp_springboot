package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.IssueReturnDto;
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
    public String  SaveSugMaterialConsumption(@RequestBody ArrayList<SaveSugMaterialConsumptionDto > entry) throws Exception{
        return inventoryService.SaveSugMaterialConsumption(entry);
    }


    @GetMapping("/issuereturn/{branch_ID}")
    public ArrayList<IssueReturnDto>GetIssueReturn(@PathVariable String branch_ID)throws Exception{
        return inventoryService.GetIssueReturn(branch_ID);}
}
