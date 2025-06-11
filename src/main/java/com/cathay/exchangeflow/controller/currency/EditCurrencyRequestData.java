package com.cathay.exchangeflow.controller.currency;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EditCurrencyRequestData {
    @NotBlank
    private String code;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotNull
    @Min(1)
    private Integer version;

    public EditCurrencyRequestData() {

    }

    public EditCurrencyRequestData(String code, String name, Integer version) {
        this.code = code;
        this.name = name;
        this.version = version;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
