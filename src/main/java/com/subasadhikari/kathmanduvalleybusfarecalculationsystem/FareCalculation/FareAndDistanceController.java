package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.FareCalculation;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service.BusRouteService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusStopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class FareAndDistanceController {

    final
    private BusStopService busStopService;
    final
    private BusRouteService busRouteService;

    final
    private Utils utils;
    public FareAndDistanceController(BusStopService busStopService, BusRouteService busRouteService, Utils utils) {
        this.busStopService = busStopService;
        this.busRouteService = busRouteService;
        this.utils = utils;
    }

    @CrossOrigin
    @GetMapping("/fare/normal/{busStop1}/{busStop2}/{route}")
    ResponseEntity<Integer> getNormalFare(@PathVariable Long busStop1, @PathVariable Long busStop2, @PathVariable Long route) throws NoBusStopFoundException, NoRouteFoundException {
          BusStop initBusStop = this.busStopService.findById(busStop1);
          BusStop finalBusStop = this.busStopService.findById(busStop2);
          BusRoute routeTaken = this.busRouteService.findById(route);
          Integer fare = utils.fareCalculationForNormal(initBusStop,finalBusStop,routeTaken);
          return ResponseEntity.ok(fare);
    }
    @CrossOrigin
    @GetMapping("/fare/discounted/{busStop1}/{busStop2}/{route}")
    ResponseEntity<Integer> getDiscountedFare(@PathVariable Long busStop1, @PathVariable Long busStop2,@PathVariable Long route) throws NoBusStopFoundException, NoRouteFoundException {
        BusStop initBusStop = this.busStopService.findById(busStop1);
        BusStop finalBusStop = this.busStopService.findById(busStop2);
        BusRoute routeTaken = this.busRouteService.findById(route);
        Integer fare = utils.fareCalculationDiscounted(initBusStop,finalBusStop,routeTaken);
        return ResponseEntity.ok(fare);
    }




}
