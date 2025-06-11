package com.cathay.exchangeflow.controller.exchangerate;

import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateResult;
import com.cathay.exchangeflow.core.DateTimeUtils;

public class RetrieveExchangeRateResponseDataBuilder {
    private RetrieveExchangeRateResult retrieveExchangeRateResult;

    public RetrieveExchangeRateResponseDataBuilder(
            RetrieveExchangeRateResult retrieveExchangeRateResult) {
        this.retrieveExchangeRateResult = retrieveExchangeRateResult;
    }

    public RetrieveExchangeRateResponseData build() {
        return new RetrieveExchangeRateResponseData(
                retrieveExchangeRateResult.getExchangeRates().stream()
                        .map(x -> new RetrieveExchangeRateResponseData.ExchangeRate(
                                x.getBaseCurrency(), x.getQuoteCurrency(), x.getRate().getValue(),
                                DateTimeUtils.format(x.getDateTime())))
                        .toList());
    }
}
