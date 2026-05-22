package com.suguna.breeder_revamp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suguna.breeder_revamp.dto.BranchRequest;
import com.suguna.breeder_revamp.dto.SugGppsObservationBatchDTO;
import com.suguna.breeder_revamp.dto.SugGppsObservationDTO;
import com.suguna.breeder_revamp.model.*;
import com.suguna.breeder_revamp.repositories.SugGppsObservationDetailsRepositories;
import com.suguna.breeder_revamp.repositories.SugGppsObservationHeaderRepositories;
import com.suguna.breeder_revamp.repositories.SugMaiGppsConsumptionsRepositories;
import com.suguna.breeder_revamp.repositories.SugMaiGppsItemAllocationRepositories;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {

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
    private ObjectMapper mapper;

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
    public String saveFeedDetails(BranchRequest branchRequest) {
     /*   List<BranchRequest.SugFeedDetails> data=new ArrayList<>();
        data= (List<BranchRequest.SugFeedDetails>) branchRequest.getData();
*/
        Object rawData = branchRequest.getData();
        List<BranchRequest.SugFeedDetails> data = new ArrayList<>();
        List<SugGppsObservationBatchDTO> batchDTOS = getBatchDetails(branchRequest.getBatchID());
        if (rawData instanceof List<?>) {
            for (Object item : (List<?>) rawData) {
                // Convert each LinkedHashMap into SugFeedDetails
                BranchRequest.SugFeedDetails details =
                        mapper.convertValue(item, BranchRequest.SugFeedDetails.class);
                data.add(details);
            }
        }
        SugGppsObservationBatchDTO gppsObservationBatchDTO = batchDTOS.get(0);
        if (!data.isEmpty()) {
            for (BranchRequest.SugFeedDetails sugFeedDetails : data) {
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

                shedDetailsArrayList.add(shedDetails);
            }
        } catch (Exception e) {

        }
        details.setFarmFlockDetails(shedDetailsArrayList);
        details.setCullsReasonDetails(getexcessshortagereason(branchID));
        return details;
    }

    @Override
    public String saveMortalityPmlDetails(BranchRequest branchRequest) {
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
                    maiGppsConsumptions.setTXN_TYPE("MORTALITY_PML");
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
                    maiGppsConsumptions.setTXN_TYPE("MORTALITY_PML");
                    sugMaiGppsConsumptionsRepositories.save(maiGppsConsumptions);
                }

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


}
