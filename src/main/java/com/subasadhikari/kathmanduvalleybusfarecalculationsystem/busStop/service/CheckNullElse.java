package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.Location;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckNullElse {
    static String getName(BusStop busStopOriginal, BusStop busStopUpdated)
    {
        return busStopUpdated.getName() != null ? busStopUpdated.getName() : busStopOriginal.getName();
    }

    static String getName(BusRoute busRouteOriginal, BusRoute busRouteUpdated){
        return busRouteUpdated.getName() != null ? busRouteUpdated.getName() : busRouteOriginal.getName();
    }
    static Set<BusStop> getBusStops(BusRoute busRouteOriginal, BusRoute busRouteUpdated){
       return  busRouteUpdated.getBusStopSet() != null ? busRouteUpdated.getBusStopSet() : busRouteOriginal.getBusStopSet();
    }

    public static Location getBusLocation(BusStop busStopOriginal, BusStop busStopUpdated) {
        return  busStopUpdated.getBusStopLocation() != null ? busStopUpdated.getBusStopLocation() : busStopOriginal.getBusStopLocation();
    }
}
