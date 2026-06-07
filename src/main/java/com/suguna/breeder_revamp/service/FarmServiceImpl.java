package com.suguna.breeder_revamp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suguna.breeder_revamp.components.FileStorageService;
import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.dto.PlacementRequest;
import com.suguna.breeder_revamp.dto.SugGppsObservationBatchDTO;
import com.suguna.breeder_revamp.dto.SugGppsObservationDTO;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
                branchUserArrayList.add(branchUser);
            }
        } catch (Exception e) {

        }
        return branchUserArrayList;
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

                supervisorDetailsArrayList.add(supervisorDetails);
            }
        } catch (Exception e) {

        }
        return supervisorDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ShedDetails> getShedDetails(String branchID) {
        ArrayList<BranchUser.ShedDetails> shedDetailsArrayList = new ArrayList<BranchUser.ShedDetails>();
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

    @Override
    public ArrayList<BranchUser.ShedLineDetails> getShedLineDetails(String branchID) {
        ArrayList<BranchUser.ShedLineDetails> shedLineDetailsArrayList = new ArrayList<BranchUser.ShedLineDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshedline_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

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
    public ArrayList<BranchUser.DailyFlockEntryDetails> getDailyEntrySchedule(String branchID) {
        ArrayList<BranchUser.DailyFlockEntryDetails> shedDetailsArrayList = new ArrayList<BranchUser.DailyFlockEntryDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getshed_dailyentrydtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.setParameter(2, branchID);
            storedProcedureQuery.setParameter(3, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(4);

            while (resultSet.next()) {
                BranchUser.DailyFlockEntryDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.DailyFlockEntryDetails.class);

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        return shedDetailsArrayList;
    }

    @Override
    public ArrayList<BranchUser.ObservationCategory> getObservationCategory(String branchID) {
        ArrayList<BranchUser.ObservationCategory> shedDetailsArrayList = new ArrayList<BranchUser.ObservationCategory>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getobservationslist");

            storedProcedureQuery.registerStoredProcedureParameter(1, ArrayList.class, ParameterMode.REF_CURSOR);

            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(1);

            while (resultSet.next()) {
                BranchUser.ObservationCategory shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.ObservationCategory.class);
                shedDetails.setQuestion(getObservationCategoryDetails(shedDetails.getCategoryId()));
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
                    sugGppsObservationHeader.setLEDGER_ID(2025L);
                    sugGppsObservationHeader.setBRANCH_ID(Long.valueOf(branchRequest.getBranchID()));
                    sugGppsObservationHeader.setBRANCH_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    sugGppsObservationHeader.setFLOCK_NO(gppsObservationBatchDTO.getFLOCK_NO());
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
                        sugGppsObservationDetails.setTRANS_ID(Long.valueOf(1));
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
                shedBirsDetailsArrayList.add(shedWiseFeedBirdsDetails);
            }

        } catch (Exception e) {

        }
        return shedBirsDetailsArrayList;
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
                maiGppsConsumptions.setTXN_TYPE("FEED");
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

                maiGppsConsumptions.setQTY(Long.valueOf(sugMortalityDetails.getTotalBirds()));
                maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                maiGppsConsumptions.setSEX(sugMortalityDetails.getBirdType());
                maiGppsConsumptions.setCREATION_DATE(new Date());
                maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                maiGppsConsumptions.setTXN_TYPE("MORTALITY");
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
                    maiGppsConsumptions.setTXN_TYPE("EGG COLLECTION");
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
                    maiGppsConsumptions.setTXN_TYPE("WEEK");
                    maiGppsConsumptions.setREMARK(sugWeekBirdDetails.getReasonType());
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
                    shedDetails.setOpFemaleFeedStandard(parts[0]);
                    shedDetails.setOpMaleFeedStandard(parts[1]);
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

    public ArrayList<BranchUser.ObservationCategoryDetails> getObservationCategoryDetails(String categoryID) {
        ArrayList<BranchUser.ObservationCategoryDetails> shedDetailsArrayList = new ArrayList<BranchUser.ObservationCategoryDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getobservationslist_dtls");
            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, categoryID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

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
                "       h.BATCH_NO\n" +
                "  FROM sug_organization_mv c, gme_batch_header h\n" +
                " where h.organization_id = c.branch_id\n" +
                "   and h.BATCH_ID = " + batchID + " ";

        List<Object[]> results =
                entityManager.createNativeQuery(sql).getResultList();

        return results.stream()
                .map(row -> new SugGppsObservationBatchDTO(
                        ((String) row[0]),   // LEDGER_ID
                        (String) row[1],                 // DIVISION
                        (String) row[2],                 // CATEGORY
                        ((String) row[3])   // CATEGORY_ID
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
                    shedDetails.setOpFemaleFeedStandard(parts[0]);
                    shedDetails.setOpMaleFeedStandard(parts[1]);
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
        if (!data.isEmpty()) {
            for (BranchRequest.SugCullingDetails sugCullingDetails : data) {
                if(!sugCullingDetails.getFemaleBirdsCount().isEmpty()) {
                    SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                    maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                    maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                    // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                    maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getFemaleBirdsCount()));
                    maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getFemaleBirdsWeight())));
                    maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                    maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                    maiGppsConsumptions.setCREATION_DATE(new Date());
                    maiGppsConsumptions.setSEX("Female");
                    maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                    maiGppsConsumptions.setTXN_TYPE("CULLING");
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                }
                    if(!sugCullingDetails.getMaleBirdsCount().isEmpty()) {
                        SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
                        maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
                        maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
                        // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

                        maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
                        maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
                        maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
                        maiGppsConsumptions.setREASON(sugCullingDetails.getReason());
                        maiGppsConsumptions.setCREATION_DATE(new Date());
                        maiGppsConsumptions.setSEX("Male");
                        maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
                        maiGppsConsumptions.setTXN_TYPE("CULLING");
                        sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                    }

            }

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
                    shedDetails.setOpFemaleFeedStandard(parts[0]);
                    shedDetails.setOpMaleFeedStandard(parts[1]);
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

            SugMaiGppsConsumptions maiGppsConsumptions = new SugMaiGppsConsumptions();
            maiGppsConsumptions.setFARM_CODE(gppsObservationBatchDTO.getBRANCH_CODE());
            maiGppsConsumptions.setFLOCK_ID(gppsObservationBatchDTO.getFLOCK_NO());
            // maiGppsConsumptions.setSHED_CODE(branchRequest.getShedNo());

           // maiGppsConsumptions.setQTY(Long.valueOf(sugCullingDetails.getMaleBirdsCount()));
            //maiGppsConsumptions.setWEIGHT(BigDecimal.valueOf(Double.parseDouble(sugCullingDetails.getMaleBirdsWeight())));
            maiGppsConsumptions.setBATCH_ID(Long.valueOf(branchRequest.getBatchID()));
            maiGppsConsumptions.setREASON(data.getReason());
            maiGppsConsumptions.setREMARK(data.getRemark());
            maiGppsConsumptions.setCREATION_DATE(new Date());

            maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
            maiGppsConsumptions.setTXN_TYPE("DESTROY");
            sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
        }

        return "200";
    }

    @Override
    public BranchUser.MortalityPmlDetails getMortalityPmlDetails(String branchID) {
        BranchUser.MortalityPmlDetails details = new BranchUser.MortalityPmlDetails();
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
                    shedDetails.setOpFemaleFeedStandard(parts[0]);
                    shedDetails.setOpMaleFeedStandard(parts[1]);
                } catch (Exception e) {
                    // throw new RuntimeException(e);
                }
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setCullsReasonDetails(getmortalitypmlreason(branchID));
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
                    shedDetails.setOpFemaleFeedStandard(parts[0]);
                    shedDetails.setOpMaleFeedStandard(parts[1]);
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
                    maiGppsConsumptions.setTXN_TYPE("MORTALITY_PML");
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
                    maiGppsConsumptions.setTXN_TYPE("MORTALITY_PML");
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
                    maiGppsConsumptions.setTXN_TYPE(sugCullingDetails.getType());
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
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
                    maiGppsConsumptions.setTXN_TYPE(sugCullingDetails.getType());
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
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
        ArrayList<BranchUser.PlacementInfoDetails> shedDetailsArrayList = new ArrayList<BranchUser.PlacementInfoDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getplacementinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.PlacementInfoDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.PlacementInfoDetails.class);
                details.setTotalBirdsAllocate(shedDetails.getTotalBirdsAllocate());
                details.setRemainingBirds(shedDetails.getRemainingBirds());
                details.setMaleNos(shedDetails.getMaleNos());
                details.setFemaleNos(shedDetails.getFemaleNos());
                details.setAllocatePer(shedDetails.getAllocatePer());
                details.setFlockNumber(shedDetails.getFlockNumber());
                details.setBatchId(shedDetails.getBatchId());
               // shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
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
    public ArrayList<BranchUser.DashboardDetails> getDashboardInfo(String branchID) {
        //BranchUser.PlacementInfoDetails details = new BranchUser.PlacementInfoDetails();
        ArrayList<BranchUser.DashboardDetails> shedDetailsArrayList = new ArrayList<BranchUser.DashboardDetails>();
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SUG_MAI_GPPS_MOB_PKG.getdashboardinfo");

            storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.setParameter(1, branchID);
            storedProcedureQuery.execute();
            ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);

            while (resultSet.next()) {
                BranchUser.DashboardDetails shedDetails = ResultSetMapper.mapResultSetToObject(resultSet, BranchUser.DashboardDetails.class);
                shedDetails.setHenWeekDetails(getHenweekinfo(branchID,shedDetails.getFlockNumber()));
                shedDetails.setFertilityDetails(getFertilityinfo(branchID,shedDetails.getFlockNumber()));
                shedDetails.setHatchabilityDetails(getHatchabilityinfo(branchID,shedDetails.getFlockNumber()));
                shedDetails.setMortalityDetails(getMortalityinfo(branchID,shedDetails.getFlockNumber()));
                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        //details.setPlacementInfoShedDetails(getplacementshedinfo(branchID));
        //details.setCullsReasonDetails(getexcessshortagereason(branchID));
        return shedDetailsArrayList;
    }

    @Override
    public String savePlacementInfoDetails(PlacementRequest placementRequest) {
        Object rawData = placementRequest.getData();
        List<PlacementRequest.SugLineDetails> data = new ArrayList<>();
        //List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(placementRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                PlacementRequest.SugLineDetails details =
                        mapper.convertValue(item, PlacementRequest.SugLineDetails.class);
                data.add(details);
            }
        }
        //SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            if(!placementRequest.getTotalFemaleQty().isEmpty()) {
                SugMaiGppsHousingShed sugMaiGppsHousingShed = new SugMaiGppsHousingShed();
                sugMaiGppsHousingShed.setFLOCK_ID(placementRequest.getFlockID());
                sugMaiGppsHousingShed.setTXN_DATE(new Date());
                sugMaiGppsHousingShed.setFARM_CODE("BGI");
                sugMaiGppsHousingShed.setSHED_NO(placementRequest.getShedNo());
                sugMaiGppsHousingShed.setSEX("F");
                sugMaiGppsHousingShed.setOP_QTY(Long.valueOf(placementRequest.getTotalFemaleQty()));
                sugMaiGppsHousingShed.setCREATED_BY(placementRequest.getUserCode());
                sugMaiGppsHousingShed.setCREATION_DATE(new Date());
                sugMaiGppsHousingShed.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                sugMaiGppsHousingShed.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                sugMaiGppsHousingShedRepositories.save(sugMaiGppsHousingShed);
            }
            if(!placementRequest.getTotalMaleQty().isEmpty()) {
                SugMaiGppsHousingShed sugMaiGppsHousingShed = new SugMaiGppsHousingShed();
                sugMaiGppsHousingShed.setFLOCK_ID(placementRequest.getFlockID());
                sugMaiGppsHousingShed.setTXN_DATE(new Date());
                sugMaiGppsHousingShed.setFARM_CODE("BGI");
                sugMaiGppsHousingShed.setSHED_NO(placementRequest.getShedNo());
                sugMaiGppsHousingShed.setSEX("M");
                sugMaiGppsHousingShed.setOP_QTY(Long.valueOf(placementRequest.getTotalMaleQty()));
                sugMaiGppsHousingShed.setCREATED_BY(placementRequest.getUserCode());
                sugMaiGppsHousingShed.setCREATION_DATE(new Date());
                sugMaiGppsHousingShed.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                sugMaiGppsHousingShed.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                sugMaiGppsHousingShedRepositories.save(sugMaiGppsHousingShed);
            }
            for (PlacementRequest.SugLineDetails sugLineDetails : data) {
                if(!sugLineDetails.getFemaleBirdsCount().isEmpty()) {
                    SugMaiGppsHousingLine maiGppsHousingLine = new SugMaiGppsHousingLine();
                    maiGppsHousingLine.setFLOCK_ID(placementRequest.getFlockID());
                    maiGppsHousingLine.setTXN_DATE(new Date());
                    maiGppsHousingLine.setFARM_CODE("BGI");
                    maiGppsHousingLine.setSHED_NO(placementRequest.getShedNo());
                    maiGppsHousingLine.setSEX("F");
                    maiGppsHousingLine.setGRADE("3");
                    maiGppsHousingLine.setOP_QTY(Long.valueOf(sugLineDetails.getFemaleBirdsCount()));
                    maiGppsHousingLine.setLINE_NO(sugLineDetails.getLineNo());
                    maiGppsHousingLine.setCREATED_BY(placementRequest.getUserCode());
                    maiGppsHousingLine.setCREATION_DATE(new Date());
                    maiGppsHousingLine.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                    maiGppsHousingLine.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                    sugMaiGppsHousingLineRepositories.save(maiGppsHousingLine);
                }
                if(!sugLineDetails.getMaleBirdsCount().isEmpty()) {
                    SugMaiGppsHousingLine maiGppsHousingLine = new SugMaiGppsHousingLine();
                    maiGppsHousingLine.setFLOCK_ID(placementRequest.getFlockID());
                    maiGppsHousingLine.setTXN_DATE(new Date());
                    maiGppsHousingLine.setFARM_CODE("BGI");
                    maiGppsHousingLine.setSHED_NO(placementRequest.getShedNo());
                    maiGppsHousingLine.setSEX("M");
                    maiGppsHousingLine.setGRADE("3");
                    maiGppsHousingLine.setOP_QTY(Long.valueOf(sugLineDetails.getMaleBirdsCount()));
                    maiGppsHousingLine.setLINE_NO(sugLineDetails.getLineNo());
                    maiGppsHousingLine.setCREATED_BY(placementRequest.getUserCode());
                    maiGppsHousingLine.setCREATION_DATE(new Date());
                    maiGppsHousingLine.setBATCH_ID(Long.valueOf(placementRequest.getBatchID()));
                    maiGppsHousingLine.setBRANCH_ID(Long.valueOf(placementRequest.getBranchID()));
                    sugMaiGppsHousingLineRepositories.save(maiGppsHousingLine);
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
                Standard=standardDetails.getFemaleWeight()+"~"+standardDetails.getMaleWeight();
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
            maiGppsConsumptions.setREMARKS(data.getRemarks());
            maiGppsConsumptions.setLIGTHING_START_HRS(data.getLightStartTime());
            maiGppsConsumptions.setLIGTHING_END_HRS(data.getLightEndTime());
            maiGppsConsumptions.setSANITIZATION_START_HRS(data.getSanitizationStartTime());
            maiGppsConsumptions.setSANITIZATION_END_HRS(data.getSanitizationEndTime());
            maiGppsConsumptions.setTEMP_MAX(Double.parseDouble(data.getTempMax()));
             maiGppsConsumptions.setTEMP_MIN(Double.parseDouble(data.getTempMin()));
            maiGppsConsumptions.setCREATION_DATE(new Date());

            maiGppsConsumptions.setCREATED_BY(branchRequest.getUserCode());
            maiGppsConsumptions.setTXN_TYPE("DAY_CLOSE");
            sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
        //}

        return "200";
    }

}
