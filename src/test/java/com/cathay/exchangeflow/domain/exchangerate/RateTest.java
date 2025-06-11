package com.cathay.exchangeflow.domain.exchangerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class RateTest {
    @Test
    void testOf_calculatesAverageAndRoundsToTwoDecimalPlaces() {
        BigDecimal averageBid = new BigDecimal("1.234");
        BigDecimal averageAsk = new BigDecimal("1.236");
        Rate rate = Rate.of(averageBid, averageAsk);

        // (1.234 + 1.236) / 2 = 1.235, rounded to 1.24 (HALF_UP)
        assertEquals(new BigDecimal("1.24"), rate.getValue());
    }

    @Test
    void testOf_exactAverage() {
        BigDecimal averageBid = new BigDecimal("2.00");
        BigDecimal averageAsk = new BigDecimal("4.00");
        Rate rate = Rate.of(averageBid, averageAsk);

        // (2.00 + 4.00) / 2 = 3.00
        assertEquals(new BigDecimal("3.00"), rate.getValue());
    }

    @Test
    void testOf_roundingDown() {
        BigDecimal averageBid = new BigDecimal("1.221");
        BigDecimal averageAsk = new BigDecimal("1.222");
        Rate rate = Rate.of(averageBid, averageAsk);

        // (1.221 + 1.222) / 2 = 1.2215, rounded to 1.22
        assertEquals(new BigDecimal("1.22"), rate.getValue());
    }

    @Test
    void testOf_roundingUp() {
        BigDecimal averageBid = new BigDecimal("1.225");
        BigDecimal averageAsk = new BigDecimal("1.225");
        Rate rate = Rate.of(averageBid, averageAsk);

        // (1.225 + 1.225) / 2 = 1.225, rounded to 1.23
        assertEquals(new BigDecimal("1.23"), rate.getValue());
    }
}
