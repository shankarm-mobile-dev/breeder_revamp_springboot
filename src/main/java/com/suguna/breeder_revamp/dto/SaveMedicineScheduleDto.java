package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class SaveMedicineScheduleDto {
    @JsonProperty("FARM_CODE")
    public String farmCode;

    @JsonProperty("ITEM_TYPE")
    public String itemType;

    @JsonProperty("ITEM_ID")
    public String itemID;
    @JsonProperty("FLOCK_ID")
    public String flockId;

    @JsonProperty("SEX")
    public String sex;

    @JsonProperty("AGE")
    public String age;

    @JsonProperty("GRADE")
    public String grade;

    @JsonProperty("QTY")
    public String qty;

    @JsonProperty("DATE_FROM")
    public String dateFrom;

    @JsonProperty("DATE_TO")
    public String dateTo;

    @JsonProperty("UOM")
    public String uom;

    @JsonProperty("CREATED_BY")
    public String createdBy;

    @JsonProperty("CREATION_DATE")
    public Date creationDate;

    @JsonProperty("TRANS_ID")
    public String transId;

}
