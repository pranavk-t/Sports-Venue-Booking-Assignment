package com.example.SportsBookingApplication.repository;


import com.example.SportsBookingApplication.entity.*;
import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.*;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    /**
     * Finds overlapping slots for a venue.
     * Used during slot creation to prevent overlaps.
     */
    @Query("""
        select s from Slot s
        where s.venue.id = :venueId
          and s.startTime < :endTime
          and s.endTime > :startTime
    """)
    List<Slot> findOverlappingSlots(
            @Param("venueId") Long venueId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    /**
     * Finds available slots for availability API.
     */
    @Query("""
        select s from Slot s
        where s.sport.id = :sportId
          and s.status = :status
          and s.startTime >= :startTime
          and s.endTime <= :endTime
    """)
    List<Slot> findAvailableSlots(
            @Param("sportId") Long sportId,
            @Param("status") SlotStatus status,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    /**
     * Locks a slot row for safe booking.
     * Prevents double booking under concurrency.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Slot s where s.id = :id")
    Optional<Slot> findByIdForUpdate(@Param("id") Long id);
}