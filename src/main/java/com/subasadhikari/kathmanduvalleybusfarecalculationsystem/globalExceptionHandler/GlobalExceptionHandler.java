package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.globalExceptionHandler;


import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {

        private static final
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        @ExceptionHandler({NoRouteFoundException.class})
        public ResponseEntity<Object> handleRouteNotFoundException(NoRouteFoundException exception) {
            logger.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
        @ExceptionHandler({NoBusStopFoundException.class})
        public ResponseEntity<Object> handleBusStopNotFoundException(NoBusStopFoundException exception) {
            logger.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    @ExceptionHandler({BusStopAlreadyExistsException.class})
    public ResponseEntity<Object> handleBusStopALreadyExistsException(BusStopAlreadyExistsException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

        @ExceptionHandler({RuntimeException.class})
        public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
            logger.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }

