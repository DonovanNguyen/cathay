package com.cathay.exchangeflow.application.exchangerate;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateFetcher;

@Service
public class RetrieveExchangeRateUseCase {
    private final ExchangeRateFetcher exchangeRateFetcher;
    private final RetrieveExchangeRateValidator retrieveExchangeRateValidator;

    public RetrieveExchangeRateUseCase(ExchangeRateFetcher exchangeRateFetcher,
            RetrieveExchangeRateValidator retrieveExchangeRateValidator) {
        this.exchangeRateFetcher = exchangeRateFetcher;
        this.retrieveExchangeRateValidator = retrieveExchangeRateValidator;
    }

    public RetrieveExchangeRateResult execute(RetrieveExchangeRateCommand command) {
        retrieveExchangeRateValidator.validate(command.getStartDate(), command.getEndDate());
        List<ExchangeRate> result =
                exchangeRateFetcher.retrieveExchangeRateByDateRange(command.getBaseCurrency(),
                        command.getQuoteCurrency(), command.getStartDate(), command.getEndDate());
        return new RetrieveExchangeRateResult(result);
    }
}
