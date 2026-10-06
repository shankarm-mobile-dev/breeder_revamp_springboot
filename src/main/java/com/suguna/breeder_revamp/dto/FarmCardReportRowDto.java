package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmCardReportRowDto {

    @Column(name = "TRANS_ID", type = String.class)
    @JsonProperty("transId")
    private String transId;

    @Column(name = "FARM_CODE", type = String.class)
    @JsonProperty("farmCode")
    private String farmCode;

    @Column(name = "FLOCK_ID", type = String.class)
    @JsonProperty("flockId")
    private String flockId;

    @Column(name = "SHED_CODE", type = String.class)
    @JsonProperty("shedCode")
    private String shedCode;

    @Column(name = "BATCH_ID", type = String.class)
    @JsonProperty("batchId")
    private String batchId;

    @Column(name = "LINE_NO", type = String.class)
    @JsonProperty("lineNo")
    private String lineNo;

    @Column(name = "TXN_TYPE", type = String.class)
    @JsonProperty("txnType")
    private String txnType;

    @Column(name = "ITEM_ID", type = String.class)
    @JsonProperty("itemId")
    private String itemId;

    @Column(name = "SEX", type = String.class)
    @JsonProperty("sex")
    private String sex;

    @Column(name = "TXN_DATE", type = String.class)
    @JsonProperty("txnDate")
    private String txnDate;

    @Column(name = "AGE", type = String.class)
    @JsonProperty("age")
    private String age;

    @Column(name = "GRADE", type = String.class)
    @JsonProperty("grade")
    private String grade;

    @Column(name = "QTY", type = String.class)
    @JsonProperty("qty")
    private String qty;

    @Column(name = "UOM", type = String.class)
    @JsonProperty("uom")
    private String uom;

    @Column(name = "CREATED_BY", type = String.class)
    @JsonProperty("createdBy")
    private String createdBy;

    @Column(name = "CREATION_DATE", type = String.class)
    @JsonProperty("creationDate")
    private String creationDate;

    @Column(name = "STATUS", type = String.class)
    @JsonProperty("status")
    private String status;

    @Column(name = "WEIGHT", type = String.class)
    @JsonProperty("weight")
    private String weight;

    @Column(name = "REMARK", type = String.class)
    @JsonProperty("remark")
    private String remark;

    @Column(name = "REASON", type = String.class)
    @JsonProperty("reason")
    private String reason;

    @Column(name = "TEMP_MIN", type = String.class)
    @JsonProperty("tempMin")
    private String tempMin;

    @Column(name = "TEMP_MAX", type = String.class)
    @JsonProperty("tempMax")
    private String tempMax;

    @Column(name = "LIGTHING_START_HRS", type = String.class)
    @JsonProperty("lightingStartHrs")
    private String lightingStartHrs;

    @Column(name = "LIGTHING_END_HRS", type = String.class)
    @JsonProperty("lightingEndHrs")
    private String lightingEndHrs;

    @Column(name = "SANITIZATION_START_HRS", type = String.class)
    @JsonProperty("sanitizationStartHrs")
    private String sanitizationStartHrs;

    @Column(name = "SANITIZATION_END_HRS", type = String.class)
    @JsonProperty("sanitizationEndHrs")
    private String sanitizationEndHrs;

    @Column(name = "PH_LEVEL", type = String.class)
    @JsonProperty("phLevel")
    private String phLevel;

    @Column(name = "PM_LEVEL", type = String.class)
    @JsonProperty("pmLevel")
    private String pmLevel;

    @Column(name = "REMARKS", type = String.class)
    @JsonProperty("remarks")
    private String remarks;

    @Column(name = "BRANCH_ID", type = String.class)
    @JsonProperty("branchId")
    private String branchId;

    @Column(name = "SIDE_NO", type = String.class)
    @JsonProperty("sideNo")
    private String sideNo;

    @Column(name = "BIRD_TYPE", type = String.class)
    @JsonProperty("birdType")
    private String birdType;

    @Column(name = "DEBEAKING", type = String.class)
    @JsonProperty("debeaking")
    private String debeaking;
}
