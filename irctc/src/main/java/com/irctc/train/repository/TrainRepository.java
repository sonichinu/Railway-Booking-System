package com.irctc.train.repository;

import com.irctc.train.dto.TrainInfoDto;
import com.irctc.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Integer> {




//    @Query("SELECT "+
//            "t.name AS trainName, " +
//            "MIN(s1.name) AS fromStationName, " +
//            "MIN(r1.arrivalTime) AS fromStationArrivalTime, " +
//            "MAX(s2.name) AS toStationName, " +
//            "MAX(r2.arrivalTime) AS toStationArrivalTime, " +
//            "MAX(r2.distance - r1.distance) AS distance " +
//            "FROM Route r1 " +
//            "JOIN Train t ON r1.train.id = t.id " +
//            "JOIN Station s1 ON r1.station.id = s1.id " +
//            "JOIN Route r2 " +
//            "JOIN Station s2 ON r2.station.id = s2.id " +
//            "WHERE s1.name = 'Indore' " +
//            "AND s2.name = 'Ujjain' " +
//            "GROUP BY t.name")
@Query(value = "SELECT t FROM Train t " +
        "JOIN Route r1 ON t.id = r1.train.id " +
        "JOIN Station s1 ON r1.station.id = s1.id " +
        "JOIN Route r2 ON t.id = r2.train.id " +
        "JOIN Station s2 ON r2.station.id = s2.id " +
        "WHERE r1.distance < r2.distance And s1.name = :from " +
        "AND s2.name = :to")
    List<TrainInfoDto> findTrainBetweenStation(String from, String to);
}
