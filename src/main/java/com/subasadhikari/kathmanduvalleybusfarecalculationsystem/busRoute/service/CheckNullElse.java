package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;

import java.util.Set;

public class CheckNullElse  {
    public static String getName(BusStop busStopOriginal, BusStop busStopUpdated)
    {
        return busStopUpdated.getName() != null ? busStopUpdated.getName() : busStopOriginal.getName();
    }

    static String getName(BusRoute busRouteOriginal, BusRoute busRouteUpdated){
        return busRouteUpdated.getName() != null ? busRouteUpdated.getName() : busRouteOriginal.getName();
    }
    static Set<BusStop> getBusStops(BusRoute busRouteOriginal, BusRoute busRouteUpdated){
       return  busRouteUpdated.getBusStopSet() != null ? busRouteUpdated.getBusStopSet() : busRouteOriginal.getBusStopSet();
    }

}
