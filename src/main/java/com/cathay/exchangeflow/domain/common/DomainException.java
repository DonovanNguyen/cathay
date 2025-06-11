package com.cathay.exchangeflow.domain.common;

import com.cathay.exchangeflow.core.MessageCode;

public class DomainException extends RuntimeException {
    private final MessageCode messageCode;

    public DomainException(MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }
}

