package com.suguna.breeder_revamp.service;



import com.suguna.breeder_revamp.dto.SaleResultDto;
import com.suguna.breeder_revamp.dto.deliveryHeaderDto;
import com.suguna.breeder_revamp.dto.deliveryLinesDto;
import com.suguna.breeder_revamp.dto.deliveryLotDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SaleServices {
    public ArrayList<SaleResultDto.customerdetails> CUSTOMERDETAILS(String branch_id) throws SQLException;
    public ArrayList<SaleResultDto.vehicleno> VEHICLENOS(String regionid) throws SQLException;
    public ArrayList<SaleResultDto.orderdetails>ORDERDETAILS(String branch_ID,String timestring) throws SQLException;
    public ArrayList<SaleResultDto.onhandculleggstock> ONHANDCULLEGGSTOCKS(String branch_ID)throws SQLException;
    public ArrayList<SaleResultDto.despatchtime>DESPATCHTIMES(String regionid )throws SQLException;

    public String getDeliveryHeader(ArrayList<deliveryHeaderDto> entry);
    public String getDeliveryLines(ArrayList<deliveryLinesDto> entry) ;
    public String getDeliveryLotDetails(ArrayList<deliveryLotDetailsDto> entry);
}
