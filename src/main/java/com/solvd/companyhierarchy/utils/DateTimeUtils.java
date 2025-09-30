package com.solvd.companyhierarchy.utils;

import com.solvd.companyhierarchy.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtils {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private DateTimeUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated!");
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatDate(LocalDateTime dateAndTime) {
        return formatDate(dateAndTime.toLocalDate());
    }

    public static String formatTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public static String formatTime(LocalDateTime dateAndTime) {
        return formatTime(dateAndTime.toLocalTime());
    }

    public static String formateDateAndTime(LocalDateTime dateAndTime) {
        return formatDate(dateAndTime) + " " + formatTime(dateAndTime);
    }

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format, expected dd/MM/yyyy: " + dateStr, e);
        }
    }
}
