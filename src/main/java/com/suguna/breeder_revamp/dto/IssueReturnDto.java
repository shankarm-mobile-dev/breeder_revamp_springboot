package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;

public class IssueReturnDto {

    @Column(name = "report_ID", type = String.class)
    @JsonProperty("report_ID")
    public String report_ID;
    @Column(name = "trans_TYPE", type = String.class)
    @JsonProperty("trans_TYPE")
    public String trans_TYPE;

    @Column(name = "trans_DATE", type = String.class)
    @JsonProperty("trans_DATE")
    public String trans_DATE;
    @Column(name = "inventory_ITEM_ID", type = String.class)
    @JsonProperty("inventory_ITEM_ID")
    public String inventory_ITEM_ID;

    @Column(name = "uom", type = String.class)
    @JsonProperty("uom")
    public String uom;
    @Column(name = "trans_QTY", type = String.class)
    @JsonProperty("trans_QTY")
    public String trans_QTY;

    @Column(name = "description", type = String.class)
    @JsonProperty("description")
    public String description  ;

    @Column(name = "inventory_ITEM_CODE", type = String.class)
    @JsonProperty("inventory_ITEM_CODE")
    public String inventory_ITEM_CODE;
    @Column(name = "location", type = String.class)
    @JsonProperty("location")
    public String location ;

    @Column(name = "branch_ID", type = String.class)
    @JsonProperty("branch_ID")
    public String branch_ID;

    @Column(name = "bird_TYPE", type = String.class)
    @JsonProperty("bird_TYPE")
    public String bird_TYPE ;
}
