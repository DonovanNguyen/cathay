package com.cathay.exchangeflow.domain.currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.common.DomainException;

class EditCurrencyConstraintTest {
    @Test
    void testCurrencyCodeMustBeUnique_whenUnique_shouldNotThrow() {
        Currency editedCurrency =
                new Currency(2L, CurrencyCode.of("GBP"), "British Pound", Version.of(1));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(3L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));

        assertDoesNotThrow(() -> EditCurrencyConstraint
                .currencyCodeMustBeUnique(editedCurrency, existing).violatesThenThrow());
    }

    @Test
    void testCurrencyCodeMustBeUnique_whenSameId_shouldNotThrow() {
        // Editing currency with same code and same id (should not throw)
        Currency editedCurrency =
                new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));

        assertDoesNotThrow(() -> EditCurrencyConstraint
                .currencyCodeMustBeUnique(editedCurrency, existing).violatesThenThrow());
    }

    @Test
    void testCurrencyCodeMustBeUnique_whenDuplicated_shouldThrowDomainException() {
        // Editing currency with code that already exists for a different id
        Currency editedCurrency =
                new Currency(2L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(3L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));

        DomainException ex = assertThrows(DomainException.class, () -> EditCurrencyConstraint
                .currencyCodeMustBeUnique(editedCurrency, existing).violatesThenThrow());
        assertEquals("currency.code.duplicated", ex.getMessageCode().value());
    }
}
