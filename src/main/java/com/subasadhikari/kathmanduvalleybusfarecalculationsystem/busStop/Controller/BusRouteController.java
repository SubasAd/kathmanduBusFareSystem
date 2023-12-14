package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.Controller;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusRouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/api/busRoute")
public class BusRouteController {

    final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

}
