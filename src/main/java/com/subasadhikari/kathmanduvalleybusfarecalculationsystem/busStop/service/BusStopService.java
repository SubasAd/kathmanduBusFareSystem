package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;

import java.util.List;

public interface BusStopService {

    List<BusStop> findAll();
    BusStop findById(Long Id) throws NoBusStopFoundException;
    BusStop findByName(String name);


    BusStop createNewBusStop(BusStop busStop) throws NoRouteFoundException, BusStopAlreadyExistsException;

    BusStop updateBusStop(Long id,BusStop busStop) throws NoBusStopFoundException;

    BusStop deleteById(Long id) throws NoBusStopFoundException;

}
