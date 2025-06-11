package com.cathay.exchangeflow.batch.exchangerate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.cathay.exchangeflow.application.exchangerate.ExchangeRateSyncService;

@Component
public class ExchangeRateScheduler {
    private final ExchangeRateSyncService exchangeRateSyncService;

    public ExchangeRateScheduler(ExchangeRateSyncService exchangeRateSyncService) {
        this.exchangeRateSyncService = exchangeRateSyncService;
    }

    @Scheduled(cron = "#{@batchConfig.retrieveExchangeRatesCron}")
    public void syncExchangeRates() {
        exchangeRateSyncService.sync();
    }
}
