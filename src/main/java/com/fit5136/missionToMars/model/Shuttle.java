package com.fit5136.missionToMars.model;

import java.util.Date;

public class Shuttle {
    private final long id;
    private final String name;
    private final Date manufactureYear;
    private final int fuelCap;
    private final int passengerCap;
    private final int cargoCap;
    private final int speed;
    private final String origin;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getManufactureYear() {
        return manufactureYear;
    }

    public int getFuelCap() {
        return fuelCap;
    }

    public int getPassengerCap() {
        return passengerCap;
    }

    public int getCargoCap() {
        return cargoCap;
    }

    public int getSpeed() {
        return speed;
    }

    public String getOrigin() {
        return origin;
    }

    public Shuttle(long id, String name, Date manufactureYear, int fuelCap, int passengerCap, int cargoCap, int speed, String origin) {
        this.id = id;
        this.name = name;
        this.manufactureYear = manufactureYear;
        this.fuelCap = fuelCap;
        this.passengerCap = passengerCap;
        this.cargoCap = cargoCap;
        this.speed = speed;
        this.origin = origin;
    }
}
