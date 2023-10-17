package com.irctc.Bookings.Controller;

import com.irctc.Bookings.Entity.Bookings;
import com.irctc.Bookings.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/book/{fromStation}/{toStation}/{train_id}/{user_id}")
    public Bookings book(@RequestBody Bookings booking, @PathVariable int fromStation, @PathVariable int toStation, @PathVariable int train_id, @PathVariable int user_id){
        System.out.println(booking);
        System.out.println("???????"+fromStation+toStation+train_id+user_id);
        return this.service.booking(booking,fromStation,toStation,train_id,user_id);
//        return null;
    }

    @GetMapping("/find/bookings")
    public List<Bookings> getall(){
        return this.service.getAll();
    }

//    @PostMapping("/book/passengers/{fromStation}/{toStation}/{train_id}/{user_id}")
//    public Bookings bookPassengers(@RequestBody Bookings booking, @PathVariable int fromStation, @PathVariable int toStation, @PathVariable int train_id, @PathVariable int user_id){
//        System.out.println(booking);
//        return this.service.passengerbooking(booking,fromStation,toStation,train_id,user_id);
//    }
}
