package com.suguna.breeder_revamp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_GPPS_EGG_UNBOXING", schema = "SUG")
public class SugGppsEggUnboxingModels {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_SugGppsEggUnboxing_S")
    @SequenceGenerator(sequenceName = "SUG_GPPS_EGG_UNBOXING_S", allocationSize = 1, name = "id_seq_SugGppsEggUnboxing_S")
    long TRANS_ID;
    long BRANCH_ID;
    String FLOCK;
    Date UNBOXING_DATE;
    long CRACK_EGGS;
    long DAMAGE_EGGS;
    long MISSING_EGGS;
    long TOTAL_CHECKED;
    long TOTAL_DEFECTED;
    String CREATED_BY;
    //Date  CREATION_DATE  DATE default (sysdate),
    String REMARKS;
    String INSPECTOR_NAME;
    long HDR_ID;
}
