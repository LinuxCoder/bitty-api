package com.levoncorp.bitty.storage;

import com.levoncorp.bitty.database.entity.BtcValuePrediction;
import com.levoncorp.bitty.database.service.CurrencyValuePredictionService;
import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;
import com.levoncorp.bitty.springcontext.annotation.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Component
public class BtcValuePredictionCache implements CurrencyEntityDtoCache<BtcValuePredictionDto> {
    private final int MAX_BTC_VALUE_PREDICTION_CACHE_SIZE = 1000;

    @Autowired
    private CurrencyValuePredictionService<BtcValuePrediction> btcValuePredictionService;

    @Autowired
    private InterjacentBtcValuePredictionStorage interjacentStorage;

    private ConcurrentSkipListSet<BtcValuePredictionDto> btcValuePredictions
            = new ConcurrentSkipListSet<>(Comparator.comparingLong(BtcValuePredictionDto::getPredictionTime));

    @Override
    @Scheduled(fixedDelay = 1000 * 60 * 2)
    public void updateCache() {
        Iterator<BtcValuePredictionDto> iterator = interjacentStorage.getHandledBtcValuePredictions().iterator();
        while(iterator.hasNext()) {
            BtcValuePredictionDto prediction = iterator.next();
            addToCache(prediction);
            iterator.remove();
        }
    }

    @Override
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void synchronize() {
        recreateCache();
    }


    @PostProxy
    public void recreateCache() {
        ConcurrentSkipListSet<BtcValuePredictionDto> tmp =
                new ConcurrentSkipListSet<>(btcValuePredictions.comparator());
        btcValuePredictionService.last(MAX_BTC_VALUE_PREDICTION_CACHE_SIZE)
                .stream().map(BtcValuePredictionDto::new)
                .sorted(Comparator.comparing(BtcValuePredictionDto::getPredictionTime))
                .filter(o->o.getPredictionValue()!=null)
                .forEach(tmp::add);
        btcValuePredictions = tmp;
    }

    @Override
    public BtcValuePredictionDto currentValue() throws EmptyCacheException {
        if (btcValuePredictions.size() > 0)
            return btcValuePredictions.last();
        else
            throw new EmptyCacheException();
    }

    @Override
    public List<BtcValuePredictionDto> listFromTime(long time) {
        return btcValuePredictions.stream().filter(o->o.getPredictionTime() > time).collect(Collectors.toList());
    }

    @Override
    public void addToCache(BtcValuePredictionDto btcValuePredictionDto) {
        if (btcValuePredictionDto.getPredictionValue() != null) {
            btcValuePredictions.add(btcValuePredictionDto);
            if (btcValuePredictions.size() > MAX_BTC_VALUE_PREDICTION_CACHE_SIZE)
                btcValuePredictions.remove(btcValuePredictions.first());
        }
    }
}
