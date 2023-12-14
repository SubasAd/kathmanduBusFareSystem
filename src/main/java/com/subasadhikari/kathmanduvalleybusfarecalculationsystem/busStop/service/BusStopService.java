package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.Exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;

import java.util.List;

public interface BusStopService {

    List<BusStop> findAll();
    BusStop findById(Long Id) throws NoBusStopFoundException;
    BusStop findByLocation(Double Longitude, Double Latitude);
    BusStop findByName(String name);


    BusStop createNewBusStop(BusStop busStop);

    BusStop updateBusStop(Long id,BusStop busStop) throws NoBusStopFoundException;

    BusStop deleteById(Long id) throws NoBusStopFoundException;

    Integer getFare(BusStop bs1, BusStop bs2, Boolean isDiscounted);

}
