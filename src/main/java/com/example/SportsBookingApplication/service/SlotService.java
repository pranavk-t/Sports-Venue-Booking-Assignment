package com.example.SportsBookingApplication.service;


import com.example.SportsBookingApplication.dto.Slot.SlotRequest;
import com.example.SportsBookingApplication.dto.Slot.SlotResponse;
import com.example.SportsBookingApplication.entity.*;
import com.example.SportsBookingApplication.exception.ResourceNotFoundException;
import com.example.SportsBookingApplication.exception.SlotOverlapException;
import com.example.SportsBookingApplication.repository.SlotRepository;
import com.example.SportsBookingApplication.repository.SportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final SportRepository sportRepository;
    private final VenueService venueService;

    public SlotService(SlotRepository slotRepository,
                       SportRepository sportRepository,
                       VenueService venueService) {
        this.slotRepository = slotRepository;
        this.sportRepository = sportRepository;
        this.venueService = venueService;
    }

    @Transactional
    public SlotResponse createSlot(Long venueId, SlotRequest request) {

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        Venue venue = venueService.getVenueEntity(venueId);

        Sport sport = sportRepository.findById(request.getSportId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sport not found with id: " + request.getSportId()));


        if (!slotRepository.findOverlappingSlots(
                venueId, request.getStartTime(), request.getEndTime()).isEmpty()) {
            throw new SlotOverlapException("Slot overlaps with existing slot");
        }

        Slot slot = new Slot();
        slot.setVenue(venue);
        slot.setSport(sport);
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setStatus(SlotStatus.AVAILABLE);

        Slot saved = slotRepository.save(slot);

        return mapToResponse(saved);
    }

    public List<SlotResponse> findAvailableSlots(Long sportId,
                                                 java.time.LocalDateTime start,
                                                 java.time.LocalDateTime end) {

        return slotRepository.findAvailableSlots(
                        sportId, SlotStatus.AVAILABLE, start, end)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SlotResponse mapToResponse(Slot slot) {
        return new SlotResponse(
                slot.getId(),
                slot.getVenue().getId(),
                slot.getVenue().getName(),
                slot.getSport().getId(),
                slot.getStartTime(),
                slot.getEndTime(),
                slot.getStatus().name()
        );
    }
}