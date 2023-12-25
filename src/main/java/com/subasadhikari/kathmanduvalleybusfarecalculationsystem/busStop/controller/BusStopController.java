package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.controller;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusStopService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/busstop")
public class BusStopController {

    final BusStopService busStopService;

    public BusStopController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }
    @CrossOrigin
    @GetMapping("")
    ResponseEntity<List<BusStop>> findAllStop()
    {
        return ResponseEntity.ok(this.busStopService.findAll());
    }
    @CrossOrigin
    @GetMapping("/{busStopId}")
    ResponseEntity<BusStop> findById(@PathVariable Long busStopId) throws NoBusStopFoundException {
        return ResponseEntity.ok(this.busStopService.findById(busStopId));
    }

    @CrossOrigin
    @PostMapping("")
    ResponseEntity<BusStop> addNewBusStop(@RequestBody BusStop busStop) throws NoRouteFoundException, BusStopAlreadyExistsException {
        return ResponseEntity.ok(this.busStopService.createNewBusStop(busStop));
    }
    @CrossOrigin
    @PutMapping("/{id}")

    ResponseEntity<BusStop> updateBusStop(@PathVariable Long id, @RequestBody BusStop busStop) throws NoBusStopFoundException {
      return ResponseEntity.ok(this.busStopService.updateBusStop(id,busStop));
    }
    @CrossOrigin
    @DeleteMapping("/{busStopId}")
    ResponseEntity<BusStop> deleteById(@PathVariable Long busStopId) throws NoBusStopFoundException {
        return ResponseEntity.ok(this.busStopService.deleteById(busStopId));
    }
    @CrossOrigin
    @GetMapping("/name/{busStopName}")
    ResponseEntity<BusStop> findByName(@PathVariable String busStopName ){
        return ResponseEntity.ok(this.busStopService.findByName(busStopName));
    }



}
