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
@CrossOrigin("http://localhost:4200")
public class BookingController {
    @Autowired
    private BookingService service;

//********GET TRAIN DETAILS FOR A PARTICULAR ROUTE************************************
    @PostMapping("/book/{fromStation}/{toStation}/{train_id}/{userEmail}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<ApiResponse> bookTicket(@RequestBody Bookings booking, @PathVariable int fromStation, @PathVariable int toStation, @PathVariable int train_id, @PathVariable String userEmail){
        this.service.bookTicket(booking,fromStation,toStation,train_id,userEmail);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking Done Successfully",true), HttpStatus.OK);
    }

//    ******GET ALL BOOKING RELATED INFORMATION***************
    @GetMapping("/find/bookings")
    public ResponseEntity<List<Bookings>> getAllBookings(){
        return ResponseEntity.ok(service.getAllBookings());
    }


//******FIND BOOKINGS OF A PARTICULAR USER*****************
    @GetMapping("/find/bookings/{userId}")
    public ResponseEntity<List<Object[]>> getBookingsForUser(@PathVariable int userId){
        return new ResponseEntity<List<Object[]>>(this.service.getListByUser(userId), HttpStatus.OK);
    }

//******FIND BOOKINGS FOR UPCOMMING TRIPS***************
    @GetMapping("/find/bookings/upcomming/{userId}")
    public ResponseEntity<List<Object[]>> getUpCommingBookingsForUser(@PathVariable int userId){
        return new ResponseEntity<List<Object[]>>(this.service.getUpCommingTrips(userId), HttpStatus.OK);
    }

//******DELETE AN UPCOMMING BOOKING*************************
    @GetMapping("delete/booking/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable int bookingId){
        try {
            this.service.deleteBooking(bookingId);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Booking deleted Sucessfully",true),HttpStatus.OK);
        }catch(Exception e)
        {
            String message = e.getMessage().toString();
            ApiResponse apiResponse= new ApiResponse(message, false);
            return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }
}
