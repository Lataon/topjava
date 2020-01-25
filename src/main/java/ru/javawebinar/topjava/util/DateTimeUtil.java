package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static <T extends Comparable<? super T>> boolean isBetween(T lt, T startTime, T endTime) {
        if (startTime==null && endTime==null) return true;
        if (startTime==null) return lt.compareTo(endTime) <= 0;
        if (endTime==null) return lt.compareTo(startTime) >= 0;
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static @Nullable LocalDate parseLocalDate(@Nullable String str) {
               return StringUtils.isEmpty(str) ? null : LocalDate.parse(str, DATE_FORMATTER);
         }
    public static @Nullable LocalTime parseLocalTime(@Nullable String str) {
             return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
         }

    public static LocalDateTime createDateTime(@Nullable LocalDate date, LocalDate defaultDate, LocalTime time) {
        return LocalDateTime.of(date != null ? date : defaultDate, time);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

