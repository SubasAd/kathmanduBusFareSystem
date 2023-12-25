package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.repository.BusRouteRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.BusStopAlreadyExistsException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusStopRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BusStopServiceImpl implements BusStopService{
@NonNull
    final
    private BusStopRepository busStopRepository;

@NonNull
    final
    private BusRouteRepository busRouteRepository;

    @Override
    public List<BusStop> findAll() {
       return this.busStopRepository.findAll();
    }

    @Override
    public BusStop findById(Long id) throws NoBusStopFoundException {
        return this.busStopRepository.findById(id).orElseThrow(()->new NoBusStopFoundException());
    }

    @Override
    public BusStop findByName(String name) {
        return this.busStopRepository.findByName(name);
    }
    @Override
    public BusStop createNewBusStop(BusStop busStop) throws BusStopAlreadyExistsException {
        checkIfAlreadyExiststhenThrowBusStopAlreadyFoundException(busStop);
        associateBusStopWithBusRoutesSavesBusRoutes(busStop);
        BusStop  busStop1= busStopRepository.save(busStop);
        return busStop1;
    }

    private void associateBusStopWithBusRoutesSavesBusRoutes(BusStop busStop) {
        List<BusRoute> allBusRoutes = this.busRouteRepository.findAll();
        for (BusRoute busRoute: allBusRoutes) {
            for (LocationKey l: busRoute.getLocations()) {
                if(busStop.getLocation().equals(l)){
                    busRoute.getBusStopSet().add(busStop);
                    this.busRouteRepository.save(busRoute);
                    break;
                }

            }

        }
    }

    private void checkIfAlreadyExiststhenThrowBusStopAlreadyFoundException(BusStop busStop) throws BusStopAlreadyExistsException {
        BusStop busStop1 = this.busStopRepository.findBusStopByLocation(busStop.getLocation());
        if(busStop1 != null){
            throw new BusStopAlreadyExistsException();
        }
    }


    public BusStop updateBusStop(Long id, BusStop busStop) throws NoBusStopFoundException {
        BusStop bs = this.busStopRepository.findById(id).orElseThrow(()->new NoBusStopFoundException());
        BusStop updatedBusStop = this.getBusStopForUpdate(bs,busStop);
        return updatedBusStop;
    }

    @Override
    public BusStop deleteById(Long id) throws NoBusStopFoundException {
        BusStop bs = this.busStopRepository.findById(id).orElseThrow(()-> new NoBusStopFoundException());
        this.busStopRepository.deleteById(bs.getId());
        return bs;
    }

    private BusStop getBusStopForUpdate(BusStop busStopOriginal, BusStop busStopUpdated){
        busStopUpdated.setName(CheckNullElse.getName(busStopOriginal,busStopUpdated));
        return busStopUpdated;
    }

}
