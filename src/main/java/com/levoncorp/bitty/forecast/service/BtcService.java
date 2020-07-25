package com.levoncorp.bitty.forecast.service;

import com.levoncorp.bitty.database.entity.BtcValue;
import com.levoncorp.bitty.database.entity.BtcValuePrediction;
import com.levoncorp.bitty.database.service.CurrencyValuePredictionService;
import com.levoncorp.bitty.storage.CurrencyEntityDtoCache;
import com.levoncorp.bitty.storage.EmptyCacheException;
import com.levoncorp.bitty.database.service.CurrencyValueService;
import com.levoncorp.bitty.storage.dto.BtcValueDto;
import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("btcService")
public class BtcService implements CurrencyService<BtcValueDto> {
    @Autowired
    private CurrencyValueService<BtcValue> btcValueService;

    @Autowired
    private CurrencyValuePredictionService<BtcValuePrediction> btcValuePredictionService;

    @Autowired
    private CurrencyEntityDtoCache<BtcValueDto> btcValueCache;

    @Autowired
    private CurrencyEntityDtoCache<BtcValuePredictionDto> btcValuePredictionCache;

    @Override
    public Optional<BtcValueDto> currentValue() {
        Optional<BtcValueDto> currentValue;
        try {
            currentValue = Optional.ofNullable(btcValueCache.currentValue());
        }catch (EmptyCacheException e) {
            currentValue = Optional.empty();
        }
        return currentValue;
    }

    @Override
    public List<BtcValueDto> listValuesFromTime(long time) {
        return btcValueCache.listFromTime(time);
    }

    @Override
    public List<BtcValuePredictionDto> listPredictionsFromTime(long time) {
        return btcValuePredictionCache.listFromTime(time);
    }

    @Override
    public Optional<BtcValuePredictionDto> prediction() {
        Optional<BtcValuePredictionDto> prediction;
        try {
            prediction = Optional.ofNullable(btcValuePredictionCache.currentValue());
        } catch (EmptyCacheException e) {
            prediction = Optional.empty();
        }
        return prediction;
    }
}