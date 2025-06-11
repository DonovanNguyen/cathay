package com.cathay.exchangeflow.infrastructure.external.oanda;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oanda")
public class OandaProperties {

    private String exchangeRateUrl;

    public String getExchangeRateUrl() {
        return exchangeRateUrl;
    }

    public void setExchangeRateUrl(String exchangeRateUrl) {
        this.exchangeRateUrl = exchangeRateUrl;
    }
}
