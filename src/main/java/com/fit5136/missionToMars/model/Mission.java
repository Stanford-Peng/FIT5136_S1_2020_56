package com.fit5136.missionToMars.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Mission {
    private final long id;
    private String missionName;
    private String missionDesc;
    private String origin;
    private String[] allowedCountries;
    private CoordinatorInfo coordinatorInfo;
    private HashMap<String, String> jobs;
    private Date launchDate;
    private int duration;
    private String cargoFor;
    private HashMap<String, Integer> empReq;
    private String status;
    private List<Long> candidates;
    private Long shuttleId;

    public Mission(@JsonProperty long id, @JsonProperty String missionName,
                   @JsonProperty String missionDesc, @JsonProperty String origin,
                   @JsonProperty String[] allowedCountries,
                   @JsonProperty CoordinatorInfo coordinatorInfo,
                   @JsonProperty HashMap<String, String> jobs, @JsonProperty Date launchDate,
                   @JsonProperty int duration, @JsonProperty String cargoFor,
                   @JsonProperty HashMap<String, Integer> empReq,
                   @JsonProperty String status, @JsonProperty List<Long> candidates,
                   @JsonProperty Long shuttleId) {
        this.id = id;
        this.missionName = missionName;
        this.missionDesc = missionDesc;
        this.origin = origin;
        this.allowedCountries = allowedCountries;
        this.coordinatorInfo = coordinatorInfo;
        this.jobs = jobs;
        this.launchDate = launchDate;
        this.duration = duration;
        this.cargoFor = cargoFor;
        this.empReq = empReq;
        this.status = status;
        this.candidates = candidates;
        this.shuttleId = shuttleId;
    }

    public List<Long> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Long> candidates) {
        this.candidates = candidates;
    }

    public Long getShuttleId() {
        return shuttleId;
    }

    public void setShuttleId(Long shuttleId) {
        this.shuttleId = shuttleId;
    }

    public long getId() {
        return id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionDesc() {
        return missionDesc;
    }

    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String[] getAllowedCountries() {
        return allowedCountries;
    }

    public void setAllowedCountries(String[] allowedCountries) {
        this.allowedCountries = allowedCountries;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CoordinatorInfo getCoordinatorInfo() {
        return coordinatorInfo;
    }

    public void setCoordinatorInfo(CoordinatorInfo coordinatorInfo) {
        this.coordinatorInfo = coordinatorInfo;
    }

    public HashMap<String, String> getJobs() {
        return jobs;
    }

    public void setJobs(HashMap<String, String> jobs) {
        this.jobs = jobs;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCargoFor() {
        return cargoFor;
    }

    public void setCargoFor(String cargoFor) {
        this.cargoFor = cargoFor;
    }

    public HashMap<String, Integer> getEmpReq() {
        return empReq;
    }

    public void setEmpReq(HashMap<String, Integer> empReq) {
        this.empReq = empReq;
    }

    public String[] toArray(){
        return null;
    }
}
