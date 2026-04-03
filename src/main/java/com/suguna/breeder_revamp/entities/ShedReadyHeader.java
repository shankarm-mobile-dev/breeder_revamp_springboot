package com.suguna.breeder_revamp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUG_MAI_GPPS_SHED_READY_HEADER", schema = "SUG")
public class ShedReadyHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shed_gpps_hdr_seq")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_SHED_READY_HEADER_S", allocationSize = 1, name = "shed_gpps_hdr_seq")
    @Column(name = "TRANS_ID")
    private Long transId;
    @Column(name = "ORGANIZATION_ID")
    private int orgId;
    @Column(name = "FARM_CODE")
    private String farmCode;
    @Column(name = "BATCH_ID")
    private Integer batchId;
    @Column(name = "SHED_CODE")
    private String shedCode;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "POSTED_FLAG")
    private String postedFlag;
    @Column(name = "FARMER_STATUS")
    private String farmerStatus;
    @Column(name = "MANAGER_STATUS")
    private String managerStatus;

}
