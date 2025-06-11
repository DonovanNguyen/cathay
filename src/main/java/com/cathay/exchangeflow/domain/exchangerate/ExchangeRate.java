package com.cathay.exchangeflow.domain.exchangerate;

import java.time.LocalDateTime;

public class ExchangeRate {

    private String baseCurrency;
    private String quoteCurrency;
    private LocalDateTime dateTime;
    private Rate rate;

    public ExchangeRate(String baseCurrency, String quoteCurrency, LocalDateTime dateTime,
            Rate rate) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.dateTime = dateTime;
        this.rate = rate;
    }

    public static ExchangeRate of(String baseCurrency, String quoteCurrency, LocalDateTime dateTime,
            Rate rate) {
        return new ExchangeRate(baseCurrency, quoteCurrency, dateTime, rate);
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Rate getRate() {
        return rate;
    }

}
