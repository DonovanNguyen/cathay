package com.cathay.exchangeflow.application.exchangerate;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import com.cathay.exchangeflow.application.common.ValidationResult;
import com.cathay.exchangeflow.domain.exchangerate.RetrieveExchangeRateConstraint;

@Component
public class RetrieveExchangeRateValidator {
    public void validate(LocalDate startDate, LocalDate endDate) {
        new ValidationResult()
                .validate(RetrieveExchangeRateConstraint.dateRangeMustBeValid(startDate, endDate))
                .violatesThenThrow();
    }
}
