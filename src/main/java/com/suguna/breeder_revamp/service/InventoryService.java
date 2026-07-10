package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.IssueReturnDto;
import com.suguna.breeder_revamp.dto.SaveSugMaterialConsumptionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryService {
    String SaveSugMaterialConsumption(ArrayList<SaveSugMaterialConsumptionDto> entry);

    ArrayList<IssueReturnDto> GetIssueReturn(String branch_ID ) throws SQLException;

    ArrayList<IssueReturnDto> GetConsumptionItem(String branchId)  throws SQLException;

    String SaveSugIssueReturn(ArrayList<IssueReturnDto> entry);
}
