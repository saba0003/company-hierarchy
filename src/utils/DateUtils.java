package utils;

import exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private DateUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated!");
    }

    public static DateTimeFormatter getDateFormatter() {
        return DATE_FORMATTER;
    }

    public static DateTimeFormatter getTimeFormatter() {
        return TIME_FORMATTER;
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format, expected dd/MM/yyyy: " + dateStr, e);
        }
    }
}
