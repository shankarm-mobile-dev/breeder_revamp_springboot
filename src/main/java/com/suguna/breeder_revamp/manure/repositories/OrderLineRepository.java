/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 6/3/2025
 */

package com.suguna.breeder_revamp.manure.repositories;

import com.suguna.breeder_revamp.manure.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM SUG.SUG_MAI_ORDER_LINE where HEADER_ID = :headerId")
    List<OrderLine> findByHeaderId(Long headerId);
}
