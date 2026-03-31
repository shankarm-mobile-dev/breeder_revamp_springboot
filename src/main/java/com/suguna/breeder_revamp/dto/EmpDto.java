package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpDto {

    @JsonProperty("ID")
    String id;

    @JsonProperty("NAME")
    String name;

    @JsonProperty("AGE")
    String age;

    @JsonProperty("FARM_NAME")
    String farm_name;

    @JsonProperty("EMPCODE")
    String empCode;


}
