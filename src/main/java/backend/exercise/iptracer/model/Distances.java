package backend.exercise.iptracer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Distances {
    @JsonProperty("nearest_distance")
    private double nearestDistance = 0.0;
    @JsonProperty("furthest_distance")
    private double furthestDistance = 0.0;
    @JsonProperty("average_distance")
    private double averageDistance = 0.0;

    public Distances() {

    }

    public double getNearestDistance() {
        return nearestDistance;
    }

    public void setNearestDistance(double nearestDistance) {
        this.nearestDistance = nearestDistance;
    }

    public double getFurthestDistance() {
        return furthestDistance;
    }

    public void setFurthestDistance(double furthestDistance) {
        this.furthestDistance = furthestDistance;
    }

    public double getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(double averageDistance) {
        this.averageDistance = averageDistance;
    }

    public void updateDistances(Double distance) {
        if (distance < nearestDistance) nearestDistance = distance;
        if (distance > furthestDistance) furthestDistance = distance;
    }

    public void initializeWith(Double distance) {
        nearestDistance = distance;
        furthestDistance = distance;
        averageDistance = distance;
    }
}