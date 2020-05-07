package com.fit5136.missionToMars.model;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Mission {
    private final long id;
    private String missionName;
    private String missionDesc;
    private String origin;
    private String[] allowedCountries;
    private Coordinator coordinator;
    private HashMap<String, String> jobs;
    private Date launchDate;
    private int duration;
    private String cargoFor;
    private HashMap<String, String> empReq;
    private String status;
    private List<Candidate> candidates;
    private Shuttle shuttle;

    public Mission(long id, String missionName, String missionDesc, String origin,
                   String[] allowedCountries, Coordinator coordinator, HashMap<String, String> jobs,
                   Date launchDate, int duration, String cargoFor, HashMap<String, String> empReq,
                   String status, List<Candidate> candidates, Shuttle shuttle) {
        this.id = id;
        this.missionName = missionName;
        this.missionDesc = missionDesc;
        this.origin = origin;
        this.allowedCountries = allowedCountries;
        this.coordinator = coordinator;
        this.jobs = jobs;
        this.launchDate = launchDate;
        this.duration = duration;
        this.cargoFor = cargoFor;
        this.empReq = empReq;
        this.status = status;
        this.candidates = candidates;
        this.shuttle = shuttle;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Shuttle getShuttle() {
        return shuttle;
    }

    public void setShuttle(Shuttle shuttle) {
        this.shuttle = shuttle;
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

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
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

    public HashMap<String, String> getEmpReq() {
        return empReq;
    }

    public void setEmpReq(HashMap<String, String> empReq) {
        this.empReq = empReq;
    }
}
