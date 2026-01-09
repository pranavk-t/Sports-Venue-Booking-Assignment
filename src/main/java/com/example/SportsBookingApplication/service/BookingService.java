package com.example.SportsBookingApplication.service;


import com.example.SportsBookingApplication.dto.Booking.BookingResponse;
import com.example.SportsBookingApplication.entity.*;
import com.example.SportsBookingApplication.exception.ResourceNotFoundException;
import com.example.SportsBookingApplication.exception.SlotAlreadyBookedException;
import com.example.SportsBookingApplication.repository.BookingRepository;
import com.example.SportsBookingApplication.repository.SlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final SlotRepository slotRepository;
    private final BookingRepository bookingRepository;

    public BookingService(SlotRepository slotRepository,
                          BookingRepository bookingRepository) {
        this.slotRepository = slotRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public BookingResponse bookSlot(Long slotId) {

        Slot slot = slotRepository.findByIdForUpdate(slotId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Slot not found with id: " + slotId));

        if (slot.getStatus() == SlotStatus.BOOKED) {
            throw new SlotAlreadyBookedException("Slot already booked");
        }

        slot.setStatus(SlotStatus.BOOKED);

        Booking booking = new Booking(slot);
        Booking saved = bookingRepository.save(booking);

        return new BookingResponse(
                saved.getId(),
                slot.getId(),
                saved.getStatus().name(),
                saved.getBookedAt()
        );
    }

    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            return;
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.getSlot().setStatus(SlotStatus.AVAILABLE);
    }
}