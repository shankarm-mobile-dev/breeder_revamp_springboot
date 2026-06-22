package com.suguna.breeder_revamp.manure.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse<T> {
    String status;
    int statusCode;
    String message;
    T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
