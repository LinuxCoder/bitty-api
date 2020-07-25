package com.levoncorp.bitty.forecast.request;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "currency")
public class CurrencyApiProperties {
    private String btcApi;

    public void setBtcApi(String btcApi) {
        this.btcApi = btcApi;
    }

    public String getBtcApi() {
        return btcApi;
    }
}
