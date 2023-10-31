package com.irctc.seats.serviceimpl;

import com.irctc.seats.entity.Seats;
import com.irctc.seats.repository.SeatsRepository;
import com.irctc.seats.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatsServiceImpl implements SeatsService{

    @Autowired
    private SeatsRepository repo;

    @Override
    public List<Seats> getAllSeats() {
        return this.repo.findAll();
    }

    @Override
    public Seats getSingleSeat(int id) {
        return this.repo.findById(id).get();
    }
}
