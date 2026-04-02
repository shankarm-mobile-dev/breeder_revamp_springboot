package com.suguna.breeder_revamp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suguna.breeder_revamp.dto.EmployeeValidationResult;
import com.suguna.breeder_revamp.dto.ManagerLoginDto;
import com.suguna.breeder_revamp.dto.UserRequest;
import com.suguna.breeder_revamp.model.OTPModel;
import com.suguna.breeder_revamp.repositories.EmpDataRepository;
import com.suguna.breeder_revamp.repositories.OtpRepositories;
import com.suguna.breeder_revamp.smsgateway.SMSGateway;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import com.suguna.breeder_revamp.utils.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    EmpDataRepository empDataRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    OtpRepositories otpRepositories;

    @Autowired
    SMSGateway smsGateway;

    public Integer getUserId(String empCode) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_manager_type(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, empCode)
                .getSingleResult()).intValue();
    }


    @Transactional(readOnly = true)
    public EmployeeValidationResult validateEmployee(String empNo) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.check_user_valid");
        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
       // sp.registerStoredProcedureParameter("p_out_msg", String.class, ParameterMode.OUT);
       // sp.registerStoredProcedureParameter("p_out_flag", String.class, ParameterMode.OUT);
       // sp.registerStoredProcedureParameter("p_emp_type", String.class, ParameterMode.OUT);
        sp.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        sp.setParameter(1, empNo);
        sp.execute();
        ResultSet resultSet = (ResultSet) sp.getOutputParameterValue(2);
        EmployeeValidationResult result = new EmployeeValidationResult();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String message = null;          // by column name
            String flag = null;
            String emptype = null;
            try {
                message = resultSet.getString(2);
                flag = resultSet.getString(1);
                emptype = resultSet.getString(3);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
                  // by column index (3rd column)

            result.setMessage(message);
            result.setFlag(flag);
            result.setUserType(emptype);
            System.out.println("FLAG: " + flag + ", MESSAGE: " + message + ", TYPE: " + emptype);
        }



        return result;
    }


    public boolean authenticateManager(String username, String password) {
        String ldapUrl = "ldap://10.4.1.71";
        String domain = "Sugunapoultry.com";
        String empCode="0";
        empCode=get_emp_to_ad_id(username);
        String principal = empCode + "@" + domain;
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapUrl);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, principal);
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext ctx = new InitialDirContext(env);
            ctx.close();
            return true;
        } catch (NamingException e) {
            return false;
        }
    }

    @Override
    public ManagerLoginDto.UserDetailsSetResultDto getManagerDetails(String UserCode) {
        ManagerLoginDto.UserDetailsSetResultDto userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
        try {

            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getmanager_login");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, UserCode);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
                userDetailsSetResultDto = ResultSetMapper.mapResultSetToObject(resultSet, ManagerLoginDto.UserDetailsSetResultDto.class);


            }
        } catch (Exception e) {

        }
        return userDetailsSetResultDto;
    }

    public String get_emp_to_ad_id(String userName)
    {
        String count ="0";
        try {
            count = (String) entityManager.createNativeQuery("select sug_hrms_pkg.get_AD_emp_no(?1) as CCOUNT from dual")
                    .setParameter(1, userName)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            count ="0";
        }
        return count;

    }
    public Integer getDeviceId(String deviceID) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_device_register(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, deviceID)
                .getSingleResult()).intValue();
    }

    public Integer getUserCodeId(String userCode) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_userid_register(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, userCode)
                .getSingleResult()).intValue();
    }


    public ManagerLoginDto.UserDetailsSetResultDto getOthersLoginDetails(UserRequest UserCode) {
        ManagerLoginDto.UserDetailsSetResultDto userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
        try {

            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getothers_login");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, UserCode.getUserCode());
            storedProcedureQuery.setParameter(2, UserCode.getPassword());
            storedProcedureQuery.setParameter(3, UserCode.getDeviceID());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
                userDetailsSetResultDto = ResultSetMapper.mapResultSetToObject(resultSet, ManagerLoginDto.UserDetailsSetResultDto.class);

            }
        } catch (Exception e) {

        }
        return userDetailsSetResultDto;
    }

    public ManagerLoginDto.UserDetailsSetResultDto getOthersEmpLoginDetails(UserRequest UserCode) {
        ManagerLoginDto.UserDetailsSetResultDto userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
        try {

            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getothersemp_login");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, UserCode.getUserCode());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                userDetailsSetResultDto=new ManagerLoginDto.UserDetailsSetResultDto();
                userDetailsSetResultDto = ResultSetMapper.mapResultSetToObject(resultSet, ManagerLoginDto.UserDetailsSetResultDto.class);

            }
        } catch (Exception e) {

        }
        return userDetailsSetResultDto;
    }



    public String update_mpin(UserRequest UserCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.updatempin");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter(1, UserCode.getUserCode());
        storedProcedureQuery.setParameter(2, UserCode.getmPin());
        String Output = (String) storedProcedureQuery.getOutputParameterValue(3);
        return Output;
    }

    public String update_password(UserRequest UserCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.updatepwd");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter(1, UserCode.getUserCode());
        storedProcedureQuery.setParameter(2, UserCode.getPassword());
        String Output = (String) storedProcedureQuery.getOutputParameterValue(3);
        return Output;
    }

    public EmployeeValidationResult createOtp(UserRequest UserCode) {
        EmployeeValidationResult result = new EmployeeValidationResult();
        if(UserCode.getMode().equals("REGISTER")) {

        }
        else if(UserCode.getMode().equals("FORGOT"))
        {
            int isCustomerExist = this.getUserCodeId(UserCode.getUserCode());

            if (isCustomerExist==0) {
                result.setFlag("N");
                result.setMessage("Given UserCode is Not Registered");
                result.setUserType(UserCode.getUserType());
                return result;
            }
            long mobileNumber=0;
            if(UserCode.getUserType().equals("INCHARGE")  || UserCode.getUserType().equals("SUPERVISOR"))
            {
                mobileNumber=this.get_employee_mobileno(UserCode.getUserCode());
            }
            else if(UserCode.getUserType().equals("CPFFARM_OWNER"))
            {
                mobileNumber=this.get_cbf_owner_mobileno(UserCode.getUserCode());
            }
            else if(UserCode.getUserType().equals("CPFFARM_SUPERVISOR"))
            {
                mobileNumber=this.get_cbf_employee_mobileno(UserCode.getUserCode());
            }
            String otp = "123456"; //Utils.generateOtp();
            OTPModel otpModel = new OTPModel();
            otpModel.setOTP(otp);
            otpModel.setAPPLICATION("BREEDER_NEW");

            otpModel.setCREATION_DATE(Utils.addMinutsToDate(-3));
            otpModel.setEXPIRY_DATE(Utils.addMinutsToDate(30));
            otpModel.setTYPE(UserCode.getMode()); // REGISTER,FORGOT
            otpModel.setLOGIN_NAME(UserCode.getUserCode());
            otpModel.setMOBILE_NO(String.valueOf(mobileNumber));
            OTPModel responseOtp = createNewOtp(otpModel);

            if (responseOtp.getSEQ() == null) {
                result.setFlag("N");
                result.setMessage("OTP Generation error.");
                result.setUserType(UserCode.getUserType());
                return result;
            }

            String sms = "Dear Customer your registration OTP is " + otp + ". Do not share with anyone.-SUGUNA";
            if(UserCode.getMode().equals("REGISTER")) {
                sms = "Dear Customer, your registration OTP is " + otp + ". Do not share with anyone.-SUGUNA";
            }
            else
            {
                sms = "Dear Customer your forgot password OTP is " + otp + ". Do not share with anyone -SUGUNA";
            }

            System.out.println("Message Content " + sms);

            long ledgerId = this.get_registeruser_ledgerid(UserCode.getUserCode());

            if(ledgerId == 2025)
            {
               // smsGateway.sendSMS(String.valueOf(mobileNumber), sms); // Working
            }
            else
            {
                //smsGateway.sendSMS_BD(String.valueOf(mobileNumber), sms);
            }

            result.setFlag("Y");
            result.setMessage("OTP Generated");
            result.setUserType(UserCode.getUserType());

        }
    return result;
    }

    public long get_employee_mobileno(String empCode) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_employee_mobileno(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, empCode)
                .getSingleResult()).longValue();
    }

    public long get_cbf_owner_mobileno(String empCode) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_cbf_owner_mobileno(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, empCode)
                .getSingleResult()).longValue();
    }

    public long get_cbf_employee_mobileno(String empCode) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_cbf_employee_mobileno(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, empCode)
                .getSingleResult()).longValue();
    }

    public long get_registeruser_ledgerid(String empCode) {
        String sql = "SELECT sug_mai_gpps_mob_pkg.get_registeruser_ledgerid(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, empCode)
                .getSingleResult()).longValue();
    }

    public OTPModel createNewOtp(OTPModel otpModel) {
        return otpRepositories.save(otpModel);
    }

    public EmployeeValidationResult updatePassword(UserRequest UserCode) {
        EmployeeValidationResult result = new EmployeeValidationResult();
        if(UserCode.getMode().equals("REGISTER")) {

        }
        else if(UserCode.getMode().equals("FORGOT"))
        {
            int isCustomerExist = this.getUserCodeId(UserCode.getUserCode());

            if (isCustomerExist==0) {
                result.setFlag("N");
                result.setMessage("Given UserCode is Not Registered");
                result.setUserType(UserCode.getUserType());
                return result;
            }
            OTPModel otpModel = otpRepositories.findRecentOtp(UserCode.getUserCode(), "BREEDER_NEW", UserCode.getOtp(), UserCode.getMode()); //FORGOT
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonStr = objectMapper.writeValueAsString(otpModel);
                System.out.println(jsonStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            if (otpModel == null) {
                result.setFlag("N");
                result.setMessage("Invalid OTP or Expired");
                result.setUserType(UserCode.getUserType());
                return result;


            }

            String update="0";

            update  = update_password(UserCode);

            if(update.equals("0"))
            {
                result.setFlag("N");
                result.setMessage("Password Update Error");
                result.setUserType(UserCode.getUserType());
                return result;
            }

            result.setFlag("Y");
            result.setMessage("Password Updated");
            result.setUserType(UserCode.getUserType());

        }
        return result;
    }


    public EmployeeValidationResult createLogin(UserRequest UserCode) {
        EmployeeValidationResult result = new EmployeeValidationResult();
        String Output ="";
        if(UserCode.getMode().equals("NEW")) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.registerotheruser");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter(1, "BREEDER_NEW");
            storedProcedureQuery.setParameter(2, UserCode.getUserCode());
            storedProcedureQuery.setParameter(3, UserCode.getUserType());
            storedProcedureQuery.setParameter(4, UserCode.getPassword());
            storedProcedureQuery.setParameter(5, UserCode.getBranchId());
            storedProcedureQuery.setParameter(6, UserCode.getMobileNumber());
            storedProcedureQuery.setParameter(7, UserCode.getDeviceID());
            storedProcedureQuery.setParameter(8, UserCode.getShedNo());
            Output = (String) storedProcedureQuery.getOutputParameterValue(9);
        }
        else if(UserCode.getMode().equals("EDIT")) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.updateotheruser");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter(1, "BREEDER_NEW");
            storedProcedureQuery.setParameter(2, UserCode.getUserCode());
            storedProcedureQuery.setParameter(3, UserCode.getUserType());
            storedProcedureQuery.setParameter(4, UserCode.getPassword());
            storedProcedureQuery.setParameter(5, UserCode.getBranchId());
            storedProcedureQuery.setParameter(6, UserCode.getMobileNumber());
            storedProcedureQuery.setParameter(7, UserCode.getDeviceID());
            storedProcedureQuery.setParameter(8, UserCode.getExistingCode());
            Output = (String) storedProcedureQuery.getOutputParameterValue(9);
        }
        else if(UserCode.getMode().equals("INACTIVE")) {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.inactiveotheruser");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter(1, "BREEDER_NEW");
            storedProcedureQuery.setParameter(2, UserCode.getUserCode());
            storedProcedureQuery.setParameter(3, UserCode.getUserType());
            storedProcedureQuery.setParameter(4, UserCode.getPassword());
            storedProcedureQuery.setParameter(5, UserCode.getBranchId());
            storedProcedureQuery.setParameter(6, UserCode.getMobileNumber());
            storedProcedureQuery.setParameter(7, UserCode.getDeviceID());
            storedProcedureQuery.setParameter(8, UserCode.getExistingCode());
            Output = (String) storedProcedureQuery.getOutputParameterValue(9);
        }
            if(Output.equals("1")) {
                result.setFlag("Y");
                result.setMessage("User ID Created");
                result.setUserType(UserCode.getUserType());
            }
            else
            {
                result.setFlag("N");
                result.setMessage("User ID Creation Error");
                result.setUserType(UserCode.getUserType());
            }

        return result;
    }

}
