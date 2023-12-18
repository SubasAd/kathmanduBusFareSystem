package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.FareCalculation.Utils;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoRouteFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusRouteRepository;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusStopRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BusStopServiceImpl implements BusStopService{

    final
    BusStopRepository busStopRepository;
    BusRouteRepository busRouteRepository;

    public BusStopServiceImpl(BusStopRepository busStopRepository,BusRouteRepository busRouteRepository) {
        this.busStopRepository = busStopRepository;
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public List<BusStop> findAll() {
       return this.busStopRepository.findAll();
    }

    @Override
    public BusStop findById(Long id) throws NoBusStopFoundException {
        return this.busStopRepository.findById(id).orElseThrow(()->new NoBusStopFoundException());
    }

    @Override
    public BusStop findByLocation(Double Longitude, Double Latitude) {
     return this.busStopRepository.findBusStopsByLatitudeEqualsAndLongitude(Longitude,Latitude);
    }

    @Override
    public BusStop findByName(String name) {
        return this.busStopRepository.findByName(name);
    }


    @Override
    public BusStop createNewBusStop(BusStop busStop) throws NoRouteFoundException {
       Set<BusRoute> busRoute =  busStop.getBusRouteSet();
       Set<BusRoute> busRoutesFromdb = new HashSet<>();
       for(BusRoute each: busRoute){
           BusRoute busRoute1 = this.busRouteRepository.findById(each.getId()).orElse(this.busRouteRepository.save(each));

           busRoutesFromdb.add(busRoute1);
       }
       busStop.setBusRouteSet(busRoutesFromdb);
        BusStop bs = this.busStopRepository.save(busStop);
        return bs;
    }

    @Override
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


    @Override
    public Integer getFare(BusStop bs1, BusStop bs2, Boolean isDiscounted, BusRoute busRoute) throws NoRouteFoundException {
        if(isDiscounted)
            return Utils.fareCalculationDiscounted(bs1,bs2,busRoute);
        return Utils.fareCalculationForNormal(bs1,bs2,busRoute);
    }

    private BusStop getBusStopForUpdate(BusStop busStopOriginal, BusStop busStopUpdated){
        busStopUpdated.setName(CheckNullElse.getName(busStopOriginal,busStopUpdated));
        busStopUpdated.setBusStopLocation(CheckNullElse.getBusLocation(busStopOriginal,busStopUpdated));
        return busStopUpdated;
    }

}
