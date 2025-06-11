package com.cathay.exchangeflow.domain.currency;

import java.util.List;
import com.cathay.exchangeflow.core.MessageCode;
import com.cathay.exchangeflow.domain.common.Constraint;
import com.cathay.exchangeflow.domain.common.DomainException;

public class AddCurrencyConstraint {

    public static Constraint currencyCodeMustBeUnique(Currency currency,
            List<Currency> existingCurrencies) {
        return new Constraint(() -> {
            if (existingCurrencies.stream().anyMatch(
                    existingCurrency -> existingCurrency.getCode().equals(currency.getCode()))) {
                throw new DomainException(MessageCode.of("currency.code.duplicated"));
            }
            return null;
        });
    }
}
