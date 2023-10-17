package com.irctc.Bookings.ServiceImpl;

import com.irctc.Bookings.Entity.Bookings;
import com.irctc.Bookings.Repository.BookingsRepository;
import com.irctc.Bookings.Service.BookingService;
import com.irctc.Passengers.Entity.Passengers;
import com.irctc.Station.Entity.Station;
import com.irctc.Station.Repository.StationRepository;
import com.irctc.Train.Entity.Train;
import com.irctc.Train.Repository.TrainRepository;
import com.irctc.User.Entity.User;
import com.irctc.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingsRepository repo;
    @Autowired
    private StationRepository srepo;
    @Autowired
    private TrainRepository trepo;
    @Autowired
    private UserRepository urepo;

    @Override
    public Bookings booking(Bookings book, int fromStation, int toStation, int train_id, int user_id) {
        Station fromstation = this.srepo.findById(fromStation).get();
        Station tostation = this.srepo.findById(toStation).get();
        Train train = this.trepo.findById(train_id).get();
        User user = this.urepo.findById(user_id).get();
        book.setFromstation(fromstation);
        book.setTostation(tostation);
        book.setTrain(train);
        book.setUser(user);
        List<Passengers> passengers = book.getPassengers();
        for(Passengers p: passengers){
            p.setBooking(book);
        }
//        if(book.getNumberOfTickets()==1){
            return this.repo.save(book);
//        }
//        else{
//
//        }

    }

    @Override
    public List<Bookings> getAll() {
        return this.repo.findAll();
    }

//    @Override
//    public Bookings passengerbooking(Bookings book, int fromStation, int toStation, int train_id, int user_id) {
//        Station fromstation = this.srepo.findById(fromStation).get();
//        Station tostation = this.srepo.findById(toStation).get();
//        Train train = this.trepo.findById(train_id).get();
//        User user = this.urepo.findById(user_id).get();
//        book.setFromstation(fromstation);
//        book.setTostation(tostation);
//        book.setTrain(train);
//        book.setUser(user);
//        List<Passengers> passengers = book.getPassengers();
//        for(Passengers p: passengers){
//            p.setBooking(book);
//        }
//        return this.repo.save(book);
//    }
}
