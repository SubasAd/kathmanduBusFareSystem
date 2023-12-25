package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRouteRepository extends JpaRepository<BusRoute,Long>, BusRouteRequiredFields{

}