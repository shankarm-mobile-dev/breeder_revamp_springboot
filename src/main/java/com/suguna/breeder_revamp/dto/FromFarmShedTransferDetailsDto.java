package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

public class FromFarmShedTransferDetailsDto {
    @Column(name = "inventory_LOCATION_ID", type = String.class)
    @JsonProperty("inventory_LOCATION_ID")
    String inventory_LOCATION_ID;
    @Column(name = "location", type = String.class)
    @JsonProperty("location")
    String location;
    @Column(name = "loc_TYPE", type = String.class)
    @JsonProperty("loc_TYPE")
    String loc_TYPE;
}
