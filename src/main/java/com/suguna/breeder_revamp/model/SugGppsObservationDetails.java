package com.suguna.breeder_revamp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_GPPS_OBSERVATION_DETAILS", schema = "SUG")
public class SugGppsObservationDetails {
    Long TRANS_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_obs_details")
    @SequenceGenerator(sequenceName = "SUG_GPPS_OBSERVATION_DETAILS_S", allocationSize = 1, name = "id_seq_gpps_obs_details")
    Long TRANS_LINE_ID;
    Long CATEGORY_ID;
    BigDecimal OBSERVATION_ID;
    String OBSERVATION_FLAG;
    Long CREATED_BY;
    Date CREATION_DATE;
    Long LAST_UPDATED_BY;
    Date LAST_UPDATED_DATE;
}
