package com.irctc.Route.ServiceImpl;

import com.irctc.Route.Entity.Route;
import com.irctc.Route.Repository.RouteRepository;
import com.irctc.Route.Service.RouteService;
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
