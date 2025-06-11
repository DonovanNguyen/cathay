package com.cathay.exchangeflow.application.exchangerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.core.MessageCode;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateFetcher;
import com.cathay.exchangeflow.domain.exchangerate.Rate;

class RetrieveExchangeRateUseCaseTest {

    private ExchangeRateFetcher exchangeRateFetcher;
    private RetrieveExchangeRateValidator retrieveExchangeRateValidator;
    private RetrieveExchangeRateUseCase retrieveExchangeRateUseCase;

    @BeforeEach
    void setUp() {
        exchangeRateFetcher = mock(ExchangeRateFetcher.class);
        retrieveExchangeRateValidator = mock(RetrieveExchangeRateValidator.class);
        retrieveExchangeRateUseCase =
                new RetrieveExchangeRateUseCase(exchangeRateFetcher, retrieveExchangeRateValidator);
    }

    @Test
    void testExecute_returnsExchangeRates() {
        RetrieveExchangeRateCommand command = new RetrieveExchangeRateCommand("USD", "EUR",
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 10));

        ExchangeRate mockRate = ExchangeRate.of("USD", "EUR", LocalDateTime.of(2025, 6, 10, 12, 0),
                Rate.of(new BigDecimal("1.22"), new BigDecimal("1.44")));
        when(exchangeRateFetcher.retrieveExchangeRateByDateRange("USD", "EUR",
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 10)))
                        .thenReturn(List.of(mockRate));

        RetrieveExchangeRateResult result = retrieveExchangeRateUseCase.execute(command);

        assertNotNull(result);
        List<ExchangeRate> rates = result.getExchangeRates();
        assertEquals(1, rates.size());
        ExchangeRate rate = rates.get(0);
        assertEquals("USD", rate.getBaseCurrency());
        assertEquals("EUR", rate.getQuoteCurrency());
        assertEquals(new BigDecimal("1.33"), rate.getRate().getValue());
        assertEquals(LocalDateTime.of(2025, 6, 10, 12, 0), rate.getDateTime());
    }

    @Test
    void testExecute_returnsEmptyListWhenNoRates() {
        RetrieveExchangeRateCommand command = new RetrieveExchangeRateCommand("USD", "JPY",
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 11));
        when(exchangeRateFetcher.retrieveExchangeRateByDateRange("USD", "JPY",
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 11)))
                        .thenReturn(Collections.emptyList());
        RetrieveExchangeRateResult result = retrieveExchangeRateUseCase.execute(command);

        assertNotNull(result);
        assertEquals(0, result.getExchangeRates().size());
    }

    @Test
    void testExecute_validatorThrowsException() {
        RetrieveExchangeRateCommand command = new RetrieveExchangeRateCommand("USD", "EUR",
                LocalDate.of(2025, 6, 12), LocalDate.of(2025, 6, 10));

        retrieveExchangeRateValidator.validate(command.getStartDate(), command.getEndDate());

        doThrow(new ApplicationException(MessageCode.of("exchange-rate.date.invalid-range")))
                .when(retrieveExchangeRateValidator)
                .validate(command.getStartDate(), command.getEndDate());


        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> retrieveExchangeRateUseCase.execute(command));
        assertEquals("exchange-rate.date.invalid-range", ex.getMessageCodes().get(0).value());
        verify(exchangeRateFetcher, never()).retrieveExchangeRateByDateRange(any(), any(), any(),
                any());
    }

    @Test
    void testExecute_multipleRatesReturned() {
        RetrieveExchangeRateCommand command = new RetrieveExchangeRateCommand("USD", "EUR",
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 11));

        ExchangeRate rate1 = ExchangeRate.of("USD", "EUR", LocalDateTime.of(2025, 6, 10, 12, 0),
                Rate.of(new BigDecimal("1.10"), new BigDecimal("1.20")));
        ExchangeRate rate2 = ExchangeRate.of("USD", "EUR", LocalDateTime.of(2025, 6, 11, 12, 0),
                Rate.of(new BigDecimal("1.15"), new BigDecimal("1.25")));

        when(exchangeRateFetcher.retrieveExchangeRateByDateRange("USD", "EUR",
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 11)))
                        .thenReturn(List.of(rate1, rate2));

        RetrieveExchangeRateResult result = retrieveExchangeRateUseCase.execute(command);

        assertNotNull(result);
        List<ExchangeRate> rates = result.getExchangeRates();
        assertEquals(2, rates.size());
        assertEquals(LocalDateTime.of(2025, 6, 10, 12, 0), rates.get(0).getDateTime());
        assertEquals(LocalDateTime.of(2025, 6, 11, 12, 0), rates.get(1).getDateTime());
    }
}
