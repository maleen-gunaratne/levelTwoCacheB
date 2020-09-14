package com.cache.levelTwo.model;

import java.util.ArrayList;
import java.util.List;

public class CacheResponse {

    private List memoryCacheOrders;
    private List fileSystemCacheOrders;

    public List getMemoryCacheOrders() {
        return memoryCacheOrders;
    }

    public void setMemoryCacheOrders(ArrayList memoryCacheOrders) {
        this.memoryCacheOrders = memoryCacheOrders;
    }

    public List getFileSystemCacheOrders() {
        return fileSystemCacheOrders;
    }

    public void setFileSystemCacheOrders(ArrayList fileSystemCacheOrders) {
        this.fileSystemCacheOrders = fileSystemCacheOrders;
    }



}
