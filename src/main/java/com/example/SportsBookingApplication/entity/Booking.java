package com.example.SportsBookingApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_booking_slot",
                        columnNames = {"slot_id"}
                )
        }
)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(name = "booked_at", nullable = false)
    private LocalDateTime bookedAt;

    public Booking() {
    }

    public Booking(Slot slot) {
        this.slot = slot;
        this.status = BookingStatus.ACTIVE;
        this.bookedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Slot getSlot() {
        return slot;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;

    }
}