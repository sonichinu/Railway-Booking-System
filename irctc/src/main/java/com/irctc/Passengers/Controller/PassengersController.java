package com.irctc.Passengers.Controller;

import com.irctc.Passengers.Entity.Passengers;
import com.irctc.Passengers.Service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PassengersController {
    @Autowired
    private PassengersService service;

    @GetMapping("/find/passengers")
    public List<Passengers> getAllPassengers(){
        return this.service.getAllPassengers();
    }
}
