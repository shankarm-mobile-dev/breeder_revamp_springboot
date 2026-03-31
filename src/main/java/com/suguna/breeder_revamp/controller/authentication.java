package com.suguna.breeder_revamp.controller;

import com.suguna.breeder_revamp.dto.EmpDto;
import com.suguna.breeder_revamp.dto.EmployeeValidationResult;
import com.suguna.breeder_revamp.dto.ManagerLoginDto;
import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.model.ManagerLoginModel;
import com.suguna.breeder_revamp.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/farm/")
public class authentication {
    @Autowired
    AuthenticationServiceImpl authService;

    @PostMapping("/getUserID")
    public Integer getLogin(@RequestBody String empCode)
    {
        return authService.getUserId(empCode);
    }

    @PostMapping("/getUserType")
    public  ResponseDto validateEmployee(String empNo)
    {
        ResponseDto loginDto=new ResponseDto();
         var response=  authService.validateEmployee(empNo);
         if(Objects.equals(response.getEmpType(), null))
         {
             loginDto.setMessage(response.getMessage());
             loginDto.setStatusCode("201");
             loginDto.setStatus("Failed");
             List<EmployeeValidationResult> resultList = new ArrayList<>();
             loginDto.setResult(resultList);
             return loginDto;
         }else {
             loginDto.setMessage(response.getMessage());
             loginDto.setStatusCode("200");
             loginDto.setStatus("Success");
             List<EmployeeValidationResult> resultList = new ArrayList<>();
             resultList.add(response);
             loginDto.setResult(resultList);
             return loginDto;
         }
    }

    @PostMapping("/ManagerLogin")
    public ManagerLoginDto getLogin(@RequestBody String empCode , String password)
    {
        ManagerLoginDto loginDto=new ManagerLoginDto();
        if(authService.authenticateManager(empCode,password))
        {
            loginDto.setStatuscode("200");
            loginDto.setStatus("Success");
            loginDto.setMessage("Login Success");
            loginDto.setUser_details(authService.getManagerDetails(empCode));
            return loginDto;
        }
        else {
            loginDto.setStatuscode("201");
            loginDto.setStatus("Un Success");
            loginDto.setMessage("Login Failed");
        }
        return loginDto;
    }






}
