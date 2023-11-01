package com.irctc.bookings.serviceImpl;

import com.irctc.bookings.entity.Bookings;
import com.irctc.bookings.repository.BookingsRepository;
import com.irctc.bookings.service.BookingService;
import com.irctc.exception.ApiException;
import com.irctc.passengers.entity.Passengers;
import com.irctc.station.service.StationService;
import com.irctc.train.service.TrainService;
import com.irctc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private int chairseatCount=0;
    private int sleeperCount=0;
    private int thirdAcCount=0;
    private int secondAcCount=0;
    private int firstAcCount=0;

    @Override
    public Bookings bookTicket(Bookings book, int fromStation, int toStation, int train_id, String userEmail) {
        String seat= book.getSeatType();
        book.setSeatType(seat.substring(6));
        book.setFromstation(this.stationService.getFromStation(fromStation));
        book.setTostation(this.stationService.getToStation(toStation));
        book.setTrain(this.trainService.getTrainById(train_id));
        book.setUser(this.userService.getSingleUser(userEmail));
        book.setBookingDate(new Date());
        List<Passengers> passengers = book.getPassengers();
            for (Passengers p : passengers) {
                if(seat.contains("sleeper")){
                    String s = seat + ' ' + String.valueOf(++sleeperCount);
                    p.setReservedSeat(s);
                }else if(seat.contains("seat")){
                    String s = seat + ' ' + String.valueOf(++chairseatCount);
                    p.setReservedSeat(s);
                }else if(seat.contains("thirdAC")){
                    String s = seat + ' ' + String.valueOf(++thirdAcCount);
                    p.setReservedSeat(s);
                }else if(seat.contains("secondAC")){
                    String s = seat + ' ' + String.valueOf(++secondAcCount);
                    p.setReservedSeat(s);
                }else if(seat.contains("firstAC")){
                    String s = seat + ' ' + String.valueOf(++firstAcCount);
                    p.setReservedSeat(s);
                }else{
                    throw new ApiException("something went wrong!!");
                }
                p.setBooking(book);
            }
        return this.repo.save(book);
    }

    @Override
    public List<Bookings> getAllBookings() {
        return this.repo.findAll();
    }

    @Override
    public int seatCount(String seatType,int trainId, Date travelDate) {
        Integer count =this.repo.seatCount(seatType,trainId,travelDate);
        if(count==null){
            count=0;
        }
        return count;
    }

    @Override
    public List<Object[]> getListByUser(int userId) {
        return this.repo.getBookingsByUserId(userId);
    }

    @Override
    public List<Object[]> getUpCommingTrips(int userId) {
        return this.repo.getUpCommingBookingsByUSerId(userId);
    }

    @Override
    public void deleteBooking(int id) {
        this.repo.delete(this.repo.findById(id).orElseThrow(()-> new ApiException("Booking not found")));
    }

}
