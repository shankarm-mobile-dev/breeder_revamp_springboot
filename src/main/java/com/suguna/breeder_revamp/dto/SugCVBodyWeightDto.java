package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class SugCVBodyWeightDto {
    @JsonProperty("device_id")
    public long device_id;

    @JsonProperty("emp_code")
    public String emp_code;

    @JsonProperty("branch_id")
    public long branch_id;

    @JsonProperty("inventory_location_id")
    public long inventory_location_id;

    @JsonProperty("location")
    public String location;

    @JsonProperty("txn_header_id")
    public BigDecimal txn_header_id;

    @JsonProperty("bird_type")
    public String bird_type;

    @JsonProperty("txn_date")
    public String txn_date;

    @JsonProperty("min_weight")
    public BigDecimal min_weight;

    @JsonProperty("max_weight")
    public BigDecimal max_weight;

    @JsonProperty("increment_value")
    public BigDecimal increment_value;

    @JsonProperty("age")
    public long age;

    @JsonProperty("std_bodywt")
    public BigDecimal std_bodywt;

    @JsonProperty("act_bodywt")
    public BigDecimal act_bodywt;

    @JsonProperty("cv")
    public BigDecimal cv;

    @JsonProperty("below_std")
    public BigDecimal below_std;

    @JsonProperty("above_std")
    public BigDecimal above_std;

    @JsonProperty("within_std")
    public BigDecimal within_std;

    @JsonProperty("entry_creation_date")
    public String entry_creation_date;

    @JsonProperty("most_above_date")
    public BigDecimal most_above_date;

    @JsonProperty("most_below_date")
    public BigDecimal most_below_date;

    @JsonProperty("grading_no")
    public String grading_no;

    @JsonProperty("physical_shed_no")
    public String physical_shed_no;

    @JsonProperty("line_no")
    public String line_no;

    @JsonProperty("flock_no")
    public String flock_no;

    @JsonProperty("Details")
    ArrayList<SugCVBodyWeightDtlDto> Details;

    @Getter
    @Setter
    public static class SugCVBodyWeightDtlDto {
        @JsonProperty("device_id")
        public String device_id;

        @JsonProperty("emp_code")
        public String emp_code;

        @JsonProperty("branch_id")
        public BigDecimal branch_id;

        @JsonProperty("inventory_location_id")
        public BigDecimal inventory_location_id;

        @JsonProperty("txn_header_id")
        public BigDecimal txn_header_id;

        @JsonProperty("weight")
        public BigDecimal weight;

        @JsonProperty("no_of_birds")
        public long no_of_birds;

        @JsonProperty("entry_creation_date")
        public String entry_creation_date;

        @JsonProperty("bird_type")
        public String bird_type;

        @JsonProperty("line_no")
        public String line_no;

        @JsonProperty("grading_no")
        public String grading_no;

        @JsonProperty("flock_no")
        public String flock_no;

        @JsonProperty("physical_shed_no")
        public String physical_shed_no;

        @JsonProperty("age")
        public long age;


    }
}
