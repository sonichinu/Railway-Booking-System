package com.irctc.Train.Service;

import com.irctc.Train.Entity.Train;

import java.util.List;

public interface TrainService {

    List<Train> getAllTrains();
    List<Train> getTrainRoutes(String from, String to);
}
