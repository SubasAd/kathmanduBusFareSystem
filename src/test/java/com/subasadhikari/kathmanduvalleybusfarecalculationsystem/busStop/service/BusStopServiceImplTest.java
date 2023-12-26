package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.repository.BusRouteRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusStopRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusStopServiceImplTest{
    @Mock
    BusStopRepository busStopRepository;
    @Mock
    BusRouteRepository busRouteRepository;
    List<BusStop> busStopList;
    @InjectMocks
    BusStopServiceImpl busStopService;
    BusStop busStop;
    private BusStop initBusStop;
    private BusStop finalBusStop;
    private BusRoute routeTaken;

    @BeforeEach
    void setup(){
         busStopList = new ArrayList<>();
        busStopList.add(new BusStop(1L, "kalanki", null, new LocationKey(85.273685, 56.28989)));
        busStopList.add(new BusStop(2L, "kalanki2", null, new LocationKey(85.275963, 56.28989)));

        busStop =  new BusStop(1L, "kalanki", null, new LocationKey(85.273685, 56.28989));

        initBusStop = new BusStop(1L, "BusStop1", null, new LocationKey(85.27, 56.2));
        finalBusStop = new BusStop(2L, "BusStop2", null, new LocationKey(85.27, 56.2));
        List<LocationKey> locations = new ArrayList<>();
        locations.add(new LocationKey(85.273685, 56.28989));
        locations.add(new LocationKey(85.275963, 56.28989));
        routeTaken = new BusRoute(1L, "RouteA", new HashSet<>(),locations);
    }
    @Test
    public void givenBusStopsInDB_whenFindAll_returnListOfBusStops(){

        when(busStopRepository.findAll()).thenReturn(busStopList);

        List<BusStop> busStopList1 =busStopService.findAll();
        verify(busStopRepository,times(1)).findAll();
        assertEquals(busStopList1,busStopList);
    }
    @Test
    public void givenBusStopInDB_whenFindById_thenBusStopWithBusStop() throws NoBusStopFoundException {

        when(busStopRepository.findById(1L)).thenReturn(Optional.ofNullable(busStop));

        BusStop foundFromService  = busStopService.findById(1L);
        assertEquals(foundFromService,busStop);

    }
    @Test
    public void givenBusStopNotInDb_whenFindById_thenThrowsNoBusStopFoundException() throws NoBusStopFoundException{
        when(busStopRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoBusStopFoundException.class,()->busStopService.findById(1L));
    }
    @Test
    public void givenBusStopWithNameInDB_whenFindByName_thenReturnBusStop() throws NoBusStopFoundException {
        when(busStopRepository.findByName("kalanki")).thenReturn(Optional.ofNullable(busStop));
        assertEquals(busStopService.findByName("kalanki"),busStop);

    }
    @Test
    public void givenBusStopWithNameNotPresentInDb_whenFindByName_thenReturnNoBusStopFoundException(){
        when(busStopRepository.findByName("baneshwor")).thenReturn(Optional.empty());


        assertThrows(NoBusStopFoundException.class,()->busStopService.findByName("baneshwor"));
    }

    @Test
    public void givenBusStopNotInDb_whenCreateBusStop_thenCreateNewBusStop() throws BusStopAlreadyExistsException {
        when(busStopRepository.save(any(BusStop.class))).thenReturn(busStop);

        assertEquals(busStopService.createNewBusStop(busStop),busStop);
    }
    @Test
    public void givenBusStopInDb_whenCreateBusStop_thenThrowsBusStopAlreadyExistsException() throws BusStopAlreadyExistsException{
        when(busStopRepository.findBusStopByLocation(any())).thenReturn(busStop);

        assertThrows(BusStopAlreadyExistsException.class,()->busStopService.createNewBusStop(busStop));
    }

    @Test
    public void givenBusStopNotInDbButBusRouteOfLocationOfNewBusStopExists_whenCreateBusStop_thenSavesBusStopUpdatesBusRoute() throws BusStopAlreadyExistsException{
        List<BusRoute> busRouteList = new ArrayList<>();
        busRouteList.add(routeTaken);

        when(busRouteRepository.findAll()).thenReturn(busRouteList);
        when(busStopRepository.save(any())).thenReturn(busStop);
        when(busRouteRepository.save(any())).thenReturn(routeTaken);

        assertEquals(busStopService.createNewBusStop(busStop),busStop);
        assertEquals(routeTaken.getBusStopSet().size(),1);

        verify(busStopRepository,times(1)).save(any());

    }

    @Test
    public void givenBusStopInDb_whenUpdateBusStop_thenReturnUpdatedBusStop() throws  NoBusStopFoundException{
        when(busStopRepository.findById(any())).thenReturn(Optional.ofNullable(busStop));

        assertEquals(busStopService.updateBusStop(1L,new BusStop(1L,"updatedBusStop",null,busStop.getLocation())).getName(),"updatedBusStop");
    }

    @Test
    public void givenBusStopNotInDb_whenUpdateBusStop_thenThrowsNoBusStopFoundException() throws NoBusStopFoundException{
        when(busStopRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoBusStopFoundException.class,()->busStopService.updateBusStop(1L,busStop));


    }

    @Test
    public void givenBusStopInDb_whenDeleteById_thenReturnsDeletedBusStop() throws NoBusStopFoundException{
        when(busStopRepository.findById(any())).thenReturn(Optional.ofNullable(busStop));

        assertEquals(busStopService.deleteById(1L),busStop);

    }
    @Test
    public void givenBusStopNotInDb_whenDeleteById_thenThrowsNoBusStopFoundException() throws NoBusStopFoundException {
        when(busStopRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NoBusStopFoundException.class,()->busStopService.deleteById(1L));

    }



}