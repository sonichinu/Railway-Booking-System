package com.irctc.station.service;

import com.irctc.station.entity.Station;

import java.util.List;

public interface StationService {
    List<Object[]> getAllStations();
    Station getFromStation(int fromStation);
    Station getToStation(int toStation);

}
