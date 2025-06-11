package com.cathay.exchangeflow.controller.exchangerate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateCommand;
import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateResult;
import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateUseCase;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

    private final RetrieveExchangeRateUseCase retrieveExchangeRateUseCase;

    public ExchangeRateController(RetrieveExchangeRateUseCase retrieveExchangeRateUseCase) {
        this.retrieveExchangeRateUseCase = retrieveExchangeRateUseCase;
    }

    @GetMapping
    public RetrieveExchangeRateResponseData getExchangeRate(
            @Valid @ModelAttribute RetrieveExchangeRateRequestData requestData) {

        RetrieveExchangeRateCommand command = new RetrieveExchangeRateCommand(requestData.getBase(),
                requestData.getQuote(), requestData.getStartDate(), requestData.getEndDate());
        RetrieveExchangeRateResult result = retrieveExchangeRateUseCase.execute(command);
        return new RetrieveExchangeRateResponseDataBuilder(result).build();
    }
}
