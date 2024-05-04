package com.example.lab4_20206442.Data;

public class GeoData {

    private String state;
    private String lat;
    private String lon;

    public String getState() {
        return state;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public GeoData(String name, String lat, String lon) {
        this.state = name;
        this.lat = lat;
        this.lon = lon;
    }

}
