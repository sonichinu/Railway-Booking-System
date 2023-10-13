package com.irctc.Bookings.Repository;

import com.irctc.Bookings.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings,Integer> {
}
