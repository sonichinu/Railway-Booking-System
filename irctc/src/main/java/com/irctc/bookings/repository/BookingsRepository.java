package com.irctc.bookings.repository;

import com.irctc.bookings.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings,Integer> {

    @Query("SELECT SUM(b.numberOfTickets) FROM Bookings b WHERE b.seatType = :seatType AND b.train.id = :trainId AND b.travelDate = :travelDate ")
    Integer seatCount(String seatType,int trainId, Date travelDate );

    @Query("SELECT t.name, t.number, b.bookingDate, b.travelDate, fs.name, ts.name, b.numberOfTickets, b.seatType, b.amountPaid FROM Bookings b " +
            "JOIN User u ON u.id = b.user.id " +
            "JOIN Train t ON t.id = b.train.id " +
            "JOIN Station fs ON fs.id = b.tostation.id " +
            "JOIN Station ts ON ts.id = b.fromstation.id " +
            "WHERE b.user.id = :userId " +
            "ORDER BY b.travelDate DESC")
    List<Object[]> getBookingsByUserId(int userId);

    @Query("SELECT t.name, t.number, b.bookingDate, b.travelDate, fs.name, ts.name, b.numberOfTickets, b.seatType, b.amountPaid " +
            "FROM Bookings b " +
            "JOIN b.user u " +
            "JOIN b.train t " +
            "JOIN b.tostation fs " +
            "JOIN b.fromstation ts " +
            "WHERE u.id = :userId AND b.travelDate >= CURRENT_DATE " +
            "ORDER BY b.travelDate")
    List<Object[]> getUpCommingBookingsByUSerId(int userId);



}
