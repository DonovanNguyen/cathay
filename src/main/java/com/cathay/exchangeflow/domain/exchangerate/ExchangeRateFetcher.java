package com.cathay.exchangeflow.domain.exchangerate;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateFetcher {

    ExchangeRate retrieveExchangeRateByDate(String base, String quote, LocalDate date);

    List<ExchangeRate> retrieveExchangeRateByDateRange(String base, String quote,
            LocalDate startDate, LocalDate endDate);
}
