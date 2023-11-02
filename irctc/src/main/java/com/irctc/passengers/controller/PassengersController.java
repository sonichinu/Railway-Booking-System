package com.irctc.passengers.controller;

import com.irctc.passengers.entity.Passengers;
import com.irctc.passengers.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PassengersController {
    @Autowired
    private PassengersService service;

    @GetMapping("/find/passengers/{bookingId}")
    public ResponseEntity<List<Passengers>> getAllPassengersOfBooking(@PathVariable int bookingId){
        System.out.println("11111111111111" + bookingId);
        return ResponseEntity.ok(this.service.getAllPassengers(bookingId));
    }
}
