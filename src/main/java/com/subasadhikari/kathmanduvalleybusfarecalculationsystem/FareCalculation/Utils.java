package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.FareCalculation;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.Location;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.Valleystreet;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.exceptions.NoRouteFoundException;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Utils {
    @Value("$app.discountFactor")
    private  static Double discountFactor;
    public  static Double calculateDistance(BusStop bs1, BusStop bs2, BusRoute busRoute) throws NoRouteFoundException {
        Location bs1Location = bs1.getBusStopLocation();
        Location bs2Location = bs2.getBusStopLocation();
        Set<BusStop> busStopSet = busRoute.getBusStopSet();
        List<Location> busRouteLocations = busRoute.getLocations();
        List<Location> actualTravelledLocation = new ArrayList<>();
        Integer firstIndex = -1;
        Integer lastIndex = -1;
        for (Location l: busRouteLocations) {
            if(l.equals(bs1Location)){
                firstIndex = busRouteLocations.indexOf(l);

            }
            if(l.equals(bs2Location)){
                lastIndex = busRouteLocations.indexOf(l);
            }



        }
        if(firstIndex==-1||lastIndex==-1){
            throw new NoRouteFoundException("The given location isn't in the route");

        }
        if(firstIndex> lastIndex){
            Integer temp = lastIndex;
            lastIndex = firstIndex;
            firstIndex = temp;
        }
        Double distance  = 0.0;
        for(int i = firstIndex;i<lastIndex;i++){
            Location l1 = busRouteLocations.get(i);
            Location l2  = busRouteLocations.get(i+1);
            Double lat1 = l1.getLatitude();
            Double lon1 = l1.getLongitude();
            Double lat2 = l2.getLatitude();
            Double lon2 = l2.getLongitude();
            Double individualDistance = calculateDistance(lat1,lon1,lat2,lon2);
            distance+=individualDistance;

        }
        return distance;



    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Radius of the Earth in kilometers
        double earthRadius = 6371;

        // Calculate the differences in coordinates
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance
        double distance = earthRadius * c;

        return distance;
    }
    public static Integer fareCalculationForNormal(BusStop bs1, BusStop bs2,BusRoute busRoute) throws NoRouteFoundException {
        Double distance =  Utils.calculateDistance(bs1,bs2,busRoute);
        return (int) (15+distance);

    }
    public static Integer fareCalculationDiscounted(BusStop bs1, BusStop bs2,BusRoute busRoute) throws NoRouteFoundException {
        Double distance =  Utils.calculateDistance(bs1,bs2,busRoute);
        return (int)(15+discountFactor*distance);
    }
}
