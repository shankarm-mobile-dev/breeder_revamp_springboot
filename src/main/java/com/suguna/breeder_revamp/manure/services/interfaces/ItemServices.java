package com.suguna.breeder_revamp.manure.services.interfaces;

import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.ItemDto;
import org.springframework.http.ResponseEntity;

public interface ItemServices {
    ResponseEntity<APIResponseList<ItemDto>> getItems(String orgId);

    ResponseEntity<APIResponse<?>> checkIfExist(String orgId);
}
