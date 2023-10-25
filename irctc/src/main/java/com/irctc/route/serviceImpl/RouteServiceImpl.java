package com.irctc.route.serviceImpl;

import com.irctc.route.entity.Route;
import com.irctc.route.repository.RouteRepository;
import com.irctc.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository service;

    @Override
    public List<Route> getAll() {
        return this.service.findAll();
    }
}
