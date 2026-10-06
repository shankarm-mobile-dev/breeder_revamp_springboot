package com.suguna.breeder_revamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suguna.breeder_revamp.utils.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MortalitySavedBirdsDto {

    @Column(name = "SIDE_NO", type = String.class)
    @JsonProperty("sideNo")
    private String sideNo;

    @Column(name = "LINE_NO", type = String.class)
    @JsonProperty("lineNo")
    private String lineNo;

    @Column(name = "SEX", type = String.class)
    @JsonProperty("sex")
    private String sex;

    @Column(name = "QTY", type = String.class)
    @JsonProperty("qty")
    private String qty;

    @Column(name = "BIRD_TYPE", type = String.class)
    @JsonProperty("birdType")
    private String birdType;

}
