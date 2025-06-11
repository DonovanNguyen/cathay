package com.cathay.exchangeflow.application.exchangerate;

import java.util.List;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;

public class RetrieveExchangeRateResult {
    private final List<ExchangeRate> exchangeRates;

    public RetrieveExchangeRateResult(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

}
