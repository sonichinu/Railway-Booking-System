package com.irctc.passengers.service;

import com.irctc.passengers.entity.Passengers;

import java.util.List;

public interface PassengersService {
    List<Passengers> getAllPassengers(int id);
}
