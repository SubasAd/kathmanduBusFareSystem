package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.globalExceptionHandler;


import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler({NoRouteFoundException.class})
        public ResponseEntity<Object> handleRouteNotFoundException(NoRouteFoundException exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
        @ExceptionHandler({NoBusStopFoundException.class})
        public ResponseEntity<Object> handleBusStopNotFoundException(NoBusStopFoundException exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    @ExceptionHandler({BusStopAlreadyExistsException.class})
    public ResponseEntity<Object> handleBusStopALreadyExistsException(BusStopAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

        @ExceptionHandler({RuntimeException.class})
        public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }

