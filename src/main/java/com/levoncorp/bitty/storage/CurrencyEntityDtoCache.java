package com.levoncorp.bitty.storage;

import org.springframework.scheduling.annotation.Scheduled;

public interface CurrencyEntityDtoCache<T> extends EntityDtoCache<T>, TimeEntityDtoCache<T> {
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    void synchronize();
}
