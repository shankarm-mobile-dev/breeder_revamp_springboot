package com.suguna.breeder_revamp.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {
    String status;
    int statusCode;
    String message;
    T data;
}
