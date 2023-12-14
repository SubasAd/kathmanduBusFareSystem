package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.Controller;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.Exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusStopService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/api/busstop")
public class BusStopController {

    final BusStopService busStopService;

    public BusStopController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @CrossOrigin
    @GetMapping("/")
    ResponseEntity<List<BusStop>> findAllStop()
    {
        return ResponseEntity.ok(this.busStopService.findAll());
    }
    @CrossOrigin
    @GetMapping("/{BusStopId}")
    ResponseEntity<BusStop> findById(@PathVariable Long busStopId) throws NoBusStopFoundException {
        return ResponseEntity.ok(this.busStopService.findById(busStopId));
    }

    @CrossOrigin
    @PostMapping("/")
    ResponseEntity<BusStop> addNewBusStop(@RequestBody BusStop busStop){
        return ResponseEntity.ok(this.busStopService.createNewBusStop(busStop));
    }
    @CrossOrigin
    @PutMapping("/{id}")

    ResponseEntity<BusStop> updateBusStop(@PathVariable Long id, @RequestBody BusStop busStop) throws NoBusStopFoundException {
      return ResponseEntity.ok(this.busStopService.updateBusStop(id,busStop));
    }

}
