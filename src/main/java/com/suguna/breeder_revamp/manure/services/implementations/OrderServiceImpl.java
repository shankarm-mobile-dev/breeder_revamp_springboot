package com.suguna.breeder_revamp.manure.services.implementations;

import com.suguna.breeder_revamp.manure.constants.Constants;
import com.suguna.breeder_revamp.manure.dtos.*;
import com.suguna.breeder_revamp.manure.models.*;
import com.suguna.breeder_revamp.manure.repositories.OrderLineRepository;
import com.suguna.breeder_revamp.manure.repositories.OrderRepository;
import com.suguna.breeder_revamp.manure.services.interfaces.OrderServices;
import com.suguna.breeder_revamp.manure.specifications.OrderEntitySpecification;
import com.suguna.breeder_revamp.manure.utils.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service implementation for managing orders and order lines.
 */
@Service
public class OrderServiceImpl implements OrderServices {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    OtpServiceImpl otpService;

    @Autowired
    CustomerServiceImpl customerService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");


    public boolean isBeforeExpiry(String expiryDateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime expiryLocalDateTime = LocalDateTime.parse(expiryDateStr, formatter);
            ZonedDateTime expiryDate = expiryLocalDateTime.atZone(ZoneId.systemDefault());
            ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.systemDefault());

            System.out.println("Current Date: " + currentDate);
            System.out.println("Expiry Date: " + expiryDate);
            System.out.println("Status: " + currentDate.isBefore(expiryDate));

