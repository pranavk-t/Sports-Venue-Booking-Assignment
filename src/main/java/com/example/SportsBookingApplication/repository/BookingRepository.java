package com.example.SportsBookingApplication.repository;


import com.example.SportsBookingApplication.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}