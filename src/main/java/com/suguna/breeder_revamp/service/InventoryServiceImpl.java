package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.SaveSugMaterialConsumptionDto;
import com.suguna.breeder_revamp.model.SaveSugNaterialConsumptionModel;
import com.suguna.breeder_revamp.repositories.SaveSugMaterialConsumptionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Service
public class InventoryServiceImpl implements  InventoryService{

    @Autowired
    EntityManager entityManager;

    @Autowired
    SaveSugMaterialConsumptionRepository saveSugMaterialConsumptionRepository;
    @Transactional
    public String SaveSugMaterialConsumption(ArrayList<SaveSugMaterialConsumptionDto> entry) {
        String fromdateFormat  = "dd-MM-yyyy HH:mm:ss";
        String fromdateFormat1 = "dd-MM-yyyy";
        try {
            for (SaveSugMaterialConsumptionDto Farmdto : entry) {

                String Material = Sugmaterialconsumption(
                        Farmdto.device_id, Farmdto.transdate, Farmdto.branch_id,
                        Farmdto.inventory_item_id, Farmdto.quantity, Farmdto.entry_creation_date
                );

                if (Material.equals("0")) {
                    SaveSugNaterialConsumptionModel model = new SaveSugNaterialConsumptionModel();


                    model.setTRANS_DATE(getTxnDateString(Farmdto.transdate, fromdateFormat1));
                    model.setBRANCH_ID(new BigDecimal(Farmdto.getBranch_id()));
                    model.setINVENTORY_ITEM_ID(new BigDecimal(Farmdto.inventory_item_id));
                    model.setITEM_DESCRIPTION(Farmdto.getItem_description());
                    model.setTRANS_TYPE(Farmdto.getTrans_type());
                    model.setSTK_QTY(Farmdto.getStk_qty());
                    model.setQUANTITY(new BigDecimal(Farmdto.quantity));
                    model.setUOM(Farmdto.getUom());
                    model.setPOSTED_FLAG("N");
                    model.setENTRY_CREATION_DATE(getTxnDateString(Farmdto.getEntry_creation_date(), fromdateFormat));
                    model.setINVENTORY_LOCATION_ID(Farmdto.getInventory_location_id());
                    model.setCREATION_DATE(new Date());
                    model.setFOR_LTR_WATER(Farmdto.getFor_ltr_water());
                    model.setADVISED_BY(Farmdto.getAdvised_by());
                    model.setISSUED_BY(Farmdto.getIssued_by());

                    saveSugMaterialConsumptionRepository.save(model);
                }
            }
            return "True";  // ✅ return after processing all entries
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    public String Sugmaterialconsumption(String device_id, String transdate, String branch_id, String inventory_item_id, String quantity, String entry_creation_date) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.getsugmaterialconsumption");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter(1, transdate);
        storedProcedureQuery.setParameter(2, branch_id);
        storedProcedureQuery.setParameter(3, inventory_item_id);
        storedProcedureQuery.setParameter(4, quantity);
        storedProcedureQuery.setParameter(5, entry_creation_date);
        String output = (String) storedProcedureQuery.getOutputParameterValue(6);
        return output;
    }

    public int gettrxid(String seqname) {
        BigDecimal count = (BigDecimal) entityManager.createNativeQuery("select "+seqname+".NEXTVAL from dual")

                .getSingleResult();
        return count.intValue();

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
}
