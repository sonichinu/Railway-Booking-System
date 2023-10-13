package com.irctc.Train.Service;

import com.irctc.Train.Entity.Train;

import java.util.List;

public interface TrainService {

    List<Train> getAll();
    List<Train> find(String from, String to);
}
