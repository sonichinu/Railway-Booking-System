package com.irctc.train.repository;

import com.irctc.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query(value = "SELECT t FROM Train t " +
            "JOIN Route r1 ON t.id = r1.train.id " +
            "JOIN Station s1 ON r1.station.id = s1.id " +
            "JOIN Route r2 ON t.id = r2.train.id " +
            "JOIN Station s2 ON r2.station.id = s2.id " +
            "WHERE r1.distance < r2.distance And s1.name = :from " +
            "AND s2.name = :to")
//@Query(value = "SELECT t.* FROM train t " +
//        "JOIN route r1 ON t.id = r1.train_id " +
//        "JOIN station s1 ON r1.station_id = s1.id " +
//        "JOIN route r2 ON t.id = r2.train_id " +
//        "JOIN station s2 ON r2.station_id = s2.id " +
//        "WHERE s1.name = 'Indore' AND s2.name = 'Ujjain'", nativeQuery = true)
    List<Train> findTrainBetweenStation(String from, String to);
}
