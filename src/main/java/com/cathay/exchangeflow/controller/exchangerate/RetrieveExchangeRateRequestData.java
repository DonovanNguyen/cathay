package com.cathay.exchangeflow.controller.exchangerate;

import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;

public class RetrieveExchangeRateRequestData {
    @NotBlank
    @Length(min = 3, max = 3)
    private String base;
    @NotBlank
    @Length(min = 3, max = 3)
    private String quote;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public RetrieveExchangeRateRequestData() {}

    public RetrieveExchangeRateRequestData(String base, String quote, LocalDate startDate,
            LocalDate endDate) {
        this.base = base;
        this.quote = quote;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
