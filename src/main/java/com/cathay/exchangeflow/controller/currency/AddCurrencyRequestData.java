package com.cathay.exchangeflow.controller.currency;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddCurrencyRequestData {

    @NotBlank
    private String code;
    @NotBlank
    @Size(max = 100)
    private String name;

    public AddCurrencyRequestData() {}

    public AddCurrencyRequestData(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
