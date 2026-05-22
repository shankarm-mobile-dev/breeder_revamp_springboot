package com.suguna.breeder_revamp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SugGppsObservationDTO {

    private Long ledgerId;
    private String division;
    private String category;
    private Long categoryId;
    private String observationDescription;
    private BigDecimal observationId;

    public SugGppsObservationDTO(Long ledgerId,
                          String division,
                          String category,
                          Long categoryId,
                          String observationDescription,
                          BigDecimal observationId) {
        this.ledgerId = ledgerId;
        this.division = division;
        this.category = category;
        this.categoryId = categoryId;
        this.observationDescription = observationDescription;
        this.observationId = observationId;
    }

}
