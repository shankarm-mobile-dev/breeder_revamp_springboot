package com.suguna.breeder_revamp.enums;

public enum FileStorageCategory {
    FEED("feed"),
    MORTALITY("mortality"),
    SHED_READY("shed_ready"),
    MEDICINE("medicine");


    private final String folder;


    FileStorageCategory(String folder) {
        this.folder = folder;
    }

    public String folder() {
        return folder;
    }
}
