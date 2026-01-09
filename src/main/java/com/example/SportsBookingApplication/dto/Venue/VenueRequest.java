package com.example.SportsBookingApplication.dto.Venue;

import jakarta.validation.constraints.*;
public class VenueRequest {

    @NotBlank(message = "Venue name is required")
    private String name;

    private String location;

    public VenueRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}