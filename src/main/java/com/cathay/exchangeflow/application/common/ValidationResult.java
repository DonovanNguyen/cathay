package com.cathay.exchangeflow.application.common;

import java.util.ArrayList;
import java.util.List;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.core.MessageCode;
import com.cathay.exchangeflow.domain.common.Constraint;
import com.cathay.exchangeflow.domain.common.DomainException;

public class ValidationResult {
    private final List<MessageCode> messageCodes;

    public ValidationResult() {
        this(new ArrayList<>());
    }

    private ValidationResult(List<MessageCode> messageCodes) {
        this.messageCodes = messageCodes;
    }

    public ValidationResult validate(Constraint constraint) {
        try {
            constraint.violatesThenThrow();
        } catch (DomainException e) {
            messageCodes.add(e.getMessageCode());
        }
        return this;
    }

    public ValidationResult violatesThenThrow() {
        if (!messageCodes.isEmpty()) {
            throw new ApplicationException(messageCodes);
        }
        return this;
    }

}
