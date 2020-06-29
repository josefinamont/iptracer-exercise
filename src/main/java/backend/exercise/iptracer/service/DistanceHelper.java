package backend.exercise.iptracer.service;

import org.springframework.stereotype.Component;

@Component
public class DistanceHelper {
    private final double latBuenosAires = -34.637145;
    private final double longBuenosAires = -58.406460;

    public double distance(double lat, double lon) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon = Math.toRadians(lon);
        lat = Math.toRadians(lat);

        // Haversine formula
        double dlon = longBuenosAires - lon;
        double dlat = latBuenosAires - lat;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat) * Math.cos(latBuenosAires)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return Math.round(c * r);
    }
}
