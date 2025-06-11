package com.cathay.exchangeflow.application.currency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cathay.exchangeflow.application.exception.DataNotFoundException;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@Service
public class GetCurrencyUseCase {
    private final CurrencyRepository currencyRepository;

    public GetCurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional(readOnly = true)
    public Currency execute(GetCurrencyCommand command) {
        return currencyRepository.findById(command.getId())
                .orElseThrow(() -> new DataNotFoundException());
    }

}
