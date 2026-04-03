package com.suguna.breeder_revamp.enums;

public enum FileStorageCategory {
    SHED_READY("shed_ready");


    private final String folder;


    FileStorageCategory(String folder) {
        this.folder = folder;
    }

    public String folder() {
        return folder;
    }
}
