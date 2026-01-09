package com.example.SportsBookingApplication.service;


import com.example.SportsBookingApplication.dto.Venue.VenueRequest;
import com.example.SportsBookingApplication.dto.Venue.VenueResponse;
import com.example.SportsBookingApplication.entity.Venue;
import com.example.SportsBookingApplication.exception.ResourceNotFoundException;
import com.example.SportsBookingApplication.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public VenueResponse createVenue(VenueRequest request) {
        Venue venue = new Venue();
        venue.setName(request.getName());
        venue.setLocation(request.getLocation());

        Venue saved = venueRepository.save(venue);

        return new VenueResponse(saved.getId(), saved.getName(), saved.getLocation());
    }

    public List<VenueResponse> getAllVenues() {
        return venueRepository.findAll()
                .stream()
                .map(v -> new VenueResponse(v.getId(), v.getName(), v.getLocation()))
                .collect(Collectors.toList());
    }

    public Venue getVenueEntity(Long venueId) {
        return venueRepository.findById(venueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue not found with id: " + venueId));
    }
}