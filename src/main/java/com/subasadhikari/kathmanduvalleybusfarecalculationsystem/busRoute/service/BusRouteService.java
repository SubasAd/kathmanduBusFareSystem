package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;

import java.util.List;

public interface BusRouteService {

    List<BusRoute> findAll();
    BusRoute findById(Long Id) throws NoRouteFoundException;

    BusRoute findByName(String name);


    BusRoute createNewBusRoute(BusRoute busRoute) throws NoBusStopFoundException;

    BusRoute updateBusRoute(Long id,BusRoute busRoute) throws NoRouteFoundException;

    BusRoute deleteById(Long id) throws NoRouteFoundException;


}
