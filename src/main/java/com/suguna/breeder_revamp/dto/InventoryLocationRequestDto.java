package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryLocationRequestDto {

    @JsonProperty("fromBatchId")
    @JsonAlias({"batchID", "batchId", "from_batch_id"})
    private BigDecimal fromBatchId;

    @JsonProperty("fromFarmId")
    @JsonAlias({"fromId", "farmID", "farmId", "branchID", "branchId", "from_farm_id"})
    private BigDecimal fromFarmId;
}
