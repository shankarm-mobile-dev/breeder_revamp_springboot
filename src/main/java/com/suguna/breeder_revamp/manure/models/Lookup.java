/*
 * Copyright (c) 2025 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 15/3/2025
 */

package com.suguna.breeder_revamp.manure.models;

public class Lookup {
    private String LOOKUP_CODE;
    private String MEANING;
    private String DESCRIPTION;
    private String TAG;

    public Lookup(){}


    public Lookup(String LOOKUP_CODE, String MEANING, String DESCRIPTION, String TAG) {
        this.LOOKUP_CODE = LOOKUP_CODE;
        this.MEANING = MEANING;
        this.DESCRIPTION = DESCRIPTION;
        this.TAG = TAG;
    }

    public String getLOOKUP_CODE() {
        return LOOKUP_CODE;
    }

    public void setLOOKUP_CODE(String LOOKUP_CODE) {
        this.LOOKUP_CODE = LOOKUP_CODE;
    }

    public String getMEANING() {
        return MEANING;
    }

    public void setMEANING(String MEANING) {
        this.MEANING = MEANING;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
}
