package com.example.SportsBookingApplication.dto.Booking;


import jakarta.validation.constraints.*;

public class BookingRequest {

    @NotNull(message = "Slot ID is required")
    private Long slotId;

    public BookingRequest() {
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }
}