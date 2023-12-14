package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;

import java.util.HashSet;
import java.util.List;

public class CheckNullElse {
    static String getName(BusStop busStopOriginal, BusStop busStopUpdated)
    {
        return busStopUpdated.getName() != null ? busStopUpdated.getName() : busStopOriginal.getName();
    }

    public static Double getLatitude(BusStop busStopOriginal, BusStop busStopUpdated) {
        return busStopUpdated.getLatitude() != null ? busStopUpdated.getLatitude() : busStopOriginal.getLatitude();
    }

    static Double getLongitude(BusStop busStopOriginal, BusStop busStopUpdated) {
        return busStopUpdated.getLongitute() != null ? busStopUpdated.getLongitute() : busStopOriginal.getLongitute();
    }
    static String getName(BusRoute busRouteOriginal, BusRoute busRouteUpdated){
        return busRouteUpdated.getName() != null ? busRouteUpdated.getName() : busRouteOriginal.getName();
    }
    static HashSet<BusStop> getBusStops(BusRoute busRouteOriginal, BusRoute busRouteUpdated){
       return  busRouteUpdated.getBusStopSet() != null ? busRouteUpdated.getBusStopSet() : busRouteOriginal.getBusStopSet();
    }

}
