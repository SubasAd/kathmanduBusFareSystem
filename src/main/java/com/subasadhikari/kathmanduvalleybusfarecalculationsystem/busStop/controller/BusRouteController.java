package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.controller;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/api")
public class BusRouteController {

    final BusRouteService busRouteService;

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
    ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRoute busRoute) {
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
