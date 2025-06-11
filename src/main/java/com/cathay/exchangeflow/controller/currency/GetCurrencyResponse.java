package com.cathay.exchangeflow.controller.currency;

public class GetCurrencyResponse {
    private final Long id;
    private final String code;
    private final String name;
    private final Integer version;

    public GetCurrencyResponse(Long id, String code, String name, Integer version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }
}
