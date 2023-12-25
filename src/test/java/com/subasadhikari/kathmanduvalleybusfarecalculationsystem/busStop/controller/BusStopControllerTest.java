package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service.BusRouteService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BusStopController.class)
class BusStopControllerTest {
    @MockBean
    private BusStopService busStopService;
    @MockBean
    private BusRouteService busRouteService;
    @Autowired
    private MockMvc mockMvc ;
    List<BusStop> busStopList;
    BusStop busStop;
    @BeforeEach
    void setUp() {
        // Common mocking behavior for busStopService
        busStopList = new ArrayList<>();
        busStopList.add(new BusStop(1L, "kalanki", null, new LocationKey(85.27, 56.2)));
        busStopList.add(new BusStop(2L, "kalanki2", null, new LocationKey(85.27, 56.2)));

        busStop =  new BusStop(1L, "kalanki", null, new LocationKey(85.27, 56.2));
    }
    @Test
    public void givenBusStops_whenGet_thenStatus200_thenReturnListOfBusStops() throws Exception {

        when(busStopService.findAll()).thenReturn(busStopList);


        mockMvc.perform(get("/api/busstop"))
                .andExpect(status().isOk());
    }
    @Test
    public void givenBusStopWithId_whenGetBusStopWithId_shouldReturnTheBusStopWithThatIdAndStatus200() throws Exception {


       when(busStopService.findById(1L)).thenReturn(busStop);

       mockMvc.perform(get("/api/busstop/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1L));

    }
    @Test
    public void givenBusStopNotExistsForGivenId_whenGetBusStopWithId_shouldThrowNoBusStopFoundExceptionWithInternalServerError() throws Exception {
        when(busStopService.findById(1L)).thenThrow(new NoBusStopFoundException("Bus Stop not found"));
        mockMvc.perform(get("/api/busstop/1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Stop not found")));

    }
    @Test
    public void whenAddNewBusStop_shouldReturnStatusOkWithBusStopAdded() throws Exception {


        when(busStopService.createNewBusStop(any())).thenReturn(busStop);

        mockMvc.perform(post("/api/busstop").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(busStop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));


    }
    @Test
    public void givenBusStopAtThatLocationAlreadyExists_whenAddNewBusStop_shouldReturnInternalServerError_throwsBusStopAlreadyExistsException() throws Exception {
        when(busStopService.createNewBusStop(any())).thenThrow(new BusStopAlreadyExistsException("Bus Stop at that location already exists"));

        mockMvc.perform(post("/api/busstop").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(busStop)))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Stop at that location already exists")));

    }

    @Test
    public void givenBusStopIsThere_whenUpdateBusStop_shouldHaveStatus200AndReturnTheBusStop() throws Exception {
        when(busStopService.updateBusStop(eq(1L),any(busStop.getClass()))).thenReturn(busStop);

        mockMvc.perform(put("/api/busstop/1").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(busStop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));


    }
    @Test
    public void givenBusStopOfThatIdNotExists_whenUpdateBusStop_shouldHaveStatus5xx_ThrowsExceptionNoBusStopFoundException() throws Exception {
        when(busStopService.updateBusStop(eq(1L),any(busStop.getClass()))).thenThrow(new NoBusStopFoundException("Bus Stop doesn't exists"));

        mockMvc.perform(put("/api/busstop/1").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(busStop)))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Stop doesn't exists")));

    }
    @Test
    void givenExistingBusStopId_whenDeleteById_thenStatus200AndDeletedBusStop() throws Exception {
        Long busStopId = 1L;
        when(busStopService.deleteById(busStopId)).thenReturn(busStop);

        mockMvc.perform(delete("/api/busstop/{busStopId}", busStopId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void givenNonExistingBusStopId_whenDeleteById_thenStatus500AndErrorMessage() throws Exception {
        Long busStopId = 2L;
        when(busStopService.deleteById(busStopId)).thenThrow(new NoBusStopFoundException("Bus Stop not found"));

        mockMvc.perform(delete("/api/busstop/{busStopId}", busStopId))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Bus Stop not found")));

    }

    @Test
    void givenExistingBusStopName_whenFindByName_thenStatus200AndBusStopDetails() throws Exception {

        when(busStopService.findByName(eq("kalanki"))).thenReturn(busStop);
        mockMvc.perform(get("/api/busstop/name/kalanki"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()); // Ensure the response contains the BusStop details

    }


}