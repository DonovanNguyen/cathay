package com.cathay.exchangeflow.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "batch")
public class BatchConfig {

    private Cron cron;

    public static class Cron {
        private String retrieveExchangeRates;

        public String getRetrieveExchangeRates() {
            return retrieveExchangeRates;
        }

        public void setRetrieveExchangeRates(String retrieveExchangeRates) {
            this.retrieveExchangeRates = retrieveExchangeRates;
        }
    }

    public Cron getCron() {
        return cron;
    }

    public void setCron(Cron cron) {
        this.cron = cron;
    }

    public String getRetrieveExchangeRatesCron() {
        return cron.getRetrieveExchangeRates();
    }
}
