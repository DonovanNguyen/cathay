package com.cathay.exchangeflow.controller.exchangerate;

import java.math.BigDecimal;
import java.util.List;

public class RetrieveExchangeRateResponseData {

    private List<ExchangeRate> exchangeRates;

    public RetrieveExchangeRateResponseData(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public static class ExchangeRate {
        private final String base;
        private final String quote;
        private final BigDecimal rate;
        private final String dateTime;

        public ExchangeRate(String base, String quote, BigDecimal rate, String dateTime) {
            this.base = base;
            this.quote = quote;
            this.rate = rate;
            this.dateTime = dateTime;
        }

        public String getBase() {
            return base;
        }

        public String getQuote() {
            return quote;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public String getDateTime() {
            return dateTime;
        }

    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

}
