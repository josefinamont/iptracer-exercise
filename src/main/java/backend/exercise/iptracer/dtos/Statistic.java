package backend.exercise.iptracer.dtos;

public class Statistic {
    private double distance = 0;
    private int invocations = 0;

    public Statistic() {

    }

    public Statistic(double distance, int invocations) {
        this.distance = distance;
        this.invocations = invocations;
    }

    public void incrementInvocations() {
        this.invocations++;
    }

    public void withDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public int getInvocations() {
        return invocations;
    }
}
