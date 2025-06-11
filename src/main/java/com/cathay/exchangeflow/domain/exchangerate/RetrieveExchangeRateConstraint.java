package com.cathay.exchangeflow.domain.exchangerate;

import java.time.LocalDate;
import com.cathay.exchangeflow.core.MessageCode;
import com.cathay.exchangeflow.domain.common.Constraint;
import com.cathay.exchangeflow.domain.common.DomainException;

public class RetrieveExchangeRateConstraint {
    public static Constraint dateRangeMustBeValid(LocalDate startDate, LocalDate endDate) {
        return new Constraint(() -> {
            if (startDate.isAfter(endDate)) {
                throw new DomainException(MessageCode.of("exchange-rate.date.invalid-range"));
            }
            return null;
        });
    }
}
