package com.irctc.train.repository;

import com.irctc.train.dto.TrainInfoDto;
import com.irctc.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Integer> {




    @Query("SELECT t.id, t.name, t.number , r1.arrivalTime, r1.distance, r2.arrivalTime, r2.distance, s1.id, s1.name, s2.id, s2.name " +
            "FROM Train t " +
            "JOIN Route r1 ON t.id = r1.train.id " +
            "JOIN Station s1 ON r1.station.id = s1.id " +
            "JOIN Route r2 ON t.id = r2.train.id " +
            "JOIN Station s2 ON r2.station.id = s2.id " +
            "WHERE r1.distance < r2.distance And s1.name = :from " +
            "AND s2.name = :to")
//@Query(value = "SELECT t FROM Train t " +
//        "JOIN Route r1 ON t.id = r1.train.id " +
//        "JOIN Station s1 ON r1.station.id = s1.id " +
//        "JOIN Route r2 ON t.id = r2.train.id " +
//        "JOIN Station s2 ON r2.station.id = s2.id " +
//        "WHERE r1.distance < r2.distance And s1.name = :from " +
//        "AND s2.name = :to")
    List<Object[]> findTrainBetweenStation(String from, String to);
}
