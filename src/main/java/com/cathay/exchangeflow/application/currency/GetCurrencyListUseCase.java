package com.cathay.exchangeflow.application.currency;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@Service
public class GetCurrencyListUseCase {
    private final CurrencyRepository currencyRepository;

    public GetCurrencyListUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional(readOnly = true)
    public CurrencyListResult execute() {
        List<Currency> currencies = currencyRepository.findAll();
        return new CurrencyListResult(currencies);
    }

}
