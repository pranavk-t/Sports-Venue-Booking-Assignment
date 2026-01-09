package com.example.SportsBookingApplication.dto.Booking;


import java.time.LocalDateTime;

public class BookingResponse {

    private Long bookingId;
    private Long slotId;
    private String status;
    private LocalDateTime bookedAt;

    public BookingResponse() {
    }

    public BookingResponse(Long bookingId, Long slotId,
                           String status, LocalDateTime bookedAt) {
        this.bookingId = bookingId;
        this.slotId = slotId;
        this.status = status;
        this.bookedAt = bookedAt;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }
}