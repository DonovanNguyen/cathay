package com.cathay.exchangeflow.domain.exchangerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ExchangeRateTest {
    @Test
    void testOf() {
        // Given:
        String base = "USD";
        String quote = "EUR";
        LocalDateTime dateTime = LocalDateTime.of(2024, 6, 10, 12, 0);
        BigDecimal averageBid = new BigDecimal("1.111111");
        BigDecimal averageAsk = new BigDecimal("1.333333");
        Rate rate = Rate.of(averageBid, averageAsk);

        // When:
        ExchangeRate exchangeRate = ExchangeRate.of(base, quote, dateTime, rate);

        // Then:
        assertEquals("USD", exchangeRate.getBaseCurrency());
        assertEquals("EUR", exchangeRate.getQuoteCurrency());
        assertEquals(LocalDateTime.of(2024, 6, 10, 12, 0), exchangeRate.getDateTime());
        assertEquals(new BigDecimal("1.22"), exchangeRate.getRate().getValue());
    }
}
