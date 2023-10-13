package com.irctc.Route.Controller;

import com.irctc.Route.Entity.Route;
import com.irctc.Route.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private RouteService service;

    @GetMapping("/find/route")
    public List<Route> route(){
        return this.service.getAll();
    }
}
