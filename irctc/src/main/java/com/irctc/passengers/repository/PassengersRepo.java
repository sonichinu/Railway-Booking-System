package com.irctc.passengers.repository;

import com.irctc.passengers.entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengersRepo extends JpaRepository<Passengers, Integer> {

    @Query("SELECT p FROM Passengers p WHERE p.booking.id = :bookingId")
    List<Passengers> findByBookingId(int bookingId);
}
