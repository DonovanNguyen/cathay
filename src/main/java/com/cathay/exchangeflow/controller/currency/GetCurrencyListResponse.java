package com.cathay.exchangeflow.controller.currency;

import java.util.List;

public class GetCurrencyListResponse {
    List<Currency> currencies;

    public GetCurrencyListResponse(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public static class Currency {
        private Long id;
        private String code;
        private String name;
        private Integer version;

        public Currency(Long id, String code, String name, Integer version) {
            this.id = id;
            this.code = code;
            this.name = name;
            this.version = version;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
