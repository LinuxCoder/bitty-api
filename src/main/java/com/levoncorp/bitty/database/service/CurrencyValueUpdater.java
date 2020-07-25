package com.levoncorp.bitty.database.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface CurrencyValueUpdater<T> {
    void updateCurrencyValue();
}