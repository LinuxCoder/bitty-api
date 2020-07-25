package com.levoncorp.bitty.database.service;

import java.util.List;

public interface TimeEntityService<T> {
    List<T> listFromTime(long timeInMillis);

    T last();

    List<T> last(int number);
}
