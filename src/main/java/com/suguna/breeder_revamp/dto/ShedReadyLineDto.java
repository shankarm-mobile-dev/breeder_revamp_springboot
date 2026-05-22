package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShedReadyLineDto {
    @JsonProperty("TRANS_LINE_ID")
    private Long transLineId;
    @JsonProperty("TRANS_ID")
    private Long transId;
    @JsonProperty("ACTIVITY_ID")
    private Long activityId;
    @JsonProperty("FARMER_STATUS")
    private String farmerStatus;
    @JsonProperty("MANAGER_STATUS")
    private String managerStatus;
    @JsonProperty("IMAGE_PATH")
    private String imagePath;
    @JsonProperty("CREATION_DATE")
    private Date creationDate;
    @JsonProperty("UPDATED_DATE")
    private Date updationDate;
    @JsonProperty("FARM_CODE")
    private String farmCode;
    @JsonProperty("ORG_ID")
    private int orgId;
    @JsonProperty("REMARKS")
    private String remarks;
    @JsonProperty("VOICE_PATH")
    private String voicePath;
    @JsonProperty("BATCH_ID")
    private Integer batchId;
}
