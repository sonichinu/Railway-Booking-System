package com.irctc.Passengers.ServiceImpl;

import com.irctc.Passengers.Entity.Passengers;
import com.irctc.Passengers.Repository.PassengersRepo;
import com.irctc.Passengers.Service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassengersServiceImpl implements PassengersService {

    @Autowired
    private PassengersRepo repo;
    @Override
    public List<Passengers> getAllPassengers() {
        return this.repo.findAll();
    }
}
