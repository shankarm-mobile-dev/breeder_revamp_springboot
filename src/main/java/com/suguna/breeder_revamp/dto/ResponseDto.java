package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDto<T> {
    @JsonProperty("STATUS")
    String status;
    @JsonProperty("STATUSCODE")
    String statusCode;
    @JsonProperty("MESSAGE")
    String message;
    @JsonProperty("RESULT")
    List<T> result;

}