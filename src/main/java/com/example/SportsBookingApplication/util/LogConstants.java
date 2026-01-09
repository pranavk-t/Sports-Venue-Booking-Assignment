package com.example.SportsBookingApplication.util;

public final class LogConstants {

    private LogConstants() {
        // utility class
    }

    // Venue
    public static final String CREATE_VENUE = "Creating venue";
    public static final String FETCH_VENUES = "Fetching all venues";

    // Slot
    public static final String CREATE_SLOT = "Creating slot for venueId={}";
    public static final String SLOT_OVERLAP = "Slot overlap detected for venueId={}";
    public static final String FETCH_AVAILABLE_SLOTS =
            "Fetching available slots for sportId={} between {} and {}";

    // Booking
    public static final String BOOK_SLOT = "Attempting to book slotId={}";
    public static final String SLOT_ALREADY_BOOKED = "Slot already booked slotId={}";
    public static final String CANCEL_BOOKING = "Cancelling bookingId={}";
}