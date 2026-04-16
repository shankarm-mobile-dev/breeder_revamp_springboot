package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUG_MAI_GPPS_ITEM_ALLOCATION", schema = "SUG")
public class SaveMedicineScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shed_gpps_medicine_hdr_seq")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_ITEM_ALLOCATION_S", allocationSize = 1, name = "shed_gpps_medicine_hdr_seq")
    @Column(name = "TRANS_ID")
    private Long transId;
    @Column(name = "ITEM_TYPE")
    private String itemType;
    @Column(name = "ITEM_ID")
    private String itemId;
    @Column(name = "FLOCK_ID")
    private String flockId;
    @Column(name = "AGE")
    private String age;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "GRADE")
    private String grade;
    @Column(name = "QTY")
    private String qty;
    @Column(name = "DATE_FROM")
    private Date dateFrom;
    @Column(name = "DATE_TO")
    private Date dateTo;
    @Column(name = "UOM")
    private String uom;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "FARM_CODE")
    private String farmCode;
}
