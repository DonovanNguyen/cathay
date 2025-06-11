package com.cathay.exchangeflow.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class VersionTest {
    @Test
    void testOf_validValue() {
        Version version = Version.of(5);
        assertNotNull(version);
        assertEquals(5, version.getValue());
    }

    @Test
    void testOf_nullThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Version.of(null));
        assertEquals("Version value must be a positive integer.", exception.getMessage());
    }

    @Test
    void testOf_zeroThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Version.of(0));
        assertEquals("Version value must be a positive integer.", exception.getMessage());
    }

    @Test
    void testOf_negativeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Version.of(-3));
        assertEquals("Version value must be a positive integer.", exception.getMessage());
    }
}
