package com.suguna.breeder_revamp.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiResponseList<T> {
    String status;
    int statusCode;
    String message;
    List<T> data;
}
