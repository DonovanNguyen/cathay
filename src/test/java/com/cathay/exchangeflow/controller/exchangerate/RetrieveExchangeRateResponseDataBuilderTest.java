package com.cathay.exchangeflow.controller.exchangerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateResult;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.Rate;

class RetrieveExchangeRateResponseDataBuilderTest {
    @Test
    void testBuild() {
        ExchangeRate rate1 = ExchangeRate.of("USD", "EUR", LocalDateTime.of(2025, 6, 10, 12, 0, 0),
                Rate.of(new BigDecimal("1.11"), new BigDecimal("1.33")));
        ExchangeRate rate2 = ExchangeRate.of("USD", "AUD", LocalDateTime.of(2025, 6, 10, 13, 0, 0),
                Rate.of(new BigDecimal("1.22"), new BigDecimal("1.44")));
        RetrieveExchangeRateResult result = new RetrieveExchangeRateResult(List.of(rate1, rate2));

        RetrieveExchangeRateResponseDataBuilder builder =
                new RetrieveExchangeRateResponseDataBuilder(result);
        RetrieveExchangeRateResponseData response = builder.build();

        assertNotNull(response);
        assertEquals(2, response.getExchangeRates().size());

        RetrieveExchangeRateResponseData.ExchangeRate resp1 = response.getExchangeRates().get(0);
        assertEquals("USD", resp1.getBase());
        assertEquals("EUR", resp1.getQuote());
        assertEquals(new BigDecimal("1.22"), resp1.getRate());
        assertEquals("2025/06/10 12:00:00", resp1.getDateTime());

        RetrieveExchangeRateResponseData.ExchangeRate resp2 = response.getExchangeRates().get(1);
        assertEquals("USD", resp2.getBase());
        assertEquals("AUD", resp2.getQuote());
        assertEquals(new BigDecimal("1.33"), resp2.getRate());
        assertEquals("2025/06/10 13:00:00", resp2.getDateTime());
    }
}
