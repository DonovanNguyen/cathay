package com.cathay.exchangeflow.application.exchangerate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.application.exception.ApplicationException;

class RetrieveExchangeRateValidatorTest {

    private final RetrieveExchangeRateValidator validator = new RetrieveExchangeRateValidator();

    @Test
    void testValidate_validDateRange_shouldNotThrow() {
        LocalDate start = LocalDate.of(2025, 6, 10);
        LocalDate end = LocalDate.of(2025, 6, 11);

        assertDoesNotThrow(() -> validator.validate(start, end));
        assertDoesNotThrow(() -> validator.validate(start, start));
    }

    @Test
    void testValidate_invalidDateRange_shouldThrowApplicationException() {
        LocalDate start = LocalDate.of(2025, 6, 12);
        LocalDate end = LocalDate.of(2025, 6, 11);

        ApplicationException ex =
                assertThrows(ApplicationException.class, () -> validator.validate(start, end));
        assertEquals("exchange-rate.date.invalid-range", ex.getMessageCodes().get(0).value());
    }
}
