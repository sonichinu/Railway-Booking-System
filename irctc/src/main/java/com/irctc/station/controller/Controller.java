package com.irctc.station.controller;

import com.irctc.station.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private StationService service;

//    @PostMapping("/find/{from}/{to}")

    @GetMapping("/find/stations/find-only-stations")
    public ResponseEntity<List<Object[]>> getAllStations(){
        return ResponseEntity.ok(this.service.getAllStations());
    }


}
