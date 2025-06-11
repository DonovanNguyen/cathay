package com.cathay.exchangeflow.domain.exchangerate;

import java.util.Objects;

public class ExchangeRatePair {
    private final String base;
    private final String quote;

    public ExchangeRatePair(String base, String quote) {
        this.base = base;
        this.quote = quote;
    }

    public String getBase() {
        return base;
    }

    public String getQuote() {
        return quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExchangeRatePair that = (ExchangeRatePair) o;
        return Objects.equals(base, that.base) && Objects.equals(quote, that.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, quote);
    }
}
