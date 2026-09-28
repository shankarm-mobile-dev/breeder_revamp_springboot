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
import java.util.Locale;

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
    BreederVehicleGateInOutRepository breederVehicleGateInOutRepository;

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

    private Object[] getFromInventoryLocation(BigDecimal fromBatchId, BigDecimal fromFarmId) {
        if (fromBatchId == null || fromFarmId == null) {
            return null;
        }
        try {
            @SuppressWarnings("unchecked")
            List<Object[]> rows = entityManager.createNativeQuery(
                            "select a.inventory_location_id, a.segment1 as inventory_loc_desc " +
                                    "  from mtl_item_locations a," +
                                    "       gme_batch_header b," +
                                    "       sug_organization_mv c," +
                                    "       hr_locations_all e" +
                                    " where b.organization_id = a.organization_id" +
                                    "   and nvl(b.attribute4, a.segment1) = a.segment1" +
                                    "   and b.batch_status = 2" +
                                    "   and a.organization_id = c.branch_id" +
                                    "   and b.organization_id = c.branch_id" +
                                    "   and b.attribute_category <> 'None'" +
                                    "   and a.organization_id = e.inventory_organization_id" +
                                    "   and e.location_id = c.hr_location_id" +
                                    "   and not exists (select 1 from fm_form_mst x" +
                                    "                    where x.formula_no like '%CONVERSION%'" +
                                    "                      and x.formula_id = b.formula_id)" +
                                    "   and nvl(a.attribute4, 1) = 1" +
                                    "   and b.batch_id = ?1" +
                                    "   and b.organization_id = ?2" +
                                    "   and rownum = 1")
                    .setParameter(1, fromBatchId)
                    .setParameter(2, fromFarmId)
                    .getResultList();
            if (rows == null || rows.isEmpty()) {
                return null;
            }
            return rows.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public String saveTransOut(ArrayList<SUGMAIGPPSTRANS_HDRDto> entry) {
        try {
            for (SUGMAIGPPSTRANS_HDRDto FarmDto : entry) {
                String HDR = "0";//getSUGMAIGPPSTRANS_HDR(FarmDto.DEVICEID, FarmDto.txn_header_id, FarmDto.entry_creation_date);
                if (HDR.equals("0")) {
                    SugMaiGppsTransHdr sugmaigppstransHdrModels = new SugMaiGppsTransHdr();
                    sugmaigppstransHdrModels.setDEVICE_ID(new BigDecimal("123456"));
                    sugmaigppstransHdrModels.setEMPCODE(FarmDto.getEmpcode());
                    sugmaigppstransHdrModels.setFROM_FARM_ID(FarmDto.getFrom_farm_id());
                    sugmaigppstransHdrModels.setFROM_FARM_NAME(FarmDto.getFrom_farm_name());
                    sugmaigppstransHdrModels.setTO_FARM_ID(FarmDto.getTo_farm_id());
                    //sugmaigppstransHdrModels.setTXN_HEADER_ID(new BigDecimal(FarmDto.txn_header_id));
                    sugmaigppstransHdrModels.setTRANS_TYPE(FarmDto.getTransfer_type());
                    sugmaigppstransHdrModels.setTXN_DATE(parseTransferTxnDate(FarmDto.getTxn_date(), null));
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
                    applyPlanDtlIdFromPid(FarmDto, sugmaigppstransHdrModels);
                    SugMaiGppsTransHdr sugmaigppstransHdrModels1=sugMaiGppsTransHdrRepository.save(sugmaigppstransHdrModels);
                    if (isTransferIn(FarmDto.getTransfer_type())) {
                        updateVehicleGateInOutInStatusForTransIn(FarmDto);
                    } else {
                        updateVehicleGateInOutForTransOut(FarmDto);
                    }
                    long txn_id=0;
                    txn_id=sugmaigppstransHdrModels1.getTXN_HEADER_ID();
                    for (SUGMAIGPPSTRANS_HDRDto.SugMaiGppsTrans_DtlDto FarmDto1 : FarmDto.getDetails()) {
                        String Dtl = "0";//getTransferoutDtl(FarmDto.DEVICEID, FarmDto.txn_header_id, FarmDto.txn_line_id, FarmDto.entry_creation_date);
                        if (Dtl.equals("0")) {
                            SugMaiGppsTransDtl sugMaiGppsTransDtlModels = new SugMaiGppsTransDtl();
                            sugMaiGppsTransDtlModels.setDEVICE_ID(new BigDecimal("123456"));
                            sugMaiGppsTransDtlModels.setTXN_HEADER_ID(txn_id);
                            //sugMaiGppsTransDtlModels.setTXN_LINE_ID(new BigDecimal(FarmDto1.txn_line_id));
                            sugMaiGppsTransDtlModels.setFROM_FARM_ID(FarmDto1.getFrom_farm_id());
                            sugMaiGppsTransDtlModels.setTO_FARM_ID(FarmDto1.getTo_farm_id());
                            BigDecimal fromFarmId = FarmDto1.getFrom_farm_id() != null
                                    ? FarmDto1.getFrom_farm_id() : FarmDto.getFrom_farm_id();
                            Object[] fromLocation = getFromInventoryLocation(FarmDto1.getFrom_batch_id(), fromFarmId);
                            if(FarmDto1.getTxn_type().equals("BIRD")) {
                                if (fromLocation != null) {
                                    if (fromLocation[0] instanceof Number) {
                                        sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOCATION_ID(
                                                BigDecimal.valueOf(((Number) fromLocation[0]).longValue()));
                                    }
                                    sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOC_DESC(
                                            fromLocation[1] == null ? null : String.valueOf(fromLocation[1]));
                                } else {
                                    sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOCATION_ID(FarmDto1.getFrom_inventory_location_id());
                                    sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOC_DESC(FarmDto1.getFrom_inventory_loc_desc());
                                }
                            }
                            else {
                                sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOCATION_ID(FarmDto1.getFrom_inventory_location_id());
                                sugMaiGppsTransDtlModels.setFROM_INVENTORY_LOC_DESC(FarmDto1.getFrom_inventory_loc_desc());
                            }
                            sugMaiGppsTransDtlModels.setFROM_BATCH_ID(FarmDto1.getFrom_batch_id());
                            sugMaiGppsTransDtlModels.setTO_BATCH_ID(FarmDto1.getTo_batch_id());
                            sugMaiGppsTransDtlModels.setTXN_TYPE(FarmDto1.getTxn_type());
                            if(FarmDto1.getTxn_type().equals("BIRD"))
                            {
                                sugMaiGppsTransDtlModels.setBIRD_TYPE(FarmDto1.getBird_type().substring(0, 1));
                                sugMaiGppsTransDtlModels.setITEM_DESC("NA");
                            }
                            else
                            {
                                sugMaiGppsTransDtlModels.setBIRD_TYPE(FarmDto1.getBird_type());
                                sugMaiGppsTransDtlModels.setITEM_DESC(FarmDto1.getItem_desc());
                            }

                            sugMaiGppsTransDtlModels.setITEM_ID(FarmDto1.getItem_id());
                            sugMaiGppsTransDtlModels.setTO_INVENTORY_LOCATION_ID(FarmDto1.getTo_inventory_location_id());
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
                            if (FarmDto1.getLaydate() != null && !"NA".equals(FarmDto1.getLaydate())) {
                                Date layDate = parseTransferTxnDate(FarmDto1.getLaydate(), null);
                                if (layDate == null) {
                                    layDate = getTxnDateString(FarmDto1.getLaydate(), fromdateFormat1);
                                }
                                sugMaiGppsTransDtlModels.setLAY_DATE(layDate);
                            }
                            sugMaiGppsTransDtlModels.setLOCATION_TYPE(FarmDto1.getLocation_TYPE());
                            sugMaiGppsTransDtlModels.setTXN_TIME(FarmDto1.getTXN_TIME());
                            sugMaiGppsTransDtlModels.setBREEDNAME(FarmDto1.getBreedname());
                            sugMaiGppsTransDtlModels.setFROM_LINE_NAME(FarmDto1.getFromLine());
                            sugMaiGppsTransDtlModels.setTO_LINE_NAME(FarmDto1.getToLine());
                            sugMaiGppsTransDtlModels.setFROM_SIDE_NAME(FarmDto1.getFromSide());
                            sugMaiGppsTransDtlModels.setTO_SIDE_NAME(FarmDto1.getToSide());
                            sugMaiGppsTransDtlRepository.save(sugMaiGppsTransDtlModels);

                        } else {

                        }
                    }
                } else {

                }
            }
        } catch (Exception e) {
            System.out.println("Error in saveTransOut: " + e.getMessage());
            e.printStackTrace();
            throw e;
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
                sugMaiGppsTransPlanDtl.setFROM_LINE_NAME(transferPlanDtlsDto.fromLine);
                sugMaiGppsTransPlanDtl.setTO_LINE_NAME(transferPlanDtlsDto.toLine);
                sugMaiGppsTransPlanDtl.setFROM_SIDE_NAME(transferPlanDtlsDto.fromSide);
                sugMaiGppsTransPlanDtl.setTO_SIDE_NAME(transferPlanDtlsDto.toSide);
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
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);


            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getUserType());



            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

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
              System.out.println("error:"+ e.getMessage());
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
    @Override
    public String changeTransPlan(TransferPlanDto entry) {
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
            sugMaiGppsTransPlanHdrRepository.updateentry(String.valueOf(entry.getTxnHeaderId()),"I");
            for(TransferPlanDto.TransferPlanDtlsDto transferPlanDtlsDto:entry.getTransferPlanDtls())
            {
                SugMaiGppsTransPlanDtl sugMaiGppsTransPlanDtl=new SugMaiGppsTransPlanDtl();
                sugMaiGppsTransPlanDtl.setTXN_HEADER_ID(sugMaiGppsTransPlanHdr1.getTXN_HEADER_ID());
                sugMaiGppsTransPlanDtl.setTXN_TYPE(entry.getTransType());
                sugMaiGppsTransPlanDtl.setBIRD_TYPE(transferPlanDtlsDto.itemType);
                sugMaiGppsTransPlanDtl.setQTY(transferPlanDtlsDto.quantity);
                sugMaiGppsTransPlanDtl.setFROM_INVENTORY_LOC_DESC(transferPlanDtlsDto.fromFarmLocation);
                sugMaiGppsTransPlanDtl.setTO_INVENTORY_LOC_DESC(transferPlanDtlsDto.toFarmLocation);
                sugMaiGppsTransPlanDtl.setFROM_LINE_NAME(transferPlanDtlsDto.fromLine);
                sugMaiGppsTransPlanDtl.setTO_LINE_NAME(transferPlanDtlsDto.toLine);
                sugMaiGppsTransPlanDtl.setFROM_SIDE_NAME(transferPlanDtlsDto.fromSide);
                sugMaiGppsTransPlanDtl.setTO_SIDE_NAME(transferPlanDtlsDto.toSide);
                sugMaiGppsTransPlanDtlRepository.save(sugMaiGppsTransPlanDtl);
            }
        } catch (Exception e) {

        }
        return "200";
    }

    @Override
    @Transactional
    public VehicleGateInOutDto saveManualGateInDetails(VehicleGateInOutDto entry, List<MultipartFile> imageFile) {
        SugMaiBreederVehicleGateinout saved = null;
        try {
            if (resolveFromBranchId(entry) == null) {
                throw new IllegalArgumentException("FROM_BRANCH_ID is required");
            }
            if (entry.getVEHICLE_NO() == null || entry.getVEHICLE_NO().isBlank()) {
                throw new IllegalArgumentException("VEHICLE_NO is required");
            }
            SugMaiBreederVehicleGateinout gateInOut = new SugMaiBreederVehicleGateinout();
            gateInOut.setFromBranchId(resolveFromBranchId(entry));
            gateInOut.setVehicleNo(entry.getVEHICLE_NO());
            gateInOut.setDriverName(entry.getDRIVER_NAME());
            gateInOut.setDriverMobileNo(entry.getDRIVER_MOBILE_NO());
            gateInOut.setPurpose(entry.getPURPOSE());
            Date fromGateInDate = parseGateDate(entry.getFROM_GATE_IN_DATE());
            gateInOut.setFromGateInDate(fromGateInDate != null ? fromGateInDate : new Date());
            gateInOut.setFromCreatedBy(entry.getFROM_CREATED_BY());
            gateInOut.setFromCreatedDate(new Date());
            gateInOut.setToBranchId(entry.getTO_BRANCH_ID());
            gateInOut.setShipmentNo(entry.getSHIPMENT_NO());
            gateInOut.setOrderNo(entry.getORDER_NO());

            saved = breederVehicleGateInOutRepository.save(gateInOut);
        } catch (Exception e) {
            System.out.println("Error in saveManualGateInDetails: " + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data1 : imageFile) {
                    if (data1 != null && !data1.isEmpty()) {
                        Long branchId = resolveFromBranchId(entry);
                        fileStorageService.saveImage(data1, entry.getVEHICLE_NO(), branchId != null ? branchId : 0L, FileStorageCategory.GATE_IN_OUT);
                    }
                }
            }
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("Error in saveManualGateInDetails image: " + ex.getMessage());
        }
        return mapToVehicleGateInOutDto(saved);
    }

    @Override
    @Transactional
    public String saveManualGateOutDetails(VehicleGateInOutDto entry, List<MultipartFile> imageFile) {
        try {
            if (entry.getGATE_IN_ID() == null) {
                return "GATE_IN_ID is required";
            }
            SugMaiBreederVehicleGateinout gateInOut = breederVehicleGateInOutRepository.findById(entry.getGATE_IN_ID()).orElse(null);
            if (gateInOut == null) {
                return "Gate in record not found";
            }
            Date fromGateOutDate = parseGateDate(entry.getFROM_GATE_OUT_DATE());
            gateInOut.setFromGateOutDate(fromGateOutDate != null ? fromGateOutDate : new Date());
            gateInOut.setFromUpdatedBy(entry.getFROM_UPDATED_BY());
            gateInOut.setFromUpdatedDate(new Date());
            breederVehicleGateInOutRepository.save(gateInOut);
        } catch (Exception e) {
            System.out.println("Error in saveManualGateOutDetails: " + e.getMessage());
        }
        try {String mortalityImage = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data1 : imageFile) {
                    Long branchId = resolveFromBranchId(entry);
                    mortalityImage = fileStorageService.saveImage(data1, entry.getVEHICLE_NO(), branchId != null ? branchId : 0L, FileStorageCategory.GATE_IN_OUT);
                    /*DailyEntryLines dailyEntryLines = DailyEntryLines.builder()
                            .transId(saveResult.getTransId())
                            .hdrType("MORTALITY")
                            .imagePath(mortalityImage)
                            .build();*/
                    /**
                     * AI Mortality Count
                     */



                }
            }
        } catch (IOException | IllegalArgumentException ex) {
            //  return Response.buildSingleResponse("Failed", HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        }
        return "200";
    }

    @Override
    public ArrayList<VehicleGateInOutDto> getManualGateInDetails(BranchRequest branchRequest) {
        ArrayList<VehicleGateInOutDto> result = new ArrayList<>();
        try {
            Long branchId = Long.valueOf(branchRequest.getBranchID());
            List<SugMaiBreederVehicleGateinout> records = breederVehicleGateInOutRepository.findByFromBranchIdOrderByFromGateInDateDesc(branchId);
            for (SugMaiBreederVehicleGateinout record : records) {
                result.add(mapToVehicleGateInOutDto(record));
            }
        } catch (Exception e) {
            System.out.println("Error in getManualGateInDetails: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<VehicleGateInOutDto> getManualGateOutDetails(BranchRequest branchRequest) {
        ArrayList<VehicleGateInOutDto> result = new ArrayList<>();
        try {
            Long branchId = Long.valueOf(branchRequest.getBranchID());
            List<SugMaiBreederVehicleGateinout> records = breederVehicleGateInOutRepository.findByFromBranchIdAndFromGateOutDateIsNullOrderByFromGateInDateDesc(branchId);
            for (SugMaiBreederVehicleGateinout record : records) {
                result.add(mapToVehicleGateInOutDto(record));
            }
        } catch (Exception e) {
            System.out.println("Error in getManualGateOutDetails: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<VehicleGateInOutDto> getReceivingFarmGateInDetails(BranchRequest branchRequest) {
        ArrayList<VehicleGateInOutDto> result = new ArrayList<>();
        try {
            Long branchId = resolveBranchIdFromRequest(branchRequest);
            if (branchId == null) {
                System.out.println("branchID missing in getReceivingFarmGateInDetails payload");
                return result;
            }
            List<SugMaiBreederVehicleGateinout> records =
                    breederVehicleGateInOutRepository.findPendingReceivingFarmGateIn(branchId);
            for (SugMaiBreederVehicleGateinout record : records) {
                result.add(mapToVehicleGateInOutDto(record));
            }
        } catch (Exception e) {
            System.out.println("Error in getReceivingFarmGateInDetails: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<VehicleGateInOutDto> getToGateOutDetails(BranchRequest branchRequest) {
        ArrayList<VehicleGateInOutDto> result = new ArrayList<>();
        try {
            Long branchId = resolveBranchIdFromRequest(branchRequest);
            if (branchId == null) {
                System.out.println("branchID missing in getToGateOutDetails payload");
                return result;
            }
            List<SugMaiBreederVehicleGateinout> records =
                    breederVehicleGateInOutRepository.findPendingToGateOut(branchId);
            for (SugMaiBreederVehicleGateinout record : records) {
                result.add(mapToVehicleGateInOutDto(record));
            }
        } catch (Exception e) {
            System.out.println("Error in getToGateOutDetails: " + e.getMessage());
        }
        return result;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public VehicleGateInOutDto saveToGateInDetails(VehicleGateInOutDto entry, List<MultipartFile> imageFile) {
        if (entry.getGATE_IN_ID() == null) {
            throw new IllegalArgumentException("GATE_IN_ID is required");
        }
        if (entry.getTO_GATE_IN_DATE() == null || entry.getTO_GATE_IN_DATE().isBlank()) {
            throw new IllegalArgumentException("TO_GATE_IN_DATE is required");
        }
        Long gateInId = entry.getGATE_IN_ID();
        SugMaiBreederVehicleGateinout existing = breederVehicleGateInOutRepository.findById(gateInId)
                .orElseThrow(() -> new IllegalArgumentException("Gate in record not found for GATE_IN_ID: " + gateInId));
        if (existing.getFromGateOutDate() == null) {
            throw new IllegalArgumentException("Sending farm gate-out must be completed before TO gate-in");
        }
        if (existing.getToGateInDate() != null) {
            throw new IllegalArgumentException("TO_GATE_IN_DATE is already set for GATE_IN_ID: " + gateInId);
        }

        Date toGateInDate = parseGateDate(entry.getTO_GATE_IN_DATE());
        if (toGateInDate == null) {
            throw new IllegalArgumentException("Invalid TO_GATE_IN_DATE format");
        }
        Date toFarmCreatedDate = parseGateDate(entry.getTO_FARM_CREATED_DATE());
        if (toFarmCreatedDate == null) {
            toFarmCreatedDate = new Date();
        }
        String toFarmCreatedBy = entry.getTO_FARM_CREATED_BY();
        Long toBranchId = entry.getTO_BRANCH_ID() != null ? entry.getTO_BRANCH_ID() : existing.getToBranchId();

        int updatedRows = updateToGateInDateNative(gateInId, toGateInDate, toFarmCreatedBy, toFarmCreatedDate, toBranchId);
        System.out.println("saveToGateInDetails native updatedRows=" + updatedRows + " GATE_IN_ID=" + gateInId);

        if (updatedRows == 0) {
            existing.setToGateInDate(toGateInDate);
            existing.setToFarmCreatedBy(toFarmCreatedBy);
            existing.setToFarmCreatedDate(toFarmCreatedDate);
            if (toBranchId != null) {
                existing.setToBranchId(toBranchId);
            }
            breederVehicleGateInOutRepository.saveAndFlush(existing);
            System.out.println("saveToGateInDetails JPA fallback GATE_IN_ID=" + gateInId);
        }

        entityManager.flush();
        entityManager.clear();
        SugMaiBreederVehicleGateinout saved = breederVehicleGateInOutRepository.findById(gateInId)
                .orElseThrow(() -> new IllegalArgumentException("Gate in record not found after update"));

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data1 : imageFile) {
                    if (data1 != null && !data1.isEmpty()) {
                        Long branchId = toBranchId != null ? toBranchId : saved.getToBranchId();
                        fileStorageService.saveImage(data1,
                                entry.getVEHICLE_NO() != null ? entry.getVEHICLE_NO() : saved.getVehicleNo(),
                                branchId != null ? branchId : 0L, FileStorageCategory.GATE_IN_OUT);
                    }
                }
            }
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("Error in saveToGateInDetails image: " + ex.getMessage());
        }
        return mapToVehicleGateInOutDto(saved);
    }

    @Override
    @Transactional
    public VehicleGateInOutDto saveReceivingFarmGateInDetails(VehicleGateInOutDto entry, List<MultipartFile> imageFile) {
        return saveToGateInDetails(entry, imageFile);
    }

    private int updateToGateInDateNative(
            Long gateInId,
            Date toGateInDate,
            String toFarmCreatedBy,
            Date toFarmCreatedDate,
            Long toBranchId) {
        return entityManager.createNativeQuery("""
                        UPDATE SUG.SUG_MAI_BREEDER_VEHICLE_GATEINOUT
                           SET TO_GATE_IN_DATE = :toGateInDate,
                               TO_FARM_CREATED_BY = :toFarmCreatedBy,
                               TO_FARM_CREATED_DATE = :toFarmCreatedDate,
                               TO_BRANCH_ID = :toBranchId
                         WHERE GATE_IN_ID = :gateInId
                        """)
                .setParameter("toGateInDate", new java.sql.Timestamp(toGateInDate.getTime()))
                .setParameter("toFarmCreatedBy", toFarmCreatedBy)
                .setParameter("toFarmCreatedDate", new java.sql.Timestamp(toFarmCreatedDate.getTime()))
                .setParameter("toBranchId", toBranchId)
                .setParameter("gateInId", gateInId)
                .executeUpdate();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public VehicleGateInOutDto saveToGateOutDetails(VehicleGateInOutDto entry, List<MultipartFile> imageFile) {
        if (entry.getGATE_IN_ID() == null) {
            throw new IllegalArgumentException("GATE_IN_ID is required");
        }
        if (entry.getTO_GATE_OUT_DATE() == null || entry.getTO_GATE_OUT_DATE().isBlank()) {
            throw new IllegalArgumentException("TO_GATE_OUT_DATE is required");
        }
        Long gateInId = entry.getGATE_IN_ID();
        SugMaiBreederVehicleGateinout existing = breederVehicleGateInOutRepository.findById(gateInId)
                .orElseThrow(() -> new IllegalArgumentException("Gate in record not found for GATE_IN_ID: " + gateInId));
        if (existing.getToGateInDate() == null) {
            throw new IllegalArgumentException("TO gate-in must be completed before TO gate-out");
        }
        if (existing.getToGateOutDate() != null) {
            throw new IllegalArgumentException("TO_GATE_OUT_DATE is already set for GATE_IN_ID: " + gateInId);
        }
        if (!isInStatusY(existing.getInStatus())) {
            throw new IllegalArgumentException("IN_STATUS must be Y before TO gate-out");
        }
        if (entry.getTO_BRANCH_ID() != null && existing.getToBranchId() != null
                && !entry.getTO_BRANCH_ID().equals(existing.getToBranchId())) {
            throw new IllegalArgumentException("TO_BRANCH_ID does not match gate record");
        }

        Date toGateOutDate = parseGateDate(entry.getTO_GATE_OUT_DATE());
        if (toGateOutDate == null) {
            throw new IllegalArgumentException("Invalid TO_GATE_OUT_DATE format");
        }
        Date toFarmUpdatedDate = parseGateDate(entry.getTO_FARM_UPDATED_DATE());
        if (toFarmUpdatedDate == null) {
            toFarmUpdatedDate = new Date();
        }
        String toFarmUpdatedBy = entry.getTO_FARM_UPDATED_BY();
        if (toFarmUpdatedBy == null || toFarmUpdatedBy.isBlank()) {
            toFarmUpdatedBy = entry.getTO_FARM_CREATED_BY();
        }

        int updatedRows = updateToGateOutDateNative(gateInId, toGateOutDate, toFarmUpdatedBy, toFarmUpdatedDate);
        System.out.println("saveToGateOutDetails native updatedRows=" + updatedRows + " GATE_IN_ID=" + gateInId);

        if (updatedRows == 0) {
            existing.setToGateOutDate(toGateOutDate);
            existing.setToFarmUpdatedBy(toFarmUpdatedBy);
            existing.setToFarmUpdatedDate(toFarmUpdatedDate);
            breederVehicleGateInOutRepository.saveAndFlush(existing);
            System.out.println("saveToGateOutDetails JPA fallback GATE_IN_ID=" + gateInId);
        }

        entityManager.flush();
        entityManager.clear();
        SugMaiBreederVehicleGateinout saved = breederVehicleGateInOutRepository.findById(gateInId)
                .orElseThrow(() -> new IllegalArgumentException("Gate in record not found after update"));

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data1 : imageFile) {
                    if (data1 != null && !data1.isEmpty()) {
                        Long branchId = entry.getTO_BRANCH_ID() != null ? entry.getTO_BRANCH_ID() : saved.getToBranchId();
                        fileStorageService.saveImage(data1,
                                entry.getVEHICLE_NO() != null ? entry.getVEHICLE_NO() : saved.getVehicleNo(),
                                branchId != null ? branchId : 0L, FileStorageCategory.GATE_IN_OUT);
                    }
                }
            }
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println("Error in saveToGateOutDetails image: " + ex.getMessage());
        }
        return mapToVehicleGateInOutDto(saved);
    }

    private boolean isInStatusY(String inStatus) {
        return inStatus != null && "Y".equalsIgnoreCase(inStatus.trim());
    }

    private int updateToGateOutDateNative(
            Long gateInId,
            Date toGateOutDate,
            String toFarmUpdatedBy,
            Date toFarmUpdatedDate) {
        return entityManager.createNativeQuery("""
                        UPDATE SUG.SUG_MAI_BREEDER_VEHICLE_GATEINOUT
                           SET TO_GATE_OUT_DATE = :toGateOutDate,
                               TO_FARM_UPDATED_BY = :toFarmUpdatedBy,
                               TO_FARM_UPDATED_DATE = :toFarmUpdatedDate
                         WHERE GATE_IN_ID = :gateInId
                        """)
                .setParameter("toGateOutDate", new java.sql.Timestamp(toGateOutDate.getTime()))
                .setParameter("toFarmUpdatedBy", toFarmUpdatedBy)
                .setParameter("toFarmUpdatedDate", new java.sql.Timestamp(toFarmUpdatedDate.getTime()))
                .setParameter("gateInId", gateInId)
                .executeUpdate();
    }

    private Long resolveBranchIdFromRequest(BranchRequest branchRequest) {
        if (branchRequest.getBranchID() != null && !branchRequest.getBranchID().isBlank()) {
            return Long.valueOf(branchRequest.getBranchID().trim());
        }
        return null;
    }

    @Override
    public ArrayList<VehicleGateInOutDto> getVehicleGateInOutDetails(VehicleGateInOutDto entry) {
        ArrayList<VehicleGateInOutDto> result = new ArrayList<>();
        try {
            if (entry.getGATE_IN_ID() != null) {
                SugMaiBreederVehicleGateinout record = breederVehicleGateInOutRepository.findById(entry.getGATE_IN_ID()).orElse(null);
                if (record != null) {
                    result.add(mapToVehicleGateInOutDto(record));
                }
                return result;
            }
            Long fromBranchId = resolveFromBranchId(entry);
            if (fromBranchId != null) {
                List<SugMaiBreederVehicleGateinout> records = breederVehicleGateInOutRepository.findByFromBranchIdOrderByFromGateInDateDesc(fromBranchId);
                for (SugMaiBreederVehicleGateinout record : records) {
                    result.add(mapToVehicleGateInOutDto(record));
                }
            } else if (entry.getTO_BRANCH_ID() != null) {
                List<SugMaiBreederVehicleGateinout> records = breederVehicleGateInOutRepository.findByToBranchIdOrderByToGateInDateDesc(entry.getTO_BRANCH_ID());
                for (SugMaiBreederVehicleGateinout record : records) {
                    result.add(mapToVehicleGateInOutDto(record));
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getVehicleGateInOutDetails: " + e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public String editVehicleGateInOutDetails(VehicleGateInOutDto entry, List<MultipartFile> imageFile) {
        try {
            if (entry.getGATE_IN_ID() == null) {
                return "GATE_IN_ID is required";
            }
            SugMaiBreederVehicleGateinout gateInOut = breederVehicleGateInOutRepository.findById(entry.getGATE_IN_ID()).orElse(null);
            if (gateInOut == null) {
                return "Gate in record not found";
            }
            applyEditVehicleGateInOutFields(gateInOut, entry);
            breederVehicleGateInOutRepository.save(gateInOut);

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            System.out.println("Error in editVehicleGateInOutDetails: " + e.getMessage());
            return e.getMessage();
        }
        try {String mortalityImage = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data1 : imageFile) {
                    Long branchId = resolveFromBranchId(entry);
                    mortalityImage = fileStorageService.saveImage(data1, entry.getVEHICLE_NO(), branchId != null ? branchId : 0L, FileStorageCategory.GATE_IN_OUT);
                    /*DailyEntryLines dailyEntryLines = DailyEntryLines.builder()
                            .transId(saveResult.getTransId())
                            .hdrType("MORTALITY")
                            .imagePath(mortalityImage)
                            .build();*/
                    /**
                     * AI Mortality Count
                     */



                }
            }
        } catch (IOException | IllegalArgumentException ex) {
            //  return Response.buildSingleResponse("Failed", HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        }
        return "200";
    }

    private boolean isTransferIn(String transferType) {
        return transferType != null && "IN".equalsIgnoreCase(transferType.trim());
    }

    /**
     * transfer_type IN: planId/pid = GATE_IN_ID → set IN_STATUS = Y on receiving transfer post.
     */
    private void updateVehicleGateInOutInStatusForTransIn(SUGMAIGPPSTRANS_HDRDto farmDto) {
        String planId = resolvePidForTransOut(farmDto);
        if (planId == null || planId.isBlank()) {
            System.out.println("planId/pid missing in saveTransOut (transfer IN) for IN_STATUS update");
            return;
        }
        try {
            Long gateInId = parsePayloadId(planId);
            int updatedRows = entityManager.createNativeQuery("""
                            UPDATE SUG.SUG_MAI_BREEDER_VEHICLE_GATEINOUT
                               SET IN_STATUS = 'Y'
                             WHERE GATE_IN_ID = :gateInId
                            """)
                    .setParameter("gateInId", gateInId)
                    .executeUpdate();
            if (updatedRows == 0) {
                breederVehicleGateInOutRepository.findById(gateInId).ifPresent(gateRow -> {
                    gateRow.setInStatus("Y");
                    breederVehicleGateInOutRepository.saveAndFlush(gateRow);
                });
            }
            System.out.println("Gate IN_STATUS=Y GATE_IN_ID=" + gateInId + " updatedRows=" + updatedRows);
        } catch (NumberFormatException e) {
            System.out.println("Invalid planId/pid for IN_STATUS update: " + planId);
        } catch (Exception e) {
            System.out.println("Error in updateVehicleGateInOutInStatusForTransIn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * pid = PLAN_DTL_ID. Gate row: GATE_IN_ID = pid and FROM_GATE_OUT_DATE is null → set TO_BRANCH_ID from to_farm_id.
     */
    private void updateVehicleGateInOutForTransOut(SUGMAIGPPSTRANS_HDRDto farmDto) {
        String pid = resolvePidForTransOut(farmDto);
        if (pid == null || pid.isBlank()) {
            System.out.println("pid (plan_dtl_id) missing in saveTransOut payload for gate update");
            return;
        }
        Long toBranchId = resolveToBranchIdForTransOut(farmDto);
        if (toBranchId == null) {
            System.out.println("to_farm_id missing in saveTransOut payload for gate TO_BRANCH_ID update");
            return;
        }
        try {
            Long gateInId = parsePayloadId(pid);
            SugMaiBreederVehicleGateinout gateInOut = breederVehicleGateInOutRepository
                    .findByGateInIdAndFromGateOutDateIsNull(gateInId)
                    .orElse(null);
            if (gateInOut == null) {
                System.out.println("No open gate row (FROM_GATE_OUT_DATE null) for GATE_IN_ID=PLAN_DTL_ID: " + gateInId);
                return;
            }
            gateInOut.setToBranchId(toBranchId);
            breederVehicleGateInOutRepository.saveAndFlush(gateInOut);
            System.out.println("Gate update: GATE_IN_ID=" + gateInId + " TO_BRANCH_ID=" + toBranchId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid pid for gate update: " + pid);
        } catch (Exception e) {
            System.out.println("Error in updateVehicleGateInOutForTransOut: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void applyPlanDtlIdFromPid(SUGMAIGPPSTRANS_HDRDto farmDto, SugMaiGppsTransHdr header) {
        String pid = resolvePidForTransOut(farmDto);
        if (pid == null || pid.isBlank()) {
            System.out.println("pid missing in saveTransOut payload for PLAN_DTL_ID");
            return;
        }
        try {
            Long planDtlId = parsePayloadId(pid);
            header.setPLAN_DTL_ID(planDtlId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid pid for PLAN_DTL_ID: " + pid);
        }
    }

    private String resolvePidForTransOut(SUGMAIGPPSTRANS_HDRDto farmDto) {
        if (farmDto.getPid() != null && !farmDto.getPid().isBlank()) {
            return farmDto.getPid().trim();
        }
        return null;
    }

    private Long parsePayloadId(String idValue) {
        return new BigDecimal(idValue.trim()).longValue();
    }

    private Date parseTransferTxnDate(String txnDate, String txnTime) {
        if (txnDate == null || txnDate.isBlank()) {
            return null;
        }
        String datePart = txnDate.trim();
        boolean hasTime = txnTime != null && !txnTime.isBlank();
        String valueToParse = hasTime ? datePart + " " + txnTime.trim() : datePart;
        String[] patterns = hasTime
                ? new String[]{
                "dd-MM-yyyy hh:mm a",
                "dd-MM-yyyy h:mm a",
                "dd-MM-yyyy HH:mm",
                "dd-MMM-yyyy hh:mm a",
                "dd-MMM-yyyy HH:mm:ss",
                "dd/MM/yyyy hh:mm a"
        }
                : new String[]{
                "dd-MM-yyyy",
                "dd-MMM-yyyy",
                "dd/MM/yyyy",
                "yyyy-MM-dd"
        };
        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
                sdf.setLenient(false);
                return sdf.parse(valueToParse);
            } catch (ParseException ignored) {
            }
        }
        if (hasTime) {
            return parseTransferTxnDate(datePart, null);
        }
        return null;
    }

    private Long resolveToBranchIdForTransOut(SUGMAIGPPSTRANS_HDRDto farmDto) {
        if (farmDto.getTo_branch_id() != null) {
            return farmDto.getTo_branch_id().longValue();
        }
        if (farmDto.getTo_farm_id() != null) {
            return farmDto.getTo_farm_id().longValue();
        }
        if (farmDto.getDetails() != null) {
            for (SUGMAIGPPSTRANS_HDRDto.SugMaiGppsTrans_DtlDto detail : farmDto.getDetails()) {
                if (detail.getTo_farm_id() != null) {
                    return detail.getTo_farm_id().longValue();
                }
            }
        }
        return null;
    }

    private Long resolveFromBranchId(VehicleGateInOutDto entry) {
        return entry.getFROM_BRANCH_ID();
    }

    private VehicleGateInOutDto mapToVehicleGateInOutDto(SugMaiBreederVehicleGateinout record) {
        VehicleGateInOutDto dto = new VehicleGateInOutDto();
        dto.setGATE_IN_ID(record.getGateInId());
        dto.setFROM_BRANCH_ID(record.getFromBranchId());
        dto.setVEHICLE_NO(record.getVehicleNo());
        dto.setDRIVER_NAME(record.getDriverName());
        dto.setDRIVER_MOBILE_NO(record.getDriverMobileNo());
        dto.setPURPOSE(record.getPurpose());
        dto.setFROM_GATE_IN_DATE(formatDate(record.getFromGateInDate()));
        dto.setFROM_GATE_OUT_DATE(formatDate(record.getFromGateOutDate()));
        dto.setFROM_CREATED_BY(record.getFromCreatedBy());
        dto.setFROM_CREATED_DATE(formatDate(record.getFromCreatedDate()));
        dto.setFROM_UPDATED_BY(record.getFromUpdatedBy());
        dto.setFROM_UPDATED_DATE(formatDate(record.getFromUpdatedDate()));
        dto.setTO_GATE_IN_DATE(formatDate(record.getToGateInDate()));
        dto.setTO_GATE_OUT_DATE(formatDate(record.getToGateOutDate()));
        dto.setTO_BRANCH_ID(record.getToBranchId());
        dto.setTO_FARM_CREATED_BY(record.getToFarmCreatedBy());
        dto.setTO_FARM_CREATED_DATE(formatDate(record.getToFarmCreatedDate()));
        dto.setTO_FARM_UPDATED_BY(record.getToFarmUpdatedBy());
        dto.setTO_FARM_UPDATED_DATE(formatDate(record.getToFarmUpdatedDate()));
        dto.setSHIPMENT_NO(record.getShipmentNo());
        dto.setORDER_NO(record.getOrderNo());
        dto.setIN_STATUS(record.getInStatus());
        return dto;
    }

    private void applyEditVehicleGateInOutFields(SugMaiBreederVehicleGateinout gateInOut, VehicleGateInOutDto entry) {
        if (entry.getVEHICLE_NO() != null && !entry.getVEHICLE_NO().isBlank()) {
            gateInOut.setVehicleNo(entry.getVEHICLE_NO());
        }
        if (entry.getDRIVER_NAME() != null) {
            gateInOut.setDriverName(entry.getDRIVER_NAME());
        }
        if (entry.getDRIVER_MOBILE_NO() != null) {
            gateInOut.setDriverMobileNo(entry.getDRIVER_MOBILE_NO());
        }
        if (entry.getPURPOSE() != null) {
            gateInOut.setPurpose(entry.getPURPOSE());
        }
        if (entry.getFROM_GATE_IN_DATE() != null && !entry.getFROM_GATE_IN_DATE().isBlank()) {
            Date fromGateInDate = parseGateDate(entry.getFROM_GATE_IN_DATE());
            if (fromGateInDate == null) {
                throw new IllegalArgumentException("Invalid GATE_IN_DATE / FROM_GATE_IN_DATE format");
            }
            gateInOut.setFromGateInDate(fromGateInDate);
        }
        if (entry.getFROM_GATE_OUT_DATE() != null && !entry.getFROM_GATE_OUT_DATE().isBlank()) {
            Date fromGateOutDate = parseGateDate(entry.getFROM_GATE_OUT_DATE());
            if (fromGateOutDate == null) {
                throw new IllegalArgumentException("Invalid GATE_OUT_DATE / FROM_GATE_OUT_DATE format");
            }
            gateInOut.setFromGateOutDate(fromGateOutDate);
        }
        if (entry.getFROM_UPDATED_BY() != null) {
            gateInOut.setFromUpdatedBy(entry.getFROM_UPDATED_BY());
        }
        gateInOut.setFromUpdatedDate(new Date());
    }

    private Date parseGateDate(String dateValue) {
        if (dateValue == null || dateValue.isBlank()) {
            return null;
        }
        String trimmed = dateValue.trim();
        String[] dateTimePatterns = {
                fromdateFormat,
                "dd-MMM-yyyy HH:mm:ss",
                "dd-MMM-yyyy HH:mm",
                "dd-MM-yyyy HH:mm:ss",
                "dd-MM-yyyy HH:mm",
                "dd-MMM-yyyy hh:mm a",
                "dd-MM-yyyy hh:mm a",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd'T'HH:mm:ss"
        };
        for (String pattern : dateTimePatterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
                sdf.setLenient(false);
                return sdf.parse(trimmed);
            } catch (ParseException ignored) {
            }
        }
        Date parsedDate = parseTransferTxnDate(trimmed, null);
        if (parsedDate == null) {
            parsedDate = getTxnDateString(trimmed, fromdateFormat);
        }
        if (parsedDate == null) {
            parsedDate = getTxnDateString(trimmed, fromdateFormat1);
        }
        return parsedDate;
    }

    private String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(fromdateFormat).format(date);
    }

    @Override
    public ArrayList<FromFarmShedTransferDetailsDto> gettranfershedmaster(String branchID) {
        // BranchUser.FeedAllocationDetails details = new BranchUser.FeedAllocationDetails();
        ArrayList<FromFarmShedTransferDetailsDto> shedDetailsArrayList = new ArrayList<FromFarmShedTransferDetailsDto>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.gettranfershedmaster");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                FromFarmShedTransferDetailsDto shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, FromFarmShedTransferDetailsDto.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        //  details.setFarmFlockDetails(shedDetailsArrayList);
        //  details.setGardeMstDetails(getgrademst(branchID));
        return shedDetailsArrayList;
    }

}
