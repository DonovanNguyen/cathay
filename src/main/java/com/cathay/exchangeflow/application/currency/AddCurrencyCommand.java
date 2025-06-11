package com.cathay.exchangeflow.application.currency;

public class AddCurrencyCommand {
    private final String currencyCode;
    private final String currencyName;

    public AddCurrencyCommand(String currencyCode, String currencyName) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

}
