package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FarmCardReportRequestDto {

    @JsonProperty("flockNo")
    @JsonAlias({"flockNo", "flockNo"})
    private String flockNo;

    @JsonProperty("shedNo")
    @JsonAlias({"shedNO", "shed_no", "shedno", "SHED_CODE"})
    private String shedNo;

    @JsonProperty("branchId")
    private String branchId;

    /** Transaction date, e.g. DD-MM-YYYY or yyyy-MM-dd */
    @JsonProperty("date")
    @JsonAlias({"txnDate", "txn_date"})
    private String date;
}
