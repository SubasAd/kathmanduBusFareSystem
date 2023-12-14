package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.repository;

import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BusStopRepository extends JpaRepository<BusStop,Long> ,BusStopRequiredFields{

}
