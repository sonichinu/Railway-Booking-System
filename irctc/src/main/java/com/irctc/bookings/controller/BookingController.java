package com.irctc.bookings.controller;

import com.irctc.bookings.entity.Bookings;
import com.irctc.bookings.service.BookingService;
import com.irctc.exception.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/book/{fromStation}/{toStation}/{train_id}/{user_id}")
    public ResponseEntity<ApiResponse> bookTicket(@RequestBody Bookings booking, @PathVariable int fromStation, @PathVariable int toStation, @PathVariable int train_id, @PathVariable int user_id){
        this.service.bookTicket(booking,fromStation,toStation,train_id,user_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking Done Successfully",true), HttpStatus.OK);
    }

    @GetMapping("/find/bookings")
    public ResponseEntity<List<Bookings>> getAllBookings(){
        return ResponseEntity.ok(service.getAllBookings());
    }

}
