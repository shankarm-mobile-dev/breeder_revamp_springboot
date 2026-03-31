package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.EmpDto;
import com.suguna.breeder_revamp.dto.EmployeeValidationResult;
import com.suguna.breeder_revamp.dto.ManagerLoginDto;
import com.suguna.breeder_revamp.dto.ResponseDto;
import com.suguna.breeder_revamp.model.EmpModel;
import com.suguna.breeder_revamp.repositories.EmpDataRepository;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    EmpDataRepository empDataRepository;

    @Autowired
    EntityManager entityManager;



    public Integer getUserId(String empCode) {
        String sql = "SELECT sug_mai_gppsmgr_pkg.get_manager_type(?1) FROM dual";
        return ((Number) entityManager
                .createNativeQuery(sql)
                .setParameter(1, empCode)
                .getSingleResult()).intValue();
    }


    @Transactional(readOnly = true)
    public EmployeeValidationResult validateEmployee(String empNo) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.prc_validate_employee");
        sp.registerStoredProcedureParameter("p_emp_no", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_out_msg", String.class, ParameterMode.OUT);
        sp.registerStoredProcedureParameter("p_out_flag", String.class, ParameterMode.OUT);
        sp.registerStoredProcedureParameter("p_emp_type", String.class, ParameterMode.OUT);
        sp.setParameter("p_emp_no", empNo);
        sp.execute();
        EmployeeValidationResult result = new EmployeeValidationResult();
        result.setMessage((String) sp.getOutputParameterValue("p_out_msg"));
        result.setFlag((String) sp.getOutputParameterValue("p_out_flag"));
        result.setEmpType((String) sp.getOutputParameterValue("p_emp_type"));
        return result;
    }


    public boolean authenticateManager(String username, String password) {
        String ldapUrl = "ldap://10.4.1.71";
        String domain = "Sugunapoultry.com";
        String principal = "0"+username + "@" + domain;
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
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getloginpost");
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




}
