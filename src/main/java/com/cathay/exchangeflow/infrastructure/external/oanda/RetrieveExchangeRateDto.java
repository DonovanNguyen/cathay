package com.cathay.exchangeflow.infrastructure.external.oanda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.Rate;

public class RetrieveExchangeRateDto {
    private String baseCurrency;
    private String quoteCurrency;
    private LocalDateTime dateTime;
    private BigDecimal averageBid;
    private BigDecimal averageAsk;
    private BigDecimal highBid;
    private BigDecimal highAsk;
    private BigDecimal lowBid;
    private BigDecimal lowAsk;

    public ExchangeRate toExchangeRate() {
        return ExchangeRate.of(baseCurrency, quoteCurrency, dateTime,
                Rate.of(averageBid, averageAsk));
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getAverageBid() {
        return averageBid;
    }

    public void setAverageBid(BigDecimal averageBid) {
        this.averageBid = averageBid;
    }

    public BigDecimal getAverageAsk() {
        return averageAsk;
    }

    public void setAverageAsk(BigDecimal averageAsk) {
        this.averageAsk = averageAsk;
    }

    public BigDecimal getHighBid() {
        return highBid;
    }

    public void setHighBid(BigDecimal highBid) {
        this.highBid = highBid;
    }

    public BigDecimal getHighAsk() {
        return highAsk;
    }

    public void setHighAsk(BigDecimal highAsk) {
        this.highAsk = highAsk;
    }

    public BigDecimal getLowBid() {
        return lowBid;
    }

    public void setLowBid(BigDecimal lowBid) {
        this.lowBid = lowBid;
    }

    public BigDecimal getLowAsk() {
        return lowAsk;
    }

    public void setLowAsk(BigDecimal lowAsk) {
        this.lowAsk = lowAsk;
    }


}
