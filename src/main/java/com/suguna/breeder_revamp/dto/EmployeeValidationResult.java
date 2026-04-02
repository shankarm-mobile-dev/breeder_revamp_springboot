package com.suguna.breeder_revamp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeValidationResult {
    private String message;
    private String flag;
    private String userType;
}