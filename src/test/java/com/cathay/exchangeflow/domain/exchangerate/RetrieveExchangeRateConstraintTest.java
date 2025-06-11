package com.cathay.exchangeflow.domain.exchangerate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.domain.common.DomainException;

class RetrieveExchangeRateConstraintTest {

    @Test
    void testDateRangeMustBeValid_whenStartBeforeOrEqualEnd_shouldNotThrow() {
        LocalDate start = LocalDate.of(2025, 6, 10);
        LocalDate end = LocalDate.of(2025, 6, 11);

        assertDoesNotThrow(() -> RetrieveExchangeRateConstraint.dateRangeMustBeValid(start, end)
                .violatesThenThrow());

        // Also test when start equals end
        assertDoesNotThrow(() -> RetrieveExchangeRateConstraint.dateRangeMustBeValid(start, start)
                .violatesThenThrow());
    }

    @Test
    void testDateRangeMustBeValid_whenStartAfterEnd_shouldThrowDomainException() {
        LocalDate start = LocalDate.of(2025, 6, 12);
        LocalDate end = LocalDate.of(2025, 6, 11);

        DomainException ex =
                assertThrows(DomainException.class, () -> RetrieveExchangeRateConstraint
                        .dateRangeMustBeValid(start, end).violatesThenThrow());
        assertEquals("exchange-rate.date.invalid-range", ex.getMessageCode().value());
    }
}
