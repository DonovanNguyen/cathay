package com.cathay.exchangeflow.infrastructure.persistence.exchangerate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@IdClass(ExchangeRateDto.ExchangeRateId.class)
@Table(name = "exchange_rates")
public class ExchangeRateDto {

    @Id
    @Column(name = "base_currency", nullable = false, length = 3)
    private String baseCurrency;

    @Id
    @Column(name = "quote_currency", nullable = false, length = 3)
    private String quoteCurrency;

    @Id
    @Column(name = "rate_date", nullable = false)
    private LocalDateTime rateDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal rate;

    public ExchangeRateDto() {
        // Default constructor for JPA
    }

    public ExchangeRateDto(String baseCurrency, String quoteCurrency, LocalDateTime rateDate,
            BigDecimal rate) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.rateDate = rateDate;
        this.rate = rate;
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

    public LocalDateTime getRateDate() {
        return rateDate;
    }

    public void setRateDate(LocalDateTime rateDate) {
        this.rateDate = rateDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExchangeRateDto that = (ExchangeRateDto) o;
        return baseCurrency.equals(that.baseCurrency) && quoteCurrency.equals(that.quoteCurrency)
                && rateDate.equals(that.rateDate);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(baseCurrency, quoteCurrency, rateDate);
    }

    // Composite key class
    public static class ExchangeRateId implements Serializable {
        private String baseCurrency;
        private String quoteCurrency;
        private LocalDateTime rateDate;

        public ExchangeRateId() {}

        public ExchangeRateId(String baseCurrency, String quoteCurrency, LocalDateTime rateDate) {
            this.baseCurrency = baseCurrency;
            this.quoteCurrency = quoteCurrency;
            this.rateDate = rateDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            ExchangeRateId that = (ExchangeRateId) o;
            return baseCurrency.equals(that.baseCurrency)
                    && quoteCurrency.equals(that.quoteCurrency) && rateDate.equals(that.rateDate);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(baseCurrency, quoteCurrency, rateDate);
        }
    }
}
