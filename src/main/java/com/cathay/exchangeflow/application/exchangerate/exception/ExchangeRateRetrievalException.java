package com.cathay.exchangeflow.application.exchangerate.exception;

import java.time.LocalDate;

public class ExchangeRateRetrievalException extends RuntimeException {
    public ExchangeRateRetrievalException(String base, String quote, LocalDate date) {
        super("Failed to retrieve exchange rate for pair: " + base + "/" + quote + " on " + date);
    }
}
