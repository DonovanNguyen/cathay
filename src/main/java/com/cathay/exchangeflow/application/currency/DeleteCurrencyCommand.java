package com.cathay.exchangeflow.application.currency;

public class DeleteCurrencyCommand {
    private final long id;

    public DeleteCurrencyCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
