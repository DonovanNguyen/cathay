package com.cathay.exchangeflow.application.currency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.application.exception.DataNotFoundException;
import com.cathay.exchangeflow.domain.common.DomainException;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@Service
public class EditCurrencyUseCase {

    private final CurrencyRepository currencyRepository;
    private final EditCurrencyValidator editCurrencyValidator;

    public EditCurrencyUseCase(CurrencyRepository currencyRepository,
            EditCurrencyValidator editCurrencyValidator) {
        this.currencyRepository = currencyRepository;
        this.editCurrencyValidator = editCurrencyValidator;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(EditCurrencyCommand command) {
        Currency currency = currencyRepository.findById(command.getId())
                .orElseThrow(() -> new DataNotFoundException());
        try {
            currency.update(command.getCurrencyCode(), command.getCurrencyName());
        } catch (DomainException e) {
            throw new ApplicationException(e.getMessageCode());
        }
        editCurrencyValidator.validate(currency);
        currencyRepository.edit(currency);
    }

}
