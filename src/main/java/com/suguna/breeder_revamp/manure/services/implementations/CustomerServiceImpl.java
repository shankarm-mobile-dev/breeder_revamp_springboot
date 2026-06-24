package com.suguna.breeder_revamp.manure.services.implementations;


import com.suguna.breeder_revamp.manure.constants.Constants;
import com.suguna.breeder_revamp.manure.dtos.APIResponseList;
import com.suguna.breeder_revamp.manure.dtos.CustomerDto;
import com.suguna.breeder_revamp.manure.dtos.ShipToBillToDto;
import com.suguna.breeder_revamp.manure.models.Customers;
import com.suguna.breeder_revamp.manure.models.ShipToBillTo;
import com.suguna.breeder_revamp.manure.services.interfaces.CustomerServices;
import jakarta.persistence.EntityManager;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServices {

    @Autowired
    EntityManager entityManager;

    @Override
    public ResponseEntity<APIResponseList<CustomerDto>> getCustomersRegionAndAppCategory(String region, String category) {
        APIResponseList<CustomerDto> customerDtoAPIResponseList = new APIResponseList<>();
        try {
            List<Customers> customersList = entityManager.createNativeQuery("SELECT A.APPL_CODE,A.CUSTOMER_NUMBER,TO_CHAR(A.CUSTOMER_ID) CUSTOMER_ID, A.CUSTOMER_NAME,TO_CHAR(A.CUST_ACCT_SITE_ID) CUST_ACCT_SITE_ID,TO_CHAR(A.PARTY_SITE_ID) PARTY_SITE_ID,TO_CHAR(A.SITE_USE_ID) SITE_USE_ID,TO_CHAR(A.ORG_ID) ORG_ID,TO_CHAR(A.PRIMARY_SALESREP_ID) PRIMARY_SALESREP_ID, A.LOCATION FROM SUG_MAI_SO_CUSTOMER_LIST_V A WHERE A.APPL_CODE = ?1 and A.ORG_ID = ?2")
                    .setParameter(1, category)
                    .setParameter(2, region)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(Customers.class))
                    .getResultList();

            if (customersList.isEmpty()) {
                customerDtoAPIResponseList.setMessage("Not Found");
                customerDtoAPIResponseList.setStatus(Constants.FAILURE);
                customerDtoAPIResponseList.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customerDtoAPIResponseList, HttpStatus.NOT_FOUND);
            }


            customerDtoAPIResponseList.setMessage("Found");
            customerDtoAPIResponseList.setStatus(Constants.SUCCESS);
            customerDtoAPIResponseList.setStatusCode(HttpStatus.OK.value());
            customerDtoAPIResponseList.setData(convertEntityToDtoList(customersList));
            return new ResponseEntity<>(customerDtoAPIResponseList, HttpStatus.OK);

        } catch (Exception e) {
            customerDtoAPIResponseList.setMessage("Internal Server Error " + e.getMessage());
            customerDtoAPIResponseList.setStatus(Constants.FAILURE);
            customerDtoAPIResponseList.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customerDtoAPIResponseList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves ship-to and bill-to information based on the provided customer code and region code.
     *
     * @param customerId The customer ID.
     * @param regionCode The region code.
     * @return ResponseEntity containing the API response with the ship-to and bill-to information.
     */
    @Override
    public ResponseEntity<APIResponseList<ShipToBillToDto>> getShipToBillTo(String customerId, String regionCode) {
        APIResponseList<ShipToBillToDto> shipToBillToDtoAPIResponseList = new APIResponseList<>();
        try {
            List<ShipToBillTo> shipToBillToList = entityManager.createNativeQuery("SELECT TO_CHAR(a.SITE_USE_ID) as SHIP_TO_ID, TO_CHAR(a.BILL_TO_SITE_USE_ID) AS BILL_TO_ID, a.Region_code_bl, a.Region_code, A.bill_to_loc, (select decode(ee.status, 'A', 'Active', 'I', 'Inactive') from hz_cust_site_uses_all ee where 1 = 1 and ee.SITE_USE_ID = a.BILL_TO_SITE_USE_ID and ee.site_use_code = 'BILL_TO' and rownum = 1) bill_to_status, ship_lto_loc as ship_to_loc, statssiteuse as ship_to_status FROM SUG_CUST_MST_S_V A WHERE 1 = 1 AND A.CUST_ACCOUNT_ID = ?1 AND a.Region_code = ?2 and a.StatSsiteUse = 'Active' and a.StatSite = 'Active' and a.StatCust = 'Active' union all SELECT TO_CHAR(a.SITE_USE_ID) as SHIP_TO_ID, TO_CHAR(a.BILL_TO_SITE_USE_ID) AS BILL_TO_ID, a.Region_code_bl, a.Region_code, A.bill_to_loc, (select decode(ee.status, 'A', 'Active', 'I', 'Inactive') from hz_cust_site_uses_all ee where 1 = 1 and ee.SITE_USE_ID = a.BILL_TO_SITE_USE_ID and ee.site_use_code = 'BILL_TO' and rownum = 1) bill_to_status, ship_lto_loc as ship_to_loc, statssiteuse as ship_to_status FROM SUG_CUST_MST_S_V A WHERE 1 = 1 AND A.CUST_ACCOUNT_ID = ?1 AND a.Region_code in ('BK') and a.StatSsiteUse = 'Active' and a.StatSite = 'Active' and a.StatCust = 'Active'")
                    .setParameter(1, customerId)
                    .setParameter(2, regionCode)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(ShipToBillTo.class))
                    .getResultList();

            /*List<ShipToBillTo> shipToBillToList = entityManager.createNativeQuery("SELECT TO_CHAR(a.SITE_USE_ID) as SHIP_TO_ID, TO_CHAR(a.BILL_TO_SITE_USE_ID) AS BILL_TO_ID, a.Region_code_bl, a.Region_code, A.bill_to_loc, (select decode(ee.status, 'A', 'Active', 'I', 'Inactive') from hz_cust_site_uses_all ee where 1 = 1 and ee.SITE_USE_ID = a.BILL_TO_SITE_USE_ID and ee.site_use_code = 'BILL_TO' and rownum = 1) bill_to_status, ship_lto_loc as ship_to_loc, statssiteuse as ship_to_status FROM SUG_CUST_MST_S_V A WHERE 1 = 1 AND A.CUST_ACCOUNT_ID = ?1 and a.StatSsiteUse = 'Active' and a.StatSite = 'Active' and a.StatCust = 'Active' and a.Region_code = ?2")
                    .setParameter(1, customerId)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(ShipToBillTo.class))
                    .getResultList();*/

            if (shipToBillToList.isEmpty()) {
                shipToBillToDtoAPIResponseList.setMessage("Not Found");
                shipToBillToDtoAPIResponseList.setStatus(Constants.FAILURE);
                shipToBillToDtoAPIResponseList.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(shipToBillToDtoAPIResponseList, HttpStatus.NOT_FOUND);
            }


            shipToBillToDtoAPIResponseList.setMessage("Found");
            shipToBillToDtoAPIResponseList.setStatus(Constants.SUCCESS);
            shipToBillToDtoAPIResponseList.setStatusCode(HttpStatus.OK.value());
            shipToBillToDtoAPIResponseList.setData(convertEntityToDtoShipList(shipToBillToList));
            return new ResponseEntity<>(shipToBillToDtoAPIResponseList, HttpStatus.OK);

        } catch (Exception e) {
            shipToBillToDtoAPIResponseList.setMessage("Internal Server Error " + e.getMessage());
            shipToBillToDtoAPIResponseList.setStatus(Constants.FAILURE);
            shipToBillToDtoAPIResponseList.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(shipToBillToDtoAPIResponseList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ShipToBillToDto convertEntityToDto(ShipToBillTo ship) {
        ShipToBillToDto shipToBillToDto = new ShipToBillToDto();
        shipToBillToDto.setBillToId(ship.getBILL_TO_ID());
        shipToBillToDto.setBillToLocation(ship.getBILL_TO_LOC());
        shipToBillToDto.setBillToStatus(ship.getBILL_TO_STATUS());
        shipToBillToDto.setShipToId(ship.getSHIP_TO_ID());
        shipToBillToDto.setShipToLocation(ship.getSHIP_TO_LOC());
        shipToBillToDto.setShipToLocation(ship.getSHIP_TO_LOC());
        shipToBillToDto.setShipToStatus(ship.getSHIP_TO_STATUS());
        return shipToBillToDto;
    }

    public List<ShipToBillToDto> convertEntityToDtoShipList(List<ShipToBillTo> ship) {
        List<ShipToBillToDto> shipToBillToDtos = new ArrayList<>();
        for (ShipToBillTo shipToBillTo : ship) {
            shipToBillToDtos.add(convertEntityToDto(shipToBillTo));
        }
        return shipToBillToDtos;
    }

    public List<CustomerDto> convertEntityToDtoList(List<Customers> customersList) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customers customers : customersList) {
            customerDtoList.add(convertEntityToDto(customers));
        }
        return customerDtoList;
    }

    public CustomerDto convertEntityToDto(Customers customers) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerName(customers.getCUSTOMER_NAME());
        customerDto.setCustomerId(customers.getCUSTOMER_ID());
        customerDto.setCustomerNumber(customers.getCUSTOMER_NUMBER());
        customerDto.setLocation(customers.getLOCATION());
        customerDto.setApplCode(customers.getAPPL_CODE());
        customerDto.setOrgId(customers.getORG_ID());
        customerDto.setSiteUseId(customers.getSITE_USE_ID());
        customerDto.setCustomerAccSiteId(customers.getCUST_ACCT_SITE_ID());
        customerDto.setPartySiteId(customers.getPARTY_SITE_ID());
        customerDto.setPrimarySalesRepId(customers.getPRIMARY_SALESREP_ID());
        return customerDto;
    }

    /*public String getCustomerMobileNumber(String customerId,String source){
        return "";
    }*/

    /**
     * Get Customer Mobile Number
     *
     * @param customerId Customer ID
     * @param partyId  Customer Party Site ID
     * @return String
     */
    @Override
    public String getCustomerMobileNumber(String customerId,String partyId) {
        try {
            return (String) entityManager.createNativeQuery("select sug_ar_pkg.customer_mobile(?1,?2) from dual")
                    .setParameter(1,partyId)
                    .setParameter(2,customerId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public String getCustomerPartySiteId(String customerId)
    {
        try {
            return (String) entityManager.createNativeQuery("SELECT TO_CHAR(PARTY_SITE_ID) PARTY_SITE_ID FROM SUG_MAI_SO_CUSTOMER_LIST_V A WHERE 1 = 1\n" +
                            "AND A.appl_code = 'MANURE_SALES'\n" +
                            "AND A.CUSTOMER_ID = ?1")
                    .setParameter(1,customerId)
                    .getSingleResult();
        } catch (Exception e) {
            return "";
        }
    }


}
