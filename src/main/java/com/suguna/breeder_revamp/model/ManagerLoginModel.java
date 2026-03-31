package com.suguna.breeder_revamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerLoginModel {
    @JsonProperty("usercode")
    String userCode;
    @JsonProperty("password")
    String passWord;
}
