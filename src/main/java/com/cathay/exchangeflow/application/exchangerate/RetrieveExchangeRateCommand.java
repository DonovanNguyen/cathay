package com.cathay.exchangeflow.application.exchangerate;

import java.time.LocalDate;

public class RetrieveExchangeRateCommand {

    private final String baseCurrency;
    private final String quoteCurrency;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public RetrieveExchangeRateCommand(String baseCurrency, String quoteCurrency,
            LocalDate startDate, LocalDate endDate) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
