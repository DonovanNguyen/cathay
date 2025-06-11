package com.cathay.exchangeflow.application.currency;

import java.util.List;
import org.springframework.stereotype.Component;
import com.cathay.exchangeflow.application.common.ValidationResult;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;
import com.cathay.exchangeflow.domain.currency.EditCurrencyConstraint;

@Component
public class EditCurrencyValidator {
    private final CurrencyRepository currencyRepository;

    public EditCurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void validate(Currency currency) {
        List<Currency> existingCurrencies = currencyRepository.findAll();
        new ValidationResult().validate(
                EditCurrencyConstraint.currencyCodeMustBeUnique(currency, existingCurrencies))
                .violatesThenThrow();
    }

}
