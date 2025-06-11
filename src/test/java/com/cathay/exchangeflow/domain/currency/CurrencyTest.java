package com.cathay.exchangeflow.domain.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CurrencyTest {
    @Test
    void testOf() {
        // When:
        Currency currency = Currency.of(1L, "USD", "US Dollar", 2);

        // Then:
        assertEquals(1L, currency.getId());
        assertEquals("USD", currency.getCode().getValue());
        assertEquals("US Dollar", currency.getName());
        assertEquals(2, currency.getVersion().getValue());
    }

    @Test
    void testUpdate() {
        // Given:
        Currency currency = Currency.of(1L, "USD", "US Dollar", 2);

        // When:
        currency.update("EUR", "Euro");

        // Then:
        assertEquals(1L, currency.getId());
        assertEquals("EUR", currency.getCode().getValue());
        assertEquals("Euro", currency.getName());
        assertEquals(2, currency.getVersion().getValue());
    }
}
