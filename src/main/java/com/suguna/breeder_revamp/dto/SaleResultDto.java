package com.suguna.breeder_revamp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

public class SaleResultDto {
    public static class customerdetails {
        @Column(name = "customer_ID",type = String.class)
        @JsonProperty("customer_ID")
        public String customer_ID;

        @Column(name = "customer_NAME",type = String.class)
        @JsonProperty("customer_NAME")
        public String customer_NAME;
    }
    public static class vehicleno{
        @Column(name = "vehicle_NO",type = String.class)
        @JsonProperty("vehicle_NO")
        public String vehicle_NO;
    }
    public static class orderdetails{
        @Column(name = "customer_ID",type = String.class)
        @JsonProperty("customer_ID")
        public String customer_ID;

        @Column(name = "CUSTOMER_NAME",type = String.class)
        @JsonProperty("CUSTOMER_NAME")
        public String CUSTOMER_NAME;

        @Column(name = "shortname",type = String.class)
        @JsonProperty("shortname")
        public String shortname ;

        @Column(name = "order_NUMBER",type = String.class)
        @JsonProperty("order_NUMBER")
        public String order_NUMBER;

        @Column(name = "location",type = String.class)
        @JsonProperty("location")
        public String location;

        @Column(name = "ordered_QUANTITY",type = String.class)
        @JsonProperty("ordered_QUANTITY")
        public String ordered_QUANTITY;

        @Column(name = "header_ID",type = String.class)
        @JsonProperty("header_ID")
        public String header_ID ;

        @Column(name = "line_ID",type = String.class)
        @JsonProperty("line_ID")
        public String line_ID;

        @Column(name = "inventory_ITEM_ID",type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID;

        @Column(name = "item_CODE",type = String.class)
        @JsonProperty("item_CODE")
        public String item_CODE;

        @Column(name = "item_NAME",type = String.class)
        @JsonProperty("item_NAME")
        public String item_NAME ;

        @Column(name = "order_QUANTITY_UOM",type = String.class)
        @JsonProperty("order_QUANTITY_UOM")
        public String order_QUANTITY_UOM;

        @Column(name = "ordered_QUANTITY_UOM2",type = String.class)
        @JsonProperty("ordered_QUANTITY_UOM2")
        public String ordered_QUANTITY_UOM2;

        @Column(name = "ordered_QUANTITY2",type = String.class)
        @JsonProperty("ordered_QUANTITY2")
        public String ordered_QUANTITY2;
    }
    public static class onhandculleggstock{
        @Column(name = "lot_NUMBER",type = String.class)
        @JsonProperty("lot_NUMBER")
        public String lot_NUMBER;

        @Column(name = "organization_ID",type = String.class)
        @JsonProperty("organization_ID")
        public String organization_ID;

        @Column(name = "subinventory_CODE",type = String.class)
        @JsonProperty("subinventory_CODE")
        public String subinventory_CODE ;

        @Column(name = "locator_ID",type = String.class)
        @JsonProperty("locator_ID")
        public String locator_ID;

        @Column(name = "item_CODE",type = String.class)
        @JsonProperty("item_CODE")
        public String item_CODE;

        @Column(name = "inventory_ITEM_ID",type = String.class)
        @JsonProperty("inventory_ITEM_ID")
        public String inventory_ITEM_ID ;

        @Column(name = "transaction_DATE",type = String.class)
        @JsonProperty("transaction_DATE")
        public String transaction_DATE ;

        @Column(name = "primary_QTY_NORESERVED",type = String.class)
        @JsonProperty("primary_QTY_NORESERVED")
        public String primary_QTY_NORESERVED;

        @Column(name = "sec_QTY",type = String.class)
        @JsonProperty("sec_QTY")
        public String sec_QTY;

        @Column(name = "conversion_RATE",type = String.class)
        @JsonProperty("conversion_RATE")
        public String conversion_RATE;

        @Column(name = "primary_QTY",type = String.class)
        @JsonProperty("primary_QTY")
        public String primary_QTY ;
    }
    public static class despatchtime{
        @Column(name = "despatchtime",type = String.class)
        @JsonProperty("despatchtime")
        public String despatchtime ;
    }
}
