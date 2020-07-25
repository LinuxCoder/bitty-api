package com.levoncorp.bitty.database.service;

public interface EntityService<T> {
    void add(T val);
    void delete(long id);
    T findById(long id);
}
