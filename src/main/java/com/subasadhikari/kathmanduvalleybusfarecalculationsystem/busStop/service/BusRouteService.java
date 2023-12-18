package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;

import java.util.List;

public interface BusRouteService {

    List<BusRoute> findAll();
    BusRoute findById(Long Id) throws NoRouteFoundException;

    BusRoute findByName(String name);


    BusRoute createNewBusRoute(BusRoute busRoute);

    BusRoute updateBusRoute(Long id,BusRoute busRoute) throws NoRouteFoundException;

    BusRoute deleteById(Long id) throws NoRouteFoundException;


}
