package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;

public interface BusRouteRequiredFields {
    public BusRoute findByName(String name);
}
