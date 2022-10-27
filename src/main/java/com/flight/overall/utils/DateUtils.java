package com.flight.overall.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String dateToPrettyString(LocalDate date) {
        if (date == null)
            return "";

        return dateFormatter.format(date);
    }
}
