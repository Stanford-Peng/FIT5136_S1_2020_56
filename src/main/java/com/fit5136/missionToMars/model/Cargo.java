package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cargo {
    private final int id;
    private final String name;
    private final int quantity;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cargo(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
