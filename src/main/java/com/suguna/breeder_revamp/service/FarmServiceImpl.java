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
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {
    String fromdateFormat  = "dd-MMM-yyyy HH:mm:ss";
    String fromdateFormat1 = "dd-MMM-yyyy";
    @Autowired
    EntityManager entityManager;

    @Autowired
    SugGppsObservationHeaderRepositories sugGppsObservationHeaderRepositories;

    @Autowired
    SugGppsObservationDetailsRepositories sugGppsObservationDetailsRepositories;

    @Autowired
    SugMaiGppsConsumptionsRepositories sugMaiGppsConsumptionsRepositories;

    @Autowired
    SugMaiGppsItemAllocationRepositories sugMaiGppsItemAllocationRepositories;

    @Autowired
    SugMaiGppsHousingShedRepositories sugMaiGppsHousingShedRepositories;

    @Autowired
    SugMaiGppsHousingLineRepositories sugMaiGppsHousingLineRepositories;

    @Autowired
    SugMaiGppsFarmLogRepositories sugMaiGppsFarmLogRepositories;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    SugMaiGppsItemConsumptionRepository sugMaiGppsItemConsumptionRepository;

    @Autowired
    SugMaiGppsEggWeightReadingRepositories sugMaiGppsEggWeightReadingRepositories;

    @Autowired
    SugCVBodyWeightRepository sugCVBodyWeightRepository;

    @Autowired
    SugCVBodyWeightDtlRepository sugCVBodyWeightDtlRepository;

    @Autowired
    EggWeightCaptureRepository eggWeightCaptureRepository;

    @Autowired
    SugMaiBreederDailyEntryRepository sugMaiBreederDailyEntryRepository;

    @Autowired
    SugMaiEggQualityCaptureRepository sugMaiEggQualityCaptureRepository;

    @Override
    public ArrayList<BranchUser> getBranchUsers(BranchRequest branchRequest) {
        ArrayList<BranchUser> branchUserArrayList = new ArrayList<BranchUser>();
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
                branchUser.setUserDetails(getRegisteredBranchUsers(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType(), branchUser.getBranchName()));
                branchUser.setBranchUserDetails(getSupervisorNewDetails(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType()));
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
    }

    public ArrayList<BranchUser.SupervisorDetails> getSupervisorNewDetails(String branchid,String userType) {
        ArrayList<BranchUser.SupervisorDetails> supervisorDetailsArrayList = new ArrayList<BranchUser.SupervisorDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getsupervisornew_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchid);
            storedProcedureQuery.setParameter(2, userType);
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
    public ArrayList<BranchUser.SupervisorDetails> getSupervisorDetails(BranchRequest branchRequest) {
        ArrayList<BranchUser.SupervisorDetails> supervisorDetailsArrayList = new ArrayList<BranchUser.SupervisorDetails>();
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
                supervisorDetails.setShedNo(getShedname_sup(supervisorDetails.getEmpNo()));
                supervisorDetailsArrayList.add(supervisorDetails);
            }
        } catch (Exception e) {

        }
        return supervisorDetailsArrayList;
    }

    private ArrayList<String> getShedname_sup(String empNo) {
        ArrayList<String> shedDetailsArrayList = new ArrayList<String>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getShedname_sup");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, empNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                String value = resultSet.getString("LINE_NAME");
                shedDetailsArrayList.add(value);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ShedDetails> getShedDetails(String branchID,String userType,String userCode) {
        ArrayList<BranchUser.ShedDetails> shedDetailsArrayList = new ArrayList<BranchUser.ShedDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshed_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, userType);
            storedProcedureQuery.setParameter(3, userCode);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.ShedDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedDetails.class);
                String Standard=getFeedStandard(branchID, String.valueOf(shedDetails.getAge()));
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
                try {
                    BranchUser.DailyEntryCompletedDetails standardDetailsArrayList = new BranchUser.DailyEntryCompletedDetails();
                    standardDetailsArrayList=getDailyShedEntryDetails(branchID,shedDetails.getBatchId(),shedDetails.getFlockID());
                    shedDetails.setEggProductionAge(standardDetailsArrayList.getEgg_PRODUCTION_AGE());
                    shedDetails.setEntryDate(standardDetailsArrayList.getEntry_DATE());
                    shedDetails.setEntryAllowed(standardDetailsArrayList.getEntry_allowed());
                    //String bodyweightdeviation=getbodyweightdeviation(branchID, String.valueOf(shedDetails.getAge()));
                   // shedDetails.setBodyWeightDeviation(bodyweightdeviation);
                } catch (Exception e) {
                    // throw new RuntimeException(e);
                }
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ShedLineDetails> getShedLineDetails(String branchID, String shedNo) {
        ArrayList<BranchUser.ShedLineDetails> shedLineDetailsArrayList = new ArrayList<BranchUser.ShedLineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshedline_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.ShedLineDetails shedLineDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedLineDetails.class);

                shedLineDetailsArrayList.add(shedLineDetails);
            }
        } catch (Exception e) {

        }
        return shedLineDetailsArrayList;
    }


    public ArrayList<BranchUser.RegisteredBranchUser> getRegisteredBranchUsers(String branchId, String userType, String branchCode) {
        ArrayList<BranchUser.RegisteredBranchUser> branchUserArrayList = new ArrayList<BranchUser.RegisteredBranchUser>();
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

    @Override
    public ArrayList<BranchUser.DailyFlockEntryDetails> getDailyEntrySchedule(BranchRequest branchRequest) {
        ArrayList<BranchUser.DailyFlockEntryDetails> shedDetailsArrayList = new ArrayList<BranchUser.DailyFlockEntryDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshed_dailyentrydtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getBatchID());
            storedProcedureQuery.setParameter(3, branchRequest.getShedNo());
            storedProcedureQuery.setParameter(4, branchRequest.getEntryDate());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(5);

            while (resultSet.next()) {
                BranchUser.DailyFlockEntryDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.DailyFlockEntryDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ObservationCategory> getObservationCategory(BranchRequest branchRequest) {
        ArrayList<BranchUser.ObservationCategory> shedDetailsArrayList = new ArrayList<BranchUser.ObservationCategory>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getobservationslist");

            storedProcedureQuery.registerStoredProcedureParameter(1, ArrayList.class, ParameterMode.REF_CURSOR);

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(1);

            while (resultSet.next()) {
                BranchUser.ObservationCategory shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ObservationCategory.class);
                shedDetails.setQuestion(getObservationCategoryDetails(shedDetails.getCategoryId(),branchRequest));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public String saveObservationDetails(BranchRequest branchRequest) {
        try {
            List<String> data = new ArrayList<>();
            data = (List<String>) branchRequest.getData();
            if (!data.isEmpty()) {
                List<SugGppsObservationDTO> observationDTOS = getObservations();
                List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
                if (!batchDTOS.isEmpty()) {
                    Long transid;
                    SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
                    SugGppsObservationHeader sugGppsObservationHeader = new SugGppsObservationHeader();
                    sugGppsObservationHeader.setLEDGER_ID((gppsObservationBatchDTO.getLEDGER_ID()));
                    sugGppsObservationHeader.setTRANS_DATE(getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1));
                    sugGppsObservationHeader.setEMP_CODE(branchRequest.getUserCode());
                    sugGppsObservationHeader.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                    sugGppsObservationHeader.setBRANCH_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    sugGppsObservationHeader.setFLOCK_NO(gppsObservationBatchDTO.getFLOCK_NO());
                    sugGppsObservationHeader.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    sugGppsObservationHeader.setBATCH_NO(Long.valueOf(gppsObservationBatchDTO.getBATCH_NO()));
                    sugGppsObservationHeader.setLOCATION_CODE(gppsObservationBatchDTO.getLOCATION_CODE());
                    sugGppsObservationHeader.setCREATION_DATE(new Date());
                    sugGppsObservationHeader.setSHED_NO(branchRequest.getShedNo());
                    SugGppsObservationHeader sugGppsObservationHeader1 = sugGppsObservationHeaderRepositories.save(sugGppsObservationHeader);
                    transid = sugGppsObservationHeader1.getTRANS_ID();
                    for (SugGppsObservationDTO sugGppsObservationDTO : observationDTOS) {
                        String value = "false";
                        if (data.contains(String.valueOf(sugGppsObservationDTO.getObservationId()))) {
                            value = "true";
                        }
                        SugGppsObservationDetails sugGppsObservationDetails = new SugGppsObservationDetails();
                        sugGppsObservationDetails.setTRANS_ID(transid);
                        sugGppsObservationDetails.setCATEGORY_ID(sugGppsObservationDTO.getCategoryId());
                        sugGppsObservationDetails.setOBSERVATION_ID(sugGppsObservationDTO.getObservationId());
                       // sugGppsObservationDetails.setTRANS_ID(Long.valueOf(1));
                        sugGppsObservationDetails.setCREATION_DATE(new Date());
                        sugGppsObservationDetails.setOBSERVATION_FLAG(value);
                        sugGppsObservationDetailsRepositories.save(sugGppsObservationDetails);

                    }
                }
            }
            return "200";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<BranchUser.ShedWiseFeedBirdsDetails> getshedwise_feeddtls(BranchRequest branchRequest) {
        ArrayList<BranchUser.ShedWiseFeedBirdsDetails> shedBirsDetailsArrayList = new ArrayList<BranchUser.ShedWiseFeedBirdsDetails>();
        ArrayList<BranchUser.ShedWiseFeedDetails> shedDetailsArrayList = new ArrayList<BranchUser.ShedWiseFeedDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshedwise_feeddtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getShedNo());
            storedProcedureQuery.setParameter(3, branchRequest.getFlockID());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.ShedWiseFeedDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedWiseFeedDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
            // Use a Set to collect unique bird types
            Set<String> uniqueBirdTypes = new LinkedHashSet<>();
            for (BranchUser.ShedWiseFeedDetails b : shedDetailsArrayList) {
                uniqueBirdTypes.add(b.getBirdType());
            }

            // Convert back to list if needed
            List<String> uniqueBirdList = new ArrayList<>(uniqueBirdTypes);
            for (String birdType : uniqueBirdList) {
                System.out.println("Bird Type: " + birdType);
                BranchUser.ShedWiseFeedBirdsDetails shedWiseFeedBirdsDetails = new BranchUser.ShedWiseFeedBirdsDetails();
                shedWiseFeedBirdsDetails.setBirdType(birdType);
                ArrayList<BranchUser.ShedWiseFeedDetails> shedDetailsArray = new ArrayList<BranchUser.ShedWiseFeedDetails>();
                for (BranchUser.ShedWiseFeedDetails b : shedDetailsArrayList) {

                    if (b.getBirdType().equalsIgnoreCase(birdType)) {
                        shedDetailsArray.add(b);

                    }
                }
                shedWiseFeedBirdsDetails.setFeedDetails(shedDetailsArray);
                shedWiseFeedBirdsDetails.setFeedEntryMadeDetails(getShedWiseFeedMadeDetails(branchRequest,shedWiseFeedBirdsDetails.getBirdType()));
                shedBirsDetailsArrayList.add(shedWiseFeedBirdsDetails);
            }

        } catch (Exception e) {

        }
        return shedBirsDetailsArrayList;
    }

    private ArrayList<BranchUser.ShedWiseFeedMadeDetails> getShedWiseFeedMadeDetails(BranchRequest branchRequest, String birdType) {
        ArrayList<BranchUser.ShedWiseFeedMadeDetails> shedDetailsArrayList = new ArrayList<BranchUser.ShedWiseFeedMadeDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getfeeddailymadedtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getEntryDate());
            storedProcedureQuery.setParameter(2, branchRequest.getBatchID());
            storedProcedureQuery.setParameter(3, branchRequest.getShedNo());
            storedProcedureQuery.setParameter(4, birdType);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(5);

            while (resultSet.next()) {
                BranchUser.ShedWiseFeedMadeDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedWiseFeedMadeDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ShedWiseBirdsDetails> getshedwise_birdsdtls(BranchRequest branchRequest) {
        ArrayList<BranchUser.ShedWiseBirdsDetails> shedDetailsArrayList = new ArrayList<BranchUser.ShedWiseBirdsDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshedwise_birdsdtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getShedNo());
            storedProcedureQuery.setParameter(3, branchRequest.getFlockID());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.ShedWiseBirdsDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedWiseBirdsDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public BranchUser.EggDetails getegg_collectiondtls(BranchRequest branchRequest) {
        ArrayList<BranchUser.EggDetails> eggDetails = new ArrayList<BranchUser.EggDetails>();
        ArrayList<BranchUser.EggItemDetails> eggItemDetailsArrayList = new ArrayList<BranchUser.EggItemDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.geteggitemdtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getShedNo());
            storedProcedureQuery.setParameter(3, branchRequest.getFlockID());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.EggItemDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.EggItemDetails.class);

                eggItemDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        BranchUser.EggDetails details = new BranchUser.EggDetails();
        details.setEggItemDetails(eggItemDetailsArrayList);
        details.setEggCollectedDetails(getegg_collecteddtls(branchRequest));

        return details;
    }

    public ArrayList<BranchUser.EggCollectionDetails> getegg_collecteddtls(BranchRequest branchRequest) {

        ArrayList<BranchUser.EggCollectionDetails> eggItemDetailsArrayList = new ArrayList<BranchUser.EggCollectionDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.geteggcollecteddtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getShedNo());
            storedProcedureQuery.setParameter(3, branchRequest.getFlockID());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.EggCollectionDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.EggCollectionDetails.class);

                eggItemDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }

        return eggItemDetailsArrayList;
    }

    @Override
    public String saveFeedDetails(BranchRequest branchRequest, List<MultipartFile> imageFile) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugFeedDetails> data1 = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugFeedDetails details =
                        mapper.convertValue(item, BranchRequest.SugFeedDetails.class);
                data1.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data1.isEmpty()) {
            for (BranchRequest.SugFeedDetails sugFeedDetails : data1) {
                SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());
                maiGppsConsumptions.setGRADE(sugFeedDetails.getGrade());
                maiGppsConsumptions.setQTY(Long.valueOf(sugFeedDetails.getTotalActualFeed()));
                maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                maiGppsConsumptions.setSEX(sugFeedDetails.getBirdType());
                maiGppsConsumptions.setCREATION_DATE(new Date());
                maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                maiGppsConsumptions.setBIRD_TYPE(sugFeedDetails.getBirdCategory());
                maiGppsConsumptions.setTXN_TYPE("FEED");
                maiGppsConsumptions.setTXN_DATE(getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1));
                sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
            }

        }
        try {String mortalityImage = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                for (MultipartFile data : imageFile) {
                    mortalityImage = fileStorageService.saveImage(data, gppsObservationBatchDTO.getBRANCH_CODE(), Long.valueOf(branchRequest.getBatchID()), FileStorageCategory.FEED);
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
    public String saveMortalityDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugMortalityDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugMortalityDetails details =
                        mapper.convertValue(item, BranchRequest.SugMortalityDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SugMortalityDetails sugMortalityDetails : data) {
                SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());
                maiGppsConsumptions.setLINE_NO(sugMortalityDetails.getLineNo());
                maiGppsConsumptions.setQTY(Long.valueOf(sugMortalityDetails.getTotalBirds()));
                maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                maiGppsConsumptions.setSEX(sugMortalityDetails.getBirdType());
                maiGppsConsumptions.setCREATION_DATE(new Date());
                maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                maiGppsConsumptions.setSIDE_NO(sugMortalityDetails.getSideNo());
                maiGppsConsumptions.setBIRD_TYPE(sugMortalityDetails.getBirdCategory());
                maiGppsConsumptions.setTXN_TYPE("MORTALITY");
                maiGppsConsumptions.setTXN_DATE(getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1));
                sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
            }

        }
        return "200";
    }

    @Override
    public String saveEggCollectionDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugEggCollectionDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugEggCollectionDetails details =
                        mapper.convertValue(item, BranchRequest.SugEggCollectionDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SugEggCollectionDetails sugEggCollectionDetails : data) {
                if (sugEggCollectionDetails.getMode().equalsIgnoreCase("ADD")) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugEggCollectionDetails.getQuantity()));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setITEM_ID(Long.valueOf(sugEggCollectionDetails.getItemID()));
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSTATUS("N");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("EGG COLLECTION");
                    maiGppsConsumptions.setTXN_DATE(getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1));
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                    if (!sugEggCollectionDetails.getItemID().equalsIgnoreCase("0")) {
                        sugMaiGppsConsumptionsRepositories.updatestatus(sugEggCollectionDetails.getQuantity(), branchRequest.getShedNo(), gppsObservationBatchDTO.getBRANCH_CODE());
                    }
                } else if (sugEggCollectionDetails.getMode().equalsIgnoreCase("EDIT")) {
                    sugMaiGppsConsumptionsRepositories.updateentry(sugEggCollectionDetails.getQuantity(), sugEggCollectionDetails.getRowId());
                } else if (sugEggCollectionDetails.getMode().equalsIgnoreCase("DELETE")) {
                    sugMaiGppsConsumptionsRepositories.deleteentry(sugEggCollectionDetails.getQuantity(), sugEggCollectionDetails.getRowId());
                }
            }

        }
        return "200";
    }

    @Override
    public String saveWeekSeperationDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugWeekBirdDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugWeekBirdDetails details =
                        mapper.convertValue(item, BranchRequest.SugWeekBirdDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SugWeekBirdDetails sugWeekBirdDetails : data) {
                if (!sugWeekBirdDetails.getFemaleQty().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());
                    maiGppsConsumptions.setLINE_NO(sugWeekBirdDetails.getLineNo());
                    maiGppsConsumptions.setSEX("F");
                    maiGppsConsumptions.setQTY(Long.valueOf(sugWeekBirdDetails.getFemaleQty()));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    // maiGppsConsumptions.setITEM_ID(Long.valueOf(sugEggCollectionDetails.getItemID()));
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSTATUS("N");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("WEEK");
                    maiGppsConsumptions.setTXN_DATE(getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1));
                    maiGppsConsumptions.setREASON(sugWeekBirdDetails.getReasonType());
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);

                }
                if (!sugWeekBirdDetails.getMaleQty().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());
                    maiGppsConsumptions.setLINE_NO(sugWeekBirdDetails.getLineNo());
                    maiGppsConsumptions.setSEX("M");
                    maiGppsConsumptions.setQTY(Long.valueOf(sugWeekBirdDetails.getFemaleQty()));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    // maiGppsConsumptions.setITEM_ID(Long.valueOf(sugEggCollectionDetails.getItemID()));
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSTATUS("N");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("WEEK");
                    maiGppsConsumptions.setREMARK(sugWeekBirdDetails.getReasonType());
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);

                }
            }

        }
        return "200";
    }

    @Override
    public BranchUser.FeedAllocationDetails getFeedAllocationDetails(String branchID) {
        BranchUser.FeedAllocationDetails details = new BranchUser.FeedAllocationDetails();
        ArrayList<BranchUser.FarmFlockDetails> shedDetailsArrayList = new ArrayList<BranchUser.FarmFlockDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getFeedAllocationFlockdtls");

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
                shedDetails.setFarmFlockDetails(getFeedAllocationPreviousDetails(branchID,shedDetails.getFlock()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setGardeMstDetails(getgrademst(branchID));
        return details;
    }

    public ArrayList<BranchUser.GardeMstDetails> getgrademst(String branchID) {
        ArrayList<BranchUser.GardeMstDetails> shedDetailsArrayList = new ArrayList<BranchUser.GardeMstDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getgrademst");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.GardeMstDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.GardeMstDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    public ArrayList<BranchUser.ObservationCategoryDetails> getObservationCategoryDetails(String categoryID, BranchRequest branchRequest) {
        ArrayList<BranchUser.ObservationCategoryDetails> shedDetailsArrayList = new ArrayList<BranchUser.ObservationCategoryDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getobservationslist_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, categoryID);
            storedProcedureQuery.setParameter(2, branchRequest.getEntryDate());
            storedProcedureQuery.setParameter(3, branchRequest.getBatchID());
            storedProcedureQuery.setParameter(4, branchRequest.getShedNo());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(5);

            while (resultSet.next()) {
                BranchUser.ObservationCategoryDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ObservationCategoryDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }


    public List<SugGppsObservationDTO> getObservations() {

        String sql = """
                    SELECT
                      a.LEDGER_ID,
                      a.DIVISION,
                      a.CATEGORY,
                      a.CATEGORY_ID,
                      a.OBSERVATION_DESCRIPTION,
                      a.OBSERVATION_ID
                    FROM sug_gpps_observations a
                """;

        List<Object[]> results =
                entityManager.createNativeQuery(sql).getResultList();

        return results.stream()
                .map(row -> new SugGppsObservationDTO(
                        ((Number) row[0]).longValue(),   // LEDGER_ID
                        (String) row[1],                 // DIVISION
                        (String) row[2],                 // CATEGORY
                        ((Number) row[3]).longValue(),   // CATEGORY_ID
                        (String) row[4],                 // OBSERVATION_DESCRIPTION
                        ((BigDecimal) row[5])    // OBSERVATION_ID
                ))
                .collect(Collectors.toList());
    }

    public List<SugGppsObservationBatchDTO> getBatchDetails(String batchID) {

        String sql = "select h.attribute1  as flock_no,\n" +
                "       h.attribute4  as location_code,\n" +
                "       c.branch_code,\n" +
                "       h.BATCH_NO,l.INVENTORY_LOCATION_ID,c.ledger_id\n" +
                "  FROM sug_organization_mv c, gme_batch_header h,mtl_item_locations l\n" +
                " where h.organization_id = c.branch_id\n" +
                "   and h.BATCH_ID = " + batchID + "  and l.SEGMENT1=h.attribute4";

        List<Object[]> results =
                entityManager.createNativeQuery(sql).getResultList();

        return results.stream()
                .map(row -> new SugGppsObservationBatchDTO(
                        ((String) row[0]),   // LEDGER_ID
                        (String) row[1],                 // DIVISION
                        (String) row[2],                 // CATEGORY
                        ((String) row[3]) , ((BigDecimal) row[4]) , ((BigDecimal) row[5]) // CATEGORY_ID
                        // OBSERVATION_ID
                ))
                .collect(Collectors.toList());
    }

    @Override
    public String saveFeedAllocationDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        BranchRequest.SugFeedAllocationDetails data = new BranchRequest.SugFeedAllocationDetails();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        /*if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugFeedAllocationDetails details =
                        mapper.convertValue(item, BranchRequest.SugFeedAllocationDetails.class);
                data.add(details);
            }
        }*/
        if (rawData != null) {
            data = mapper.convertValue(rawData, BranchRequest.SugFeedAllocationDetails.class);
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.getFemale().isEmpty()) {
            for (BranchRequest.SugFeedAllocationDetails.FeedAllocationDetails sugFeedDetails : data.getFemale()) {
                SugMaiGppsItemAllocation sugMaiGppsItemAllocation = new SugMaiGppsItemAllocation();
                sugMaiGppsItemAllocation.setAGE(Long.valueOf(branchRequest.getAge()));
                sugMaiGppsItemAllocation.setFLOCK_ID(branchRequest.getFlockID());
                sugMaiGppsItemAllocation.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                sugMaiGppsItemAllocation.setITEM_TYPE("FEED");
                sugMaiGppsItemAllocation.setSEX("F");
                sugMaiGppsItemAllocation.setUOM("GRAMS");
                sugMaiGppsItemAllocation.setQTY(BigDecimal.valueOf(Double.parseDouble(sugFeedDetails.getQuantity())));
                sugMaiGppsItemAllocation.setGRADE(sugFeedDetails.getGrade());
                sugMaiGppsItemAllocation.setCREATED_BY(branchRequest.getUserCode());
                sugMaiGppsItemAllocation.setCREATION_DATE(new Date());
                sugMaiGppsItemAllocation.setDATE_FROM(convertToDate(branchRequest.getStartDate()));
                sugMaiGppsItemAllocation.setDATE_TO(convertToDate(branchRequest.getEndDate()));
                sugMaiGppsItemAllocation.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                sugMaiGppsItemAllocationRepositories.save(sugMaiGppsItemAllocation);
            }

        }
        if (!data.getMale().isEmpty()) {
            for (BranchRequest.SugFeedAllocationDetails.FeedAllocationDetails sugFeedDetails : data.getMale()) {
                SugMaiGppsItemAllocation sugMaiGppsItemAllocation = new SugMaiGppsItemAllocation();
                sugMaiGppsItemAllocation.setAGE(Long.valueOf(branchRequest.getAge()));
                sugMaiGppsItemAllocation.setFLOCK_ID(branchRequest.getFlockID());
                sugMaiGppsItemAllocation.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                sugMaiGppsItemAllocation.setITEM_TYPE("FEED");
                sugMaiGppsItemAllocation.setSEX("M");
                sugMaiGppsItemAllocation.setUOM("GRAMS");
                sugMaiGppsItemAllocation.setQTY(BigDecimal.valueOf(Double.parseDouble(sugFeedDetails.getQuantity())));
                sugMaiGppsItemAllocation.setGRADE(sugFeedDetails.getGrade());
                sugMaiGppsItemAllocation.setCREATED_BY(branchRequest.getUserCode());
                sugMaiGppsItemAllocation.setCREATION_DATE(new Date());
                sugMaiGppsItemAllocation.setDATE_FROM(convertToDate(branchRequest.getStartDate()));
                sugMaiGppsItemAllocation.setDATE_TO(convertToDate(branchRequest.getEndDate()));
                sugMaiGppsItemAllocation.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                sugMaiGppsItemAllocationRepositories.save(sugMaiGppsItemAllocation);
            }

        }
        return "200";
    }

    @Override
    public BranchUser.CullDetails getCullsDetails(String branchID) {
        BranchUser.CullDetails details = new BranchUser.CullDetails();
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
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setCullsReasonDetails(getcullsreasom(branchID));
        return details;
    }
    public ArrayList<BranchUser.CullsReasonDetails> getcullsreasom(String branchID) {
        ArrayList<BranchUser.CullsReasonDetails> shedDetailsArrayList = new ArrayList<BranchUser.CullsReasonDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getcullsreason");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.CullsReasonDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.CullsReasonDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    // Method to convert String to java.util.Date
    public static Date convertToDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }


    @Override
    public String saveCullingDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugCullingDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugCullingDetails details =
                        mapper.convertValue(item, BranchRequest.SugCullingDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        String serverDate = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(serverDate);

        if (!data.isEmpty()) {
            for (BranchRequest.SugCullingDetails sugCullingDetails : data) {
                if (!sugCullingDetails.getFemaleBirdsCount().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getFemaleBirdsCount()));
                    maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getFemaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setTXN_DATE(date);
                    maiGppsConsumptions.setSEX("Female");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("CULLING");
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                }
                if (!sugCullingDetails.getMaleBirdsCount().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
                    maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setTXN_DATE(date);
                    maiGppsConsumptions.setSEX("Male");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("CULLING");
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                }

            }
        }
        } catch (ParseException e) {

            }

        return "200";
    }

    @Override
    public BranchUser.DestroyDetails getDestroyDetails(String branchID) {
        BranchUser.DestroyDetails details = new BranchUser.DestroyDetails();
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
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setCullsReasonDetails(getdestroyreasom(branchID));
        return details;
    }
    public ArrayList<BranchUser.CullsReasonDetails> getdestroyreasom(String branchID) {
        ArrayList<BranchUser.CullsReasonDetails> shedDetailsArrayList = new ArrayList<BranchUser.CullsReasonDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getdestroyreason");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.CullsReasonDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.CullsReasonDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public String saveDestroyDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        BranchRequest.SugDestroyDetails data = new BranchRequest.SugDestroyDetails();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        /*if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugFeedAllocationDetails details =
                        mapper.convertValue(item, BranchRequest.SugFeedAllocationDetails.class);
                data.add(details);
            }
        }*/
        if (rawData != null) {
            data = mapper.convertValue(rawData, BranchRequest.SugDestroyDetails.class);
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.getReason().isEmpty()) {
            try {
                String serverDate = LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = sdf.parse(serverDate);
                SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());
                maiGppsConsumptions.setLINE_NO(branchRequest.getLineNo());
                maiGppsConsumptions.setTXN_DATE(date);
                // maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
                //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
                maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                maiGppsConsumptions.setREASON(data.getReason());
                maiGppsConsumptions.setREMARK(data.getRemark());
                maiGppsConsumptions.setCREATION_DATE(new Date());

                maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                maiGppsConsumptions.setTXN_TYPE("DESTROY");
                sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
            } catch (Exception e) {

            }
        }

        return "200";
    }

    @Override
    public BranchUser.MortalityPmlDetails getMortalityPmlDetails(BranchRequest branchRequest) {
        BranchUser.MortalityPmlDetails details = new BranchUser.MortalityPmlDetails();
        ArrayList<BranchUser.FarmFlockDetails> shedDetailsArrayList = new ArrayList<BranchUser.FarmFlockDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getpmlflockddtls");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getBranchID());
            storedProcedureQuery.setParameter(2, branchRequest.getFlockID());
            storedProcedureQuery.setParameter(3, branchRequest.getShedNo());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.FarmFlockDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FarmFlockDetails.class);
                String Standard=getFeedStandard(branchRequest.getBranchID(),shedDetails.getAge());
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Parse the string into LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(shedDetails.getTransDate(), inputFormatter);

                // Example: Convert to another format (ISO or custom)
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String formattedDate = dateTime.format(outputFormatter);
                shedDetails.setTransDate(formattedDate);
                System.out.println("Converted Date: " + formattedDate);
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
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setCullsReasonDetails(getmortalitypmlreason(branchRequest.getBranchID()));
        return details;
    }


    public ArrayList<BranchUser.CullsReasonDetails> getmortalitypmlreason(String branchID) {
        ArrayList<BranchUser.CullsReasonDetails> shedDetailsArrayList = new ArrayList<BranchUser.CullsReasonDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getmortalityreason");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.CullsReasonDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.CullsReasonDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public BranchUser.ExcessShortageDetails getExcessShortageDetails(String branchID) {
        BranchUser.ExcessShortageDetails details = new BranchUser.ExcessShortageDetails();
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
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setCullsReasonDetails(getexcessshortagereason(branchID));
        return details;
    }

    @Override
    public String saveMortalityPmlDetails(BranchRequest branchRequest, List<MultipartFile> imageFile) {
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugCullingDetails> data1 = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugCullingDetails details =
                        mapper.convertValue(item, BranchRequest.SugCullingDetails.class);
                data1.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data1.isEmpty()) {
            for (BranchRequest.SugCullingDetails sugCullingDetails : data1) {
                if(!sugCullingDetails.getFemaleBirdsCount().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getFemaleBirdsCount()));
                    //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getFemaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSEX("Female");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("MORTALITY_PML");
                    maiGppsConsumptions.setLINE_NO(sugCullingDetails.getLineNo());
                    maiGppsConsumptions.setSIDE_NO(sugCullingDetails.getSideNo());
                    //sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                    try {
                        sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                    } catch (StaleObjectStateException e) {
                        // handle conflict: reload entity and retry
                    }
                }
                if(!sugCullingDetails.getMaleBirdsCount().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
                    //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSEX("Male");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
                    maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
                    maiGppsConsumptions.setTXN_TYPE("MORTALITY_PML");
                    maiGppsConsumptions.setLINE_NO(sugCullingDetails.getLineNo());
                    maiGppsConsumptions.setSIDE_NO(sugCullingDetails.getSideNo());
                    try {
                        sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                    } catch (StaleObjectStateException e) {
                        // handle conflict: reload entity and retry
                    }

                }

            }
            try {String mortalityImage = null;
                if (imageFile != null && !imageFile.isEmpty()) {
                    for (MultipartFile data : imageFile) {
                        mortalityImage = fileStorageService.saveImage(data, gppsObservationBatchDTO.getBRANCH_CODE(), Long.valueOf(branchRequest.getBatchID()), FileStorageCategory.FEED);
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
        }
        return "200";
    }

    @Override
    public String saveExcessShortageDetails(BranchRequest branchRequest) {
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugCullingDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugCullingDetails details =
                        mapper.convertValue(item, BranchRequest.SugCullingDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SugCullingDetails sugCullingDetails : data) {
                if(sugCullingDetails.getFemaleBirdsCount() != null && !sugCullingDetails.getFemaleBirdsCount().isEmpty() && !sugCullingDetails.getFemaleBirdsCount().equalsIgnoreCase("0")) {
                    try {
                        String serverDate = LocalDate.now()
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = sdf.parse(serverDate);
                        SugMaiBreederDailyEntryModel sugMaiBreederDailyEntryModel = new SugMaiBreederDailyEntryModel();
                        sugMaiBreederDailyEntryModel.setBRANCH_ID(Long.parseLong(branchRequest.getBranchID()));
                        sugMaiBreederDailyEntryModel.setINVENTORY_LOCATION_ID(Long.parseLong(String.valueOf(gppsObservationBatchDTO.getINVENTORY_LOCATION_ID())));
                        sugMaiBreederDailyEntryModel.setEMP_CODE(branchRequest.getUserCode());
                        sugMaiBreederDailyEntryModel.setTXN_TYPE("ADJUSTMENT");
                        sugMaiBreederDailyEntryModel.setTXN_DATE(date);
                        sugMaiBreederDailyEntryModel.setBATCH_ID(Long.parseLong(branchRequest.getBatchID()));
                        sugMaiBreederDailyEntryModel.setBIRD_TYPE("F");
                        sugMaiBreederDailyEntryModel.setPRIMARY_QTY(Long.parseLong(sugCullingDetails.getFemaleBirdsCount()));
                        sugMaiBreederDailyEntryModel.setREASON(sugCullingDetails.getReason());
                        sugMaiBreederDailyEntryModel.setADJ_TYPE(sugCullingDetails.getType());
                        sugMaiBreederDailyEntryModel.setENTRY_CREATION_DATE(new Date());
                        
                        sugMaiBreederDailyEntryModel.setTRANS_UOM("EA");
                        sugMaiBreederDailyEntryRepository.save(sugMaiBreederDailyEntryModel);
                    } catch (Exception e) {

                    }
/*

                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getFemaleBirdsCount()));
                    //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getFemaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSEX("Female");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setTXN_TYPE(sugCullingDetails.getType());
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
*/
                }
                if( sugCullingDetails.getMaleBirdsCount() != null && !sugCullingDetails.getMaleBirdsCount().isEmpty() && !sugCullingDetails.getMaleBirdsCount().equalsIgnoreCase("0")) {
                    try {
                        String serverDate = LocalDate.now()
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = sdf.parse(serverDate);
                        SugMaiBreederDailyEntryModel sugMaiBreederDailyEntryModel = new SugMaiBreederDailyEntryModel();
                        sugMaiBreederDailyEntryModel.setBRANCH_ID(Long.parseLong(branchRequest.getBranchID()));
                        sugMaiBreederDailyEntryModel.setINVENTORY_LOCATION_ID(Long.parseLong(String.valueOf(gppsObservationBatchDTO.getINVENTORY_LOCATION_ID())));
                        sugMaiBreederDailyEntryModel.setEMP_CODE(branchRequest.getUserCode());
                        sugMaiBreederDailyEntryModel.setTXN_TYPE("ADJUSTMENT");
                        sugMaiBreederDailyEntryModel.setTXN_DATE(date);
                        sugMaiBreederDailyEntryModel.setBATCH_ID(Long.parseLong(branchRequest.getBatchID()));
                        sugMaiBreederDailyEntryModel.setBIRD_TYPE("M");
                        sugMaiBreederDailyEntryModel.setPRIMARY_QTY(Long.parseLong(sugCullingDetails.getMaleBirdsCount()));
                        sugMaiBreederDailyEntryModel.setREASON(sugCullingDetails.getReason());
                        sugMaiBreederDailyEntryModel.setADJ_TYPE(sugCullingDetails.getType());
                        sugMaiBreederDailyEntryModel.setENTRY_CREATION_DATE(new Date());
                        sugMaiBreederDailyEntryModel.setTRANS_UOM("EA");
                        sugMaiBreederDailyEntryRepository.save(sugMaiBreederDailyEntryModel);
                    } catch (Exception e) {

                    }
                   /* SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
                    //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSEX("Male");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setTXN_TYPE(sugCullingDetails.getType());
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);*/
                }

            }

        }
        return "200";
    }


    public ArrayList<BranchUser.CullsReasonDetails> getexcessshortagereason(String branchID) {
        ArrayList<BranchUser.CullsReasonDetails> shedDetailsArrayList = new ArrayList<BranchUser.CullsReasonDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getexcessshrotagereason");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.CullsReasonDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.CullsReasonDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.CullsReasonDetails> getWeekBirdReasonsDetails(String branchID) {
        ArrayList<BranchUser.CullsReasonDetails> shedDetailsArrayList = new ArrayList<BranchUser.CullsReasonDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getweekbirdsreason");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.CullsReasonDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.CullsReasonDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public BranchUser.PlacementInfoDetails getPlacementInfo(String branchID) {
        BranchUser.PlacementInfoDetails details = new BranchUser.PlacementInfoDetails();
        ArrayList<BranchUser.PlacementInfoDetails1> shedDetailsArrayList = new ArrayList<BranchUser.PlacementInfoDetails1>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getplacementinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.PlacementInfoDetails1 shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.PlacementInfoDetails1.class);
                /*details.setTotalBirdsAllocate(shedDetails.getTotalBirdsAllocate());
                details.setRemainingBirds(shedDetails.getRemainingBirds());
                details.setMaleNos(shedDetails.getMaleNos());
                details.setFemaleNos(shedDetails.getFemaleNos());
                details.setAllocatePer(shedDetails.getAllocatePer());
                details.setFlockNumber(shedDetails.getFlockNumber());
                details.setBatchId(shedDetails.getBatchId());
                details.setReportNum(shedDetails.getReportNum());*/
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setPlacementInfoDetails(shedDetailsArrayList);
        details.setPlacementInfoShedDetails(getplacementshedinfo(branchID));
        //details.setCullsReasonDetails(getexcessshortagereason(branchID));
        return details;
    }

    public ArrayList<BranchUser.PlacementInfoShedDetails> getplacementshedinfo(String branchID) {
        ArrayList<BranchUser.PlacementInfoShedDetails> shedDetailsArrayList = new ArrayList<BranchUser.PlacementInfoShedDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getplacementshedinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.PlacementInfoShedDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.PlacementInfoShedDetails.class);
                shedDetails.setPlacementInfoLineDetails(getplacementlineinfo(branchID,shedDetails.getShedName()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    public ArrayList<BranchUser.PlacementInfoLineDetails> getplacementlineinfo(String branchID,String shedNo) {
        ArrayList<BranchUser.PlacementInfoLineDetails> shedDetailsArrayList = new ArrayList<BranchUser.PlacementInfoLineDetails>();
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
                BranchUser.PlacementInfoLineDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.PlacementInfoLineDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.DashboardDetails> getDashboardInfo(String branchID, String branchCode, String flockNumber) {
        //BranchUser.PlacementInfoDetails details = new BranchUser.PlacementInfoDetails();
        ArrayList<BranchUser.DashboardDetails> shedDetailsArrayList = new ArrayList<BranchUser.DashboardDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getdashboardinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, flockNumber);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.DashboardDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.DashboardDetails.class);
                shedDetails.setHenWeekDetails(getHenweekinfo(branchCode,shedDetails.getFlockNumber()));
                shedDetails.setFertilityDetails(getFertilityinfo(branchCode,shedDetails.getFlockNumber()));
                shedDetails.setHatchabilityDetails(getHatchabilityinfo(branchCode,shedDetails.getFlockNumber()));
                shedDetails.setMortalityDetails(getMortalityinfo(branchCode,shedDetails.getFlockNumber()));
                shedDetails.setFeedDetails(getFeedinfo(branchCode,shedDetails.getFlockNumber()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        //details.setPlacementInfoShedDetails(getplacementshedinfo(branchID));
        //details.setCullsReasonDetails(getexcessshortagereason(branchID));
        return shedDetailsArrayList;
    }

    @Override
    public String savePlacementInfoDetails(ArrayList<PlacementRequest> placementRequest1) {
        /*Object rawData = placementRequest;
        List<PlacementRequest> data1 = new ArrayList<>();
        //List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(placementRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                PlacementRequest details =
                        mapper.convertValue(item, PlacementRequest.class);
                data1.add(details);
            }
        }*/
        //SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!placementRequest1.isEmpty()) {

            for(PlacementRequest placementRequest:placementRequest1) {
                List<PlacementRequest.SugLineDetails> data = new ArrayList<>();
                data = placementRequest.getData();

                String branch_code = get_branch_code(placementRequest.getBranchID());
                if (!placementRequest.getTotalFemaleQty().isEmpty()) {
                    SugMaiGppsHousingShed sugMaiGppsHousingShed = new SugMaiGppsHousingShed();
                    sugMaiGppsHousingShed.setFLOCK_ID(placementRequest.getFlockID());
                    sugMaiGppsHousingShed.setTXN_DATE(new Date());
                    sugMaiGppsHousingShed.setFARM_CODE(branch_code);
                    sugMaiGppsHousingShed.setSHED_NO(placementRequest.getShedNo());
                    sugMaiGppsHousingShed.setSEX("F");
                    sugMaiGppsHousingShed.setOP_QTY(Long.valueOf(placementRequest.getTotalFemaleQty()));
                    sugMaiGppsHousingShed.setCREATED_BY(placementRequest.getUserCode());
                    sugMaiGppsHousingShed.setCREATION_DATE(new Date());
                    sugMaiGppsHousingShed.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                    sugMaiGppsHousingShed.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                    sugMaiGppsHousingShed.setREPORT_NUM(Long.valueOf(placementRequest.getReportNum()));
                    sugMaiGppsHousingShedRepositories.save(sugMaiGppsHousingShed);
                }
                if (!placementRequest.getTotalMaleQty().isEmpty()) {
                    SugMaiGppsHousingShed sugMaiGppsHousingShed = new SugMaiGppsHousingShed();
                    sugMaiGppsHousingShed.setFLOCK_ID(placementRequest.getFlockID());
                    sugMaiGppsHousingShed.setTXN_DATE(new Date());
                    sugMaiGppsHousingShed.setFARM_CODE(branch_code);
                    sugMaiGppsHousingShed.setSHED_NO(placementRequest.getShedNo());
                    sugMaiGppsHousingShed.setSEX("M");
                    sugMaiGppsHousingShed.setOP_QTY(Long.valueOf(placementRequest.getTotalMaleQty()));
                    sugMaiGppsHousingShed.setCREATED_BY(placementRequest.getUserCode());
                    sugMaiGppsHousingShed.setCREATION_DATE(new Date());
                    sugMaiGppsHousingShed.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                    sugMaiGppsHousingShed.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                    sugMaiGppsHousingShed.setREPORT_NUM(Long.valueOf(placementRequest.getReportNum()));
                    sugMaiGppsHousingShedRepositories.save(sugMaiGppsHousingShed);

                }
                for (PlacementRequest.SugLineDetails sugLineDetails : data) {
                    if (!sugLineDetails.getFemaleBirdsCount().isEmpty()) {
                        SugMaiGppsHousingLine maiGppsHousingLine = new SugMaiGppsHousingLine();
                        maiGppsHousingLine.setFLOCK_ID(placementRequest.getFlockID());
                        maiGppsHousingLine.setTXN_DATE(new Date());
                        maiGppsHousingLine.setFARM_CODE(branch_code);
                        maiGppsHousingLine.setSHED_NO(placementRequest.getShedNo());
                        maiGppsHousingLine.setSEX("F");
                        maiGppsHousingLine.setGRADE("3");
                        maiGppsHousingLine.setOP_QTY(Long.valueOf(sugLineDetails.getFemaleBirdsCount()));
                        maiGppsHousingLine.setLINE_NO(sugLineDetails.getLineNo());
                        maiGppsHousingLine.setCREATED_BY(placementRequest.getUserCode());
                        maiGppsHousingLine.setCREATION_DATE(new Date());
                        maiGppsHousingLine.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                        maiGppsHousingLine.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                        maiGppsHousingLine.setSIDE(sugLineDetails.getSide());
                        sugMaiGppsHousingLineRepositories.save(maiGppsHousingLine);
                    }
                    if (!sugLineDetails.getMaleBirdsCount().isEmpty()) {
                        SugMaiGppsHousingLine maiGppsHousingLine = new SugMaiGppsHousingLine();
                        maiGppsHousingLine.setFLOCK_ID(placementRequest.getFlockID());
                        maiGppsHousingLine.setTXN_DATE(new Date());
                        maiGppsHousingLine.setFARM_CODE(branch_code);
                        maiGppsHousingLine.setSHED_NO(placementRequest.getShedNo());
                        maiGppsHousingLine.setSEX("M");
                        maiGppsHousingLine.setGRADE("3");
                        maiGppsHousingLine.setOP_QTY(Long.valueOf(sugLineDetails.getMaleBirdsCount()));
                        maiGppsHousingLine.setLINE_NO(sugLineDetails.getLineNo());
                        maiGppsHousingLine.setCREATED_BY(placementRequest.getUserCode());
                        maiGppsHousingLine.setCREATION_DATE(new Date());
                        maiGppsHousingLine.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                        maiGppsHousingLine.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                        maiGppsHousingLine.setSIDE(sugLineDetails.getSide());
                        sugMaiGppsHousingLineRepositories.save(maiGppsHousingLine);
                    }
                    sugMaiGppsHousingShedRepositories.updateentry(placementRequest.getFlockID(),placementRequest.getReportNum(),branch_code);
                }
            }
        }
        return "200";
    }

    public ArrayList<BranchUser.HenWeekDetails> getHenweekinfo(String branchID,String shedNo) {
        ArrayList<BranchUser.HenWeekDetails> shedDetailsArrayList = new ArrayList<BranchUser.HenWeekDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getHenweekinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.HenWeekDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.HenWeekDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }
    public ArrayList<BranchUser.FertilityDetails> getFertilityinfo(String branchID,String shedNo) {
        ArrayList<BranchUser.FertilityDetails> shedDetailsArrayList = new ArrayList<BranchUser.FertilityDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getFertilityinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.FertilityDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FertilityDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    public ArrayList<BranchUser.HatchabilityDetails> getHatchabilityinfo(String branchID,String shedNo) {
        ArrayList<BranchUser.HatchabilityDetails> shedDetailsArrayList = new ArrayList<BranchUser.HatchabilityDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getHatchabilityinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.HatchabilityDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.HatchabilityDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    public ArrayList<BranchUser.MortalityDetails> getMortalityinfo(String branchID,String shedNo) {
        ArrayList<BranchUser.MortalityDetails> shedDetailsArrayList = new ArrayList<BranchUser.MortalityDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getMortalityinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.MortalityDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.MortalityDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }
    public ArrayList<BranchUser.FeedDetails> getFeedinfo(String branchID,String shedNo) {
        ArrayList<BranchUser.FeedDetails> shedDetailsArrayList = new ArrayList<BranchUser.FeedDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getfeedinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.FeedDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FeedDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
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

    public ArrayList<BranchUser.FarmFlockPreviousDetails> getFeedAllocationPreviousDetails(String branchID,String flock) {
        ArrayList<BranchUser.FarmFlockPreviousDetails> farmFlockPreviousDetailsArrayList = new ArrayList<BranchUser.FarmFlockPreviousDetails>();

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getFeedAllocationDetails");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, flock);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.FarmFlockPreviousDetails farmFlockPreviousDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FarmFlockPreviousDetails.class);
                farmFlockPreviousDetails.setGradeWiseDetails(getFeedAllocationPreviousBreakupDetails(branchID,flock,farmFlockPreviousDetails.getAge()));
                farmFlockPreviousDetailsArrayList.add(farmFlockPreviousDetails);

            }
        } catch (Exception e) {

        }
        return farmFlockPreviousDetailsArrayList;
    }

    public ArrayList<BranchUser.FarmFlockPreviousBreakupDetails> getFeedAllocationPreviousBreakupDetails(String branchID,String flock,String age) {
        ArrayList<BranchUser.FarmFlockPreviousBreakupDetails> farmFlockPreviousBreakupDetailsArrayList = new ArrayList<BranchUser.FarmFlockPreviousBreakupDetails>();

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getFeedAllocationBreakupDetails");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, flock);
            storedProcedureQuery.setParameter(3, age);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.FarmFlockPreviousBreakupDetails farmFlockPreviousBreakupDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FarmFlockPreviousBreakupDetails.class);
                farmFlockPreviousBreakupDetailsArrayList.add(farmFlockPreviousBreakupDetails);

            }
        } catch (Exception e) {

        }
        return farmFlockPreviousBreakupDetailsArrayList;
    }

    @Override
    public String saveMedicineScheduleDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/

        Object rawData = branchRequest.getData();
        List<BranchRequest.MedicineAllocationDetails> data = new ArrayList<>();

        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.MedicineAllocationDetails details =
                        mapper.convertValue(item, BranchRequest.MedicineAllocationDetails.class);
                data.add(details);
            }
        }


        if (!data.isEmpty()) {
            for (BranchRequest.MedicineAllocationDetails medicineAllocationDetails : data) {
                List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(medicineAllocationDetails.getBatchID());
                SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
                SugMaiGppsItemAllocation sugMaiGppsItemAllocation = new SugMaiGppsItemAllocation();
                sugMaiGppsItemAllocation.setAGE(Long.valueOf(medicineAllocationDetails.getAge()));
                sugMaiGppsItemAllocation.setFLOCK_ID(medicineAllocationDetails.getFlockID());
                sugMaiGppsItemAllocation.setSHED_NO(medicineAllocationDetails.getShedNo());
                sugMaiGppsItemAllocation.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                sugMaiGppsItemAllocation.setITEM_TYPE(medicineAllocationDetails.getItemType());
                sugMaiGppsItemAllocation.setITEM_ID(Long.valueOf(medicineAllocationDetails.getItemId()));
                sugMaiGppsItemAllocation.setUOM(medicineAllocationDetails.getUom());
                sugMaiGppsItemAllocation.setQTY(BigDecimal.valueOf(Double.parseDouble(medicineAllocationDetails.getQuantity())));
                sugMaiGppsItemAllocation.setINTAKE_MODE(medicineAllocationDetails.getIntakeMode());
                sugMaiGppsItemAllocation.setCREATED_BY(branchRequest.getUserCode());
                sugMaiGppsItemAllocation.setCREATION_DATE(new Date());
                sugMaiGppsItemAllocation.setDATE_FROM(convertToDate(medicineAllocationDetails.getStartDate()));
                sugMaiGppsItemAllocation.setDATE_TO(convertToDate(medicineAllocationDetails.getEndDate()));
                sugMaiGppsItemAllocation.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                sugMaiGppsItemAllocation.setPREPARED_BY(medicineAllocationDetails.getPreparedBy());
                sugMaiGppsItemAllocationRepositories.save(sugMaiGppsItemAllocation);
            }

        }

        return "200";
    }

    @Override
    public ArrayList<BranchUser.MedicineScheduleDetails> getMedicineScheduleDetails(String branchID,String flock) {
        ArrayList<BranchUser.MedicineScheduleDetails> medicineScheduleDetailsArrayList = new ArrayList<BranchUser.MedicineScheduleDetails>();

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getmedicinescheduledetails");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, flock);

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.MedicineScheduleDetails medicineScheduleDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.MedicineScheduleDetails.class);
                medicineScheduleDetailsArrayList.add(medicineScheduleDetails);

            }
        } catch (Exception e) {

        }
        return medicineScheduleDetailsArrayList;
    }

    @Override
    public String saveFarmLogDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/

        Object rawData = branchRequest.getData();
        List<BranchRequest.FarmLogDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.FarmLogDetails details =
                        mapper.convertValue(item, BranchRequest.FarmLogDetails.class);
                data.add(details);
            }
        }
       // SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);

        if (!data.isEmpty()) {
            for (BranchRequest.FarmLogDetails farmLogDetails : data) {
                SugMaiGppsFarmLog sugMaiGppsFarmLog = new SugMaiGppsFarmLog();
               // sugMaiGppsItemAllocation.setAGE(Long.valueOf(branchRequest.getAge()));
                //sugMaiGppsItemAllocation.setFLOCK_ID(branchRequest.getFlockID());
                //sugMaiGppsItemAllocation.setSHED_NO(branchRequest.getShedNo());
                //sugMaiGppsItemAllocation.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                sugMaiGppsFarmLog.setITEM_TYPE(farmLogDetails.getItemType());
               // sugMaiGppsItemAllocation.setITEM_ID(Long.valueOf(medicineAllocationDetails.getItemId()));
                if(farmLogDetails.getUom() != null && !farmLogDetails.getUom().isEmpty()) {
                    sugMaiGppsFarmLog.setUOM(farmLogDetails.getUom());
                }
                if(farmLogDetails.getTotal() != null && !farmLogDetails.getTotal().isEmpty()) {
                    sugMaiGppsFarmLog.setQTY(BigDecimal.valueOf(Double.valueOf(farmLogDetails.getTotal())));
                }
                if(farmLogDetails.getOpening() != null && !farmLogDetails.getOpening().isEmpty()) {
                    sugMaiGppsFarmLog.setOPENING_QTY(BigDecimal.valueOf(Double.valueOf(farmLogDetails.getOpening())));
                }
                if(farmLogDetails.getClosing() != null && !farmLogDetails.getClosing().isEmpty()) {
                    sugMaiGppsFarmLog.setCLOSING_QTY(BigDecimal.valueOf(Double.valueOf(farmLogDetails.getClosing())));
                }
                if(farmLogDetails.getMaleCount() != null && !farmLogDetails.getMaleCount().isEmpty()) {
                    sugMaiGppsFarmLog.setMALE_COUNT(Long.valueOf(farmLogDetails.getMaleCount()));
                }
                if(farmLogDetails.getFemaleCount() != null && !farmLogDetails.getFemaleCount().isEmpty()) {
                    sugMaiGppsFarmLog.setFEMALE_COUNT(Long.valueOf(farmLogDetails.getFemaleCount()));
                }



                sugMaiGppsFarmLog.setCREATED_BY(branchRequest.getUserCode());
                sugMaiGppsFarmLog.setCREATION_DATE(new Date());


                sugMaiGppsFarmLog.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
               // sugMaiGppsFarmLog.setPREPARED_BY(branchRequest.getPreparedBy());
                sugMaiGppsFarmLogRepositories.save(sugMaiGppsFarmLog);
            }

        }

        return "200";
    }

    @Override
    public ArrayList<BranchUser.FarmLogPreviousDetails> getFarmLogPreviousDetails(String branchID, String flockID) {
        ArrayList<BranchUser.FarmLogPreviousDetails> farmLogPreviousDetailsArrayList = new ArrayList<BranchUser.FarmLogPreviousDetails>();

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getFarmLogPreviousDetails");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);


            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.FarmLogPreviousDetails farmLogPreviousDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FarmLogPreviousDetails.class);
                farmLogPreviousDetailsArrayList.add(farmLogPreviousDetails);

            }
        } catch (Exception e) {

        }
        return farmLogPreviousDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.SanitizationReasonDetails> getSanitizationReasonsDetails(String branchID) {
        ArrayList<BranchUser.SanitizationReasonDetails> shedDetailsArrayList = new ArrayList<BranchUser.SanitizationReasonDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getSanitization");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.SanitizationReasonDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.SanitizationReasonDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public String saveSanitizationDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/

        Object rawData = branchRequest.getData();
        List<BranchRequest.SanitizationEntryDetails> data = new ArrayList<>();

        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SanitizationEntryDetails details =
                        mapper.convertValue(item, BranchRequest.SanitizationEntryDetails.class);
                data.add(details);
            }
        }

        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SanitizationEntryDetails sanitizationEntryDetails : data) {


                SugMaiGppsItemConsumption sugMaiGppsItemConsumption = new SugMaiGppsItemConsumption();
                sugMaiGppsItemConsumption.setTRANS_DATE(new Date());
                sugMaiGppsItemConsumption.setTRANS_TYPE(sanitizationEntryDetails.getItemType());
                sugMaiGppsItemConsumption.setINVENTORY_ITEM_ID(0);
                sugMaiGppsItemConsumption.setINVENTORY_ITEM_CODE(sanitizationEntryDetails.getItemCode());
                sugMaiGppsItemConsumption.setITEM_DESCRIPTION(sanitizationEntryDetails.getItemName());
                sugMaiGppsItemConsumption.setSTK_QTY(0);
                sugMaiGppsItemConsumption.setUOM(sanitizationEntryDetails.getUom());
                sugMaiGppsItemConsumption.setPOSTED_FLAG("N");
                sugMaiGppsItemConsumption.setFOR_LTR_WATER(Double.parseDouble(sanitizationEntryDetails.getForLtrWater()));
                sugMaiGppsItemConsumption.setINVENTORY_LOCATION_ID(Long.parseLong(String.valueOf(get_inventory_loc_id(gppsObservationBatchDTO.getLOCATION_CODE(),branchRequest.getBranchID()))));
              //  sugMaiGppsItemConsumption.setc(branchRequest.getUserCode());
                sugMaiGppsItemConsumption.setCREATION_DATE(new Date());
                sugMaiGppsItemConsumption.setENTRY_CREATION_DATE(new Date());
              //  sugMaiGppsItemAllocation.setDATE_FROM(convertToDate(medicineAllocationDetails.getStartDate()));
               // sugMaiGppsItemAllocation.setDATE_TO(convertToDate(medicineAllocationDetails.getEndDate()));
                sugMaiGppsItemConsumption.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                sugMaiGppsItemConsumption.setISSUED_BY(branchRequest.getUserCode());
                sugMaiGppsItemConsumptionRepository.save(sugMaiGppsItemConsumption);
            }


            SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
            maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
            maiGppsConsumptions.setFLOCK_ID(branchRequest.getFlockID());
            maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

            // maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
            //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
            maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
           // maiGppsConsumptions.setREMARKS(data.getRemarks());
           // maiGppsConsumptions.setLIGTHING_START_HRS(data.getLightStartTime());
           // maiGppsConsumptions.setLIGTHING_END_HRS(data.getLightEndTime());
           // maiGppsConsumptions.setSANITIZATION_START_HRS(data.getSanitizationStartTime());
          //  maiGppsConsumptions.setSANITIZATION_END_HRS(data.getSanitizationEndTime());
            maiGppsConsumptions.setPH_LEVEL(Double.parseDouble(branchRequest.getPhLevel()));
            maiGppsConsumptions.setPM_LEVEL(Double.parseDouble(branchRequest.getPmLevel()));
            maiGppsConsumptions.setCREATION_DATE(new Date());

            maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
            maiGppsConsumptions.setTXN_TYPE("WATER_SANITIZER");
            sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);

        }

        return "200";
    }

    public BigDecimal get_inventory_loc_id(String shedNo,String branchId)
    {
        BigDecimal count;
        System.out.println("shedNo :"+shedNo);
        System.out.println("branchId :"+branchId);
        try {
            count = (BigDecimal) entityManager.createNativeQuery("select t.INVENTORY_LOCATION_ID from mtl_item_locations t where t.segment1=?1 and t.ORGANIZATION_ID=?2")
                    .setParameter(1, shedNo)
                    .setParameter(2, branchId)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            count =BigDecimal.valueOf(0);
        }
        return count;

    }
    @Override
    public String saveEggWeightDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/

        Object rawData = branchRequest.getData();
        List<BranchRequest.EggWeightReadingDetails> data = new ArrayList<>();

        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.EggWeightReadingDetails details =
                        mapper.convertValue(item, BranchRequest.EggWeightReadingDetails.class);
                data.add(details);
            }
        }


        if (!data.isEmpty()) {
            for (BranchRequest.EggWeightReadingDetails eggWeightReadingDetails : data) {
                List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(eggWeightReadingDetails.getBatchID());
                SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
                SugMaiGppsEggWeightReading sugMaiGppsEggWeightReading = new SugMaiGppsEggWeightReading();
                sugMaiGppsEggWeightReading.setTRANSACTION_DATE(getTxnDateString(eggWeightReadingDetails.getTransDate(),fromdateFormat1));
                sugMaiGppsEggWeightReading.setBRANCH_NAME(get_branch_name(branchRequest.getBranchID()));
                sugMaiGppsEggWeightReading.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                sugMaiGppsEggWeightReading.setBATCHID(Long.parseLong(eggWeightReadingDetails.getBatchID()));
                sugMaiGppsEggWeightReading.setFLOCK(eggWeightReadingDetails.getFlockID());
                sugMaiGppsEggWeightReading.setNUMBEROFEGG(Long.parseLong(eggWeightReadingDetails.getNoOfEgg()));
                sugMaiGppsEggWeightReading.setEMPTY_TRAY(Long.parseLong(eggWeightReadingDetails.getEmptyTray()));
                sugMaiGppsEggWeightReading.setTOTALEGG_TRAY(Long.parseLong(eggWeightReadingDetails.getTotalTray()));
                sugMaiGppsEggWeightReading.setNET_EGGWEIGHT(Double.parseDouble(eggWeightReadingDetails.getNetEggWeight()));
                sugMaiGppsEggWeightReading.setPOSTED_FLAG("N");
                sugMaiGppsEggWeightReading.setAVERAGE_EGGWEIGHT(Double.parseDouble(eggWeightReadingDetails.getAverageEggWeight()));
               // sugMaiGppsEggWeightReading.setINVENTORY_LOCATION_ID(Long.parseLong(String.valueOf(get_inventory_loc_id(gppsObservationBatchDTO.getLOCATION_CODE(),branchRequest.getBranchID()))));
                //  sugMaiGppsItemConsumption.setc(branchRequest.getUserCode());
                sugMaiGppsEggWeightReading.setCREATION_DATE(new Date());
                //  sugMaiGppsItemAllocation.setDATE_FROM(convertToDate(medicineAllocationDetails.getStartDate()));
                // sugMaiGppsItemAllocation.setDATE_TO(convertToDate(medicineAllocationDetails.getEndDate()));


                sugMaiGppsEggWeightReadingRepositories.save(sugMaiGppsEggWeightReading);
            }

        }

        return "200";
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

    public String get_branch_name(String branchId)
    {
        String count ="0";
        try {
            count = (String) entityManager.createNativeQuery("select a.branch_name from sug_organization_mv a where a.branch_id=?1")
                    .setParameter(1, branchId)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            count ="-";
        }
        return count;

    }
    public String get_branch_code(String branchId)
    {
        String count ="0";
        try {
            count = (String) entityManager.createNativeQuery("select a.branch_code from sug_organization_mv a where a.branch_id=?1")
                    .setParameter(1, branchId)
                    .getSingleResult();
        }
        catch (Exception e)
        {
            count ="-";
        }
        return count;

    }
    @Override
    public String saveCloseEntryDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        BranchRequest.SugCloseDetails data = new BranchRequest.SugCloseDetails();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        /*if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugFeedAllocationDetails details =
                        mapper.convertValue(item, BranchRequest.SugFeedAllocationDetails.class);
                data.add(details);
            }
        }*/
        if (rawData != null) {
            data = mapper.convertValue(rawData, BranchRequest.SugCloseDetails.class);
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        //if (!data.()) {

            SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
            maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
            maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
             maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

            // maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
            //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
            maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
            /*maiGppsConsumptions.setREMARKS(data.getRemarks());
            maiGppsConsumptions.setLIGTHING_START_HRS(data.getLightStartTime());
            maiGppsConsumptions.setLIGTHING_END_HRS(data.getLightEndTime());
            maiGppsConsumptions.setSANITIZATION_START_HRS(data.getSanitizationStartTime());
            maiGppsConsumptions.setSANITIZATION_END_HRS(data.getSanitizationEndTime());
            maiGppsConsumptions.setTEMP_MAX(Double.parseDouble(data.getTempMax()));
             maiGppsConsumptions.setTEMP_MIN(Double.parseDouble(data.getTempMin()));*/
            maiGppsConsumptions.setCREATION_DATE(new Date());
            maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
            maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
            maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
        Date txnDate = getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1);
        maiGppsConsumptions.setTXN_DATE(txnDate);
            maiGppsConsumptions.setTXN_TYPE("DAY_CLOSE");
            long flockAge = getFlockShedAge(gppsObservationBatchDTO.getFLOCK_NO(), branchRequest.getShedNo(), branchRequest);
            maiGppsConsumptions.setAGE(flockAge);
            SugMaiGppsConsumptions savedDayClose = sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
            saveDayEntriesToDailyEntry(branchRequest, gppsObservationBatchDTO, txnDate, savedDayClose.getTRANS_ID(), flockAge);
        //}

        return "200";
    }

    private long getFlockShedAge(String flockId, String shedCode, BranchRequest branchRequest) {
        if (branchRequest.getAge() != null && !branchRequest.getAge().trim().isEmpty()) {
            try {
                return Long.parseLong(branchRequest.getAge().trim());
            } catch (NumberFormatException ignored) {
            }
        }
        try {
            Object age = entityManager.createNativeQuery(
                            "select age from (select age from sug.sug_mai_gpps_housing_shed " +
                                    "where flock_id = ?1 and shed_no = ?2 " +
                                    "order by txn_date desc) where rownum = 1")
                    .setParameter(1, flockId)
                    .setParameter(2, shedCode)
                    .getSingleResult();
            if (age != null) {
                return ((Number) age).longValue();
            }
        } catch (Exception ignored) {
        }
        try {
            BranchUser.DailyEntryCompletedDetails details =
                    getDailyShedEntryDetails(branchRequest.getBranchID(), branchRequest.getBatchID(), flockId);
            if (details.getEgg_PRODUCTION_AGE() != null && !details.getEgg_PRODUCTION_AGE().isEmpty()) {
                return Long.parseLong(details.getEgg_PRODUCTION_AGE());
            }
        } catch (Exception ignored) {
        }
        return 0L;
    }

    private void saveDayEntriesToDailyEntry(BranchRequest branchRequest,
                                            SugGppsObservationBatchDTO batch,
                                            Date txnDate,
                                            long dayCloseTransId,
                                            long flockAge) {
        List<SugMaiGppsConsumptions> dayEntries = sugMaiGppsConsumptionsRepositories
                .findDayEntriesByFlockAndShedAndTxnDate(batch.getFLOCK_NO(), branchRequest.getShedNo(), txnDate);

        for (SugMaiGppsConsumptions consumption : dayEntries) {
            try {
                sugMaiBreederDailyEntryRepository.save(
                        mapConsumptionToDailyEntry(consumption, branchRequest, batch, flockAge, dayCloseTransId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SugMaiBreederDailyEntryModel mapConsumptionToDailyEntry(SugMaiGppsConsumptions consumption,
                                                                    BranchRequest branchRequest,
                                                                    SugGppsObservationBatchDTO batch,
                                                                    long flockAge,
                                                                    long dayCloseTransId) {
        SugMaiBreederDailyEntryModel dailyEntry = new SugMaiBreederDailyEntryModel();
        if (branchRequest.getBranchID() != null && !branchRequest.getBranchID().isEmpty()) {
            dailyEntry.setBRANCH_ID(Long.parseLong(branchRequest.getBranchID()));
        }
        dailyEntry.setBRANCH_CODE(batch.getBRANCH_CODE());
        dailyEntry.setLOCATION_CODE(branchRequest.getShedNo());
        if (batch.getINVENTORY_LOCATION_ID() != null) {
            dailyEntry.setINVENTORY_LOCATION_ID(batch.getINVENTORY_LOCATION_ID().longValue());
        }
        dailyEntry.setEMP_CODE(branchRequest.getUserCode());
        dailyEntry.setTXN_TYPE(consumption.getTXN_TYPE());
        dailyEntry.setTXN_DATE(consumption.getTXN_DATE() != null ? consumption.getTXN_DATE() : txnDateOrNow(consumption));
        dailyEntry.setBATCH_NO(batch.getBATCH_NO());
        if (consumption.getBATCH_ID() != null) {
            dailyEntry.setBATCH_ID(consumption.getBATCH_ID());
        }
        dailyEntry.setFLOCK_NO(consumption.getFLOCK_ID());
        dailyEntry.setAGE(consumption.getAGE() != null ? consumption.getAGE() : flockAge);
        dailyEntry.setBIRD_TYPE(consumption.getSEX());
        dailyEntry.setREASON(consumption.getREASON());
        dailyEntry.setREMARKS(consumption.getREMARKS() != null ? consumption.getREMARKS() : consumption.getREMARK());
        dailyEntry.setENTRY_CREATION_DATE(new Date());
        dailyEntry.setLATITUDE(consumption.getLATITUDE());
        dailyEntry.setLONGITUDE(consumption.getLONGITUDE());
        dailyEntry.setTXN_ID(dayCloseTransId);
        dailyEntry.setMTL_REPORT_ID(dayCloseTransId);
        if (consumption.getITEM_ID() != null) {
            dailyEntry.setINVENTORY_ITEM_ID(consumption.getITEM_ID());
        }

        String txnType = consumption.getTXN_TYPE() == null ? "" : consumption.getTXN_TYPE().toUpperCase();
        long qty = consumption.getQTY() == null ? 0L : consumption.getQTY();
        boolean female = isFemale(consumption.getSEX());
        boolean male = isMale(consumption.getSEX());

        switch (txnType) {
            case "FEED":
                dailyEntry.setSECONDARY_QTY(qty);
                dailyEntry.setTRANS_UOM(consumption.getUOM() != null ? consumption.getUOM() : "KG");
                break;
            case "MORTALITY":
            case "MORTALITY_PML":
                dailyEntry.setPRIMARY_QTY(qty);
                if (female) {
                    dailyEntry.setMORT_FEMALE(qty);
                } else if (male) {
                    dailyEntry.setMORT_MALE(qty);
                }
                dailyEntry.setTRANS_UOM("EA");
                break;
            case "CULLING":
            case "DESTROY":
                dailyEntry.setPRIMARY_QTY(qty);
                if (female) {
                    dailyEntry.setCULL_FEMALE(qty);
                } else if (male) {
                    dailyEntry.setCULLS_MALE(qty);
                }
                dailyEntry.setCULL_REASON(consumption.getREASON());
                dailyEntry.setTRANS_UOM("EA");
                break;
            case "EGG COLLECTION":
                dailyEntry.setTOTAL_EGG(qty);
                dailyEntry.setPRIMARY_QTY(qty);
                dailyEntry.setTRANS_UOM(consumption.getUOM() != null ? consumption.getUOM() : "EA");
                break;
            case "OTHERS":
                dailyEntry.setTEMP_MIN((float) consumption.getTEMP_MIN());
                dailyEntry.setTEMP_MAX((float) consumption.getTEMP_MAX());
                dailyEntry.setSTART_TIME(consumption.getLIGTHING_START_HRS());
                dailyEntry.setEND_TIME(consumption.getLIGTHING_END_HRS());
                break;
            case "WATER_SANITIZER":
                dailyEntry.setPH_LEVEL((float) consumption.getPH_LEVEL());
                dailyEntry.setPPM_LEVEL((float) consumption.getPM_LEVEL());
                break;
            default:
                dailyEntry.setPRIMARY_QTY(qty);
                dailyEntry.setTRANS_UOM(consumption.getUOM());
                break;
        }
        return dailyEntry;
    }

    private Date txnDateOrNow(SugMaiGppsConsumptions consumption) {
        return consumption.getCREATION_DATE() != null ? consumption.getCREATION_DATE() : new Date();
    }

    private boolean isFemale(String sex) {
        return sex != null && (sex.equalsIgnoreCase("F") || sex.equalsIgnoreCase("Female"));
    }

    private boolean isMale(String sex) {
        return sex != null && (sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("Male"));
    }

    @Override
    public String SugCVBodyWeight(ArrayList<SugCVBodyWeightDto> entry) {
        String fromdateFormat = "DD-MM-YYYY hh:mm:ss";
        String fromdateFormat1 = "DD-MM-YYYY";
        try {
            for (SugCVBodyWeightDto Farmdto : entry) {
                SugCVBodyWeightModels sugCVBodyWeightModels = new SugCVBodyWeightModels();
                sugCVBodyWeightModels.setDEVICE_ID(Long.parseLong(String.valueOf(Farmdto.getDevice_id())));
                sugCVBodyWeightModels.setEMP_CODE(Farmdto.getEmp_code());
                sugCVBodyWeightModels.setBRANCH_ID(Farmdto.getBranch_id());
                sugCVBodyWeightModels.setINVENTORY_LOCATION_ID(Farmdto.getInventory_location_id());
                sugCVBodyWeightModels.setLOCATION(Farmdto.getLocation());
               // sugCVBodyWeightModels.setTXN_HEADER_ID(Farmdto.getTxn_header_id());
                sugCVBodyWeightModels.setTXN_DATE(getTxnDateString(Farmdto.getTxn_date(), fromdateFormat1));
                sugCVBodyWeightModels.setBIRD_TYPE(Farmdto.getBird_type());
                sugCVBodyWeightModels.setMIN_WEIGHT(Farmdto.getMin_weight());
                sugCVBodyWeightModels.setMAX_WEIGHT(Farmdto.getMax_weight());
                sugCVBodyWeightModels.setINCREMENT_VALUE(Farmdto.getIncrement_value());
                sugCVBodyWeightModels.setAGE(Farmdto.getAge());
                sugCVBodyWeightModels.setSTD_BODYWT(Farmdto.getStd_bodywt());
                sugCVBodyWeightModels.setACT_BODYWT(Farmdto.getAct_bodywt());
                sugCVBodyWeightModels.setCV(Farmdto.getCv());
                sugCVBodyWeightModels.setBELOW_STD(Farmdto.getBelow_std());
                sugCVBodyWeightModels.setABOVE_STD(Farmdto.getAbove_std());
                sugCVBodyWeightModels.setWITHIN_STD(Farmdto.getWithin_std());
                sugCVBodyWeightModels.setMOST_ABOVE_STD(Farmdto.getMost_above_date());
                sugCVBodyWeightModels.setMOST_BELOW_STD(Farmdto.getMost_below_date());
                sugCVBodyWeightModels.setGRADING_NO(Farmdto.getGrading_no());
                sugCVBodyWeightModels.setFLOCK_NO(Farmdto.getFlock_no());
                sugCVBodyWeightModels.setLINE_NO(Farmdto.getLine_no());
               // sugCVBodyWeightModels.setPHYSICAL_SHED_NO(Farmdto.getPhysical_shed_no());
                //sugCVBodyWeightModels.setPHYSICAL_SHED_NO(Long.parseLong(Farmdto.getPhysical_shed_no()));
                //sugCVBodyWeightModels.setENTRY_CREATION_DATE(getTxnDateString(Farmdto.getEntry_creation_date(), fromdateFormat));
                sugCVBodyWeightModels.setPOSTED_FLAG("N");
                sugCVBodyWeightModels.setCREATED_DATE(new Date());
                SugCVBodyWeightModels sugCVBodyWeightModels1 = sugCVBodyWeightRepository.save(sugCVBodyWeightModels);
                for(SugCVBodyWeightDto.SugCVBodyWeightDtlDto sugCVBodyWeightDtlDto:Farmdto.getDetails())
                {
                    SugCVBodyWeightDtlModels sugCVBodyWeightDtlModels = new SugCVBodyWeightDtlModels();
                    sugCVBodyWeightDtlModels.setDEVICE_ID(sugCVBodyWeightDtlDto.getDevice_id());
                    sugCVBodyWeightDtlModels.setEMP_CODE(sugCVBodyWeightDtlDto.getEmp_code());
                    sugCVBodyWeightDtlModels.setBRANCH_ID(Farmdto.getBranch_id());
                    sugCVBodyWeightDtlModels.setINVENTORY_LOCATION_ID(Farmdto.getInventory_location_id());
                    sugCVBodyWeightDtlModels.setTXN_HEADER_ID(sugCVBodyWeightModels1.getTXN_HEADER_ID());
                    sugCVBodyWeightDtlModels.setWEIGHT(sugCVBodyWeightDtlDto.getWeight());
                    sugCVBodyWeightDtlModels.setNO_OF_BIRDS(sugCVBodyWeightDtlDto.getNo_of_birds());
                   // sugCVBodyWeightDtlModels.setENTRY_CREATION_DATE(getTxnDateString(sugCVBodyWeightDtlModels.getEntry_creation_date(), fromdateFormat));
                    sugCVBodyWeightDtlModels.setBIRD_TYPE(sugCVBodyWeightDtlDto.getBird_type());
                    sugCVBodyWeightDtlModels.setLINE_NO(sugCVBodyWeightDtlDto.getLine_no());
                    sugCVBodyWeightDtlModels.setGRADING_NO(sugCVBodyWeightDtlDto.getGrading_no());
                    sugCVBodyWeightDtlModels.setFLOCK_NO(sugCVBodyWeightDtlDto.getFlock_no());
                    sugCVBodyWeightDtlModels.setPHYSICAL_SHED_NO(sugCVBodyWeightDtlDto.getPhysical_shed_no());
                    sugCVBodyWeightDtlModels.setAGE(sugCVBodyWeightDtlDto.getAge());

                    sugCVBodyWeightDtlModels.setPOSTED_FLAG("N");
                    sugCVBodyWeightDtlModels.setCREATED_DATE(new Date());
                    sugCVBodyWeightDtlRepository.save(sugCVBodyWeightDtlModels);
                }
                return "True";
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return "false";
    }

    @Override
    public ArrayList<BranchUser.BodyWeightDeviationDetails> getBodyWeightRange(String branchID) {
        ArrayList<BranchUser.BodyWeightDeviationDetails> shedDetailsArrayList = new ArrayList<BranchUser.BodyWeightDeviationDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getbodyweightrange");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.BodyWeightDeviationDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.BodyWeightDeviationDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.FlockWiseGradingDetails> getFlockWiseGradingDetails(String branchID,String shedNo,String age) {
        ArrayList<BranchUser.FlockWiseGradingDetails> shedDetailsArrayList = new ArrayList<BranchUser.FlockWiseGradingDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getflockwisegradedtls");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.setParameter(3, age);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.FlockWiseGradingDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FlockWiseGradingDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    public String getbodyweightdeviation(String branchId,String age)
    {
        String count ="0";
        try {
            count = (String) entityManager.createNativeQuery("select sug_mai_gpps_mob_pkg.getbodyweightdeviation(?1,?2) from dual")
                    .setParameter(1, branchId)
                    .setParameter(2, age)
                    .getSingleResult().toString();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            count ="0";
        }
        return count;

    }
    @Override
    public String saveFlockGradeWiseDetails(ArrayList<PlacementRequest> placementRequest1) {
        /*Object rawData = placementRequest;
        List<PlacementRequest> data1 = new ArrayList<>();
        //List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(placementRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                PlacementRequest details =
                        mapper.convertValue(item, PlacementRequest.class);
                data1.add(details);
            }
        }*/
        //SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!placementRequest1.isEmpty()) {

            for(PlacementRequest placementRequest:placementRequest1) {
                List<PlacementRequest.SugLineDetails> data = new ArrayList<>();
                data = placementRequest.getData();

                String branch_code = get_branch_code(placementRequest.getBranchID());
                if (!placementRequest.getTotalFemaleQty().isEmpty()) {
                    SugMaiGppsHousingShed sugMaiGppsHousingShed = new SugMaiGppsHousingShed();
                    sugMaiGppsHousingShed.setFLOCK_ID(placementRequest.getFlockID());
                    sugMaiGppsHousingShed.setTXN_DATE(new Date());
                    sugMaiGppsHousingShed.setFARM_CODE(branch_code);
                    sugMaiGppsHousingShed.setSHED_NO(placementRequest.getShedNo());
                    sugMaiGppsHousingShed.setSEX("F");
                    sugMaiGppsHousingShed.setOP_QTY(Long.valueOf(placementRequest.getTotalFemaleQty()));
                    sugMaiGppsHousingShed.setCREATED_BY(placementRequest.getUserCode());
                    sugMaiGppsHousingShed.setCREATION_DATE(new Date());
                    sugMaiGppsHousingShed.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                    sugMaiGppsHousingShed.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                   // sugMaiGppsHousingShed.setREPORT_NUM(Long.valueOf(placementRequest.getReportNum()));
                    sugMaiGppsHousingShed.setAGE(Long.valueOf(placementRequest.getAge()));
                    sugMaiGppsHousingShedRepositories.save(sugMaiGppsHousingShed);
                }
                if (!placementRequest.getTotalMaleQty().isEmpty()) {
                    SugMaiGppsHousingShed sugMaiGppsHousingShed = new SugMaiGppsHousingShed();
                    sugMaiGppsHousingShed.setFLOCK_ID(placementRequest.getFlockID());
                    sugMaiGppsHousingShed.setTXN_DATE(new Date());
                    sugMaiGppsHousingShed.setFARM_CODE(branch_code);
                    sugMaiGppsHousingShed.setSHED_NO(placementRequest.getShedNo());
                    sugMaiGppsHousingShed.setSEX("M");
                    sugMaiGppsHousingShed.setOP_QTY(Long.valueOf(placementRequest.getTotalMaleQty()));
                    sugMaiGppsHousingShed.setCREATED_BY(placementRequest.getUserCode());
                    sugMaiGppsHousingShed.setCREATION_DATE(new Date());
                    sugMaiGppsHousingShed.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                    sugMaiGppsHousingShed.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                  //  sugMaiGppsHousingShed.setREPORT_NUM(Long.valueOf(placementRequest.getReportNum()));
                    sugMaiGppsHousingShed.setAGE(Long.valueOf(placementRequest.getAge()));
                    sugMaiGppsHousingShedRepositories.save(sugMaiGppsHousingShed);

                }
                for (PlacementRequest.SugLineDetails sugLineDetails : data) {
                    if (!sugLineDetails.getFemaleBirdsCount().isEmpty()) {
                        SugMaiGppsHousingLine maiGppsHousingLine = new SugMaiGppsHousingLine();
                        maiGppsHousingLine.setFLOCK_ID(placementRequest.getFlockID());
                        maiGppsHousingLine.setTXN_DATE(new Date());
                        maiGppsHousingLine.setFARM_CODE(branch_code);
                        maiGppsHousingLine.setSHED_NO(placementRequest.getShedNo());
                        maiGppsHousingLine.setSEX("F");
                        maiGppsHousingLine.setGRADE(sugLineDetails.getGradeNo());
                        maiGppsHousingLine.setOP_QTY(Long.valueOf(sugLineDetails.getFemaleBirdsCount()));
                        maiGppsHousingLine.setLINE_NO(sugLineDetails.getLineNo());
                        maiGppsHousingLine.setCREATED_BY(placementRequest.getUserCode());
                        maiGppsHousingLine.setCREATION_DATE(new Date());
                        maiGppsHousingLine.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                        maiGppsHousingLine.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                        maiGppsHousingLine.setAGE(Long.valueOf(placementRequest.getAge()));
                        maiGppsHousingLine.setSIDE(sugLineDetails.getSide());
                        sugMaiGppsHousingLineRepositories.save(maiGppsHousingLine);
                    }
                    if (!sugLineDetails.getMaleBirdsCount().isEmpty()) {
                        SugMaiGppsHousingLine maiGppsHousingLine = new SugMaiGppsHousingLine();
                        maiGppsHousingLine.setFLOCK_ID(placementRequest.getFlockID());
                        maiGppsHousingLine.setTXN_DATE(new Date());
                        maiGppsHousingLine.setFARM_CODE(branch_code);
                        maiGppsHousingLine.setSHED_NO(placementRequest.getShedNo());
                        maiGppsHousingLine.setSEX("M");
                        maiGppsHousingLine.setGRADE((sugLineDetails.getGradeNo()));
                        maiGppsHousingLine.setOP_QTY(Long.valueOf(sugLineDetails.getMaleBirdsCount()));
                        maiGppsHousingLine.setLINE_NO(sugLineDetails.getLineNo());
                        maiGppsHousingLine.setCREATED_BY(placementRequest.getUserCode());
                        maiGppsHousingLine.setCREATION_DATE(new Date());
                        maiGppsHousingLine.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                        maiGppsHousingLine.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                        maiGppsHousingLine.setAGE(Long.valueOf(placementRequest.getAge()));
                        maiGppsHousingLine.setSIDE(sugLineDetails.getSide());
                        sugMaiGppsHousingLineRepositories.save(maiGppsHousingLine);
                    }
                    sugCVBodyWeightDtlRepository.updateentry(placementRequest.getBranchID(),placementRequest.getShedNo(),placementRequest.getAge(),sugLineDetails.getGradeNo());
                   // sugMaiGppsHousingShedRepositories.updateentry(placementRequest.getFlockID(),placementRequest.getReportNum(),branch_code);
                }
            }
        }
        return "200";
    }
    @Override
    public ArrayList<BranchUser.MedicineScheduleDetails> getDailyMedicineSchedule(String branchID,String shedNo,String date) {
        ArrayList<BranchUser.MedicineScheduleDetails> medicineScheduleDetailsArrayList = new ArrayList<BranchUser.MedicineScheduleDetails>();

        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getdaily_medicinevaccine_sch");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, shedNo);
            storedProcedureQuery.setParameter(3, date);

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.MedicineScheduleDetails medicineScheduleDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.MedicineScheduleDetails.class);
                medicineScheduleDetailsArrayList.add(medicineScheduleDetails);

            }
        } catch (Exception e) {

        }
        return medicineScheduleDetailsArrayList;
    }
    @Override
    public String saveDailyMedicineVaccine(BranchRequest branchRequest, List<MultipartFile> imageFile) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugMedicineVaccineDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugMedicineVaccineDetails details =
                        mapper.convertValue(item, BranchRequest.SugMedicineVaccineDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SugMedicineVaccineDetails sugMedicineVaccineDetails : data) {
                SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());
                maiGppsConsumptions.setFLOCK_ID(branchRequest.getFlockID());
                maiGppsConsumptions.setITEM_ID(Long.valueOf(sugMedicineVaccineDetails.getItemId()));
                maiGppsConsumptions.setUOM(sugMedicineVaccineDetails.getUom());
                maiGppsConsumptions.setQTY(Long.valueOf(sugMedicineVaccineDetails.getQty()));
                maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                //maiGppsConsumptions.setSEX(sugMortalityDetails.getBirdType());
                maiGppsConsumptions.setTXN_DATE(getTxnDateString(branchRequest.getTransDate(),fromdateFormat1));
                maiGppsConsumptions.setCREATION_DATE(new Date());
                maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                maiGppsConsumptions.setTXN_TYPE(sugMedicineVaccineDetails.getItemType());
                maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBranchID()));
                sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                sugMaiGppsItemAllocationRepositories.updateentry(sugMedicineVaccineDetails.getTransId());
            }

            try {String mortalityImage = null;
                if (imageFile != null && !imageFile.isEmpty()) {
                    for (MultipartFile data1 : imageFile) {
                        mortalityImage = fileStorageService.saveImage(data1, gppsObservationBatchDTO.getBRANCH_CODE(), Long.valueOf(branchRequest.getBatchID()), FileStorageCategory.MEDICINE);
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
        }
        return "200";
    }

    @Override
    public ArrayList<BranchUser.PlacementInfoShedDetails> getShedReadyshedinfo(String branchID) {
        ArrayList<BranchUser.PlacementInfoShedDetails> shedDetailsArrayList = new ArrayList<BranchUser.PlacementInfoShedDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshedreadyshedinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.PlacementInfoShedDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.PlacementInfoShedDetails.class);
                //shedDetails.setPlacementInfoLineDetails(getplacementlineinfo(branchID,shedDetails.getShedName()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ShedDetailsReport> getShedDetailsReport(String branchID) {
        ArrayList<BranchUser.ShedDetailsReport> shedDetailsArrayList = new ArrayList<BranchUser.ShedDetailsReport>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getsheddetails_rpt");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.ShedDetailsReport shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ShedDetailsReport.class);
                shedDetails.setPlacementInfoLineDetails(getplacementlineinfo(branchID,shedDetails.getShedName()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public MasterResultDto getshedready_medicine(String branchId) throws SQLException {
        MasterResultDto masterResultDto=new MasterResultDto();
        MasterResultDto.itemmaster appinfo = new MasterResultDto.itemmaster();
        ArrayList<MasterResultDto.itemmaster> Result = new ArrayList<MasterResultDto.itemmaster>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.getshedready_medicine");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        //storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branchId);

        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();
        System.out.println(branchId);
        while (resultSet.next()) {
            MasterResultDto.itemmaster pojo = null;
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet, MasterResultDto.itemmaster.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        masterResultDto.setItemmst(Result);
        return masterResultDto;
    }

    @Override
    public String saveEggWeightCapture(ArrayList<EggWeightCaptureDto> eggWeightCaptureDto) {
        try {
            for (EggWeightCaptureDto eggWeightCaptureDto1 : eggWeightCaptureDto) {
                SugGppsEggUnboxingModels sugGppsEggUnboxingModels = new SugGppsEggUnboxingModels();
                sugGppsEggUnboxingModels.setBRANCH_ID(eggWeightCaptureDto1.getBranchId());
                sugGppsEggUnboxingModels.setFLOCK(eggWeightCaptureDto1.getFlock());
                sugGppsEggUnboxingModels.setUNBOXING_DATE(getTxnDateString(eggWeightCaptureDto1.getDate(), fromdateFormat1));
                sugGppsEggUnboxingModels.setCRACK_EGGS(eggWeightCaptureDto1.getCrackEggs());
                sugGppsEggUnboxingModels.setDAMAGE_EGGS(eggWeightCaptureDto1.getDamageEggs());
                sugGppsEggUnboxingModels.setMISSING_EGGS(eggWeightCaptureDto1.getMissingEggs());
                sugGppsEggUnboxingModels.setTOTAL_CHECKED(eggWeightCaptureDto1.getTotalChecked());
                sugGppsEggUnboxingModels.setTOTAL_DEFECTED(eggWeightCaptureDto1.getTotalDefects());
                sugGppsEggUnboxingModels.setCREATED_BY(eggWeightCaptureDto1.getEmpCode());
                sugGppsEggUnboxingModels.setREMARKS(eggWeightCaptureDto1.getRemark());
                sugGppsEggUnboxingModels.setINSPECTOR_NAME(eggWeightCaptureDto1.getInspector());
                eggWeightCaptureRepository.save(sugGppsEggUnboxingModels);
            }
        } catch (Exception e) {
            return "201";
        }
        return "200";
    }

    @Override
    public ArrayList<BranchUser.EggWeightCapturePerson> getEggUnboxingPersonDtls(String branchID) {
        ArrayList<BranchUser.EggWeightCapturePerson> eggWeightCapturePersonArrayList = new ArrayList<BranchUser.EggWeightCapturePerson>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.geteggunboxing_person_details");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.EggWeightCapturePerson eggWeightCapturePerson = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.EggWeightCapturePerson.class);

                eggWeightCapturePersonArrayList.add(eggWeightCapturePerson);
            }
        } catch (Exception e) {

        }
        return eggWeightCapturePersonArrayList;
    }


    public BranchUser.DailyEntryCompletedDetails getDailyShedEntryDetails(String branchID,String batchID,String flock) {
        BranchUser.DailyEntryCompletedDetails standardDetailsArrayList = new BranchUser.DailyEntryCompletedDetails();
        String Standard="";
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshedentry_details");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, batchID);
            storedProcedureQuery.setParameter(3, flock);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.DailyEntryCompletedDetails standardDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.DailyEntryCompletedDetails.class);

              //  Standard=standardDetails.getFemaleWeight()+"~"+standardDetails.getMaleWeight()+"~"+standardDetails.getFemaleFeedPerWeek()+"~"+standardDetails.getMaleFeedPerWeek();
                return  standardDetails;
            }
        } catch (Exception e) {

        }
        return standardDetailsArrayList;
    }


    @Override
    public String saveEggQualityCapture(ArrayList<BranchRequest.SugEggQualityCaptureDetails> branchRequest) {
        try {
            for (BranchRequest.SugEggQualityCaptureDetails sugEggQualityCaptureDetails : branchRequest) {
                SugMaiEggQualityCapture sugMaiEggQualityCapture = new SugMaiEggQualityCapture();
                sugMaiEggQualityCapture.setPARENT_BRANCH_ID(Long.parseLong(sugEggQualityCaptureDetails.getParent_branch_id()));
                sugMaiEggQualityCapture.setPARENT_BRANCH_NAME(sugEggQualityCaptureDetails.getParent_branch_name());
                sugMaiEggQualityCapture.setBRANCH_ID(Long.parseLong(sugEggQualityCaptureDetails.getBranch_id()));
                sugMaiEggQualityCapture.setBRANCH_NAME(sugEggQualityCaptureDetails.getBranch_name());
                sugMaiEggQualityCapture.setFLOCK(sugEggQualityCaptureDetails.getFlock());
                sugMaiEggQualityCapture.setBREED(sugEggQualityCaptureDetails.getBreed());
                sugMaiEggQualityCapture.setTRANSACTION_DATE(getTxnDateString(sugEggQualityCaptureDetails.getTrans_date(),fromdateFormat1));
                sugMaiEggQualityCapture.setNO_OFSAMPLEEGG(Long.parseLong(sugEggQualityCaptureDetails.getNoofsampleegg()));
                sugMaiEggQualityCapture.setFERTILE(Long.parseLong(sugEggQualityCaptureDetails.getFertile()));
                sugMaiEggQualityCapture.setINFERTILE(Long.parseLong(sugEggQualityCaptureDetails.getInfertile()));
                sugMaiEggQualityCapture.setPRE_INCUBATION(Long.parseLong(sugEggQualityCaptureDetails.getPreincubation()));
                sugMaiEggQualityCapture.setYOLK_MOTTLING(Long.parseLong(sugEggQualityCaptureDetails.getYolkmottling()));
                sugMaiEggQualityCapture.setMEAT_SPOT(Long.parseLong(sugEggQualityCaptureDetails.getMeatspot()));
                sugMaiEggQualityCapture.setBLOOD_SPOT(Long.parseLong(sugEggQualityCaptureDetails.getBloodspot()));
                sugMaiEggQualityCaptureRepository.save(sugMaiEggQualityCapture);
            }
        } catch (Exception e) {
            return "201";
        }
        return "200";
    }

    @Override
    public ArrayList<BranchUser.IfftApprovalHdrDetails> getIfftApprovalHdrDetails(String branchID) {
        ArrayList<BranchUser.IfftApprovalHdrDetails> eggWeightCapturePersonArrayList = new ArrayList<BranchUser.IfftApprovalHdrDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getIfft");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.IfftApprovalHdrDetails eggWeightCapturePerson = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.IfftApprovalHdrDetails.class);
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Parse the string into LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(eggWeightCapturePerson.getTXN_DATE(), inputFormatter);

                // Example: Convert to another format (ISO or custom)
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String formattedDate = dateTime.format(outputFormatter);
                eggWeightCapturePerson.setTXN_DATE(formattedDate);
                eggWeightCapturePerson.setITEMS(getIfftApprovalDtlDetails(eggWeightCapturePerson.getTXN_HEADER_ID(),eggWeightCapturePerson.getDEVICE_ID()));
                eggWeightCapturePersonArrayList.add(eggWeightCapturePerson);
            }
        } catch (Exception e) {

        }
        return eggWeightCapturePersonArrayList;
    }
    public ArrayList<BranchUser.IfftApprovalDtlDetails> getIfftApprovalDtlDetails(String headerId,String deviceId) {
        ArrayList<BranchUser.IfftApprovalDtlDetails> eggWeightCapturePersonArrayList = new ArrayList<BranchUser.IfftApprovalDtlDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getIfftDtl");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, headerId);
            storedProcedureQuery.setParameter(2, deviceId);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.IfftApprovalDtlDetails eggWeightCapturePerson = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.IfftApprovalDtlDetails.class);
                eggWeightCapturePersonArrayList.add(eggWeightCapturePerson);
            }
        } catch (Exception e) {

        }
        return eggWeightCapturePersonArrayList;
    }
    @Override
    public String saveIfftApproval(BranchRequest.SugIfftApprovalDetails branchRequest) {
        String fromdateFormat = "DD-MM-YYYY hh:mm:ss";
        String fromdateFormat1 = "DD-MMM-YYYY";
        String isvalid="";
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.ifftstaus");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter(1, branchRequest.getHeader_id());
            storedProcedureQuery.setParameter(2, branchRequest.getOutpass_no());
            storedProcedureQuery.setParameter(3, branchRequest.getDate());
            storedProcedureQuery.setParameter(4,branchRequest.getStatus());
            storedProcedureQuery.setParameter(5,branchRequest.getIn_or_out());
            storedProcedureQuery.setParameter(6,branchRequest.getRemarks());
            storedProcedureQuery.execute();
            String output = (String) storedProcedureQuery.getOutputParameterValue(7);
            if (output.trim().equalsIgnoreCase("1")) {
                isvalid = "true";
            } else {
                isvalid = "false";
            }
        } catch (Exception e) {

        }
        return "200";
    }

    @Override
    public ArrayList<BranchUser.ReasonMaster> getReasonMasterDetails(String branchID) {
        ArrayList<BranchUser.ReasonMaster> eggWeightCapturePersonArrayList = new ArrayList<BranchUser.ReasonMaster>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getreasonmaster");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            //storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            //storedProcedureQuery.setParameter(2, deviceId);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.ReasonMaster eggWeightCapturePerson = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ReasonMaster.class);
                eggWeightCapturePersonArrayList.add(eggWeightCapturePerson);
            }
        } catch (Exception e) {

        }
        return eggWeightCapturePersonArrayList;
    }
    @Override
    public String saveOthersEntryDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        BranchRequest.SugCloseDetails data = new BranchRequest.SugCloseDetails();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        /*if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugFeedAllocationDetails details =
                        mapper.convertValue(item, BranchRequest.SugFeedAllocationDetails.class);
                data.add(details);
            }
        }*/
        if (rawData != null) {
            data = mapper.convertValue(rawData, BranchRequest.SugCloseDetails.class);
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        //if (!data.()) {

        SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
        maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
        maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
        maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

        // maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
        //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
        maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
        maiGppsConsumptions.setREMARKS(data.getRemarks());
        maiGppsConsumptions.setLIGTHING_START_HRS(data.getLightStartTime());
        maiGppsConsumptions.setLIGTHING_END_HRS(data.getLightEndTime());
        maiGppsConsumptions.setSANITIZATION_START_HRS(data.getSanitizationStartTime());
        maiGppsConsumptions.setSANITIZATION_END_HRS(data.getSanitizationEndTime());
        maiGppsConsumptions.setTEMP_MAX(Double.parseDouble(data.getTempMax()));
        maiGppsConsumptions.setTEMP_MIN(Double.parseDouble(data.getTempMin()));
        maiGppsConsumptions.setCREATION_DATE(new Date());
        maiGppsConsumptions.setLATITUDE(Float.parseFloat(branchRequest.getLatitude()));
        maiGppsConsumptions.setLONGITUDE(Float.parseFloat(branchRequest.getLongitude()));
        maiGppsConsumptions.setDEBEAKING(data.getDebeaking());
        maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
        maiGppsConsumptions.setTXN_DATE(getTxnDateString(branchRequest.getEntryDate(),fromdateFormat1));
        maiGppsConsumptions.setTXN_TYPE("OTHERS");
        sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
        //}

        return "200";
    }

    @Override
    public ArrayList<BranchUser> getTransportBranch(BranchRequest branchRequest) {
        ArrayList<BranchUser> branchUserArrayList = new ArrayList<BranchUser>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.get_transload_branch_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            // storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchRequest.getUserCode());
            //storedProcedureQuery.setParameter(2, branchRequest.getUserType());
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser branchUser = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.class);
                // branchUser.setUserDetails(getRegisteredBranchUsers(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType(), branchUser.getBranchName()));
                // branchUser.setBranchUserDetails(getSupervisorNewDetails(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType()));
               // branchUser.setFlockDetails(getFlockDetails(String.valueOf(branchUser.getBranchID())));
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
    }

    @Override
    public ArrayList<BranchUser.FarmFlockVaccineDetails> getVaccineScheduleDetails(String branchID) {
        ArrayList<BranchUser.FarmFlockVaccineDetails> eggWeightCapturePersonArrayList = new ArrayList<BranchUser.FarmFlockVaccineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getfarmflockddtls");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            //storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            //storedProcedureQuery.setParameter(2, deviceId);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.FarmFlockVaccineDetails eggWeightCapturePerson = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.FarmFlockVaccineDetails.class);
                eggWeightCapturePerson.setVaccineDetails(getVaccineDetails(branchID,eggWeightCapturePerson.getAge()));
                eggWeightCapturePersonArrayList.add(eggWeightCapturePerson);
            }
        } catch (Exception e) {

        }
        return eggWeightCapturePersonArrayList;
    }

    private ArrayList<BranchUser.VaccineDetails> getVaccineDetails(String branchID, String age) {
        ArrayList<BranchUser.VaccineDetails> branchUserArrayList = new ArrayList<BranchUser.VaccineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.get_vaccine_branch_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
             storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, age);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);

            while (resultSet.next()) {
                BranchUser.VaccineDetails branchUser = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.VaccineDetails.class);
                // branchUser.setUserDetails(getRegisteredBranchUsers(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType(), branchUser.getBranchName()));
                // branchUser.setBranchUserDetails(getSupervisorNewDetails(String.valueOf(branchUser.getBranchID()), branchRequest.getUserType()));
                // branchUser.setFlockDetails(getFlockDetails(String.valueOf(branchUser.getBranchID())));
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
    }


}
