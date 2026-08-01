package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_EGG_QUALITYCAPTURE",schema = "SUG")
public class SugMaiEggQualityCapture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_egg_qualitycapture")
    @SequenceGenerator(sequenceName = "sug_mai_egg_qualitycapture_s", allocationSize = 1, name = "id_seq_egg_qualitycapture")
    long TXN_ID;
    long PARENT_BRANCH_ID;
    String PARENT_BRANCH_NAME;
    long BRANCH_ID;
    String BRANCH_NAME;
    String FLOCK;
    String BREED;
    Date TRANSACTION_DATE;
    long NO_OFSAMPLEEGG;
    long FERTILE;
    long INFERTILE;
    long PRE_INCUBATION;
    long YOLK_MOTTLING;
    long MEAT_SPOT;
    long BLOOD_SPOT;
}
