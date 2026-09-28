package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleGateInOutDto {
    @JsonProperty("GATE_IN_ID")
    Long GATE_IN_ID;

    /** Mobile sends FROM/TO; ignored by save APIs that already target a specific endpoint. */
    @JsonProperty("GATE_FLOW")
    @JsonAlias({"FLOW_TYPE", "IN_OUT", "gate_flow"})
    String GATE_FLOW;

    @JsonProperty("FROM_BRANCH_ID")
    @JsonAlias({"BRANCH_ID"})
    Long FROM_BRANCH_ID;

    @JsonProperty("VEHICLE_NO")
    String VEHICLE_NO;

    @JsonProperty("DRIVER_NAME")
    String DRIVER_NAME;

    @JsonProperty("DRIVER_MOBILE_NO")
    String DRIVER_MOBILE_NO;

    @JsonProperty("PURPOSE")
    String PURPOSE;

    @JsonProperty("FROM_GATE_IN_DATE")
    @JsonAlias({"GATE_IN_DATE"})
    String FROM_GATE_IN_DATE;

    @JsonProperty("FROM_GATE_OUT_DATE")
    @JsonAlias({"GATE_OUT_DATE"})
    String FROM_GATE_OUT_DATE;

    @JsonProperty("FROM_CREATED_BY")
    @JsonAlias({"CREATED_BY"})
    String FROM_CREATED_BY;

    @JsonProperty("FROM_CREATED_DATE")
    @JsonAlias({"CREATED_DATE"})
    String FROM_CREATED_DATE;

    @JsonProperty("FROM_UPDATED_BY")
    @JsonAlias({"UPDATED_BY"})
    String FROM_UPDATED_BY;

    @JsonProperty("FROM_UPDATED_DATE")
    @JsonAlias({"UPDATED_DATE"})
    String FROM_UPDATED_DATE;

    @JsonProperty("TO_GATE_IN_DATE")
    @JsonAlias({"TO_GATE_IN_DATETIME"})
    String TO_GATE_IN_DATE;

    @JsonProperty("TO_GATE_OUT_DATE")
    @JsonAlias({"TO_GATE_OUT_DATETIME", "GATE_OUT_DATE"})
    String TO_GATE_OUT_DATE;

    @JsonProperty("TO_BRANCH_ID")
    Long TO_BRANCH_ID;

    @JsonProperty("TO_FARM_CREATED_BY")
    @JsonAlias({"TO_CREATED_BY", "empcode"})
    String TO_FARM_CREATED_BY;

    @JsonProperty("TO_FARM_CREATED_DATE")
    @JsonAlias({"TO_CREATED_DATE"})
    String TO_FARM_CREATED_DATE;

    @JsonProperty("TO_FARM_UPDATED_BY")
    @JsonAlias({"UPDATED_BY"})
    String TO_FARM_UPDATED_BY;

    @JsonProperty("TO_FARM_UPDATED_DATE")
    @JsonAlias({"UPDATED_DATE"})
    String TO_FARM_UPDATED_DATE;

    @JsonProperty("SHIPMENT_NO")
    String SHIPMENT_NO;

    @JsonProperty("ORDER_NO")
    String ORDER_NO;

    @JsonProperty("IN_STATUS")
    String IN_STATUS;
}
