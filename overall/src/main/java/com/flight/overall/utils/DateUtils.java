package com.flight.overall.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter toDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String dateToPrettyString(LocalDate date) {
        if (date == null)
            return "";

        return toStringFormatter.format(date);
    }

    public static LocalDate prettyStringToDate(String date) {
        if (date == null || date.isEmpty())
            return null;

        return LocalDate.parse(date, toDateFormatter);
    }
}
