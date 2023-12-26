package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;

import java.util.Optional;

public interface BusStopRequiredFields {

    Optional<BusStop> findByName(String Name);
    BusStop findBusStopByLocation(LocationKey locationKey);

}
