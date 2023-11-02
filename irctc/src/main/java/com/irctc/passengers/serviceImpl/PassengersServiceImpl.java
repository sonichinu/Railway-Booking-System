package com.irctc.passengers.serviceImpl;

import com.irctc.passengers.entity.Passengers;
import com.irctc.passengers.repository.PassengersRepo;
import com.irctc.passengers.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassengersServiceImpl implements PassengersService {

    @Autowired
    private PassengersRepo repo;
    @Override
    public List<Passengers> getAllPassengers(int id) {
        return this.repo.findByBookingId(id);
    }
}
