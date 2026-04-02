package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.model.BranchUser;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class FarmServiceImpl implements FarmService{

    @Autowired
    EntityManager entityManager;

    @Override
    public ArrayList<BranchUser> getBranchUsers(BranchRequest branchRequest) {
        ArrayList<BranchUser> branchUserArrayList=new ArrayList<BranchUser>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getmanager_branch_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getUserType());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser branchUser = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.class);
                branchUser.setUserDetails(getRegisteredBranchUsers(String.valueOf(branchUser.getBranchID()),branchRequest.getUserType(),branchUser.getBranchName()));
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
    }

    @Override
    public ArrayList<BranchUser.SupervisorDetails> getSupervisorDetails(BranchRequest branchRequest) {
        ArrayList<BranchUser.SupervisorDetails> supervisorDetailsArrayList=new ArrayList<BranchUser.SupervisorDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getsupervisor_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getUserType());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);
            while (resultSet.next()) {
                BranchUser.SupervisorDetails supervisorDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.SupervisorDetails.class);

                supervisorDetailsArrayList.add(supervisorDetails);
            }
        } catch (Exception e) {

        }
        return supervisorDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ShedDetails> getShedDetails(String branchID) {
        ArrayList<BranchUser.ShedDetails> shedDetailsArrayList=new ArrayList<BranchUser.ShedDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshed_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.ShedDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    public ArrayList<BranchUser.RegisteredBranchUser> getRegisteredBranchUsers(String branchId,String userType,String branchCode) {
        ArrayList<BranchUser.RegisteredBranchUser> branchUserArrayList=new ArrayList<BranchUser.RegisteredBranchUser>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getbranch_user_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchId);
            storedProcedureQuery.setParameter(2, userType);
            storedProcedureQuery.setParameter(3, branchCode);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);
            while (resultSet.next()) {
                BranchUser.RegisteredBranchUser branchUser = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.RegisteredBranchUser.class);
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
    }
}
