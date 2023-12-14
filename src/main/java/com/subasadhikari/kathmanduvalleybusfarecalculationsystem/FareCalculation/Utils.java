package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.FareCalculation;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import org.springframework.beans.factory.annotation.Value;


public class Utils {
    @Value("$app.discountFactor")
    private  static Double discountFactor;
    public  static Double calculateDistance(BusStop bs1, BusStop bs2) {
        Double x1 = bs1.getLatitude();
        Double x2 = bs2.getLatitude();
        Double y1 = bs1.getLongitute();
        Double y2 = bs2.getLongitute();
        Double distance = Math.pow(Math.pow((x1-x2),2.0)+Math.pow((y1-y2),2.0),0.5);
        return distance;
    }
    public static Integer fareCalculationForNormal(BusStop bs1, BusStop bs2)
    {
        Double distance =  Utils.calculateDistance(bs1,bs2);
        return (int) (15+distance);

    }
    public static Integer fareCalculationDiscounted(BusStop bs1, BusStop bs2)
    {
        Double distance =  Utils.calculateDistance(bs1,bs2);
        return (int)(15+discountFactor*distance);
    }
}
