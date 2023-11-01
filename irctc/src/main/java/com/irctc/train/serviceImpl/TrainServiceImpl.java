package com.irctc.train.serviceImpl;

import com.irctc.bookings.service.BookingService;
import com.irctc.train.entity.Train;
import com.irctc.train.repository.TrainRepository;
import com.irctc.train.service.TrainService;
import com.irctc.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository repo;
    @Autowired
    private BookingService bookinService;
    @Override
    public List<Train> getAllTrains() {
        return this.repo.findAll();
    }

    @Override
    public List<Object[]> getTrainRoutes(String from, String to, String travelDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(from+to);
        List<Object[]> responseFromBackend = this.repo.findTrainBetweenStation(from,to);
        int trainId=0;
        for(Object[] row : responseFromBackend){
            try {
                Date date = dateFormat.parse(travelDate);
                row[11] = (int)row[11] - this.bookinService.seatCount("seat",(int)row[0],date);
                row[12] = (int)row[12] - this.bookinService.seatCount("sleeper",(int)row[0],date);
                row[13] = (int)row[13] - this.bookinService.seatCount("thirdAc",(int)row[0],date);
                row[14] = (int)row[14] - this.bookinService.seatCount("secondAc",(int)row[0],date);
                row[15] = (int)row[15] - this.bookinService.seatCount("firstAc",(int)row[0],date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return responseFromBackend;
    }

    @Override
    public Train getTrainById(int trainId) {
        return this.repo.findById(trainId).orElseThrow(()->new ApiException("train not found"));
    }
}
