package com.irctc.bookings.service;

import com.irctc.bookings.entity.Bookings;

import java.util.List;

public interface BookingService {
    Bookings bookTicket(Bookings book, int fromStation, int toStation, int train_id, String userEmail);
    List<Bookings> getAllBookings();
//    Bookings passengerbooking(Bookings book, int fromStation, int toStation, int train_id, int user_id);
}
