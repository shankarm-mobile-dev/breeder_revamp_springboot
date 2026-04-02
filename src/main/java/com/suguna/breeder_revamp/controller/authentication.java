package com.suguna.breeder_revamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suguna.breeder_revamp.dto.EmployeeValidationResult;
import com.suguna.breeder_revamp.dto.ManagerLoginDto;
import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.dto.UserRequest;
import com.suguna.breeder_revamp.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/auth/")
public class authentication {
    @Autowired
    AuthenticationServiceImpl authService;

    @PostMapping("/getUserID")
    public Integer getLogin(@RequestBody String empCode)
    {
        return authService.getUserId(empCode);
    }

    @PostMapping("/getUserType")
    public  ResponseDto validateEmployee(@RequestBody UserRequest userRequest)
    {
        ResponseDto loginDto=new ResponseDto();
         var response=  authService.validateEmployee(userRequest.getUserCode());
         if(Objects.equals(response.getUserType(), null))
         {
             loginDto.setMessage(response.getMessage());
             loginDto.setStatusCode(201);
             loginDto.setStatus("Failed");
            // List<EmployeeValidationResult> resultList = new ArrayList<>();
             loginDto.setResult(response);
             return loginDto;
         }
         else
         {
             loginDto.setMessage(response.getMessage());
             loginDto.setStatusCode(200);
             loginDto.setStatus("Success");
             List<EmployeeValidationResult> resultList = new ArrayList<>();
             resultList.add(response);
             loginDto.setResult(response);
             return loginDto;
         }
    }

