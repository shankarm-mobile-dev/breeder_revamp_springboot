package com.suguna.breeder_revamp.manure.services.interfaces;

import com.suguna.breeder_revamp.manure.dtos.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface OrderServices {

    ResponseEntity<APIResponse<OrderDto>> saveOrder(OrderDto orderDto);
    ResponseEntity<APIResponseList<OrderDto>> fetchOrder(Long orgId, Long customerId, Long siteUseId, String vehicleNumber, String date, Pageable pageable, Sort sort, String created_by);
    APIResponse<?> updateStatus(String orderRefNumber,int status);

    ResponseEntity<APIResponse<OrderDetailsDto>> getOrderDetailsByRefNumber(String orderRefNumber);

    ResponseEntity<APIResponseList<LookupDto>> getLookupCode();

    ResponseEntity<APIResponse<?>> sendOTPByOrderRefNumber(String orderRefNumber);
}
