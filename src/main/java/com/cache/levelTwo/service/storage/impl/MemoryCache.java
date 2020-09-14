package com.cache.levelTwo.service.storage.impl;

import com.cache.levelTwo.service.storage.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

class MemoryCache<K extends Serializable, V extends Serializable> implements Cache<K, V> {
    private static final Logger LOG = LoggerFactory.getLogger(MemoryCache.class);

    private final Map<K, V> objectsRepository;
    private final int repositorySize;

    MemoryCache(int repositorySize) {
        this.repositorySize = repositorySize;
        this.objectsRepository = new ConcurrentHashMap<>(repositorySize);
    }

     public List<K> getAll() {
        List<K> valuesToMatch  = new ArrayList<K>((Collection<? extends K>)objectsRepository. values());
        return valuesToMatch;
    }

    @Override
    public V getFromCache(K key) {
        LOG.debug(format("Get an object with key '%s' from memory cache", key));
        return objectsRepository.get(key);
    }

    @Override
    public void putIntoCache(K key, V value) {
        LOG.debug(format("Put an object with key '%s' into memory cache", key));
        objectsRepository.put(key, value);
    }

    @Override
    public void deleteFromCache(K key) {
        LOG.debug(format("Delete an object with key '%s' from memory cache", key));
        objectsRepository.remove(key);
    }

    @Override
    public int getCacheSize() {
        return objectsRepository.size();
    }

    @Override
    public boolean isObjectContained(K key)
    {
        try {
          return  objectsRepository.containsKey(key);
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean hasFreeSpace() {
        return getCacheSize() < repositorySize;
    }

    @Override
    public void clearCache() {
        LOG.debug(format("Delete %d object(s) from memory cache", getCacheSize()));
        objectsRepository.clear();
    }
}
