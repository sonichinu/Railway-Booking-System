package com.irctc.Train.ServiceImpl;

import com.irctc.Train.Entity.Train;
import com.irctc.Train.Repository.TrainRepository;
import com.irctc.Train.Service.TrainService;
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
}
