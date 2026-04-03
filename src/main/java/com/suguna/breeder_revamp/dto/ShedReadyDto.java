package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShedReadyDto {


        @JsonProperty("QUESTION_ID")
        private int questionId;
        @JsonProperty("LEDGER_ID")
        private int ledgerId;
        @JsonProperty("FEEDBACK_REF")
        private String feedbackRef;
        @JsonProperty("USER_TYPE")
        private String userType;
        @JsonProperty("LANGUAGE")
        private String language;
        @JsonProperty("CATEGORY")
        private String category;
        @JsonProperty("CATEGORY_ID")
        private int categoryId;
        @JsonProperty("QUESTION")
        private String question;
        @JsonProperty("QUESTION_SEQ")
        private int questionSeq;

        @Builder.Default
        @JsonProperty("IS_SUBMITTED")
        private boolean submitted = false;
        @JsonProperty("SUBMITTED_DATE")
        private Date submittedDate;
        @JsonProperty("STATUS")
        private String status;
        @JsonProperty("REMARKS")
        private String remarks;



}
