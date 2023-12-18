package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {
    final
    BusRouteRepository busRouteRepository;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

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
    public BusRoute createNewBusRoute(BusRoute busRoute) {
       BusRoute busRoute1 =  this.busRouteRepository.save(busRoute);
       return busRoute1;
    }

    @Override
    public BusRoute updateBusRoute(Long id, BusRoute busRoute) throws NoRouteFoundException {
        BusRoute busRoute1 = this.busRouteRepository.findById(id).orElseThrow(()->new NoRouteFoundException());
        busRoute.setId(id);
        busRoute = this.getBusRouteForUpdate(busRoute1,busRoute);
       return this.busRouteRepository.save(busRoute);

    }

    @Override
    public BusRoute deleteById(Long id) throws NoRouteFoundException {
            BusRoute busRoute =  this.busRouteRepository.findById(id).orElseThrow(()->new NoRouteFoundException());
            this.busRouteRepository.deleteById(busRoute.getId());
            return busRoute;

    }
    public BusRoute getBusRouteForUpdate(BusRoute originalBusRoute, BusRoute updatedBusRoute){
        updatedBusRoute.setName(CheckNullElse.getName(originalBusRoute, updatedBusRoute));
        updatedBusRoute.setBusStopSet(CheckNullElse.getBusStops(originalBusRoute, updatedBusRoute));
        return updatedBusRoute;
    }
}
