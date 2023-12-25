package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service.BusRouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BusRouteController.class)
class BusRouteControllerTest {

    @MockBean
    private BusRouteService busRouteService;

    @Autowired
    private MockMvc mockMvc ;

    private List<BusRoute> busRouteList;
    private BusRoute busRoute;
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {


        busRouteList= new ArrayList<>();
        busRouteList.add(new BusRoute(1L, "Route A", null,null));
        busRouteList.add(new BusRoute(2L, "Route B", null,null));

        busRoute= new BusRoute(1L, "New Route", null,null);
    }

    @Test
    void givenBusRoutes_whenFindAll_thenStatus200AndListOfBusRoutes() throws Exception {

        when(busRouteService.findAll()).thenReturn(busRouteList);

        mockMvc.perform(get("/api/busroute"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));


    }
    @Test
    void givenNewBusRoute_whenAddBusRoute_thenStatus200AndAddedBusRoute() throws Exception {

        when(busRouteService.createNewBusRoute(any())).thenReturn(busRoute);

        mockMvc.perform(post("/api/busroute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(busRoute)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));


    }

    @Test
    void givenExistingRouteId_whenDeleteBusRouteById_thenStatus200AndDeletedBusRoute() throws Exception {
        when(busRouteService.deleteById(any(Long.class))).thenReturn(busRoute);

        mockMvc.perform(delete("/api/busroute/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void givenExistingRouteId_whenFindBusRouteById_thenStatus200AndBusRouteDetails() throws Exception {
        when(busRouteService.findById(any(Long.class))).thenReturn(busRoute);

        mockMvc.perform(get("/api/busroute/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

    }

    @Test
    void givenExistingRouteIdAndUpdatedRoute_whenUpdateBusRouteById_thenStatus200AndUpdatedBusRoute() throws Exception {
        when(busRouteService.updateBusRoute(eq(1l),any())).thenReturn(busRoute);

        mockMvc.perform(patch("/api/busroute/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(busRoute)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
    @Test
    void givenNonExistingRouteId_whenDeleteBusRouteById_thenStatus500AndErrorMessage() throws Exception {
        Long routeId = 2L;
        when(busRouteService.deleteById(routeId)).thenThrow(new NoRouteFoundException("Bus Route not found"));

        mockMvc.perform(delete("/api/busroute/{id}", routeId))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Route not found")));


    }

    @Test
    void givenNonExistingRouteId_whenFindBusRouteById_thenStatus5xxAndErrorMessage() throws Exception {
        Long routeId = 2L;
        when(busRouteService.findById(routeId)).thenThrow(new NoRouteFoundException("Bus Route not found"));

        mockMvc.perform(get("/api/busroute/{id}", routeId))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Route not found")));

    }

    @Test
    void givenNonExistingRouteId_whenUpdateBusRouteById_thenStatus500AndErrorMessage() throws Exception {
        Long routeId = 2L;
        BusRoute updatedBusRoute = new BusRoute(routeId, "Updated Route", null,null);

        when(busRouteService.updateBusRoute(eq(routeId), any(updatedBusRoute.getClass()))).thenThrow(new NoRouteFoundException("Bus Route not found"));

        mockMvc.perform(patch("/api/busroute/{id}", routeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedBusRoute)))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Route not found")));



    }
}
