/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 7/3/2025
 */

package com.suguna.breeder_revamp.manure.specifications;

import com.suguna.breeder_revamp.manure.models.Orders;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderEntitySpecification {
    public static Specification<Orders> getEntities(String created_by,Long orgId, Long customerId, String vehicleNumber, Long siteUseId, String date) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(date != null)
            {

                try {
                    // Parse the date string to a Date object
                    SimpleDateFormat originalFormatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date oldDate = originalFormatter.parse(date);

                    // Format the date to the desired format
                    SimpleDateFormat newFormatter = new SimpleDateFormat("dd-MMM-yyyy");
                    String newDateString = newFormatter.format(oldDate);

                    // Use BETWEEN function to compare the date range
                    Date startDate = newFormatter.parse(newDateString);
                    Date endDate = new Date(startDate.getTime() + (24 * 60 * 60 * 1000) - 1); // Add 23:59:59 to the date

                    predicates.add(criteriaBuilder.between(
                            root.get("CREATION_DATE"),
                            startDate,
                            endDate
                    ));
                } catch (Exception e) {
                    System.out.println("Errr "+e.getMessage());
                }
            }

            predicates.add(criteriaBuilder.equal(root.get("SOURCE"),"MANURE_SALES"));
            predicates.add(criteriaBuilder.equal(root.get("CREATED_BY"),created_by));

            if (orgId != null) {
                predicates.add(criteriaBuilder.equal(root.get("ORG_ID"), orgId));
            }
            if (customerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("CUSTOMER_ID"), customerId));
            }
            if (vehicleNumber != null) {
                predicates.add(criteriaBuilder.equal(root.get("VEHICLE_NUMBER"), vehicleNumber));
            }
            if (siteUseId != null) {
                predicates.add(criteriaBuilder.equal(root.get("CUSTOMER_SITE_USE_ID"), siteUseId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
