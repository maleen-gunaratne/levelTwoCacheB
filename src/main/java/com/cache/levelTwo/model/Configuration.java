package com.cache.levelTwo.model;

public class Configuration {

    private String cacheType;
    private String memoryMaxSize;
    private String fileSystemMaxSize;

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getMemoryMaxSize() {
        return memoryMaxSize;
    }

    public void setMemoryMaxSize(String memoryMaxSize) {
        this.memoryMaxSize = memoryMaxSize;
    }

    public String getFileSystemMaxSize() {
        return fileSystemMaxSize;
    }

    public void setFileSystemMaxSize(String fileSystemMaxSize) {
        this.fileSystemMaxSize = fileSystemMaxSize;
    }





}
