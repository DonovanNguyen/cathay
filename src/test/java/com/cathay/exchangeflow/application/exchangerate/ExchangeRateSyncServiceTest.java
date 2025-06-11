package com.cathay.exchangeflow.application.exchangerate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyCode;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateFetcher;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateRepository;
import com.cathay.exchangeflow.domain.exchangerate.Rate;

class ExchangeRateSyncServiceTest {

    private ExchangeRateFetcher exchangeRateFetcher;
    private ExchangeRateRepository exchangeRateRepository;
    private CurrencyRepository currencyRepository;
    private ExchangeRateSyncService exchangeRateSyncService;

    @BeforeEach
    void setUp() {
        exchangeRateFetcher = mock(ExchangeRateFetcher.class);
        exchangeRateRepository = mock(ExchangeRateRepository.class);
        currencyRepository = mock(CurrencyRepository.class);
        exchangeRateSyncService = new ExchangeRateSyncService(exchangeRateFetcher,
                exchangeRateRepository, currencyRepository);
    }

    @Test
    void testSync_savesExchangeRates() {
        Currency usd = new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        Currency eur = new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(1));
        List<Currency> currencies = List.of(usd, eur);

        when(currencyRepository.findAll()).thenReturn(currencies);

        LocalDate today = LocalDate.now();
        ExchangeRate rate = ExchangeRate.of("USD", "EUR",
                LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 12, 0),
                Rate.of(new BigDecimal("1.22"), new BigDecimal("1.44")));
        when(exchangeRateFetcher.retrieveExchangeRateByDate(anyString(), anyString(), eq(today)))
                .thenReturn(rate);

        exchangeRateSyncService.sync();

        verify(exchangeRateRepository, atLeastOnce()).saveAll(any());
    }

    @Test
    void testSync_noCurrencies_doesNothing() {
        when(currencyRepository.findAll()).thenReturn(List.of());

        exchangeRateSyncService.sync();

        verify(exchangeRateRepository, never()).saveAll(any());
        verify(exchangeRateFetcher, never()).retrieveExchangeRateByDate(any(), any(), any());
    }
}
