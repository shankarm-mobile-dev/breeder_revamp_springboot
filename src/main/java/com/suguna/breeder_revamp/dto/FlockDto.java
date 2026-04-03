package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

public class FlockDto {
    @Column(name = "FLOCK",type = String.class)
    @JsonProperty("FLOCK")
    public String flock;
}
