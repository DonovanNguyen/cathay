package com.cathay.exchangeflow.infrastructure.external.oanda;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import com.cathay.exchangeflow.application.exchangerate.exception.ExchangeRateRetrievalException;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateFetcher;

@Component
public class ExchangeRateFetcherImpl implements ExchangeRateFetcher {

    private final OandaExchangeRateClient oandaExchangeRateClient;

    public ExchangeRateFetcherImpl(OandaExchangeRateClient oandaExchangeRateClient) {
        this.oandaExchangeRateClient = oandaExchangeRateClient;
    }

    @Override
    public ExchangeRate retrieveExchangeRateByDate(String base, String quote, LocalDate date) {
        return retrieveExchangeRateByDateRange(base, quote, date.minusDays(1), date).stream()
                .findFirst()
                .orElseThrow(() -> new ExchangeRateRetrievalException(base, quote, date));
    }

    @Override
    public List<ExchangeRate> retrieveExchangeRateByDateRange(String base, String quote,
            LocalDate startDate, LocalDate endDate) {
        return oandaExchangeRateClient.getExchangeRateByDateRange(base, quote, startDate, endDate)
                .stream().map(RetrieveExchangeRateDto::toExchangeRate).toList();
    }
}
