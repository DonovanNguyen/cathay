package com.cathay.exchangeflow.domain.common;

import java.util.function.Supplier;

public class Constraint {

    private final Supplier<DomainException> action;

    public Constraint(Supplier<DomainException> action) {
        this.action = action;
    }

    public void violatesThenThrow() {
        DomainException exception = action.get();
        if (exception != null) {
            throw exception;
        }
    }

}
