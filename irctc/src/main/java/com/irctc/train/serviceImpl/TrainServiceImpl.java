package com.irctc.train.serviceImpl;

import com.irctc.train.entity.Train;
import com.irctc.train.repository.TrainRepository;
import com.irctc.train.service.TrainService;
import com.irctc.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository repo;
    @Override
    public List<Train> getAllTrains() {
        return this.repo.findAll();
    }

    @Override
    public List<Train> getTrainRoutes(String from, String to) {
        System.out.println(from+to);
      //  System.out.println((this.repo.findByRoutes(from,to)).toString());
        return this.repo.findTrainBetweenStation(from,to);
    }

    @Override
    public Train getTrainById(int trainId) {
        return this.repo.findById(trainId).orElseThrow(()->new ApiException("train not found"));
    }
}
