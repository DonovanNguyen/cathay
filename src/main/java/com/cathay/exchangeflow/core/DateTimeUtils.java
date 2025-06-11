package com.cathay.exchangeflow.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Converts a LocalDateTime to a formatted string: "yyyy/MM/dd HH:mm:ss"
     *
     * @param dateTime LocalDateTime to format
     * @return formatted string
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
