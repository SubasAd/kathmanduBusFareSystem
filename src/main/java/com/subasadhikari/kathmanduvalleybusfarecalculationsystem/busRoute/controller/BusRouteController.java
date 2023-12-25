package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.controller;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service.BusRouteService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class BusRouteController {

    final private BusRouteService busRouteService;
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }
    @CrossOrigin
    @GetMapping("/busroute")
    ResponseEntity<List<BusRoute>> findAll(){
        return ResponseEntity.ok(this.busRouteService.findAll());
    }
    @CrossOrigin
    @PostMapping("/busroute")
    ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) throws NoBusStopFoundException {
        return ResponseEntity.ok(this.busRouteService.createNewBusRoute(busRoute));
    }
    @CrossOrigin
    @DeleteMapping("/busroute/{id}")
    ResponseEntity<BusRoute> deleteBusRouteById(@PathVariable Long id) throws NoRouteFoundException {
        return ResponseEntity.ok(this.busRouteService.deleteById(id));
    }
    @CrossOrigin
    @GetMapping("/busroute/{id}")
    ResponseEntity<BusRoute> findBusRouteById(@PathVariable Long id) throws NoRouteFoundException {
        return ResponseEntity.ok(this.busRouteService.findById(id));
    }
    @CrossOrigin
    @PatchMapping("/busroute/{id}")
    ResponseEntity<BusRoute> updateBusRouteById(@PathVariable Long id,@RequestBody BusRoute busRoute) throws NoRouteFoundException {
        return ResponseEntity.ok(this.busRouteService.updateBusRoute(id,busRoute));
    }



}
