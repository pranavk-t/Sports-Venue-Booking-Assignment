package com.example.SportsBookingApplication.util;


import java.time.LocalDateTime;

public final class DateTimeUtil {

    private DateTimeUtil() {
        // utility class
    }

    public static boolean isStartBeforeEnd(LocalDateTime start, LocalDateTime end) {
        return start != null && end != null && start.isBefore(end);
    }

    public static boolean isOverlapping(
            LocalDateTime start1,
            LocalDateTime end1,
            LocalDateTime start2,
            LocalDateTime end2) {

        return start1.isBefore(end2) && end1.isAfter(start2);
    }
}