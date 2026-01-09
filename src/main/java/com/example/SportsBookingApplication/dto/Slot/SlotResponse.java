package com.example.SportsBookingApplication.dto.Slot;


import java.time.LocalDateTime;

public class SlotResponse {

    private Long slotId;
    private Long venueId;
    private String venueName;
    private Long sportId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public SlotResponse() {
    }

    public SlotResponse(Long slotId, Long venueId, String venueName,
                        Long sportId, LocalDateTime startTime,
                        LocalDateTime endTime, String status) {
        this.slotId = slotId;
        this.venueId = venueId;
        this.venueName = venueName;
        this.sportId = sportId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Long getSlotId() {
        return slotId;
    }

    public Long getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public Long getSportId() {
        return sportId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }
}