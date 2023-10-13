package com.irctc.Bookings.Controller;

import com.irctc.Bookings.Entity.Bookings;
import com.irctc.Bookings.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/book")
    public Bookings book(@RequestBody Bookings booking){
        System.out.println(booking);
        return this.service.booking(booking);

    }
}
