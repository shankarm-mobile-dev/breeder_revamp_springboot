package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_EGGWEIGHTREADING", schema = "SUG")
public class SugMaiGppsEggWeightReading {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_egg_weight")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_EGGWEIGHT_S", allocationSize = 1, name = "id_seq_gpps_egg_weight")
    long TXN_ID ;
    long DEVICE_ID;
    Date TRANSACTION_DATE;
    long BRANCH_ID ;
    String BRANCH_NAME ;
    long BATCHID;
    String FLOCK;
    String BREED ;
    long NUMBEROFEGG ;
    long EMPTY_TRAY;
    long TOTALEGG_TRAY ;
    double NET_EGGWEIGHT ;
    double AVERAGE_EGGWEIGHT ;
    Date ENTRY_CREATION_DATE;
    String POSTED_FLAG ;
    Date CREATION_DATE ;
}
