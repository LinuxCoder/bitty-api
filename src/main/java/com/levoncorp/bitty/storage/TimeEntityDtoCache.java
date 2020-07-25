package com.levoncorp.bitty.storage;

import java.util.List;

public interface TimeEntityDtoCache<T> {
    List<T> listFromTime(long time);

    T currentValue() throws EmptyCacheException;
}
