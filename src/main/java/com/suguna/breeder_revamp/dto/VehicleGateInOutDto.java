package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleGateInOutDto {
    @JsonProperty("GATE_IN_ID")
    Long GATE_IN_ID;
    @JsonProperty("BRANCH_ID")
    Long BRANCH_ID;
    @JsonProperty("VEHICLE_NO")
    String VEHICLE_NO;
    @JsonProperty("DRIVER_NAME")
    String DRIVER_NAME;
    @JsonProperty("DRIVER_MOBILE_NO")
    String DRIVER_MOBILE_NO;
    @JsonProperty("PURPOSE")
    String PURPOSE;
    @JsonProperty("GATE_IN_DATE")
    String GATE_IN_DATE;
    @JsonProperty("GATE_OUT_DATE")
    String GATE_OUT_DATE;
    @JsonProperty("GATE_IN_IMAGE")
    String GATE_IN_IMAGE;
    @JsonProperty("GATE_OUT_IMAGE")
    String GATE_OUT_IMAGE;
    @JsonProperty("CREATED_BY")
    String CREATED_BY;
    @JsonProperty("CREATED_DATE")
    String CREATED_DATE;
    @JsonProperty("UPDATED_BY")
    String UPDATED_BY;
    @JsonProperty("UPDATED_DATE")
    String UPDATED_DATE;
}
