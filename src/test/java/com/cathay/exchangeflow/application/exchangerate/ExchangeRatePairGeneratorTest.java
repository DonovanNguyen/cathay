package com.cathay.exchangeflow.application.exchangerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyCode;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRatePair;

class ExchangeRatePairGeneratorTest {
    @Test
    void testGenerate_AllOrderedPairs() {
        // Given:
        Currency usd = new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        Currency aud = new Currency(2L, CurrencyCode.of("AUD"), "Australian Dollar", Version.of(1));
        Currency jpy = new Currency(3L, CurrencyCode.of("JPY"), "Japanese Yen", Version.of(1));
        List<Currency> currencies = List.of(usd, aud, jpy);

        // When:
        List<ExchangeRatePair> pairs = ExchangeRatePairGenerator.generate(currencies);

        // Then:
        // There should be 6 pairs (3 currencies, 3*2 = 6 ordered pairs)
        assertEquals(6, pairs.size());
        assertTrue(pairs.contains(new ExchangeRatePair("USD", "AUD")));
        assertTrue(pairs.contains(new ExchangeRatePair("USD", "JPY")));
        assertTrue(pairs.contains(new ExchangeRatePair("AUD", "USD")));
        assertTrue(pairs.contains(new ExchangeRatePair("AUD", "JPY")));
        assertTrue(pairs.contains(new ExchangeRatePair("JPY", "USD")));
        assertTrue(pairs.contains(new ExchangeRatePair("JPY", "AUD")));

        // No self-pairs
        assertFalse(pairs.contains(new ExchangeRatePair("USD", "USD")));
        assertFalse(pairs.contains(new ExchangeRatePair("AUD", "AUD")));
        assertFalse(pairs.contains(new ExchangeRatePair("JPY", "JPY")));
    }
}
