package com.levoncorp.bitty.storage;

import com.levoncorp.bitty.database.entity.BtcValue;
import com.levoncorp.bitty.database.service.CurrencyValueService;
import com.levoncorp.bitty.storage.dto.BtcValueDto;
import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;
import com.levoncorp.bitty.springcontext.annotation.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Component
public class BtcValueCache implements CurrencyEntityDtoCache<BtcValueDto>, BtcValueCacheMBean {
    @Autowired
    private CurrencyValueService<BtcValue> btcValueService;

    @Autowired
    private InterjacentBtcValuePredictionStorage interjacentStorage;

    private final int MAX_BTC_VALUE_CACHE_SIZE = 1000;

    private ConcurrentSkipListSet<BtcValueDto> btcValues =
            new ConcurrentSkipListSet<>(Comparator.comparing(BtcValueDto::getTime));

    @Override
    @Scheduled(fixedDelay = 1000 * 60 * 2)
    public void updateCache() {
        addToCache(new BtcValueDto(btcValueService.last()));
        try {
            putOnPrediction(currentValue().getTime() + 1000 * 60 * 60);
        } catch (EmptyCacheException ignored) {}
    }

    @Override
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void synchronize() {
        recreateCache();
    }

    public void putOnPrediction(long time) {
        BtcValuePredictionDto bvpDto = new BtcValuePredictionDto(time);
        interjacentStorage.add(bvpDto);
    }

    @PostProxy
    public void recreateCache() {
        ConcurrentSkipListSet<BtcValueDto> tmp = new ConcurrentSkipListSet<>(btcValues.comparator());
        btcValueService.last(MAX_BTC_VALUE_CACHE_SIZE)
                .stream().map(BtcValueDto::new)
                .sorted(Comparator.comparing(BtcValueDto::getTime)).forEach(tmp::add);
        btcValues = tmp;
    }

    @Override
    public List<BtcValueDto> listFromTime(long time) {
        assert btcValues.comparator() != null;
        return btcValues.stream()
                  .filter(btcVal -> btcVal.getTime() >= time)
                .sorted(btcValues.comparator())
                .collect(Collectors.toList());
    }

    @Override
    public void addToCache(BtcValueDto btcValueDto) {
        if (btcValues.size() >= MAX_BTC_VALUE_CACHE_SIZE)
            btcValues.remove(btcValues.first());
        btcValues.add(btcValueDto);
    }

    @Override
    public BtcValueDto currentValue() throws EmptyCacheException {
        if (btcValues.size() > 0)
            return btcValues.last();
        else
            throw new EmptyCacheException();
    }
}
