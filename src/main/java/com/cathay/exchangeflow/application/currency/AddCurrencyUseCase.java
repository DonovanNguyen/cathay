package com.cathay.exchangeflow.application.currency;

import org.springframework.stereotype.Service;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.common.DomainException;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyCode;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;
import jakarta.transaction.Transactional;

@Service
public class AddCurrencyUseCase {
    private final CurrencyRepository currencyRepository;
    private final AddCurrencyValidator addCurrencyValidator;

    public AddCurrencyUseCase(CurrencyRepository currencyRepository,
            AddCurrencyValidator addCurrencyValidator) {
        this.currencyRepository = currencyRepository;
        this.addCurrencyValidator = addCurrencyValidator;
    }

    /**
     * Executes the command to add a new currency.
     *
     * @param command the command containing the details of the currency to be added
     */
    @Transactional(rollbackOn = Exception.class)
    public void execute(AddCurrencyCommand command) {
        try {
            Currency currency = new Currency(null, CurrencyCode.of(command.getCurrencyCode()),
                    command.getCurrencyName(), Version.INIT_VERSION);
            addCurrencyValidator.validate(currency);
            currencyRepository.add(currency);
        } catch (DomainException e) {
            throw new ApplicationException(e.getMessageCode());
        }
    }

}