            return currentDate.isBefore(expiryDate);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return false;
        }
        /*try {
            LocalDateTime expiryDate = LocalDateTime.parse(expiryDateStr, formatter);
            LocalDateTime currentDate = LocalDateTime.now();
            System.out.println("Current Date: " + currentDate);
            System.out.println("Expiry Date: " + expiryDate);
            System.out.println("Status: "+currentDate.isBefore(expiryDate));
            return currentDate.isBefore(expiryDate);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return false;
        }*/
    }

    @Override
    public ResponseEntity<APIResponse<?>> sendOTPByOrderRefNumber(String orderRefNumber) {
        APIResponse<?> apiResponse = new APIResponse<>();
        try {

            Orders orders = orderRepository.findByOrderRefNumber(Long.valueOf(orderRefNumber));
            if (orders == null) {
                apiResponse.setMessage("No Order Found");
                apiResponse.setStatus(Constants.FAILURE);
                apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

            OTPModel otpModel = otpService.fetchLastOTP(String.valueOf(orders.getORDER_REF_NUMBER()));
            if (otpModel == null) {
                apiResponse.setMessage("No OTP Found");
                apiResponse.setStatus(Constants.FAILURE);
                apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

            System.out.println("Expiry Date "+otpModel.getEXPIRY_DATE());

            boolean isExpired = isBeforeExpiry((String.valueOf(otpModel.getEXPIRY_DATE())));
            String otp = otpModel.getOtp();
            if(!isExpired)
            {
                /*apiResponse.setStatus(Constants.FAILURE);
                apiResponse.setMessage("Password is Expired. Please click Resend OTP");
                apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);*/
                otp = Utils.generateOtp();
            }

            //Get Current OTP
            String partySiteId = customerService.getCustomerPartySiteId(String.valueOf(orders.getCUSTOMER_ID()));
            if (partySiteId.isEmpty()) {
                apiResponse.setMessage("No Party Site id is found");
                apiResponse.setStatus(Constants.FAILURE);
                apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            String customerMobileNumber = customerService.getCustomerMobileNumber(String.valueOf(orders.getCUSTOMER_ID()), partySiteId);
            if (customerMobileNumber == null) {
                apiResponse.setStatus(Constants.FAILURE);
                apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("Selected customer mobile number is not available in customer master, kindly update the mobile number in customer master and proceed for order booking.");
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            OTPDto otpDto = new OTPDto();
            otpDto.setOtp(otp);
            otpDto.setUserName(String.valueOf(orders.getCUSTOMER_ID()));
            otpDto.setSource(orders.getSOURCE());
            otpDto.setUserType("CUSTOMER");
            String message = "Dear Customer, Manure Sales ";
            message += orders.getOrderLines().get(0).getQTY() + " MT is booked in your account, Share this OTP ";
            message += otpModel.getOtp() + " to generate the invoice.-SUGUNA";
            otpDto.setMessage(message);
            otpDto.setUserId(String.valueOf(orders.getCUSTOMER_ID()));
            //otpDto.setCustAccSiteId(String.valueOf(orderDto.getCustomerAcctSiteId()));
            otpDto.setOrderRefNumber(String.valueOf(orders.getORDER_REF_NUMBER()));
            //customerMobileNumber = "9944033729";
            otpDto.setMobileNumber(customerMobileNumber);
            APIResponse<?> otpResponse = otpService.otpResponse(otpDto);
            apiResponse.setMessage("OTP is sent "+otpResponse.getMessage());
            apiResponse.setStatus(Constants.SUCCESS);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setMessage("Internal Server Error: " + e.getMessage());
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves an order along with its order lines.
     *
     * @param orderDto the order data transfer object containing order and order line details
     * @return a response entity containing the API response with the saved order details
     */
    @Transactional
    @Override
    public ResponseEntity<APIResponse<OrderDto>> saveOrder(OrderDto orderDto) {
        APIResponse<OrderDto> apiResponse = new APIResponse<>();

        String customerMobileNumber = customerService.getCustomerMobileNumber(String.valueOf(orderDto.getCustomerId()), String.valueOf(orderDto.getParty_site_id()));
        if (customerMobileNumber == null) {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("Selected customer mobile number is not available in customer master, kindly update the mobile number in customer master and proceed for order booking.");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }


        try {
            Orders orders = convertDtoToEntity(orderDto);
            orders.setORDER_REF_NUMBER(getOrderRefNumber()); // Assuming the method name follows camelCase convention
            orders.setSTATUS(0);


            Orders orderResponse = orderRepository.save(orders);
            List<OrderLine> orderLineList = convertLineDtotoEntityList(orderDto.getOrderLineDtoList());
            String qty = "";
            for (OrderLine orderLine : orderLineList) {
                qty = String.valueOf(orderLine.getQTY());
                orderLine.setOrder(orderResponse);
                orderLine.setCREATION_DATE(new Date());
                orderLine.setLAST_UPDATED_DATE(new Date());
                orderLineRepository.save(orderLine);
            }

            String otp = Utils.generateOtp();
            OTPDto otpDto = new OTPDto();
            otpDto.setOtp(otp);
            otpDto.setUserName(String.valueOf(orders.getCUSTOMER_ID()));
            otpDto.setSource(orders.getSOURCE());
            otpDto.setUserType("CUSTOMER");
            String message = "Dear Customer, Manure Sales ";
            message += qty + " MT is booked in your account, Share this OTP ";
            message += otp + " to generate the invoice.-SUGUNA";
            //message = "Dear Customer, Manure Sales 100 MT is booked in your account, Share this OTP 1111 to generate the invoice.-SUGUNA";
            //String template = "Dear Customer, Manure Sales %s MT is booked in your account, Share this OTP %s to generate the invoice.-SUGUNA";
            //String message = String.format(template, qty, otp);
            //String message = "Dear Customer, Manure Sales 100 MT is booked in your account, Share this OTP 1111 to generate the invoice.-SUGUNA";
            otpDto.setMessage(message);
            otpDto.setUserId(String.valueOf(orders.getCUSTOMER_ID()));
            otpDto.setCustAccSiteId(String.valueOf(orderDto.getCustomerAcctSiteId()));
            otpDto.setOrderRefNumber(String.valueOf(orders.getORDER_REF_NUMBER()));
            //customerMobileNumber = "9944033729";
            otpDto.setMobileNumber(customerMobileNumber);
            APIResponse<?> otpResponse = otpService.otpResponse(otpDto);
            //return otpService.sendOTP(otpDto);
            List<OrderLine> orderLinesResult = orderLineRepository.findByHeaderId(orderResponse.getHeaderId());
            orderResponse.setOrderLines(orderLinesResult);
            apiResponse.setData(convertEntityToDto(orderResponse)); // Assuming you want to return the saved order in the response
            //apiResponse.setMessage("Order is saved./" + otpResponse.getMessage() + "/" + otpResponse.getStatusCode());
            String response = "Your order is saved. Order ref number is: "+orderResponse.getORDER_REF_NUMBER()+". OTP sent to the customer registered mobile no. Kindly collect the OTP from customer and enter the same in order status and confirm the order.";
            apiResponse.setMessage(response);
            //apiResponse.setMessage(message+" - "+otpResponse.getMessage());
            apiResponse.setStatus(Constants.SUCCESS);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setMessage("Internal Server Error: " + e.getMessage());
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<APIResponseList<OrderDto>> fetchOrder(Long orgId, Long customerId, Long siteUseId, String vehicleNumber, String date, Pageable pageable, Sort sort, String created_by) {
        APIResponseList<OrderDto> apiResponseList = new APIResponseList<>();

        // Debugging: Log the page number
//        System.out.println("Page number: " + pageable.getPageNumber());

        Page<Orders> ordersPage = orderRepository.findAll(OrderEntitySpecification.getEntities(created_by,orgId, customerId, vehicleNumber, siteUseId, date), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));

        if (ordersPage.isEmpty()) {
            apiResponseList.setMessage("Not Found");
            apiResponseList.setStatus(Constants.FAILURE);
            apiResponseList.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponseList, HttpStatus.NOT_FOUND);
        }

        List<OrderDto> orderDtoList = convertEntityListToDtoList(ordersPage.getContent());
        apiResponseList.setMessage("Fetched");
        apiResponseList.setStatus(Constants.SUCCESS);
        apiResponseList.setStatusCode(HttpStatus.OK.value());
        apiResponseList.setData(orderDtoList);
        apiResponseList.setTotalPages(ordersPage.getTotalPages());
        apiResponseList.setTotalElements(ordersPage.getTotalElements());
        apiResponseList.setCurrentPage(ordersPage.getNumber());

        // Debugging: Log the current page
        System.out.println("Current page: " + ordersPage.getNumber());

        return new ResponseEntity<>(apiResponseList, HttpStatus.OK);
    }

    @Override
    public APIResponse<?> updateStatus(String orderRefNumber, int status) {
        APIResponse<?> apiResponse = new APIResponse<>();
        Orders orders = orderRepository.findByOrderRefNumber(Long.valueOf(orderRefNumber));
        if (orders == null) {
            apiResponse.setStatus(Constants.FAILURE);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("Invalid data");
            return apiResponse;
        }
        orders.setSTATUS(1);
        orderRepository.save(orders);
        apiResponse.setStatus(Constants.SUCCESS);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Status updated");
        return apiResponse;
    }

    /**
     * Retrieves the next order reference number from the database sequence.
     *
     * @return the next order reference number
     */
    private Long getOrderRefNumber() {
        Query query = entityManager.createNativeQuery("SELECT SUG_SALES_ORDER_HDR_PRE_INTF_S.NEXTVAL FROM DUAL");
        return ((BigDecimal) query.getSingleResult()).longValue();
    }

    /**
     * Converts a list of OrderLineDto objects to a list of OrderLine entities.
     *
     * @param orderDtos the list of OrderLineDto objects
     * @return the list of OrderLine entities
     */
    private List<OrderLine> convertLineDtotoEntityList(List<OrderLineDto> orderDtos) {
        List<OrderLine> orderLineList = new ArrayList<>();
        for (OrderLineDto orderLineDto : orderDtos) {
            orderLineList.add(convertLineDtoToEntity(orderLineDto));
        }
        return orderLineList;
    }

    /**
     * Converts an OrderLineDto object to an OrderLine entity.
     *
     * @param orderLineDto the OrderLineDto object
     * @return the OrderLine entity
     */
    private OrderLine convertLineDtoToEntity(OrderLineDto orderLineDto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setITEM_ID(orderLineDto.getItemID());
        orderLine.setPRICE_LIST_RATE(orderLineDto.getPriceListRate());
        orderLine.setPRIMARY_UOM_CODE(orderLineDto.getPrimaryUOMCode());
        orderLine.setCREATED_BY(orderLineDto.getCreatedBy());
        orderLine.setRATE(orderLineDto.getRate());
        orderLine.setPRICE_LIST_RATE(orderLineDto.getPriceListRate());
        orderLine.setQTY(new BigDecimal(orderLineDto.getQty()));
        orderLine.setSTATUS(orderLineDto.getStatus());
        return orderLine;
    }

    /**
     * Converts an Orders entity to an OrderDto object.
     *
     * @param orders the Orders entity
     * @return the OrderDto object
     */
    private OrderDto convertEntityToDto(Orders orders) {
        OrderDto orderDto = new OrderDto();
        orderDto.setHeaderId(orders.getHeaderId());
        orderDto.setOrderRefNumber(orders.getORDER_REF_NUMBER());
        orderDto.setStatus_code(String.valueOf(orders.getSTATUS()));
        orderDto.setCreatedBy(orders.getCREATED_BY());
        orderDto.setCreationDate(orders.getCREATION_DATE());
        orderDto.setCustomerId(orders.getCUSTOMER_ID());
        orderDto.setCustomerSiteUseId(orders.getCUSTOMER_SITE_USE_ID());
        orderDto.setLastUpdatedDate(orders.getLAST_UPDATED_DATE());
        orderDto.setOrderLineDtoList(convertOrderLineListToEntityList(orders.getOrderLines()));
        orderDto.setVehicleNumber(orders.getVEHICLE_NUMBER());
        orderDto.setCustomerBillToId(orders.getCUSTOMER_BILL_TO_ID());
        orderDto.setdcNo(orders.getDC_NO());
        orderDto.setStatus(get_order_status(String.valueOf(orders.getORDER_REF_NUMBER())));
        return orderDto;
    }
    public String get_order_status(String ref_numer)
    {
        String count ="0";
        try {
            count = (String) entityManager.createNativeQuery("select sug_om_cr_hold_pkg.get_manure_ord_status(?1) as CCOUNT from dual")
                    .setParameter(1, ref_numer)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            count ="0";
        }
        return count;

    }
    /**
     * Converts an OrderLine entity to an OrderLineDto object.
     *
     * @param orderLine the OrderLine entity
     * @return the OrderLineDto object
     */
    private OrderLineDto convertLineEntityToDto(OrderLine orderLine) {
        OrderLineDto orderLineDto = new OrderLineDto();
        orderLineDto.setLineId(orderLine.getLINE_ID());
        orderLineDto.setItemID(orderLine.getITEM_ID());
        orderLineDto.setQty(String.valueOf(orderLine.getQTY()));
        orderLineDto.setRate(orderLine.getRATE());
        orderLineDto.setPriceListRate(orderLine.getPRICE_LIST_RATE());
        orderLineDto.setPrimaryUOMCode(orderLine.getPRIMARY_UOM_CODE());
        orderLineDto.setCreationDate(orderLine.getCREATION_DATE());
        orderLineDto.setLastUpdatedDate(orderLine.getLAST_UPDATED_DATE());
        return orderLineDto;
    }

    /**
     * Converts a list of OrderLine entities to a list of OrderLineDto objects.
     *
     * @param orderLines the list of OrderLine entities
     * @return the list of OrderLineDto objects
     */
    private List<OrderLineDto> convertOrderLineListToEntityList(List<OrderLine> orderLines) {
        List<OrderLineDto> orderLineDtoList = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            orderLineDtoList.add(convertLineEntityToDto(orderLine));
        }
        return orderLineDtoList;
    }

    /**
     * Converts an OrderDto object to an Orders entity.
     *
     * @param orderDto the OrderDto object
     * @return the Orders entity
     */
    private Orders convertDtoToEntity(OrderDto orderDto) {
        Orders orders = new Orders();
        orders.setCREATED_BY(orderDto.getCreatedBy());
        orders.setCREATION_DATE(new Date());
        orders.setCUSTOMER_ID(orderDto.getCustomerId());
        orders.setORDER_FROM(orderDto.getOrderFrom());
        orders.setORDER_TYPE(orderDto.getOrderType());
        orders.setCUSTOMER_SITE_USE_ID(orderDto.getCustomerSiteUseId());
        orders.setCUSTOMER_BILL_TO_ID(orderDto.getCustomerBillToId());
        orders.setORG_ID(orderDto.getOrgId());
        orders.setORGN_ID(orderDto.getOrgnId());
        orders.setREMARKS(orderDto.getRemarks());
        orders.setPOSTED_FLAG(orderDto.getPostedFlag());
        orders.setSTATUS(Integer.parseInt(orderDto.getStatus()));
        //orders.setSTATUS(get_order_status(String.valueOf(orders.getORDER_REF_NUMBER())));
        orders.setSOURCE(orderDto.getSource());
        orders.setSALES_REP_ID(orderDto.getSalesRepId());
        orders.setPRICE_LIST_ID(orderDto.getPriceListId());
        orders.setVEHICLE_NUMBER(orderDto.getVehicleNumber());
        orders.setDC_NO(orderDto.getdcNo());
        return orders;
    }

    private List<OrderDto> convertEntityListToDtoList(List<Orders> ordersList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Orders orders : ordersList) {

            orderDtoList.add(convertEntityToDto(orders));
        }
        return orderDtoList;
    }

    public ResponseEntity<APIResponse<OrderDetailsDto>> getOrderDetailsByRefNumber(String orderRefNumber) {
        Orders orders = orderRepository.findByOrderRefNumber(Long.valueOf(orderRefNumber));
        OrderDetailsDto orderDetailsDto = (OrderDetailsDto) entityManager.createNativeQuery("Select TO_CHAR(H.ORG_ID) orgId,\n" +
                        "       TO_CHAR(H.ORDER_REF_NUMBER) orderRefNumber,\n" +
                        "       TO_CHAR(H.CUSTOMER_ID) customerId,\n" +
                        "       TO_CHAR(H.CUSTOMER_SITE_USE_ID) customerSiteUseId,\n" +
                        "       sug_om_cr_hold_pkg.get_manure_ord_status(H.ORDER_REF_NUMBER) as status,h.status as status_code,\n" +
                        "       (select sug_wf_pkg.get_wf_notified_to(p_item_type => 'SUGMNRS' , p_item_key => H.HEADER_ID) AS orderstatus from dual) currentStatus,\n" +
                        "       (select a.LOCATION_NAME from sug_organization_mv a where 1 = 1 and a.branch_id = H.ORG_ID) location,\n" +
                        "       (select B.LOCATION from hz_cust_site_uses_all B where B.SITE_USE_ID = H.CUSTOMER_SITE_USE_ID) shipToLocation,\n" +
                        "       (select C.LOCATION from hz_cust_site_uses_all C where C.SITE_USE_ID = H.CUSTOMER_SITE_USE_ID) billToLocation,\n" +
                        "       (select D.CUSTOMER_NAME from sug_mai_so_customer_list_v D where D.appl_code = 'MANURE_SALES' AND D.CUSTOMER_ID = H.CUSTOMER_ID and rownum=1) customerName\n" +
                        "  from sug_mai_order_hdr H\n" +
                        " where H.ORDER_REF_NUMBER = ?1")
                .setParameter(1, orders.getORDER_REF_NUMBER())
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(OrderDetailsDto.class))
                .getSingleResult();

        List<OrderLine> orderLineList = orderLineRepository.findByHeaderId(orders.getHeaderId());
        List<OrderLineDto> orderLineDtoList = convertOrderLineListToEntityList(orderLineList);
        orderDetailsDto.setOrderLines(orderLineDtoList);

        APIResponse<OrderDetailsDto> orderDetailsDtoAPIResponse = new APIResponse<>();
        orderDetailsDtoAPIResponse.setStatus(Constants.SUCCESS);
        orderDetailsDtoAPIResponse.setMessage("Fetched");
        orderDetailsDtoAPIResponse.setStatusCode(HttpStatus.OK.value());
        orderDetailsDtoAPIResponse.setData(orderDetailsDto);
        return new ResponseEntity<>(orderDetailsDtoAPIResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponseList<LookupDto>> getLookupCode() {


        List<Lookup> lookupList = entityManager.createNativeQuery("SELECT TO_CHAR(LOOKUP_CODE) LOOKUP_CODE,MEANING,DESCRIPTION, TAG FROM fnd_lookup_values_vl a where 1 = 1 and a.lookup_type = 'MANURE_SALES_STATUS'")
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(Lookup.class))
                .getResultList();

        List<LookupDto> lookupDtos = new ArrayList<>();
        for (Lookup lookup : lookupList) {
            LookupDto lookupDto = new LookupDto();
            lookupDto.setLookupCode(Integer.parseInt(lookup.getLOOKUP_CODE()));
            lookupDto.setMeaning(lookup.getMEANING());
            lookupDto.setDescription(lookup.getDESCRIPTION());
            lookupDto.setTag(lookup.getTAG());
            lookupDtos.add(lookupDto);
        }
        APIResponseList<LookupDto> apiResponseList = new APIResponseList<>();
        apiResponseList.setMessage("Fetched");
        apiResponseList.setData(lookupDtos);
        apiResponseList.setStatus(Constants.SUCCESS);
        apiResponseList.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseList, HttpStatus.OK);
    }

}
