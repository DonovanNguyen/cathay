package com.cathay.exchangeflow.application.currency;

public class EditCurrencyCommand {
    private final long id;
    private final String currencyCode;
    private final String currencyName;
    private final int version;

    public EditCurrencyCommand(long id, String currencyCode, String currencyName, int version) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getVersion() {
        return version;
    }

}
