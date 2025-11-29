package org.assignment.core.libs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter humanFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter isoFormat = DateTimeFormatter.ISO_LOCAL_DATE;

    public static String toHuman(String isoDate) {
        LocalDate date = LocalDate.parse(isoDate, isoFormat);
        return date.format(humanFormat);
    }

    public static String toHuman(LocalDate date) {
        return date.format(humanFormat);
    }

    public static LocalDate parseHuman(String humanDate) {
        return LocalDate.parse(humanDate, humanFormat);
    }

    public static LocalDate parseISO(String isoDate) {
        return LocalDate.parse(isoDate, isoFormat);
    }
}


