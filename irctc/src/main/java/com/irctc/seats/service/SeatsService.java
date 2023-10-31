package com.irctc.seats.service;

import com.irctc.seats.entity.Seats;

import java.util.List;

public interface SeatsService {
    List<Seats> getAllSeats();
    Seats getSingleSeat(int id);
}
