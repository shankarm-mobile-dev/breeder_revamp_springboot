package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_HOUSING_LINE_HIST", schema = "SUG")
public class SugMaiGppsHousingLineHist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_hsg_line_hist")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_HSG_LINE_HIST_S", schema = "SUG", allocationSize = 1, name = "id_seq_gpps_hsg_line_hist")
    Long HIST_ID;
    Long HOUSING_TXN_ID;
    String FLOCK_ID;
    String FARM_CODE;
    String SHED_NO;
    String LINE_NO;
    String SIDE;
    String SEX;
    String GRADE;
    Long BRANCH_ID;
    Long BATCH_ID;
    String TXN_TYPE;
    Date TXN_DATE;
    Long QTY;
    Long OP_QTY_BEFORE;
    Long CL_QTY_AFTER;
    String DIRECTION;
    String BIRD_CATEGORY;
    String REASON;
    Long REF_ID;
    String PAIR_ID;
    String FROM_LINE;
    String FROM_SIDE;
    String TO_LINE;
    String TO_SIDE;
    String FROM_SHED;
    String TO_SHED;
    Long CAPACITY;
    Long TOLERANCE_PER;
    Long ALLOWED_QTY;
    String CREATED_BY;
    Date CREATION_DATE;
}
