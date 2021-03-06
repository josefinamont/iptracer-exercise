package backend.exercise.iptracer.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DistanceHelper {
    private static final double LAT_BUENOS_AIRES = Math.toRadians(-34.637145);
    private static final double LON_BUENOS_AIRES = Math.toRadians(-58.406460);

    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceHelper.class);

    public static double distance(double lat, double lon) {
        LOGGER.info("Calculating distance from Buenos Aires");

        lon = Math.toRadians(lon);
        lat = Math.toRadians(lat);

        // Haversine formula
        double dlon = LON_BUENOS_AIRES - lon;
        double dlat = LAT_BUENOS_AIRES - lat;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat) * Math.cos(LAT_BUENOS_AIRES)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return Math.round(c * r);
    }
}
