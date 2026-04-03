package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponseDto <T> {
    @JsonProperty("status")
    String status;
    @JsonProperty("statusCode")
    int statusCode;
    @JsonProperty("message")
    String message;
    @JsonProperty("data")
    T result;

}