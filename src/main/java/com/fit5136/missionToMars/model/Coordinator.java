package com.fit5136.missionToMars.model;

public class Coordinator {
    private final int id;
    private final String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinator(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
