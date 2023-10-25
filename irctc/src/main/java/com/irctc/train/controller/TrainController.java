package com.irctc.train.controller;

import com.irctc.train.entity.Train;
import com.irctc.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService service;

    @GetMapping("/find/train")
    public ResponseEntity<List<Train>> getAllTrains(){
        return ResponseEntity.ok(this.service.getAllTrains());
    }

    @GetMapping("/find/train/{from}/{to}")
    public ResponseEntity<List<Train>> getTrainRoutes(@PathVariable String to, @PathVariable String from){
//        System.out.println(to + from );
        return ResponseEntity.ok(this.service.getTrainRoutes(from,to));
    }
}
