package com.cathay.exchangeflow.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {

    @Test
    void testFormat() {
        // Given:
        LocalDateTime dateTime = LocalDateTime.of(2025, 6, 10, 15, 30, 45);
        // When:
        String formatted = DateTimeUtils.format(dateTime);
        // Then:
        assertEquals("2025/06/10 15:30:45", formatted);
    }
}
