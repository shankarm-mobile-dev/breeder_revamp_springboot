package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_TRANS_PLAN_HDR",schema = "SUG")
public class SugMaiGppsTransPlanHdr {
    BigDecimal DEVICE_ID;
    String EMPCODE;
    BigDecimal FROM_FARM_ID;
    String FROM_FARM_NAME;
    BigDecimal TO_FARM_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_trans_plan_hdr")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_TRANS_HDR_S", allocationSize = 1, name = "id_seq_gpps_trans_plan_hdr")
    long TXN_HEADER_ID;
    String TRANS_TYPE;
    String FLOCK_ID;
    Date TXN_DATE;
    String TRANS_REASON;
    Date UPDATION_DATE;
    String UPDATION_BY;
}
