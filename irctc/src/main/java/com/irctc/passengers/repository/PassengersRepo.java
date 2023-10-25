package com.irctc.passengers.repository;

import com.irctc.passengers.entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengersRepo extends JpaRepository<Passengers, Integer> {
}
