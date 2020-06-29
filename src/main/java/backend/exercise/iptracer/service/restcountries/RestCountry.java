package backend.exercise.iptracer.service.restcountries;

import java.util.List;

public class RestCountry {
    private List<String> languages;
    private String currency;
    private List<String> times;
    private double lat;
    private double lon;

    public RestCountry(List<String> languages, String currency, List<String> times, double lat, double lon) {
        this.languages = languages;
        this.currency = currency;
        this.times = times;
        this.lat = lat;
        this.lon = lon;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getTimes() {
        return times;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
