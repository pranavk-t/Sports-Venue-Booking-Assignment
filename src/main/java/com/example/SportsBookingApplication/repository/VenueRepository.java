package com.example.SportsBookingApplication.repository;


import com.example.SportsBookingApplication.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}