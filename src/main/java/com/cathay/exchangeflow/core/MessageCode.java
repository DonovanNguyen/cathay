package com.cathay.exchangeflow.core;

public class MessageCode {
    final String value;

    private MessageCode(String value) {
        this.value = value;
    }

    public static MessageCode of(String value) {
        return new MessageCode(value);
    }

    public String value() {
        return value;
    }

}
