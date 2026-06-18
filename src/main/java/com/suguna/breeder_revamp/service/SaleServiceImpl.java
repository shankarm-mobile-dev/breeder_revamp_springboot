package com.suguna.breeder_revamp.service;


import com.suguna.breeder_revamp.dto.SaleResultDto;
import com.suguna.breeder_revamp.dto.deliveryHeaderDto;
import com.suguna.breeder_revamp.dto.deliveryLinesDto;
import com.suguna.breeder_revamp.dto.deliveryLotDetailsDto;
import com.suguna.breeder_revamp.model.deliveryHeaderModels;
import com.suguna.breeder_revamp.model.deliveryLinesModels;
import com.suguna.breeder_revamp.model.deliveryLotDetailsModels;
import com.suguna.breeder_revamp.repositories.deliveryHeaderRepository;
import com.suguna.breeder_revamp.repositories.deliveryLinesRepository;
import com.suguna.breeder_revamp.repositories.deliveryLotDetailsRepository;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SaleServiceImpl implements SaleServices {
    @Autowired
    EntityManager entityManager;
    public ArrayList<SaleResultDto.customerdetails> CUSTOMERDETAILS(String branch_id) throws SQLException{
        SaleResultDto.customerdetails appinfo = new SaleResultDto.customerdetails();
        ArrayList<SaleResultDto.customerdetails> Result = new ArrayList<SaleResultDto.customerdetails>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getcustomerdetails");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_id);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,  SaleResultDto.customerdetails.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }
    public ArrayList<SaleResultDto.vehicleno> VEHICLENOS(String regionid) throws SQLException{
        SaleResultDto.vehicleno appinfo = new SaleResultDto.vehicleno();
        ArrayList<SaleResultDto.vehicleno> Result = new ArrayList<SaleResultDto.vehicleno>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getvehicleno");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, regionid);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,  SaleResultDto.vehicleno.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }
    public ArrayList<SaleResultDto.orderdetails>ORDERDETAILS(String branch_ID,String timestring) throws SQLException{
        SaleResultDto.orderdetails appinfo = new SaleResultDto.orderdetails();
        ArrayList<SaleResultDto.orderdetails> Result = new ArrayList<SaleResultDto.orderdetails>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getorderdetails");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2,String.class,ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        storedProcedureQuery.setParameter(2,timestring);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(3);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,  SaleResultDto.orderdetails.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }
    public ArrayList<SaleResultDto.onhandculleggstock> ONHANDCULLEGGSTOCKS(String branch_ID)throws SQLException{
        SaleResultDto.onhandculleggstock appinfo = new SaleResultDto.onhandculleggstock();
        ArrayList<SaleResultDto.onhandculleggstock> Result = new ArrayList<SaleResultDto.onhandculleggstock>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getonhandculleggstock");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branch_ID);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,  SaleResultDto.onhandculleggstock.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
    }
    public ArrayList<SaleResultDto.despatchtime>DESPATCHTIMES(String regionid )throws SQLException{
        SaleResultDto.despatchtime appinfo = new SaleResultDto.despatchtime();
        ArrayList<SaleResultDto.despatchtime> Result = new ArrayList<SaleResultDto.despatchtime>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getdespatchtime");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, regionid);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet,  SaleResultDto.despatchtime.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        return Result;
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
    @Autowired
    deliveryHeaderRepository deliveryHeaderRepository;

    public String getDeliveryHeader(ArrayList<deliveryHeaderDto> entry){
        String fromdateFormat = "yyyy/MM/dd hh:mm:ss";
     try {
         for(deliveryHeaderDto saledto:entry){
             deliveryHeaderModels headerModels = new deliveryHeaderModels();
             headerModels.setLEDGER_ID(saledto.getLedgerid());
             headerModels.setORG_ID(saledto.getOrgid());
             headerModels.setBRANCH_ID(saledto.getBranchid());
             int deli_trans_id;
             deli_trans_id = gettrxid("sug_delivery_header_s");
             headerModels.setDELV_TRANS_ID(new BigDecimal(deli_trans_id));
             headerModels.setDELIVERY_DATE(getTxnDateString(saledto.getDeliveryDate(),fromdateFormat));
             headerModels.setCUSTOMER_ID(saledto.getCustomerid());
             headerModels.setVEHICLE_NO(saledto.getVehicleno());
             int trans_ref_number;
             trans_ref_number=gettrxid("SUG_TRANS_REF_NUMBER_S");
             headerModels.setTRANS_REF_NUMBER(String.valueOf(trans_ref_number));
             headerModels.setCREATED_BY(saledto.getCreatedby());
             headerModels.setCREATION_DATE(getTxnDateString(saledto.getCreation_date(),fromdateFormat));
             headerModels.setLAST_UPDATE_BY(saledto.getLastupdateby());
             headerModels.setLAST_UPDATE_DATE(new Date());
             headerModels.setSTATUS(saledto.getStatus());
             headerModels.setSOURCE(saledto.getSource());
             deliveryHeaderRepository.save(headerModels);
             return "True";
         }
     }catch (Exception e){
         e.getMessage();
     }
     return "False";
    }
    @Autowired
    deliveryLinesRepository deliveryLinesRepository;
    public String getDeliveryLines(ArrayList<deliveryLinesDto> entry){
        String fromdateFormat = "yyyy/MM/dd hh:mm:ss";
        try {
            for(deliveryLinesDto saledto:entry){
                deliveryLinesModels linesModels = new deliveryLinesModels();
                linesModels.setDELV_TRANS_ID(saledto.getDelvtransid());
                int delvtranslineid;
                delvtranslineid=gettrxid("sug_delivery_lines_s");
                linesModels.setDELV_TRANS_LINE_ID(new BigDecimal(delvtranslineid));
                linesModels.setORDER_NUMBER(saledto.getOrdernumber());
                linesModels.setOE_ORDER_LINE_ID(saledto.getOeorderlineid());
                linesModels.setOE_ORDER_HEADER_ID(saledto.getOeorderheaderid());
                linesModels.setINVENTORY_ITEM_ID(saledto.getInventoryitemid());
                linesModels.setORDER_UOM(saledto.getOrderuom());
                linesModels.setORDERED_QTY(saledto.getOrderedqty());
                linesModels.setORDERED_QTY2(saledto.getOrderedqty2());
                linesModels.setSHIPPED_QTY(saledto.getShippedqty());
                linesModels.setSHIPPED_QTY2(saledto.getShippedqty2());
                linesModels.setCREATED_BY(saledto.getCreatedby());
                linesModels.setLAST_UPDATE_BY(saledto.getLastupdateby());
                linesModels.setCREATION_DATE(getTxnDateString(saledto.getCreation_date(),fromdateFormat));
                linesModels.setSTATUS(saledto.getStatus());
                linesModels.setLAST_UPDATE_DATE(new Date());
                linesModels.setINVENTORY_LOCATION_ID(saledto.getInventorylocationid());
                linesModels.setSUBINVENTORY_CODE(saledto.getSubinventorycode());
                deliveryLinesRepository.save(linesModels);
                return "true";
            }
        }catch (Exception e){
            e.getMessage();
        }
        return "false";
    }
    @Autowired
    deliveryLotDetailsRepository deliveryLotDetailsRepository;
    public String getDeliveryLotDetails(ArrayList<deliveryLotDetailsDto> entry){
        String fromdateFormat = "yyyy/MM/dd hh:mm:ss";
        try {
            for(deliveryLotDetailsDto saledto:entry){
                deliveryLotDetailsModels lotDetailsModels = new deliveryLotDetailsModels();
                lotDetailsModels.setDELV_TRANS_ID(saledto.getDelvtransid());
                lotDetailsModels.setDELV_TRANS_LINE_ID(saledto.getDelvtransid());
                int deltranslotdets_id;
                deltranslotdets_id =gettrxid("sug_delivery_lot_details_s");
                lotDetailsModels.setDELV_TRANS_LOT_DET_ID(new BigDecimal(deltranslotdets_id));
                lotDetailsModels.setLOT_NUMBER(saledto.getLotnumber());
                lotDetailsModels.setRECEIPT_DATE(new Date());
                lotDetailsModels.setONHAND_STK_QTY(saledto.getOnhandstkqty());
                lotDetailsModels.setSHIPPED_QTY(saledto.getShippedqty());
                lotDetailsModels.setSHIPPED_QTY2(saledto.getShippedqty2());
                lotDetailsModels.setCREATED_BY(saledto.getCreatedby());
                lotDetailsModels.setCREATION_DATE(getTxnDateString(saledto.getCreation_date(),fromdateFormat));
                lotDetailsModels.setLAST_UPDATE_BY(saledto.getLastupdateby());
                lotDetailsModels.setLAST_UPDATE_DATE(new Date());
                lotDetailsModels.setINVENTORY_LOCATION_ID(saledto.getInventorylocationid());
                lotDetailsModels.setSTATUS(saledto.getStatus());
                lotDetailsModels.setSUBINVENTORY_CODE(saledto.getSubinventorycode());
                lotDetailsModels.setBRANCH_ID(saledto.getBranchid());
                deliveryLotDetailsRepository.save(lotDetailsModels);

                return "True";
            }
        }catch (Exception e){
            e.getMessage();
        }
        return "False";
    }
}
