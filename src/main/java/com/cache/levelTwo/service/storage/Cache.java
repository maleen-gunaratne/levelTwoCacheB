package com.cache.levelTwo.service.storage;

import java.util.List;

public  interface Cache<K, V> {

    void putIntoCache(K key, V value);

    V getFromCache(K key);

    void deleteFromCache(K key);

    int getCacheSize();

    boolean isObjectContained(K key);

    boolean hasFreeSpace();

    void clearCache();

    List<K> getAll();



}
