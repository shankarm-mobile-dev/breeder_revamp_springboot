package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EggWeightCaptureDto {
    @JsonProperty("date")
    String date;
    @JsonProperty("branchId")
    long branchId;
    @JsonProperty("flock")
    String flock;
    @JsonProperty("crackEggs")
    long crackEggs;
    @JsonProperty("damageEggs")
    long damageEggs;
    @JsonProperty("missingEggs")
    long missingEggs;
    @JsonProperty("totalChecked")
    long totalChecked;
    @JsonProperty("totalDefects")
    long totalDefects;
    @JsonProperty("empCode")
    String empCode;
    @JsonProperty("remark")
    String remark;
    @JsonProperty("inspector")
    String inspector;
}
