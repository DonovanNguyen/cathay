package com.cathay.exchangeflow.application.currency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@Service
public class DeleteCurrencyUseCase {
    private final CurrencyRepository currencyRepository;

    public DeleteCurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(DeleteCurrencyCommand command) {
        currencyRepository.deleteById(command.getId());
    }

}
