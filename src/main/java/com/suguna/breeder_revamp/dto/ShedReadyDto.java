package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.*;

import java.util.ArrayList;
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

        @JsonProperty("VALUE")
        private String value;
        @JsonProperty("UOM")
        private String uom;

        @Builder.Default
        @JsonProperty("INPUT_MANDATORY")
        private String inputMandatory = "N";

        @JsonProperty("medicine_details")
        private ArrayList<itemmaster> medicine_details;


        @Getter
        @Setter
        public static class itemmaster{
                @Column(name = "item_TYPE", type = String.class)
                @JsonProperty("item_TYPE")
                public String item_TYPE;
                @Column(name = "item_GROUP", type = String.class)
                @JsonProperty("item_GROUP")
                public String item_GROUP;

                @Column(name = "item_CATEGORY", type = String.class)
                @JsonProperty("item_CATEGORY")
                public String item_CATEGORY;
                @Column(name = "inventory_ITEM_ID", type = String.class)
                @JsonProperty("inventory_ITEM_ID")
                public String inventory_ITEM_ID;

                @Column(name = "batch_TYPE", type = String.class)
                @JsonProperty("batch_TYPE")
                public String batch_TYPE;

                @Column(name = "item_CODE", type = String.class)
                @JsonProperty("item_CODE")
                public String item_CODE;

                @Column(name = "item_DESCRIPTION", type = String.class)
                @JsonProperty("item_DESCRIPTION")
                public String item_DESCRIPTION;

                @Column(name = "primary_UOM", type = String.class)
                @JsonProperty("primary_UOM")
                public String primary_UOM;

                @Column(name = "secondary_UOM", type = String.class)
                @JsonProperty("secondary_UOM")
                public String secondary_UOM;

                @Column(name = "organization_ID", type = String.class)
                @JsonProperty("organization_ID")
                public String organization_ID;

                @Column(name = "seq_NO", type = String.class)
                @JsonProperty("seq_NO")
                public String seq_NO;

                @Column(name = "primary_TRANSACTION_QUANTITY", type = String.class)
                @JsonProperty("STOCK")
                public String STOCK;


        }
}


