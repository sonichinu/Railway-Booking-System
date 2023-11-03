package com.irctc.bookings.service;

import com.irctc.bookings.entity.Bookings;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface BookingService {
    Bookings bookTicket(Bookings book, int fromStation, int toStation, int train_id, String userEmail) throws DocumentException, IOException, MessagingException;
    List<Bookings> getAllBookings();
//    Bookings passengerbooking(Bookings book, int fromStation, int toStation, int train_id, int user_id);

    int seatCount(String seatType,int trainId, Date travelDate);
    List<Object[]> getListByUser(int userId);
    List<Object[]> getUpCommingTrips(int userId);

    void deleteBooking(int id);

}
