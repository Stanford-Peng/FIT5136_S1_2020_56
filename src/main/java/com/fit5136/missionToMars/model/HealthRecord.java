package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HealthRecord {
    private List<String> healthIssues;


    public List<String> getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(List<String> healthIssues) {
        this.healthIssues = healthIssues;
    }

    public HealthRecord(@JsonProperty("healthIssues") List<String> healthIssues) {
        this.healthIssues = healthIssues;
    }

    @Override
    public String toString() {
        return Optional
                .ofNullable(healthIssues)
                .map(h -> h.toString())
                .orElse("");
    }
}
