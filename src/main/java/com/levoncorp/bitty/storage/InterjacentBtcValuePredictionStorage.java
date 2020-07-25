package com.levoncorp.bitty.storage;

import com.levoncorp.bitty.database.entity.BtcValuePrediction;
import com.levoncorp.bitty.database.service.CurrencyValuePredictionService;
import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InterjacentBtcValuePredictionStorage {
    @Autowired
    private CurrencyValuePredictionService<BtcValuePrediction> btcValuePredictionService;

    private Set<BtcValuePredictionDto> unhandledBtcValuePredictions = new HashSet<>();

    private Set<BtcValuePredictionDto> handledBtcValuePredictions = new HashSet<>();

    public Set<BtcValuePredictionDto> getUnhandledBtcValuePredictions() {
        return unhandledBtcValuePredictions;
    }

    public void add(BtcValuePredictionDto bvp) {
        unhandledBtcValuePredictions.add(bvp);
    }

    public Set<BtcValuePredictionDto> getHandledBtcValuePredictions() {
        return handledBtcValuePredictions;
    }
}
