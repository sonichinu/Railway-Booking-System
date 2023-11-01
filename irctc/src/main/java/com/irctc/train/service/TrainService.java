package com.irctc.train.service;

import com.irctc.train.entity.Train;

import java.util.Date;
import java.util.List;

public interface TrainService {

    List<Train> getAllTrains();
    List<Object[]> getTrainRoutes(String from, String to, String travelDate);
    Train getTrainById(int traiIid);
}
