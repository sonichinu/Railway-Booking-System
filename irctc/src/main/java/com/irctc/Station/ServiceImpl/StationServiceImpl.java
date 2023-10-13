package com.irctc.Station.ServiceImpl;

import com.irctc.Station.Entity.Station;
import com.irctc.Station.Repository.StationRepository;
import com.irctc.Station.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
@Autowired
private StationRepository repo;

    @Override
    public List<Station> getAll() {
        return this.repo.findAll();
    }
}
