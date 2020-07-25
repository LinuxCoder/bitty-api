package com.levoncorp.bitty.storage;

public interface EntityDtoCache<T> {
    void addToCache(T btcValueDto);

    void updateCache();
}
