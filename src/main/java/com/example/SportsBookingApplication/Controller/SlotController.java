package com.example.SportsBookingApplication.Controller;


import com.example.SportsBookingApplication.dto.Slot.SlotRequest;
import com.example.SportsBookingApplication.dto.Slot.SlotResponse;
import com.example.SportsBookingApplication.service.SlotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/venues")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping("/{venueId}/slots")
    public ResponseEntity<SlotResponse> createSlot(
            @PathVariable Long venueId,
            @Valid @RequestBody SlotRequest request) {

        SlotResponse response = slotService.createSlot(venueId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<SlotResponse>> getAvailableSlots(
            @RequestParam Long sportId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return ResponseEntity.ok(
                slotService.findAvailableSlots(sportId, start, end)
        );
    }
}