package com.irctc.bookings.serviceImpl;

import com.irctc.bookings.entity.Bookings;
import com.irctc.bookings.repository.BookingsRepository;
import com.irctc.bookings.service.BookingService;
import com.irctc.passengers.entity.Passengers;
import com.irctc.station.entity.Station;
import com.irctc.station.service.StationService;
import com.irctc.train.entity.Train;
import com.irctc.train.service.TrainService;
import com.irctc.user.entity.User;
import com.irctc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingsRepository repo;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;

    @Override
    public Bookings bookTicket(Bookings book, int fromStation, int toStation, int train_id, int user_id) {
        Station fromstation = this.stationService.getFromStation(fromStation);
        Station tostation = this.stationService.getToStation(toStation);
        Train train = this.trainService.getTrainById(train_id);
        User user = this.userService.getSingleUser(user_id);
        book.setFromstation(fromstation);
        book.setTostation(tostation);
        book.setTrain(train);
        book.setUser(user);
        List<Passengers> passengers = book.getPassengers();
        if(book.getNumberOfTickets()>1 || passengers!= null) {
            for (Passengers p : passengers) {
                p.setBooking(book);
            }
        }
        return this.repo.save(book);
    }

    @Override
    public List<Bookings> getAllBookings() {
        return this.repo.findAll();
    }
}
