package com.suguna.breeder_revamp.model;

import com.suguna.breeder_revamp.utils.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SUG_EGG_VEHICLE_PLAN_DTL")
@Getter
@Setter
public class SugEggVehiclePlanDtl {

        @Id
        @Column(name = "PLAN_DTL_ID")
        private Long planDtlId;

        @Column(name = "ACTUAL_ARRIVAL_DATE")
        private LocalDateTime actualArrivalDate;

        @Column(name = "ACTUAL_ARRIVAL_IMAGE")
        private String actualArrivalImage;

        // Getters and Setters
    }
