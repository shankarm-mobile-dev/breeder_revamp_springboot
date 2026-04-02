package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.EmployeeValidationResult;
import com.suguna.breeder_revamp.dto.ManagerLoginDto;

public interface AuthenticationService {
     Integer getUserId(String empCode);
     EmployeeValidationResult validateEmployee(String empNo);
     ManagerLoginDto.UserDetailsSetResultDto getManagerDetails(String UserCode);
     boolean authenticateManager(String username, String password);


}
