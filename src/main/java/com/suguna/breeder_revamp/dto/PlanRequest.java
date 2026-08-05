package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanRequest {
    @JsonProperty("PLAN_DTL_ID")
    long PLAN_DTL_ID;
    @JsonProperty("ACTUAL_ARRIVAL_DATE")
    String ACTUAL_ARRIVAL_DATE;
    @JsonProperty("ACTUAL_DEPATURE_DATE")
    String ACTUAL_DEPATURE_DATE;
    @JsonProperty("ACTUAL_ARRIVAL_IMAGE")
    String ACTUAL_ARRIVAL_IMAGE;
}
