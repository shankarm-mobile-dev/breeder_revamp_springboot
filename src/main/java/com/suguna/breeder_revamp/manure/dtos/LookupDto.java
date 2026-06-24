/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 15/3/2025
 */

package com.suguna.breeder_revamp.manure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupDto {
    @JsonProperty("meaning")
    String meaning;
    @JsonProperty("lookup_code")
    int lookupCode;
    @JsonProperty("description")
    String description;
    @JsonProperty("tag")
    String tag;

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(int lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

