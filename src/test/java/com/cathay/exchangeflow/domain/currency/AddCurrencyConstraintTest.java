package com.cathay.exchangeflow.domain.currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.common.DomainException;

class AddCurrencyConstraintTest {
    @Test
    void testCurrencyCodeMustBeUnique_whenUnique_shouldNotThrow() {
        Currency newCurrency =
                new Currency(3L, CurrencyCode.of("GBP"), "British Pound", Version.of(1));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));

        assertDoesNotThrow(() -> AddCurrencyConstraint
                .currencyCodeMustBeUnique(newCurrency, existing).violatesThenThrow());
    }

    @Test
    void testCurrencyCodeMustBeUnique_whenDuplicated_shouldThrowDomainException() {
        Currency newCurrency = new Currency(3L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));

        DomainException ex = assertThrows(DomainException.class, () -> AddCurrencyConstraint
                .currencyCodeMustBeUnique(newCurrency, existing).violatesThenThrow());
        assertEquals("currency.code.duplicated", ex.getMessageCode().value());
    }
}
