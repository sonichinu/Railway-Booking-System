package com.irctc.station.repository;

import com.irctc.station.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station,Integer> {

    @Query("SELECT s.id, s.name FROM Station s")
    List<Object[]> getAllStations();
}
