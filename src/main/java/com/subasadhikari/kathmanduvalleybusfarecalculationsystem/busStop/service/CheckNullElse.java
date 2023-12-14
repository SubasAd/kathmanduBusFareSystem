package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;

public class CheckNullElse {
    static String getName(BusStop busStopOriginal, BusStop busStopUpdated)
    {
        return busStopUpdated.getName() != null ? busStopUpdated.getName() : busStopOriginal.getName();
    }

    public static Double getLatitude(BusStop busStopOriginal, BusStop busStopUpdated) {
        return busStopUpdated.getLatitude() != null ? busStopUpdated.getLatitude() : busStopOriginal.getLatitude();
    }

    public static Double getLongitude(BusStop busStopOriginal, BusStop busStopUpdated) {
        return busStopUpdated.getLongitute() != null ? busStopUpdated.getLongitute() : busStopOriginal.getLongitute();
    }
}
