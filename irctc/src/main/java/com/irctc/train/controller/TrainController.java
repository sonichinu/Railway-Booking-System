package com.irctc.train.controller;

import com.irctc.train.entity.Train;
import com.irctc.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TrainController {

    @Autowired
    private TrainService service;

    @GetMapping("/find/train")
    public ResponseEntity<List<Train>> getAllTrains(){
        return ResponseEntity.ok(this.service.getAllTrains());
    }

    @GetMapping("/find/train/{from}/{to}/{travelDate}")
    public ResponseEntity<List<Object[]>> getTrainRoutes(@PathVariable String to, @PathVariable String from,@PathVariable String travelDate){
//        System.out.println(to + from );
        return ResponseEntity.ok(this.service.getTrainRoutes(from,to,travelDate));
    }

    @GetMapping("/find/train/{id}")
    public ResponseEntity<Train> getTrainByID(@PathVariable int id){
        return ResponseEntity.ok(this.service.getTrainById(id));
    }
}
