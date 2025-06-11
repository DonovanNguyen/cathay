package com.cathay.exchangeflow.application.currency;

public class GetCurrencyCommand {
    private final Long id;

    public GetCurrencyCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
