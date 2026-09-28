package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SUG_MAI_BREEDER_VEHICLE_GATEINOUT", schema = "SUG")
public class SugMaiBreederVehicleGateinout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GATE_IN_ID")
    private Long gateInId;

    @Column(name = "FROM_BRANCH_ID")
    private Long fromBranchId;

    @Column(name = "VEHICLE_NO", length = 30)
    private String vehicleNo;

    @Column(name = "DRIVER_NAME", length = 100)
    private String driverName;

    @Column(name = "DRIVER_MOBILE_NO", length = 15)
    private String driverMobileNo;

    @Column(name = "PURPOSE", length = 100)
    private String purpose;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FROM_GATE_IN_DATE")
    private Date fromGateInDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FROM_GATE_OUT_DATE")
    private Date fromGateOutDate;

    @Column(name = "FROM_CREATED_BY", length = 50)
    private String fromCreatedBy;

    @Column(name = "FROM_CREATED_DATE")
    private Date fromCreatedDate;

    @Column(name = "FROM_UPDATED_BY", length = 50)
    private String fromUpdatedBy;

    @Column(name = "FROM_UPDATED_DATE")
    private Date fromUpdatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TO_GATE_IN_DATE")
    private Date toGateInDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TO_GATE_OUT_DATE")
    private Date toGateOutDate;

    @Column(name = "TO_BRANCH_ID")
    private Long toBranchId;

    @Column(name = "TO_FARM_CREATED_BY", length = 50)
    private String toFarmCreatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TO_FARM_CREATED_DATE")
    private Date toFarmCreatedDate;

    @Column(name = "TO_FARM_UPDATED_BY", length = 50)
    private String toFarmUpdatedBy;

    @Column(name = "TO_FARM_UPDATED_DATE")
    private Date toFarmUpdatedDate;

    @Column(name = "SHIPMENT_NO", length = 50)
    private String shipmentNo;

    @Column(name = "ORDER_NO", length = 50)
    private String orderNo;

    @Column(name = "IN_STATUS", length = 20)
    private String inStatus;
}
