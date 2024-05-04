package com.example.lab4_20206442.Data;

public class ClimaData {

    private String name;
    private Main main;
    private Coord coord;

    public String getName() {
        return name;
    }
    public Main getMain() {
        return main;
    }

    public Coord getCoord() {
        return coord;
    }

    public ClimaData(String name, Main main, Coord coord) {
        this.name = name;
        this.main = main;
        this.coord = coord;
    }

}
