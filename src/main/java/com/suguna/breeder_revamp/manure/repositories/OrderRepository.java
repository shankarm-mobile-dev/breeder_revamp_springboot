package com.suguna.breeder_revamp.manure.repositories;

import com.suguna.breeder_revamp.manure.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>, JpaSpecificationExecutor<Orders> {
    @Query("SELECT o FROM Orders o LEFT JOIN FETCH o.orderLines WHERE o.headerId = :headerId")
    Orders findByIdWithOrderLines(@Param("headerId") Long headerId);

    Orders findByOrderRefNumber(Long orderRefNumber);
}
