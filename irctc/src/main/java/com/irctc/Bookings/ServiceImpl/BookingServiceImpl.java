package com.irctc.Bookings.ServiceImpl;

import com.irctc.Bookings.Entity.Bookings;
import com.irctc.Bookings.Repository.BookingsRepository;
import com.irctc.Bookings.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingsRepository repo;
    @Override
    public Bookings booking(Bookings book) {


        return this.repo.save(book);
    }
}
