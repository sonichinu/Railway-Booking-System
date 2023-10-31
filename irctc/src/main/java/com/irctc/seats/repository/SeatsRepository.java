package com.irctc.seats.repository;

import com.irctc.seats.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats,Integer> {
}
