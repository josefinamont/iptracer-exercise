package backend.exercise.iptracer.dtos;

public class DistancesResponse {
    private double nearestDistance, furthestDistance, averageDistance;

    public DistancesResponse() {

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
}