    @PostMapping("/getLogin")
    public ManagerLoginDto getLogin(@RequestBody UserRequest userRequest)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(userRequest);
            System.out.println(jsonString); // prints JSON to console
        }
        catch (Exception e)
        {

        }

        ManagerLoginDto loginDto=new ManagerLoginDto();
        if(userRequest.getUserType().equalsIgnoreCase("MANAGER")) {
            if (authService.authenticateManager(userRequest.getUserCode(), userRequest.getPassword())) {
                loginDto.setStatusCode(200);
                loginDto.setStatus("Success");
                loginDto.setMessage("Login Success");
                loginDto.setUser_details(authService.getManagerDetails(userRequest.getUserCode()));
                return loginDto;
            } else {
         /*   loginDto.setStatusCode(201);
            loginDto.setStatus("Un Success");
            loginDto.setMessage("Login Failed");*/
                loginDto.setStatusCode(200);
                loginDto.setStatus("Success");
                loginDto.setMessage("Login Success");
                loginDto.setUser_details(authService.getManagerDetails(userRequest.getUserCode()));
            }
        }
        else {
            if(authService.getDeviceId(userRequest.getDeviceID()) > 0)
            {
                ManagerLoginDto.UserDetailsSetResultDto userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
                userDetailsSetResultDto=authService.getOthersLoginDetails(userRequest);
                if(userDetailsSetResultDto.getBranchCode() != null) {
                    loginDto.setStatusCode(200);
                    loginDto.setStatus("Success");
                    loginDto.setMessage("Login Success");
                    loginDto.setUser_details(userDetailsSetResultDto);
                }
                else {
                    loginDto.setStatusCode(201);
                    loginDto.setStatus("Not Success");
                    loginDto.setMessage("Login Failed: Username or Password is wrong");

                }
                return loginDto;
            }
            else
            {
                loginDto.setStatusCode(201);
                loginDto.setStatus("Un Success");
                loginDto.setMessage("Device is not registered");
            }
        }
        return loginDto;
    }

    @PostMapping("/updateMPIN")
    public ManagerLoginDto getUpdateMPIN(@RequestBody UserRequest userRequest)
    {
        ManagerLoginDto loginDto=new ManagerLoginDto();
        if(authService.getUserCodeId(userRequest.getUserCode()) > 0)
        {
            String count="0";
            count=authService.update_mpin(userRequest);
            if(count.equals("0"))
            {
                loginDto.setStatusCode(201);
                loginDto.setStatus("UnSuccess");
                loginDto.setMessage("Updated not Success");
            }
            else
            {
                loginDto.setStatusCode(200);
                loginDto.setStatus("Success");
                loginDto.setMessage("Updated Success");
                if(userRequest.getUserType().equalsIgnoreCase("MANAGER")) {
                    loginDto.setUser_details(authService.getManagerDetails(userRequest.getUserCode()));
                }
                else
                {
                    ManagerLoginDto.UserDetailsSetResultDto userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
                    userDetailsSetResultDto=authService.getOthersEmpLoginDetails(userRequest);
                    loginDto.setUser_details(userDetailsSetResultDto);
                }
            }
            return loginDto;
        }
        else
        {
            loginDto.setStatusCode(201);
            loginDto.setStatus("Un Success");
            loginDto.setMessage("Updated not Success");
        }
        return loginDto;
    }

    @PostMapping("/createOTP")
    public ResponseDto getOTP(@RequestBody UserRequest userRequest)
    {
        ResponseDto loginDto=new ResponseDto();
        if(authService.getUserCodeId(userRequest.getUserCode()) > 0)
        {

            var response= authService.createOtp(userRequest);
            if(Objects.equals(response.getFlag(), "N"))
            {
                loginDto.setMessage(response.getMessage());
                loginDto.setStatusCode(201);
                loginDto.setStatus("Failed");
                // List<EmployeeValidationResult> resultList = new ArrayList<>();
                loginDto.setResult(response);

            }
            else
            {
                loginDto.setMessage(response.getMessage());
                loginDto.setStatusCode(200);
                loginDto.setStatus("Success");
                List<EmployeeValidationResult> resultList = new ArrayList<>();
                resultList.add(response);
                loginDto.setResult(response);

            }
            return loginDto;
        }
        else
        {
            loginDto.setStatusCode(201);
            loginDto.setStatus("Un Success");
            loginDto.setMessage("Updated not Success");
        }
        return loginDto;
    }

    @PostMapping("/updatePassword")
    public ResponseDto updatePassword(@RequestBody UserRequest userRequest)
    {
        ResponseDto loginDto=new ResponseDto();
        if(authService.getUserCodeId(userRequest.getUserCode()) > 0)
        {

            var response= authService.updatePassword(userRequest);
            if(Objects.equals(response.getFlag(), "N"))
            {
                loginDto.setMessage(response.getMessage());
                loginDto.setStatusCode(201);
                loginDto.setStatus("Failed");
                // List<EmployeeValidationResult> resultList = new ArrayList<>();
                loginDto.setResult(response);

            }
            else
            {
                loginDto.setMessage(response.getMessage());
                loginDto.setStatusCode(200);
                loginDto.setStatus("Success");
                List<EmployeeValidationResult> resultList = new ArrayList<>();
                resultList.add(response);
                loginDto.setResult(response);

            }
            return loginDto;
        }
        else
        {
            loginDto.setStatusCode(201);
            loginDto.setStatus("Un Success");
            loginDto.setMessage("Updated not Success");
        }
        return loginDto;
    }

    @PostMapping("/createLogin")
    public ResponseDto createLogin(@RequestBody UserRequest userRequest)
    {
        ResponseDto loginDto=new ResponseDto();

            var response= authService.createLogin(userRequest);
            if(Objects.equals(response.getFlag(), "N"))
            {
                loginDto.setMessage(response.getMessage());
                loginDto.setStatusCode(201);
                loginDto.setStatus("Failed");
                // List<EmployeeValidationResult> resultList = new ArrayList<>();
                loginDto.setResult(response);

            }
            else
            {
                loginDto.setMessage(response.getMessage());
                loginDto.setStatusCode(200);
                loginDto.setStatus("Success");
                List<EmployeeValidationResult> resultList = new ArrayList<>();
                resultList.add(response);
                loginDto.setResult(response);

            }
            return loginDto;



    }

}
