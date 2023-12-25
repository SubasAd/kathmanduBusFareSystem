package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions;
public class NoRouteFoundException  extends Exception{
    public NoRouteFoundException(String message) {
        super(message);
    }
    public NoRouteFoundException() {
        super("Bus Route Not Found");
    }

}
