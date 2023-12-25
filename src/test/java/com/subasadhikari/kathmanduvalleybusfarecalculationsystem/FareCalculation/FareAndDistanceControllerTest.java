package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.FareCalculation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service.BusRouteService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusStopService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FareAndDistanceController.class)
class FareAndDistanceControllerTest {

    @MockBean
    private BusStopService busStopService;

    @MockBean
    private BusRouteService busRouteService;
    @Autowired


    private MockMvc mockMvc;
    @MockBean
    Utils utils;
    private ObjectMapper objectMapper;

    private Long busStop1;
    private Long busStop2;
    private Long routeId;
    private BusStop initBusStop;
    private BusStop finalBusStop;
    private BusRoute routeTaken;
    @BeforeEach
    void setUp() throws NoRouteFoundException, NoBusStopFoundException {

        objectMapper = new ObjectMapper();
        init();
    }

    private void init() throws NoBusStopFoundException, NoRouteFoundException {
        // Initialize common variables
        busStop1 = 1L;
        busStop2 = 2L;
        routeId = 3L;
        initBusStop = new BusStop(busStop1, "BusStop1", null, new LocationKey(85.27, 56.2));
        finalBusStop = new BusStop(busStop2, "BusStop2", null, new LocationKey(85.27, 56.2));
        List<LocationKey> locations = new ArrayList<>();
        locations.add(new LocationKey(85.27, 56.2));
        locations.add(new LocationKey(85.27, 56.2));
        routeTaken = new BusRoute(routeId, "RouteA", null,locations);
        when(busStopService.findById(busStop1)).thenReturn(initBusStop);
        when(busStopService.findById(busStop2)).thenReturn(finalBusStop);
        when(busRouteService.findById(routeId)).thenReturn(routeTaken);
    }

    @Test
    void givenBusStopsAndRoute_whenGetNormalFare_thenStatus200AndFare() throws Exception {
        when(utils.fareCalculationForNormal(any(),any(),any())).thenReturn(15);
        mockMvc.perform(get("/api/fare/normal/{busStop1}/{busStop2}/{route}", busStop1, busStop2, routeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("15"));
    }
    @Test
    void getDiscountedFare() throws Exception {

        when(utils.fareCalculationDiscounted(any(),any(),any())).thenReturn(15);
        mockMvc.perform(get("/api/fare/discounted/{busStop1}/{busStop2}/{route}", busStop1, busStop2, routeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("15"));
    }


}