package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FramCardTransactionDetailsDto {

    @Column(name = "OUT_PASS_NO", type = String.class)
    @JsonProperty("outPassNo")
    private String outPassNo;

    @Column(name = "TXN_TYPE", type = String.class)
    @JsonProperty("txnType")
    private String txnType;


    @Column(name = "ITEM_DESC", type = String.class)
    @JsonProperty("itemDesc")
    private String itemDesc;

    @Column(name = "QTY", type = String.class)
    @JsonProperty("qty")
    private String qty;

    @Column(name = "UOM", type = String.class)
    @JsonProperty("uom")
    private String uom;

    @Column(name = "VEHICLE_NO", type = String.class)
    @JsonProperty("vehicleNo")
    private String vehicleNo;

    @Column(name = "TRANS_TYPE", type = String.class)
    @JsonProperty("transType")
    private String transType;

}
