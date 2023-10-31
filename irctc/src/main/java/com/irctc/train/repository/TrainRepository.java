package com.irctc.train.repository;

import com.irctc.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Integer> {




    @Query("SELECT t.id, t.name, t.number, " +
            "r1.arrivalTime AS arrivalTimeFrom, r1.distance AS distanceFrom, " +
            "r2.arrivalTime AS arrivalTimeTo, r2.distance AS distanceTo, " +
            "s1.id AS stationIdFrom, s1.name AS stationNameFrom, " +
            "s2.id AS stationIdTo, s2.name AS stationNameTo, " +
            "s.totalChairCars, s.totalSleepers, s.totalThirdAc, " +
            "s.totalSecondAc, s.totalFirstAc " +
            "FROM Train t " +
            "JOIN Route r1 ON t.id = r1.train.id " +
            "JOIN Station s1 ON r1.station.id = s1.id " +
            "JOIN Route r2 ON t.id = r2.train.id " +
            "JOIN Station s2 ON r2.station.id = s2.id " +
            "JOIN Seats s ON t.id = s.train.id " +
            "WHERE r1.distance < r2.distance AND s1.name = :from AND s2.name = :to")

    List<Object[]> findTrainBetweenStation(String from, String to);
}
