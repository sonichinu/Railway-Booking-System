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

    @GetMapping("/train/find")
    public List<Train> train(){
        return this.service.getAll();
    }

    @GetMapping("/train/find/{from}/{to}")
    public List<Train> find(@PathVariable String to, @PathVariable String from){
//        System.out.println(to + from );
        return this.service.find(from,to);
    }
}
