package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.repository.BusRouteRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusStopRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service.BusStopService;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusRouteServiceImpl implements BusRouteService {
    @NonNull
    final

    private BusRouteRepository busRouteRepository;

    @NonNull
    final
    private BusStopRepository busStopRepository;


    @Override
    public List<BusRoute> findAll() {
       return this.busRouteRepository.findAll();

    }

    @Override
    public BusRoute findById(Long Id) throws NoRouteFoundException {
        return this.busRouteRepository.findById(Id).orElseThrow(()-> new NoRouteFoundException());

    }

    @Override
    public BusRoute findByName(String name) {
        return this.busRouteRepository.findByName(name);

    }


    @Override
    public BusRoute createNewBusRoute(BusRoute busRoute) throws NoBusStopFoundException {
        List<LocationKey> locations = busRoute.getLocations();
        for(LocationKey location: locations){
            busRoute.getBusStopSet().add(this.busStopRepository.findBusStopByLocation(location));
        }

       BusRoute busRoute1 =  this.busRouteRepository.save(busRoute);
       return busRoute1;
    }

    @Override
    public BusRoute updateBusRoute(Long id, BusRoute busRoute) throws NoRouteFoundException {
        BusRoute busRoute1 = getBusRouteThrowsBusRouteNotFoundException(id);
        busRoute.setId(id);
        busRoute = this.getBusRouteForUpdate(busRoute1,busRoute);
       return this.busRouteRepository.save(busRoute);

    }

    private BusRoute getBusRouteThrowsBusRouteNotFoundException(Long id) throws NoRouteFoundException {
        BusRoute busRoute1 = this.busRouteRepository.findById(id).orElseThrow(()->new NoRouteFoundException());
        return busRoute1;
    }

    @Override
    public BusRoute deleteById(Long id) throws NoRouteFoundException {
        BusRoute busRoute = getBusRouteThrowsBusRouteNotFoundException(id);
        this.busRouteRepository.deleteById(busRoute.getId());
        return busRoute;

    }
    public BusRoute getBusRouteForUpdate(BusRoute originalBusRoute, BusRoute updatedBusRoute){
        updatedBusRoute.setName(CheckNullElse.getName(originalBusRoute, updatedBusRoute));
        updatedBusRoute.setBusStopSet(CheckNullElse.getBusStops(originalBusRoute, updatedBusRoute));
        return updatedBusRoute;
    }


    }

