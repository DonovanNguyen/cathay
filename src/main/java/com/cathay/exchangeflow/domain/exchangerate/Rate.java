package com.cathay.exchangeflow.domain.exchangerate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rate {

    private BigDecimal value;

    private Rate(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Rate of(BigDecimal averageBid, BigDecimal averageAsk) {
        return new Rate(averageBid.add(averageAsk).divide(BigDecimal.valueOf(2)));
    }

    public BigDecimal getValue() {
        return value;
    }
}
