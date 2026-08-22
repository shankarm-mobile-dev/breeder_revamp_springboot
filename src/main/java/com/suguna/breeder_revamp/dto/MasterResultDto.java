package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MasterResultDto<T> {

    @JsonProperty("RESULT")
    List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
    @JsonProperty("fvaluemst")
    ArrayList<fvalueeight> fvaluemst;

    @JsonProperty("itemmst")
    ArrayList<itemmaster> itemmst;

    @Getter
    @Setter
    public static class fvalueeight {
        @Column(name = "fvalue", type = String.class)
        @JsonProperty("fvalue")
        public String fvalue;
        @Column(name = "sample_size", type = String.class)
        @JsonProperty("sample_size")
        public String sample_size;

    }
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
