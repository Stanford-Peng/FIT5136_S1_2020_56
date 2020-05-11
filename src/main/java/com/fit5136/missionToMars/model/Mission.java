package com.fit5136.missionToMars.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fit5136.missionToMars.util.StringUtil;

import java.text.SimpleDateFormat;
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

    public Mission(@JsonProperty("id") long id, @JsonProperty("missionName") String missionName,
                   @JsonProperty("missionDesc") String missionDesc, @JsonProperty("origin") String origin,
                   @JsonProperty("allowedCountries") String[] allowedCountries,
                   @JsonProperty("coordinatorInfo") CoordinatorInfo coordinatorInfo,
                   @JsonProperty("jobs") HashMap<String, String> jobs,
                   @JsonProperty("launchDate") Date launchDate,
                   @JsonProperty("duration") int duration, @JsonProperty("cargoFor") String cargoFor,
                   @JsonProperty("empReq") HashMap<String, Integer> empReq,
                   @JsonProperty("status") String status, @JsonProperty("candidates") List<Long> candidates,
                   @JsonProperty("shuttleId") Long shuttleId) {
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
        StringBuffer buffer = new StringBuffer();
        for (String allowedCountry : allowedCountries) {
            buffer.append(allowedCountry);
        }
        String countries = buffer.toString();
        buffer.setLength(0);
        candidates.forEach(c -> buffer.append(String.valueOf(c)).append(", "));
        if (candidates.size() > 0)
            buffer.delete(buffer.length() - 2, buffer.length());
        String cans = buffer.toString();
        return new String[]{String.valueOf(id), missionName, missionDesc, origin, countries,
                coordinatorInfo.getName(), coordinatorInfo.getEmail(), coordinatorInfo.getPhone(),
                StringUtil.removeBoundary(jobs.keySet().toString()),
                StringUtil.removeBoundary(jobs.values().toString()),
                new SimpleDateFormat("MM/dd/yyyy").format(launchDate), String.valueOf(duration),
                cargoFor, StringUtil.removeBoundary(empReq.keySet().toString()),
                StringUtil.removeBoundary(empReq.values().toString()), status,
                cans, shuttleId == null? "" : String.valueOf(shuttleId)};
    }
}
