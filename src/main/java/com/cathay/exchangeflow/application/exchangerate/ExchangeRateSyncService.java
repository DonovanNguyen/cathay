package com.cathay.exchangeflow.application.exchangerate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.cathay.exchangeflow.application.exchangerate.exception.ExchangeRateRetrievalException;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateFetcher;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRatePair;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateRepository;
import jakarta.transaction.Transactional;

@Service
public class ExchangeRateSyncService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateSyncService.class);

    private final ExchangeRateFetcher exchangeRateFetcher;
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyRepository currencyRepository;

    public ExchangeRateSyncService(ExchangeRateFetcher exchangeRateFetcher,
            ExchangeRateRepository exchangeRateRepository, CurrencyRepository currencyRepository) {
        this.exchangeRateFetcher = exchangeRateFetcher;
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyRepository = currencyRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public void sync() {
        List<Currency> currencies = currencyRepository.findAll();
        if (currencies.isEmpty()) {
            return;
        }
        List<ExchangeRatePair> exchangeRatePairs = ExchangeRatePairGenerator.generate(currencies);
        LocalDate date = LocalDate.now();
        List<ExchangeRate> rates = new ArrayList<>();
        for (ExchangeRatePair pair : exchangeRatePairs) {
            try {
                rates.add(exchangeRateFetcher.retrieveExchangeRateByDate(pair.getBase(),
                        pair.getQuote(), date));
            } catch (ExchangeRateRetrievalException e) {
                logger.warn(e.getMessage());
            }
        }
        exchangeRateRepository.saveAll(rates);
    }
}
