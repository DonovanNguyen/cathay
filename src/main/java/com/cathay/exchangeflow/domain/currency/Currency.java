package com.cathay.exchangeflow.domain.currency;

import com.cathay.exchangeflow.core.Version;

public class Currency {
    private final Long id;
    private CurrencyCode code;
    private String name;
    private Version version;

    public Currency(Long id, CurrencyCode code, String name, Version version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
    }

    public static Currency of(Long id, String code, String name, Integer version) {
        return new Currency(id, CurrencyCode.of(code), name, Version.of(version));
    }

    public void update(String code, String name) {
        this.code = CurrencyCode.of(code);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public CurrencyCode getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Version getVersion() {
        return version;
    }
}
