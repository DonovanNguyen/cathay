package com.cathay.exchangeflow.controller.currency;

import com.cathay.exchangeflow.application.currency.CurrencyListResult;

public class GetCurrencyListResponseBuilder {

    private CurrencyListResult result;

    public GetCurrencyListResponseBuilder(CurrencyListResult result) {
        this.result = result;
    }

    public GetCurrencyListResponse build() {
        return new GetCurrencyListResponse(
                result.getCurrencies().stream()
                        .map(c -> new GetCurrencyListResponse.Currency(c.getId(),
                                c.getCode().getValue(), c.getName(), c.getVersion().getValue()))
                        .toList());
    }
}
