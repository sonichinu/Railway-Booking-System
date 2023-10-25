package com.irctc.bookings.repository;

import com.irctc.bookings.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings,Integer> {
}
