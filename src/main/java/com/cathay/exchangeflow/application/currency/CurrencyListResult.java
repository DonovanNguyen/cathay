package com.cathay.exchangeflow.application.currency;

import java.util.List;
import com.cathay.exchangeflow.domain.currency.Currency;

public class CurrencyListResult {
    private final List<Currency> currencies;

    public CurrencyListResult(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

}
