package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.Exceptions;

public class NoBusStopFoundException extends Exception{
    public String message(){

        return "Bus Stop not found";
    }
}
