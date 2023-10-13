package com.irctc.Station.Controller;

import com.irctc.Station.Entity.Station;
import com.irctc.Station.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private StationService service;

//    @PostMapping("/find/{from}/{to}")

    @GetMapping("/find/station")
    public List<Station> station(){
        return this.service.getAll();
    }


}
