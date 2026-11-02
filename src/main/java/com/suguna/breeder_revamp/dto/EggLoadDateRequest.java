package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EggLoadDateRequest {

    /** Vehicle plan detail id (PLAN_DTL_ID) — PHP {@code $obj->id}. */
    @JsonProperty("id")
    @JsonAlias({"plan_dtl_id", "PLAN_DTL_ID", "pid"})
    private String id;

    @JsonProperty("loadstartdate")
    @JsonAlias({"load_start_date", "loading_start_date"})
    private String loadstartdate;

    @JsonProperty("loadenddate")
    @JsonAlias({"load_end_date", "loading_end_date"})
    private String loadenddate;

    /** Collection plan line id — PHP {@code $obj->transid} → SUG_EGG_COLL_PLAN_LINES.TRANS_LINE_ID. */
    @JsonProperty("transid")
    @JsonAlias({"trans_line_id", "TRANS_LINE_ID"})
    private String transid;
}
