package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryLocationResponseDto {

    @JsonProperty("inventoryLocationId")
    private BigDecimal inventoryLocationId;

    @JsonProperty("inventoryLocDesc")
    private String inventoryLocDesc;

    @JsonProperty("batchId")
    private BigDecimal batchId;
}
