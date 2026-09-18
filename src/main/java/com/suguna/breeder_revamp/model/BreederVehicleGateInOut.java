package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BREEDER_VEHICLE_GATE_IN_OUT", schema = "SUG")
public class BreederVehicleGateInOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GATE_IN_ID")
    private Long gateInId;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @Column(name = "VEHICLE_NO", nullable = false, length = 30)
    private String vehicleNo;

    @Column(name = "DRIVER_NAME", length = 100)
    private String driverName;

    @Column(name = "DRIVER_MOBILE_NO", length = 15)
    private String driverMobileNo;

    @Column(name = "PURPOSE", length = 100)
    private String purpose;

    @Column(name = "GATE_IN_DATE")
    private Date gateInDate;

    @Column(name = "GATE_OUT_DATE")
    private Date gateOutDate;

    @Column(name = "GATE_IN_IMAGE", length = 500)
    private String gateInImage;

    @Column(name = "GATE_OUT_IMAGE", length = 500)
    private String gateOutImage;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
}
