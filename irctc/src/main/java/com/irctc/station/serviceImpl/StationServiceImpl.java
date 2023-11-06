package com.irctc.station.serviceImpl;

import com.irctc.station.entity.Station;
import com.irctc.station.repository.StationRepository;
import com.irctc.station.service.StationService;
import com.irctc.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
@Autowired
private StationRepository repo;

    @Override
    public List<Station> getAllStations() {
        return this.repo.findAll();
    }

    @Override
    public Station getFromStation(int fromStation) {
        return this.repo.findById(fromStation).orElseThrow(()->new ApiException("from station not found with id ",fromStation));
    }

    @Override
    public Station getToStation(int toStation) {
        return this.repo.findById(toStation).orElseThrow(()->new ApiException("to station not found with id ",toStation));
    }
}
