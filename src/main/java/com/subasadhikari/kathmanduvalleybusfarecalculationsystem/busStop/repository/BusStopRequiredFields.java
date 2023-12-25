package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;

public interface BusStopRequiredFields {

    BusStop findByName(String Name);
    BusStop findBusStopByLocation(LocationKey locationKey);

}
