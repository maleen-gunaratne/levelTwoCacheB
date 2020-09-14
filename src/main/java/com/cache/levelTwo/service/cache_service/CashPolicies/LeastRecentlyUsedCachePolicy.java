package com.cache.levelTwo.service.cache_service.CashPolicies;

public class LeastRecentlyUsedCachePolicy<K> extends CachePolicy<K> {
    @Override
    public void putObject(K key) {
        getRepository().put(key, System.nanoTime());
    }

    @Override
    public K getKeyToReplace() {
        refreshSortedRepository();
        return getSortedRepository().firstKey();
    }
}

