package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions;

public class NoBusStopFoundException extends Exception{
    public String message(){

        return "Bus Stop not found";
    }
}
