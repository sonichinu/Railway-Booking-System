package com.irctc.seats.controller;

import com.irctc.seats.entity.Seats;
import com.irctc.seats.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatsController {
    @Autowired
    private SeatsService service;

    @GetMapping("/find/seats")
    public ResponseEntity<List<Seats>> getAllSeats(){
        return ResponseEntity.ok(this.service.getAllSeats());
    }

    @GetMapping("/find/seats/{id}")
    public ResponseEntity<Seats> getSingleSeat(@PathVariable int id){
        return ResponseEntity.ok(this.service.getSingleSeat(id));
    }

}
