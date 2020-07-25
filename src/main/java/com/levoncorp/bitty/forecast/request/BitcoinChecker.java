package com.levoncorp.bitty.forecast.request;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service("bitcoinChecker")
public class BitcoinChecker implements CurrencyChecker {
    @Autowired
    private CurrencyApiProperties currencyApiProperties;

    @Override
    public double currentValue() throws IOException {
        String responseJson = makeRequest(currencyApiProperties.getBtcApi());
        Map<String, Object> responseMap = new Gson().fromJson(responseJson, Map.class);
        Map<String, Object> btcInUsd = (Map<String, Object>)responseMap.get("USD");
        double value = (Double)btcInUsd.get("last");
        return value;
    }
}
