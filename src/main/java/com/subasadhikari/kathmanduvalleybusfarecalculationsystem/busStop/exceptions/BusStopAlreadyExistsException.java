package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions;

public class BusStopAlreadyExistsException extends Exception {
    public BusStopAlreadyExistsException() {
        super("Bus Stop Already Exists at the given location");
    }

    public BusStopAlreadyExistsException(String message) {
        super(message);
    }
}
