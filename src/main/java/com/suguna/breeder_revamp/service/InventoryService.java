package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.SaveSugMaterialConsumptionDto;

import java.util.ArrayList;

public interface InventoryService {
    String SaveSugMaterialConsumption(ArrayList<SaveSugMaterialConsumptionDto> entry);
}
