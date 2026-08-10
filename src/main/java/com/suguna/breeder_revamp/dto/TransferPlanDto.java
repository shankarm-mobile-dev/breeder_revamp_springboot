package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class TransferPlanDto {
    @JsonProperty("fromOrgId")
    public long fromOrgId;
    @JsonProperty("fromFarmName")
    public String fromFarmName;
    @JsonProperty("toOrgId")
    public long toOrgId;
    @JsonProperty("userCode")
    public String userCode;
    @JsonProperty("transType")
    public String transType;
    @JsonProperty("transDate")
    public String transDate;
    @JsonProperty("transReason")
    public String transReason;
    @JsonProperty("flockId")
    public String flockId;
    @JsonProperty("transferPlanDtls")
    public ArrayList<TransferPlanDtlsDto> transferPlanDtls;

    public static class TransferPlanDtlsDto{
        @JsonProperty("itemType")
        public String itemType;
        @JsonProperty("itemId")
        public String itemId;
        @JsonProperty("quantity")
        public BigDecimal quantity;
        @JsonProperty("fromFarmLocation")
        public String fromFarmLocation;
        @JsonProperty("toFarmLocation")
        public String toFarmLocation;

        @JsonProperty("fromLine")
        public String fromLine;
        @JsonProperty("toLine")
        public String toLine;
        @JsonProperty("fromSide")
        public String fromSide;
        @JsonProperty("toSide")
        public String toSide;

    }
}
