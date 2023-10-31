package com.irctc.train.service;

import com.irctc.train.entity.Train;

import java.util.List;

public interface TrainService {

    List<Train> getAllTrains();
    List<Object[]> getTrainRoutes(String from, String to);
    Train getTrainById(int traiIid);
}
