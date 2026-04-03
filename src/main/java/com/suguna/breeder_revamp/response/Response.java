package com.suguna.breeder_revamp.response;

import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.ShedReadyResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class Response {

    public static <T> ResponseEntity<ShedReadyResponseDto<T>> buildSingleResponse(
            String status, HttpStatus httpStatus, String message, T data) {

        ShedReadyResponseDto<T> apiResponse = ShedReadyResponseDto.<T>builder()
                .status(status)
                .statusCode(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponseList<T>> buildSingleResponseList(
            String status, HttpStatus httpStatus, String message, List<T> data) {

        ApiResponseList<T> apiResponse = ApiResponseList.<T>builder()
                .status(status)
                .statusCode(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }
}
