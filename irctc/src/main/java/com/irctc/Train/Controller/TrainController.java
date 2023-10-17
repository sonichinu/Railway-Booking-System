package com.irctc.Train.Controller;

import com.irctc.Train.Entity.Train;
import com.irctc.Train.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainController {

    @Autowired
    private TrainService service;

    @GetMapping("/find/train")
    public List<Train> getAllTrains(){
        return this.service.getAllTrains();
    }

    @GetMapping("/find/train/{from}/{to}")
    public List<Train> getTrainRoutes(@PathVariable String to, @PathVariable String from){
//        System.out.println(to + from );
        return this.service.getTrainRoutes(from,to);
    }
}
