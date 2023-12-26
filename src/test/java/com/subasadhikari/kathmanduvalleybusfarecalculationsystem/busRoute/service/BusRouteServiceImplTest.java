package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.repository.BusRouteRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusStopRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusStopServiceImpl;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@ExtendWith(MockitoExtension.class)
class BusRouteServiceImplTest {
    @Mock
    BusStopRepository busStopRepository;
    @Mock
    BusRouteRepository busRouteRepository;
    List<BusStop> busStopList;
    @InjectMocks
    BusRouteServiceImpl busRouteService;
    BusStop busStop;
    private BusStop initBusStop;
    private BusStop finalBusStop;
    private BusRoute busRoute;
    private List<BusRoute> busRouteList;

    @BeforeEach
    void setUp() {
        busStopList = new ArrayList<>();
        busStopList.add(new BusStop(1L, "kalanki", null, new LocationKey(85.273685, 56.28989)));
        busStopList.add(new BusStop(2L, "kalanki2", null, new LocationKey(85.275963, 56.28989)));

        busStop =  new BusStop(1L, "kalanki", null, new LocationKey(85.273685, 56.28989));

        initBusStop = new BusStop(1L, "BusStop1", null, new LocationKey(85.27, 56.2));
        finalBusStop = new BusStop(2L, "BusStop2", null, new LocationKey(85.27, 56.2));
        List<LocationKey> locations = new ArrayList<>();
        locations.add(new LocationKey(85.273685, 56.28989));
        locations.add(new LocationKey(85.275963, 56.28989));
        busRoute = new BusRoute(1L, "RouteA", new HashSet<>(),locations);
        busRouteList = new ArrayList<>();
        busRouteList.add(busRoute);

    }

    @Test
    public void givenBusRoutesInDb_whenFindAll_thenReturnListOfAllBusRoutes(){
        when(this.busRouteRepository.findAll()).thenReturn(busRouteList);

        assertEquals(this.busRouteService.findAll(),busRouteList);

    }

    @Test
    public void  givenBusRouteOfIdExistsInDB_whenFindById_thenReturnThatBusRoute() throws NoRouteFoundException {

        when(busRouteRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(busRoute));

        assertEquals(busRouteService.findById(1L),busRoute);

    }
    @Test
    public void givenBusRouteNotInDb_whenFindById_throwsNoRouteFoundException(){
        when(busRouteRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NoRouteFoundException.class,()->busRouteService.findById(1L));
    }

    @Test
    public void givenBusRouteWithNameExistsInDb_whenFindByName_thenReturnBusRouteWithThatName(){
        when(busRouteRepository.findByName(any(String.class))).thenReturn(busRoute);

        assertEquals(busRouteService.findByName("RouteA"),busRoute);
    }

    @Test
    public void givenBusRoute_whenCreateBusRoute_thenReturnCreatedBusRoute() throws NoBusStopFoundException {
        when(busRouteRepository.save(any(BusRoute.class))).thenReturn(busRoute);

        assertEquals(busRouteService.createNewBusRoute(BusRoute.builder().name("any").locations(new ArrayList<>()).build()),busRoute);
    }

    @Test
    public void givenNewBusRouteAndBusStopsAlreadyExistsOnPath_whenCreateBusRoute_thenAssociateBusStopToBusRoute() throws NoBusStopFoundException {

    when(busRouteRepository.save(any(BusRoute.class))).thenReturn(busRoute);
    when(busStopRepository.findBusStopByLocation(any(LocationKey.class))).thenReturn(busStop);

    assertEquals(busRouteService.createNewBusRoute(busRoute).getBusStopSet().stream().toList().get(0),busStop);

    }

    @Test
    public void givenBusRouteInDb_whenUpdateBusRoute_thenReturnUpdatedBusRoute() throws NoRouteFoundException {
        BusRoute updatedBusRoute = BusRoute.builder().name("new Name").locations(new ArrayList<>()).build();

        when(busRouteRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(busRoute));
        when(busRouteRepository.save(eq(updatedBusRoute))).thenReturn(updatedBusRoute);


        assertEquals(busRouteService.updateBusRoute(1L,updatedBusRoute).getName(),updatedBusRoute.getName());

    }

    @Test
    public void givenBusRouteNotInDb_whenUpdateBusRoute_thenThrowsNoRouteFoundException(){
        when(busRouteRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NoRouteFoundException.class,()->busRouteService.updateBusRoute(1L,busRoute));

    }


    @Test
    public void givenBusRouteIdAndBusRouteInDb_whenDeleteById_thenReturnDeletedRoute() throws NoRouteFoundException {


        when(busRouteRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(busRoute));

        assertEquals(busRouteService.deleteById(1L),busRoute);
        verify(busRouteRepository,times(1)).deleteById(1L);



    }

    @Test
    public void givenBusRouteNotInDb_whenDeleteBusRoute_thenThrowsNoRouteFoundException(){
        when(busRouteRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NoRouteFoundException.class,()->busRouteService.deleteById(1L));
        verify(busRouteRepository,times(0)).deleteById(1L);

    }


}