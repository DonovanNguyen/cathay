package com.cathay.exchangeflow.application.currency;

import java.util.List;
import org.springframework.stereotype.Component;
import com.cathay.exchangeflow.application.common.ValidationResult;
import com.cathay.exchangeflow.domain.currency.AddCurrencyConstraint;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@Component
public class AddCurrencyValidator {
    private final CurrencyRepository currencyRepository;

    public AddCurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void validate(Currency currency) {
        List<Currency> existingCurrencies = currencyRepository.findAll();
        new ValidationResult().validate(
                AddCurrencyConstraint.currencyCodeMustBeUnique(currency, existingCurrencies))
                .violatesThenThrow();
    }
}
