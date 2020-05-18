package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CriminalRecord {
    private List<String> crimes;

    public List<String> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<String> crimes) {
        this.crimes = crimes;
    }

    public CriminalRecord(@JsonProperty("criminalRecord") List<String> crimes) {
        this.crimes = crimes;
    }


    @Override
    public String toString() {
        return Optional
                .ofNullable(crimes)
                .map(c -> c.toString())
                .orElse("");
    }
}

