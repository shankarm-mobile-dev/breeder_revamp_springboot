package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_TRANS_HDR",schema = "SUG")
public class SugMaiGppsTransHdr {
    BigDecimal DEVICE_ID;
    String EMPCODE;
    BigDecimal FROM_FARM_ID;
    String FROM_FARM_NAME;
    BigDecimal TO_FARM_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_trans_hdr")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_TRANS_HDR_S", allocationSize = 1, name = "id_seq_gpps_trans_hdr")
    long TXN_HEADER_ID;
    String TRANS_TYPE;
    Date TXN_DATE;
    String VEHICLE_NO;
    String OUT_PASS_NO;
    String RECEIVER_NAME;
    String TRANS_REASON;
    Date ENTRY_CREATION_DATE;
    Date CREATED_DATE;
    String POSTED_FLAG;
    String POST_TO_ERP;
    String LOCATION_TYPE;
    String TXN_TIME;
    String VEHICLE_TYPE;
    String TRANS_MODE;
    BigDecimal TRAY_NOS;
    BigDecimal BOX_NOS;
    String PACK_MATERIAL;
    long PLAN_DTL_ID;
}
