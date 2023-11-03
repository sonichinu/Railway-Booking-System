package com.irctc.bookings.serviceImpl;

import com.irctc.bookings.entity.Bookings;
import com.irctc.bookings.repository.BookingsRepository;
import com.irctc.bookings.service.BookingService;
import com.irctc.dto.TicketDetailsDto;
import com.irctc.exception.ApiException;
import com.irctc.passengers.entity.Passengers;
import com.irctc.sendpdfmail.EmailService;
import com.irctc.sendpdfmail.PdfGenerator;
import com.irctc.station.entity.Station;
import com.irctc.station.service.StationService;
import com.irctc.train.entity.Train;
import com.irctc.train.service.TrainService;
import com.irctc.user.entity.User;
import com.irctc.user.service.UserService;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
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
    @Autowired
    private PdfGenerator pdfGenerator;
    @Autowired
    private EmailService emailService;
    private int chairseatCount=0;
    private int sleeperCount=0;
    private int thirdAcCount=0;
    private int secondAcCount=0;
    private int firstAcCount=0;

    @Override
    public Bookings bookTicket(Bookings book, int fromStation, int toStation, int train_id, String userEmail) throws DocumentException, IOException, MessagingException {
        String seat= book.getSeatType();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        Station fromstation = this.stationService.getFromStation(fromStation);
        Station tostation = this.stationService.getToStation(toStation);
        Train train = this.trainService.getTrainById(train_id);
        User user = this.userService.getSingleUser(userEmail);
        book.setSeatType(seat.substring(6));
        book.setFromstation(fromstation);
        book.setTostation(tostation);
        book.setTrain(train);
        book.setUser(user);
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
        TicketDetailsDto dto = new TicketDetailsDto();
            dto.setUserName(user.getName());
            dto.setAmountPaid(book.getAmountPaid());
            dto.setFromStation(fromstation.getName());
            dto.setToStation(tostation.getName());
            dto.setTrainName(train.getName());
            dto.setTravelDate(book.getTravelDate());
            dto.setNoOfPassengers(book.getNumberOfTickets());
            dto.setPlist(passengers);
            this.pdfGenerator.generateTicketPDF(dto);
            this.emailService.sendEmail("rohit.chinu.soni@gmail.com",
                    "Your Booking Details",
                    "Please refer with your booking details",
                    "/home/anurag/"+date+".pdf");
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
