package com.levoncorp.bitty.forecast.service;

import com.levoncorp.bitty.storage.dto.BtcValueDto;
import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyService<T> {
    Optional<BtcValueDto> currentValue();

    List<BtcValueDto> listValuesFromTime(long time);

    List<BtcValuePredictionDto> listPredictionsFromTime(long time);

    Optional<BtcValuePredictionDto> prediction();
}
