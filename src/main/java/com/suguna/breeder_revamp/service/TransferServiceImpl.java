package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BranchRequest;

import com.suguna.breeder_revamp.dto.SUGMAIGPPSTRANS_HDRDto;
import com.suguna.breeder_revamp.model.BranchUser;
import com.suguna.breeder_revamp.model.SugMaiGppsTransDtl;
import com.suguna.breeder_revamp.model.SugMaiGppsTransHdr;
import com.suguna.breeder_revamp.model.TransferPlace;
import com.suguna.breeder_revamp.repositories.SugMaiGppsTransDtlRepository;
import com.suguna.breeder_revamp.repositories.SugMaiGppsTransHdrRepository;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TransferServiceImpl implements TransferService{
    @Autowired
    EntityManager entityManager;

    @Autowired
    SugMaiGppsTransHdrRepository sugMaiGppsTransHdrRepository;

    @Autowired
    SugMaiGppsTransDtlRepository sugMaiGppsTransDtlRepository;

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

    @Override
    public ArrayList<TransferPlace.TransferInHdr> getTransferInHdr(BranchRequest branchRequest) {
        ArrayList<TransferPlace.TransferInHdr> transferInHdrArrayList = new ArrayList<TransferPlace.TransferInHdr>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getTransferInHdr");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.TransferInHdr transferInHdr = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.TransferInHdr.class);
                transferInHdr.setTransferInDetails(getTransferInDetails(transferInHdr.getFromFarmId(),transferInHdr.getToFarmId(),transferInHdr.getTxnHeaderId()));
                transferInHdrArrayList.add(transferInHdr);
            }
        } catch (Exception e) {

        }
        return transferInHdrArrayList;
    }

    private Date getTxnDateString(String ipdate, String toformate) {
        DateFormat formatter;
        Date date = null;
        try {
            formatter = new SimpleDateFormat(toformate);
            date = formatter.parse(ipdate);

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());

        }
        return date;
    }

    @Override
    public String saveTransOut(ArrayList<SUGMAIGPPSTRANS_HDRDto> entry) {
        String fromdateFormat = "DD-MM-YYYY hh:mm:ss";
        String fromdateFormat1 = "DD-MM-YYYY";
        try {
            for (SUGMAIGPPSTRANS_HDRDto FarmDto : entry) {
                String HDR = "0";//getSUGMAIGPPSTRANS_HDR(FarmDto.DEVICEID, FarmDto.txn_header_id, FarmDto.entry_creation_date);
                if (HDR.equals("0")) {
                    SugMaiGppsTransHdr sugmaigppstransHdrModels = new SugMaiGppsTransHdr();
                    sugmaigppstransHdrModels.setDEVICE_ID(new BigDecimal(FarmDto.DEVICEID));
                    sugmaigppstransHdrModels.setEMPCODE(FarmDto.getEmpcode());
                    sugmaigppstransHdrModels.setFROM_FARM_ID(FarmDto.getFrom_farm_id());
                    sugmaigppstransHdrModels.setFROM_FARM_NAME(FarmDto.getFrom_farm_name());
                    sugmaigppstransHdrModels.setTO_FARM_ID(FarmDto.getTo_farm_id());
                    //sugmaigppstransHdrModels.setTXN_HEADER_ID(new BigDecimal(FarmDto.txn_header_id));
                    sugmaigppstransHdrModels.setTRANS_TYPE(FarmDto.getTransfer_type());
                    sugmaigppstransHdrModels.setTXN_DATE(getTxnDateString(FarmDto.getTxn_date(), fromdateFormat1));
                    sugmaigppstransHdrModels.setVEHICLE_NO(FarmDto.getVehicle_no());
                    sugmaigppstransHdrModels.setOUT_PASS_NO(FarmDto.getOut_pass_no());
                    sugmaigppstransHdrModels.setRECEIVER_NAME(FarmDto.getReceiver_name());
                    sugmaigppstransHdrModels.setTRANS_REASON(FarmDto.getTransfer_rsn());
                    sugmaigppstransHdrModels.setENTRY_CREATION_DATE(new Date());
                    sugmaigppstransHdrModels.setCREATED_DATE(new Date());
                    sugmaigppstransHdrModels.setPOSTED_FLAG(FarmDto.getPostedflg());
                    sugmaigppstransHdrModels.setPOST_TO_ERP(FarmDto.getPost_to_ERP());
                    sugmaigppstransHdrModels.setLOCATION_TYPE(FarmDto.getLocation_TYPE());
                    sugmaigppstransHdrModels.setTXN_TIME(FarmDto.getTxn_time());
                    sugmaigppstransHdrModels.setVEHICLE_TYPE(FarmDto.getVehicletype());
                    sugmaigppstransHdrModels.setTRANS_MODE(FarmDto.getTransportmode());
                    sugmaigppstransHdrModels.setTRAY_NOS(FarmDto.getTraynumber());
                    sugmaigppstransHdrModels.setBOX_NOS(FarmDto.getBoxnumber());
                    sugmaigppstransHdrModels.setPACK_MATERIAL(FarmDto.getPackmaterial());
                    sugmaigppstransHdrModels.setPLAN_DTL_ID(Long.parseLong(FarmDto.getPid()));
                    SugMaiGppsTransHdr sugmaigppstransHdrModels1=sugMaiGppsTransHdrRepository.save(sugmaigppstransHdrModels);
                    long txn_id=0;
                    txn_id=sugmaigppstransHdrModels1.getTXN_HEADER_ID();
                    for (SUGMAIGPPSTRANS_HDRDto.SugMaiGppsTrans_DtlDto FarmDto1 : FarmDto.getDetails()) {
                        String Dtl = "0";//getTransferoutDtl(FarmDto.DEVICEID, FarmDto.txn_header_id, FarmDto.txn_line_id, FarmDto.entry_creation_date);
                        if (Dtl.equals("0")) {
                            SugMaiGppsTransDtl sugMaiGppsTransDtlModels = new SugMaiGppsTransDtl();
                            sugMaiGppsTransDtlModels.setDEVICE_ID(new BigDecimal(FarmDto1.DEVICEID));
                            sugMaiGppsTransDtlModels.setTXN_HEADER_ID(txn_id);
                            //sugMaiGppsTransDtlModels.setTXN_LINE_ID(new BigDecimal(FarmDto1.txn_line_id));
                            sugMaiGppsTransDtlModels.setFROM_FARM_ID(FarmDto1.getFrom_farm_id());
                            sugMaiGppsTransDtlModels.setTO_FARM_ID(FarmDto1.getTo_farm_id());
                            sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOCATION_ID(FarmDto1.getFrom_inventory_location_id());
                            sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOC_DESC(FarmDto1.getFrom_inventory_loc_desc());
                            sugMaiGppsTransDtlModels.setFROM_BATCH_ID(FarmDto1.getFrom_batch_id());
                            sugMaiGppsTransDtlModels.setTO_BATCH_ID(FarmDto1.getTo_batch_id());
                            sugMaiGppsTransDtlModels.setTXN_TYPE(FarmDto1.getTxn_type());
                            sugMaiGppsTransDtlModels.setBIRD_TYPE(FarmDto1.getBird_type());
                            sugMaiGppsTransDtlModels.setITEM_ID(FarmDto1.getItem_id());
                            sugMaiGppsTransDtlModels.setITEM_DESC(FarmDto1.getItem_desc());
                            sugMaiGppsTransDtlModels.setUOM(FarmDto1.getUom());
                            sugMaiGppsTransDtlModels.setSTOCK_QTY(FarmDto1.getStock_qty());
                            sugMaiGppsTransDtlModels.setQTY(FarmDto1.getQty());
                            sugMaiGppsTransDtlModels.setDAYS(FarmDto1.getDays());
                            sugMaiGppsTransDtlModels.setRECEIVING_QTY(FarmDto1.getReceiving_qty());
                            sugMaiGppsTransDtlModels.setDIFF_QTY(FarmDto1.getDiff_qty());
                            sugMaiGppsTransDtlModels.setENTRY_CREATION_DATE(new Date());
                            sugMaiGppsTransDtlModels.setCREATED_DATE(new Date());
                            sugMaiGppsTransDtlModels.setPOSTED_FLAG(FarmDto1.getPostedflg());
                            sugMaiGppsTransDtlModels.setAGE(FarmDto1.getAge());
                            sugMaiGppsTransDtlModels.setPOST_TO_ERP(FarmDto1.getPost_to_ERP());
                            sugMaiGppsTransDtlModels.setLOTNUMBER(FarmDto1.getLotnumber());
                            sugMaiGppsTransDtlModels.setLOCATION_TYPE(FarmDto1.getLocation_TYPE());
                            if (FarmDto1.getLaydate() != "NA") {
                                sugMaiGppsTransDtlModels.setLAY_DATE(getTxnDateString(FarmDto1.getLaydate(), fromdateFormat1));
                            }
                            sugMaiGppsTransDtlModels.setLOCATION_TYPE(FarmDto1.getLocation_TYPE());
                            sugMaiGppsTransDtlModels.setTXN_TIME(FarmDto1.getTXN_TIME());
                            sugMaiGppsTransDtlModels.setBREEDNAME(FarmDto1.getBreedname());
                            sugMaiGppsTransDtlRepository.save(sugMaiGppsTransDtlModels);

                        } else {

                        }
                    }
                } else {

                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return "200";
    }

    public ArrayList<TransferPlace.TransferInDetails> getTransferInDetails(String fromId,String toId,String txnId) {
        ArrayList<TransferPlace.TransferInDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.TransferInDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.gettransferindetails");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, fromId);
            storedProcedureQuery.setParameter(2, toId);
            storedProcedureQuery.setParameter(3, txnId);

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                TransferPlace.TransferInDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.TransferInDetails.class);
                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }
}
