package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.service;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.FareCalculation.Utils;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.Exceptions.NoBusStopFoundException;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusStopServiceImpl implements BusStopService{

    final
    BusStopRepository busStopRepository;

    public BusStopServiceImpl(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
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
     return this.busStopRepository.findBusStopsByLatitudeEqualsAndLongitute(Longitude,Latitude);
    }

    @Override
    public BusStop findByName(String name) {
        return this.busStopRepository.findByName(name);
    }

    @Override
    public BusStop createNewBusStop(BusStop busStop) {
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
    public Integer getFare(BusStop bs1, BusStop bs2, Boolean isDiscounted) {
        if(isDiscounted)
            return Utils.fareCalculationDiscounted(bs1,bs2);
        return Utils.fareCalculationForNormal(bs1,bs2);
    }

    private BusStop getBusStopForUpdate(BusStop busStopOriginal, BusStop busStopUpdated){
        busStopUpdated.setName(CheckNullElse.getName(busStopOriginal,busStopUpdated));
        busStopUpdated.setLatitude(CheckNullElse.getLatitude(busStopOriginal,busStopUpdated));
        busStopUpdated.setLongitute(CheckNullElse.getLongitude(busStopOriginal,busStopUpdated));
        return busStopUpdated;
    }

}
