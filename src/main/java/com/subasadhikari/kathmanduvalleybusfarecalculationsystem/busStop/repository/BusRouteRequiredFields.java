package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;

import java.util.List;

public interface BusRouteRequiredFields {


    public BusRoute findByName(String name);
}
