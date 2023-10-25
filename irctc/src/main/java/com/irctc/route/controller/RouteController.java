package com.irctc.route.controller;

import com.irctc.route.entity.Route;
import com.irctc.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private RouteService service;

    @GetMapping("/find/route")
    public ResponseEntity<List<Route>> route(){
        return ResponseEntity.ok(this.service.getAll());
    }
}
