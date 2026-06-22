package com.suguna.breeder_revamp.manure.services.implementations;

import com.suguna.breeder_revamp.manure.constants.Constants;
import com.suguna.breeder_revamp.manure.dtos.APIResponse;
import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.ItemDto;
import com.suguna.breeder_revamp.manure.models.Items;
import com.suguna.breeder_revamp.manure.services.interfaces.ItemServices;
import jakarta.persistence.EntityManager;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemServices {

    @Autowired
    EntityManager entityManager;

    @Override
    public ResponseEntity<APIResponseList<ItemDto>> getItems(String orgId) {
        APIResponseList<ItemDto> itemDtoAPIResponseList = new APIResponseList<>();
        try {
            List<Items> itemsList = entityManager.createNativeQuery("SELECT A.appl_code,A.price_list_name,TO_CHAR(A.PRICE_LIST_ID) PRICE_LIST_ID,TO_CHAR(A.item_id) ITEM_ID,TO_CHAR(A.Rate) RATE,A.uom,A.item_code,A.description FROM sug_mai_so_pricelist_v A WHERE A.appl_code = 'MANURE_SALES' AND a.org_id = ?")
                    .setParameter(1,orgId)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(Items.class))
                    .getResultList();

            if (itemsList.isEmpty()) {
                itemDtoAPIResponseList.setMessage("Not Found");
                itemDtoAPIResponseList.setStatus(Constants.FAILURE);
                itemDtoAPIResponseList.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(itemDtoAPIResponseList, HttpStatus.NOT_FOUND);
            }


            itemDtoAPIResponseList.setMessage("Found");
            itemDtoAPIResponseList.setStatus(Constants.SUCCESS);
            itemDtoAPIResponseList.setStatusCode(HttpStatus.OK.value());
            itemDtoAPIResponseList.setData(convertEntiityToDtoList(itemsList));
            return new ResponseEntity<>(itemDtoAPIResponseList, HttpStatus.OK);

        } catch (Exception e) {
            itemDtoAPIResponseList.setMessage("Internal Server Error " + e.getMessage());
            itemDtoAPIResponseList.setStatus(Constants.FAILURE);
            itemDtoAPIResponseList.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(itemDtoAPIResponseList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<APIResponse<?>> checkIfExist(String orgId) {
        APIResponse<?> apiResponse = new APIResponse<>();
        BigDecimal count = (BigDecimal) entityManager.createNativeQuery("select count(1) as count from sug_item_mst_v a Where 1 = 1 and a.Itemcode='WSTFMYMNR0001' and a.organization_id = ?1")
                .setParameter(1,orgId)
                .getSingleResult();
        if (count.intValueExact() == 0) {
            apiResponse.setMessage("Not Found");
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse.setMessage("Found");
        apiResponse.setStatus(Constants.SUCCESS);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    private List<ItemDto> convertEntiityToDtoList(List<Items> items) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Items data : items) {
            itemDtoList.add(convertEntityToDto(data));
        }
        return itemDtoList;
    }

    private ItemDto convertEntityToDto(Items items) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemCode(items.getITEM_CODE());
        itemDto.setItemId(items.getITEM_ID());
        itemDto.setDescription(items.getDESCRIPTION());
        itemDto.setApplCode(items.getAPPL_CODE());
        itemDto.setRate(items.getRATE());
        itemDto.setUom(items.getUOM());
        itemDto.setPriceListId(items.getPRICE_LIST_ID());
        itemDto.setPriceListName(items.getPRICE_LIST_NAME());
        return itemDto;
    }
}
