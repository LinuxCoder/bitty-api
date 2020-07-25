package com.levoncorp.bitty.database.service;

import com.levoncorp.bitty.forecast.request.CurrencyChecker;
import com.levoncorp.bitty.database.entity.BtcValue;
import com.levoncorp.bitty.database.repository.BtcValuePredictionRepository;
import com.levoncorp.bitty.database.repository.BtcValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("prod")
public class BtcValueUpdater implements CurrencyValueUpdater<BtcValue> {
    @Autowired
    private CurrencyChecker btcChecker;

    @Autowired
    private BtcValueRepository btcValueRepository;

    @Autowired
    private BtcValuePredictionRepository btcValuePredictionRepository;

    @Override
    @Scheduled(fixedDelay = 1000*60*60)
    public void updateCurrencyValue() {
        try {
            double currentBtcValue = btcChecker.currentValue();
            long lastUpdatedTime = System.currentTimeMillis();

            BtcValue btcValue = new BtcValue(currentBtcValue, lastUpdatedTime);
            btcValueRepository.save(btcValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
