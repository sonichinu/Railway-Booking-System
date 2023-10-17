package com.irctc.Bookings.Service;

import com.irctc.Bookings.Entity.Bookings;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookingService {
    Bookings booking(Bookings book, int fromStation, int toStation, int train_id, int user_id);
    List<Bookings> getAll();
//    Bookings passengerbooking(Bookings book, int fromStation, int toStation, int train_id, int user_id);
}
