package com.suguna.breeder_revamp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suguna.breeder_revamp.components.FileStorageService;
import com.suguna.breeder_revamp.dto.*;

import com.suguna.breeder_revamp.enums.FileStorageCategory;
import com.suguna.breeder_revamp.model.*;
import com.suguna.breeder_revamp.repositories.*;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService{
    String fromdateFormat  = "dd-MMM-yyyy HH:mm:ss";
    String fromdateFormat1 = "dd-MMM-yyyy";
    @Autowired
    EntityManager entityManager;

    @Autowired
    SugMaiGppsTransHdrRepository sugMaiGppsTransHdrRepository;

    @Autowired
    SugMaiGppsTransDtlRepository sugMaiGppsTransDtlRepository;

    @Autowired
    SugMaiGppsTransPlanDtlRepository sugMaiGppsTransPlanDtlRepository;

    @Autowired
    SugMaiGppsTransPlanHdrRepository sugMaiGppsTransPlanHdrRepository;

    @Autowired
    SugEggVehiclePlanDtlRepository sugEggVehiclePlanDtlRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FileStorageService fileStorageService;

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

    @Override
    public ArrayList<TransferPlace> getTransferPlanPlace(BranchRequest branchRequest) {
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
                if(transferPlace.getOpmDivision().equalsIgnoreCase("1")) {
                    transferPlace.setShedInfoLineDetails(getShedDetailsReport(String.valueOf(transferPlace.getBranchId())));
                    transferPlacesArrayList.add(transferPlace);
                }
            }
        } catch (Exception e) {

        }
        return transferPlacesArrayList;

    }
    public ArrayList<TransferPlace.ShedDetailsReport> getShedDetailsReport(String branchID) {
        ArrayList<TransferPlace.ShedDetailsReport> shedDetailsArrayList = new ArrayList<TransferPlace.ShedDetailsReport>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getsheddetails_rpt");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.ShedDetailsReport shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.ShedDetailsReport.class);
                shedDetails.setPlacementInfoLineDetails(getplacementlineinfo(branchID,shedDetails.getShedName()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }
    public ArrayList<TransferPlace.PlacementInfoLineDetails> getplacementlineinfo(String branchID,String shedNo) {
        ArrayList<TransferPlace.PlacementInfoLineDetails> shedDetailsArrayList = new ArrayList<TransferPlace.PlacementInfoLineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getplacementlineinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                TransferPlace.PlacementInfoLineDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.PlacementInfoLineDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
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

    @Override
    public ArrayList<BranchUser> getAllBranch(BranchRequest branchRequest) {
        ArrayList<BranchUser> branchUserArrayList = new ArrayList<BranchUser>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getmanager_child_branch_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
           // storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            //storedProcedureQuery.setParameter(2, branchRequest.getUserType());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser branchUser = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.class);
               // branchUser.setUserDetails(getRegisteredBranchUsers(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType(), branchUser.getBranchName()));
               // branchUser.setBranchUserDetails(getSupervisorNewDetails(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType()));
                branchUser.setFlockDetails(getFlockDetails(String.valueOf(branchUser.getBranchID())));
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
    }

    @Override
    public String saveTransPlan(TransferPlanDto entry) {
        String fromdateFormat = "DD-MM-YYYY hh:mm:ss";
        String fromdateFormat1 = "DD-MMM-YYYY";
        try {
            SugMaiGppsTransPlanHdr sugMaiGppsTransPlanHdr=new SugMaiGppsTransPlanHdr();
            sugMaiGppsTransPlanHdr.setFROM_FARM_ID(BigDecimal.valueOf(entry.getFromOrgId()));
            sugMaiGppsTransPlanHdr.setFROM_FARM_NAME(entry.getFromFarmName());
            sugMaiGppsTransPlanHdr.setTO_FARM_ID(BigDecimal.valueOf(entry.getToOrgId()));
            sugMaiGppsTransPlanHdr.setEMPCODE(entry.getUserCode());
            sugMaiGppsTransPlanHdr.setTRANS_TYPE(entry.getTransType());
            sugMaiGppsTransPlanHdr.setTRANS_REASON(entry.getTransReason());
            sugMaiGppsTransPlanHdr.setFLOCK_ID(entry.flockId);
            sugMaiGppsTransPlanHdr.setTXN_DATE(getTxnDateString(entry.getTransDate(),fromdateFormat1));
            SugMaiGppsTransPlanHdr sugMaiGppsTransPlanHdr1=sugMaiGppsTransPlanHdrRepository.save(sugMaiGppsTransPlanHdr);
            for(TransferPlanDto.TransferPlanDtlsDto transferPlanDtlsDto:entry.getTransferPlanDtls())
            {
                SugMaiGppsTransPlanDtl sugMaiGppsTransPlanDtl=new SugMaiGppsTransPlanDtl();
                sugMaiGppsTransPlanDtl.setTXN_HEADER_ID(sugMaiGppsTransPlanHdr1.getTXN_HEADER_ID());
                sugMaiGppsTransPlanDtl.setTXN_TYPE(entry.getTransType());
                sugMaiGppsTransPlanDtl.setBIRD_TYPE(transferPlanDtlsDto.itemType);
                sugMaiGppsTransPlanDtl.setQTY(transferPlanDtlsDto.quantity);
                sugMaiGppsTransPlanDtl.setFROM_INVENTORY_LOC_DESC(transferPlanDtlsDto.fromFarmLocation);
                sugMaiGppsTransPlanDtl.setTO_INVENTORY_LOC_DESC(transferPlanDtlsDto.toFarmLocation);
                sugMaiGppsTransPlanDtlRepository.save(sugMaiGppsTransPlanDtl);
            }
        } catch (Exception e) {

        }
        return "200";
    }

    @Override
    public ArrayList<TransferPlace.VehicleGateInDetails> getEggGateInDetails(BranchRequest branchRequest) {
        ArrayList<TransferPlace.VehicleGateInDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.VehicleGateInDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getegggate_in_details");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);


            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());


            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.VehicleGateInDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.VehicleGateInDetails.class);
                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }

    @Override
    public ArrayList<TransferPlace.VehicleGateOutDetails> getEggGateOutDetails(BranchRequest branchRequest) {
        ArrayList<TransferPlace.VehicleGateOutDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.VehicleGateOutDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getegggate_out_details");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);


            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());


            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.VehicleGateOutDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.VehicleGateOutDetails.class);
                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }

    @Override
    public ArrayList<TransferPlace.HatcheryPlanDetails> getEggHatcheryPlanDetails(BranchRequest branchRequest) {
        ArrayList<TransferPlace.HatcheryPlanDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.HatcheryPlanDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.geteggplan_hatchery_details");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getHatcheryID());


            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                TransferPlace.HatcheryPlanDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.HatcheryPlanDetails.class);

                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }

    @Override
    public ArrayList<TransferPlace.TransferPlanDetails> getPlanDetails(BranchRequest branchRequest) {
        ArrayList<TransferPlace.TransferPlanDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.TransferPlanDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.gettransplan_hdr");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);


            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());



            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.TransferPlanDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.TransferPlanDetails.class);
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Parse the string into LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(transferInDetails.getTXN_DATE(), inputFormatter);

                // Example: Convert to another format (ISO or custom)
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String formattedDate = dateTime.format(outputFormatter);
                transferInDetails.setTXN_DATE(formattedDate);
                transferInDetails.setTRANS_LINES(getPlanLineDetails(transferInDetails.getTXN_HEADER_ID()));
                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }

    @Override
    public ArrayList<TransferPlace.EggItemStockDetails> getEggStockDetails(BranchRequest branchRequest) {
        ArrayList<TransferPlace.EggItemStockDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.EggItemStockDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.geteggitemstocks");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);


            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());



            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.EggItemStockDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.EggItemStockDetails.class);
                //transferInDetails.setTRANS_LINES(getPlanLineDetails(transferInDetails.getTXN_HEADER_ID()));
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Parse the string into LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(transferInDetails.getLay_DATE(), inputFormatter);

                // Example: Convert to another format (ISO or custom)
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String formattedDate = dateTime.format(outputFormatter);
                transferInDetails.setLay_DATE(formattedDate);
                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }

    public ArrayList<TransferPlace.TransferPlanLineDetails> getPlanLineDetails(String header_id) {
        ArrayList<TransferPlace.TransferPlanLineDetails> transferInDetailsArrayList = new ArrayList<TransferPlace.TransferPlanLineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.gettransplan_details");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);


            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, header_id);



            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                TransferPlace.TransferPlanLineDetails transferInDetails = ResultSetMapper.mapResultSetToObject(resultSet, TransferPlace.TransferPlanLineDetails.class);

                transferInDetailsArrayList.add(transferInDetails);
            }
        } catch (Exception e) {

        }
        return transferInDetailsArrayList;
    }


    public ArrayList<BranchUser.FarmFlockDetails> getFlockDetails(String branchID) {
       // BranchUser.FeedAllocationDetails details = new BranchUser.FeedAllocationDetails();
        ArrayList<BranchUser.FarmFlockDetails> shedDetailsArrayList = new ArrayList<BranchUser.FarmFlockDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getfarmflockddtls");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.FarmFlockDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FarmFlockDetails.class);
                String Standard=getFeedStandard(branchID,shedDetails.getAge());
                System.out.println("age : "+shedDetails.getAge());
                try {
                    String[] parts = Standard.split("~");
                    shedDetails.setOpFemaleWeightStandard(parts[0]);
                    shedDetails.setOpMaleWeightStandard(parts[1]);
                    shedDetails.setOpFemaleFeedStandard(parts[2]);
                    shedDetails.setOpMaleFeedStandard(parts[3]);
                } catch (Exception e) {
                    // throw new RuntimeException(e);
                }
                shedDetails.setFarmShedDetails(getshedwise_birdsdtls(branchID,shedDetails.getFlock()));
               // shedDetails.setFarmFlockDetails(getFeedAllocationPreviousDetails(branchID,shedDetails.getFlock()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
      //  details.setFarmFlockDetails(shedDetailsArrayList);
      //  details.setGardeMstDetails(getgrademst(branchID));
        return shedDetailsArrayList;
    }
    public String getFeedStandard(String branchID,String age) {
        ArrayList<BranchUser.StandardDetails> standardDetailsArrayList = new ArrayList<BranchUser.StandardDetails>();
        String Standard="";
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getfeedstandard");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, age);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.StandardDetails standardDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.StandardDetails.class);
                standardDetailsArrayList.add(standardDetails);
                Standard=standardDetails.getFemaleWeight()+"~"+standardDetails.getMaleWeight()+"~"+standardDetails.getFemaleFeedPerWeek()+"~"+standardDetails.getMaleFeedPerWeek();
                if(!standardDetails.getBirdType().equalsIgnoreCase("PS_FM"))
                {
                    return Standard;
                }
            }
        } catch (Exception e) {

        }
        return Standard;
    }

    public ArrayList<BranchUser.ShedBirdsDetails> getshedwise_birdsdtls(String branchID,String flockID) {
        ArrayList<BranchUser.ShedBirdsDetails> shedDetailsArrayList = new ArrayList<BranchUser.ShedBirdsDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.gettransplan_shed");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, flockID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.ShedBirdsDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedBirdsDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Transactional
    public String saveGateInDetails(PlanRequest branchRequest, List<MultipartFile> imageFile) {


            try {String mortalityImage = null;
                if (imageFile != null && !imageFile.isEmpty()) {
                    for (MultipartFile data : imageFile) {
                        mortalityImage = fileStorageService.saveImage(data, "", Long.valueOf(branchRequest.getPLAN_DTL_ID()), FileStorageCategory.FEED);
                    }
                }

                sugEggVehiclePlanDtlRepository.updateActualArrival(branchRequest.getPLAN_DTL_ID(),getTxnDateString(branchRequest.getACTUAL_ARRIVAL_DATE(),fromdateFormat),mortalityImage);
            } catch (IOException | IllegalArgumentException ex) {
                //  return Response.buildSingleResponse("Failed", HttpStatus.BAD_REQUEST, ex.getMessage(), null);
            }
        return "200";
    }
    @Transactional
    public String saveGateOutDetails(PlanRequest branchRequest, List<MultipartFile> imageFile) {


        try {
            String mortalityImage = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data : imageFile) {
                    mortalityImage = fileStorageService.saveImage(data, "", Long.valueOf(branchRequest.getPLAN_DTL_ID()), FileStorageCategory.FEED);
                }
            }

           int updatedRows= sugEggVehiclePlanDtlRepository.updateActualDeparture(branchRequest.getPLAN_DTL_ID(),getTxnDateString(branchRequest.getACTUAL_DEPATURE_DATE(),fromdateFormat),mortalityImage);

            if (updatedRows > 0) {
                sugEggVehiclePlanDtlRepository.updateTransferEntryFlag(branchRequest.getPLAN_DTL_ID());
            }
        } catch (IOException | IllegalArgumentException ex) {
            //  return Response.buildSingleResponse("Failed", HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        }
        return "200";
    }

}
