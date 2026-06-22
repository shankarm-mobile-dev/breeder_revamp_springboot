package com.suguna.breeder_revamp.manure;


import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.ItemDto;
import com.suguna.breeder_revamp.manure.services.implementations.ItemServiceImpl;
import com.suguna.breeder_revamp.manure.services.interfaces.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manure/items")
public class ItemController {
    ItemServices itemServices;
    @Autowired
    ItemController(ItemServiceImpl itemServices) {
        this.itemServices = itemServices;
    }

    @GetMapping("/{org_id}")
    public ResponseEntity<APIResponseList<ItemDto>> getItems(@PathVariable String org_id) {
        return itemServices.getItems(org_id);
    }

    @GetMapping("/check/{org_id}")
    public ResponseEntity<APIResponse<?>> checkIfExist(@PathVariable String org_id){
        return itemServices.checkIfExist(org_id);
    }

}
