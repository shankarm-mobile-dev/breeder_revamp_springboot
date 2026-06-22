package com.suguna.breeder_revamp.manure;


import com.suguna.breeder_revamp.manure.dtos.*;
import com.suguna.breeder_revamp.manure.services.implementations.OrderServiceImpl;
import com.suguna.breeder_revamp.manure.services.interfaces.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/manure/orders")
public class OrderController {

    OrderServices orderServices;

    @Autowired
    OrderController(OrderServiceImpl orderServices) {
        this.orderServices = orderServices;
    }

    /**
     * Send OTP by Order Ref Number
     * @param order_ref_number
     * @return
     */
    @GetMapping("/send_otp")
    public ResponseEntity<APIResponse<?>> sendOtp(@RequestParam(name = "order_ref_number", required = false, defaultValue = "") String order_ref_number) {
        return orderServices.sendOTPByOrderRefNumber(order_ref_number);
    }


    @GetMapping("/lookup")
    ResponseEntity<APIResponseList<LookupDto>> getLookup() {
        return orderServices.getLookupCode();
    }

    @PostMapping("/")
    ResponseEntity<APIResponse<OrderDto>> saveOrder(@RequestBody OrderDto orderDto) {
        return orderServices.saveOrder(orderDto);
    }

    @GetMapping("/")
    ResponseEntity<APIResponseList<OrderDto>> getOrder(@RequestParam(name = "org_id", required = false, defaultValue = "") String org_id,
                                                       @RequestParam(name = "customer_id", required = false, defaultValue = "") String customer_id,
                                                       @RequestParam(name = "site_use_id", required = false, defaultValue = "") String site_use_id,
                                                       @RequestParam(name = "vehicle_number", required = false, defaultValue = "") String vehicle_number,
                                                       @RequestParam(name = "date", required = false, defaultValue = "") String date,
                                                       @RequestParam(name = "current_page", required = false, defaultValue = "0") int page,
                                                       @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                       @RequestParam(name = "sort", required = false, defaultValue = "headerId") String sort,
                                                       @RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction,
                                                       @RequestParam(name="created_by", required =true) String created_by
    ) {
        Long orgnId = null;
        if (!Objects.equals(org_id, "")) {
            orgnId = Long.valueOf(org_id);
        }
        Long customerId = null;
        if (!Objects.equals(customer_id, ""))
            customerId = Long.valueOf(customer_id);
        Long siteUseId = null;
        if (!Objects.equals(site_use_id, ""))
            siteUseId = Long.valueOf(site_use_id);
        String vehicleNumber = null;
        if (!Objects.equals(vehicle_number, ""))
            vehicleNumber = vehicle_number;
        Pageable pageable = PageRequest.of(page, size);
        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        return orderServices.fetchOrder(orgnId, customerId, siteUseId, vehicleNumber, date, pageable, sortOrder, created_by);
    }

    @GetMapping("/order_details")
    public ResponseEntity<APIResponse<OrderDetailsDto>> getOrderDetailsByRefNumber(@RequestParam(name = "order_ref_number", required = false, defaultValue = "") String order_ref_number) {
        return orderServices.getOrderDetailsByRefNumber(order_ref_number);
    }
}
