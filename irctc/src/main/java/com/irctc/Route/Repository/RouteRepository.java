package com.irctc.Route.Repository;

import com.irctc.Route.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
