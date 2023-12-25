package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions;

public class NoBusStopFoundException extends Exception{


    public NoBusStopFoundException() {
        super("Bus Stop Not Found");
    }

    public NoBusStopFoundException(String message) {
        super(message);
    }
}
