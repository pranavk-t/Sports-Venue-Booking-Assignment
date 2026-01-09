package com.example.SportsBookingApplication.Controller;


import com.example.SportsBookingApplication.dto.Booking.BookingRequest;
import com.example.SportsBookingApplication.dto.Booking.BookingResponse;
import com.example.SportsBookingApplication.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookSlot(
            @Valid @RequestBody BookingRequest request) {

        BookingResponse response = bookingService.bookSlot(request.getSlotId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable Long bookingId) {

        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
