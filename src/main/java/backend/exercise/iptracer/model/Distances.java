package backend.exercise.iptracer.model;

public class Distances {
    private double nearestDistance = 0.0, furthestDistance = 0.0, averageDistance = 0.0;

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