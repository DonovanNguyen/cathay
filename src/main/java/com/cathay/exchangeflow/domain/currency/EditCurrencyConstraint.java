package com.cathay.exchangeflow.domain.currency;

import java.util.List;
import com.cathay.exchangeflow.core.MessageCode;
import com.cathay.exchangeflow.domain.common.Constraint;
import com.cathay.exchangeflow.domain.common.DomainException;

public class EditCurrencyConstraint {

    public static Constraint currencyCodeMustBeUnique(Currency editedCurrency,
            List<Currency> existingCurrencies) {
        return new Constraint(() -> {
            if (existingCurrencies.stream()
                    .anyMatch(existingCurrency -> isCurrencyCodeDuplicated(editedCurrency,
                            existingCurrency))) {
                throw new DomainException(MessageCode.of("currency.code.duplicated"));
            }
            return null;
        });
    }

    private static boolean isCurrencyCodeDuplicated(Currency newCurrency,
            Currency existingCurrency) {
        return existingCurrency.getCode().equals(newCurrency.getCode())
                && !existingCurrency.getId().equals(newCurrency.getId());
    }

}
