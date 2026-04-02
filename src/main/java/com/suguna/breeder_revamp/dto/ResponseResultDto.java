package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResultDto {
    @JsonProperty("STATUS")
    String status;
    @JsonProperty("STATUSCODE")
    String statusCode;
    @JsonProperty("MESSAGE")
    String message;
    @JsonProperty("CUSTNAME")
    String CUSTNAME;
}