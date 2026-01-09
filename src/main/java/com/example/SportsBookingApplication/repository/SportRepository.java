package com.example.SportsBookingApplication.repository;

import com.example.SportsBookingApplication.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
}