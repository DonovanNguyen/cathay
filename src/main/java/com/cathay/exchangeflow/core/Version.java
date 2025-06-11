package com.cathay.exchangeflow.core;

public class Version {

    public static final Version INIT_VERSION = new Version(1);

    private Integer value;

    private Version(Integer value) {
        this.value = value;
    }

    public static Version of(Integer value) {
        if (value == null || value < 1) {
            throw new IllegalArgumentException("Version value must be a positive integer.");
        }
        return new Version(value);
    }

    public Integer getValue() {
        return value;
    }
}
