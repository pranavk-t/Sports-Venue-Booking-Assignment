package com.example.SportsBookingApplication.dto.Venue;

public class VenueResponse {

    private Long id;
    private String name;
    private String location;

    public VenueResponse() {
    }

    public VenueResponse(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}