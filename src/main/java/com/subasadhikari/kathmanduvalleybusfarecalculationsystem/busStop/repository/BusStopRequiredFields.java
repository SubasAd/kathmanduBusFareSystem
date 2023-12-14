package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;

import java.util.List;

public interface BusStopRequiredFields {
    public BusStop findBusStopsByLatitudeEqualsAndLongitute(Double latitude, Double longitute);
    public BusStop findByName(String Name);

}
