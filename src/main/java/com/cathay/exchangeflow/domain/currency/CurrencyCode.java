package com.cathay.exchangeflow.domain.currency;

import java.util.regex.Pattern;
import com.cathay.exchangeflow.core.MessageCode;
import com.cathay.exchangeflow.domain.common.DomainException;

public class CurrencyCode {

    private static final Pattern CURRENCY_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");

    private final String value;

    private CurrencyCode(String value) {
        this.value = value;
    }

    public static CurrencyCode of(String value) {
        if (value == null || value.isEmpty() || !CURRENCY_CODE_PATTERN.matcher(value).matches()) {
            throw new DomainException(MessageCode.of("currency.code.invalid"));
        }
        return new CurrencyCode(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CurrencyCode{" + "code='" + value + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CurrencyCode that = (CurrencyCode) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
