package com.cathay.exchangeflow.domain.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.domain.common.DomainException;

class CurrencyCodeTest {
    @Test
    void testOf_validCode() {
        CurrencyCode code = CurrencyCode.of("USD");
        assertNotNull(code);
        assertEquals("USD", code.getValue());
    }

    @Test
    void testOf_lowercaseCodeThrowsException() {
        assertThrows(DomainException.class, () -> CurrencyCode.of("usd"));
    }

    @Test
    void testOf_invalidLengthThrowsException() {
        assertThrows(DomainException.class, () -> CurrencyCode.of("US"));
        assertThrows(DomainException.class, () -> CurrencyCode.of("USDE"));
    }

    @Test
    void testOf_nullOrEmptyThrowsException() {
        assertThrows(DomainException.class, () -> CurrencyCode.of(null));
        assertThrows(DomainException.class, () -> CurrencyCode.of(""));
    }

    @Test
    void testOf_nonAlphaThrowsException() {
        assertThrows(DomainException.class, () -> CurrencyCode.of("U$D"));
        assertThrows(DomainException.class, () -> CurrencyCode.of("12A"));
    }
}
