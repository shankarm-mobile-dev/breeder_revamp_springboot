package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.model.BranchUser;
import com.suguna.breeder_revamp.model.TransferPlace;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class TransferServiceImpl implements TransferService{
    @Autowired
    EntityManager entityManager;
    @Override
    public ArrayList<TransferPlace> getTransferPlace(BranchRequest branchRequest) {
        ArrayList<TransferPlace> transferPlacesArrayList = new ArrayList<TransferPlace>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getlocmaster");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace transferPlace = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.class);
                transferPlacesArrayList.add(transferPlace);
            }
        } catch (Exception e) {

        }
        return transferPlacesArrayList;

    }

    @Override
    public ArrayList<TransferPlace.EggItemDetails> getEggItemMaster(BranchRequest branchRequest) {
        ArrayList<TransferPlace.EggItemDetails> eggItemDetailsArrayList = new ArrayList<TransferPlace.EggItemDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.geteggitemmaster");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.EggItemDetails eggItemDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.EggItemDetails.class);
                eggItemDetailsArrayList.add(eggItemDetails);
            }
        } catch (Exception e) {

        }
        return eggItemDetailsArrayList;
    }

    @Override
    public ArrayList<TransferPlace.FeedItemDetails> getFeedItemMaster(BranchRequest branchRequest) {
        ArrayList<TransferPlace.FeedItemDetails> feedItemDetailsArrayList = new ArrayList<TransferPlace.FeedItemDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getfeeditemmaster");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.FeedItemDetails feedItemDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.FeedItemDetails.class);
                feedItemDetailsArrayList.add(feedItemDetails);
            }
        } catch (Exception e) {

        }
        return feedItemDetailsArrayList;
    }

    @Override
    public ArrayList<TransferPlace.MedicineVaccineDetails> getMedicineVaccineMaster(BranchRequest branchRequest) {
        ArrayList<TransferPlace.MedicineVaccineDetails> medicineVaccineDetailsArrayList = new ArrayList<TransferPlace.MedicineVaccineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getmedivaccinemaster");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.MedicineVaccineDetails medicineVaccineDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.MedicineVaccineDetails.class);
                medicineVaccineDetailsArrayList.add(medicineVaccineDetails);
            }
        } catch (Exception e) {

        }
        return medicineVaccineDetailsArrayList;
    }
}
