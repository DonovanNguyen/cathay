package com.cathay.exchangeflow.application.exception;

import java.util.List;
import com.cathay.exchangeflow.core.MessageCode;

public class ApplicationException extends RuntimeException {
    private final List<MessageCode> messageCodes;

    public ApplicationException(List<MessageCode> messageCodes) {
        this.messageCodes = messageCodes;
    }

    public ApplicationException(MessageCode messageCode) {
        this.messageCodes = List.of(messageCode);
    }

    public List<MessageCode> getMessageCodes() {
        return messageCodes;
    }

}
