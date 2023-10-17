package com.irctc.Passengers.Repository;

import com.irctc.Passengers.Entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengersRepo extends JpaRepository<Passengers, Integer> {
}
