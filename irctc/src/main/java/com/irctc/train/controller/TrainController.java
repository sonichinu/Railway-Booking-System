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
        String fromWithoutSpaces = from.replaceAll("\\s", "");
        String capitalizedFrom = fromWithoutSpaces.substring(0, 1).toUpperCase() + fromWithoutSpaces.substring(1).toLowerCase();
        String toWithoutSpaces = to.replaceAll("\\s", "");
        String capitalizedTo = toWithoutSpaces.substring(0, 1).toUpperCase() + toWithoutSpaces.substring(1).toLowerCase();
        return ResponseEntity.ok(this.service.getTrainRoutes(capitalizedFrom,capitalizedTo,travelDate));
    }

    @GetMapping("/find/train-routes/{id}")
    public ResponseEntity<List<Object[]>> getTrainRoutesByID(@PathVariable int id){
        return ResponseEntity.ok(this.service.getTrainRoutesById(id));
    }

    @GetMapping("/find/trains/find-only-trains")
    public ResponseEntity<List<Object[]>> getOnlyTrainData(){
        return ResponseEntity.ok(this.service.getOnlyTrainData());
    }
}
