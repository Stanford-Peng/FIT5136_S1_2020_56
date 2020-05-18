package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fit5136.missionToMars.util.StringUtil;

import java.text.SimpleDateFormat;

public class Candidate extends User{
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Candidate(@JsonProperty("id") long id, @JsonProperty("userName") String userName,
                     @JsonProperty("password") String password, @JsonProperty("profile") Profile profile) {
        super(id, userName, password);
        this.profile = profile;
    }
    public String[] toArray(){
        if(profile == null) {
            return new String[]{String.valueOf(getUserId()), getUserName(), getPassword(), "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        }
        StringBuffer buffer = new StringBuffer();
        String[] allergyArray = profile.getAllergies();
        for (String str : allergyArray){
            buffer.append(str).append(", ");
        }
        if (buffer.length() > 1)
            buffer.delete(buffer.length() - 2, buffer.length());
        String allergies = buffer.toString();
        buffer = new StringBuffer();
        for (String str : profile.getQualifications()){
            buffer.append(str).append(", ");
        }
        if (buffer.length() > 1)
            buffer.delete(buffer.length() - 2, buffer.length());
        String qualifications = buffer.toString();
        buffer = new StringBuffer();
        for (int str : profile.getWorkExp()){
            buffer.append(str).append(", ");
        }
        if (buffer.length() > 1)
            buffer.delete(buffer.length() - 2, buffer.length());
        String workExp = buffer.toString();
        buffer = new StringBuffer();
        for (String str : profile.getOccupations()){
            buffer.append(str).append(", ");
        }
        if (buffer.length() > 1)
            buffer.delete(buffer.length() - 2, buffer.length());
        String occupations = buffer.toString();
        buffer = new StringBuffer();
        for (String str : profile.getLanguages()){
            buffer.append(str).append(", ");
        }
        if (buffer.length() > 1)
            buffer.delete(buffer.length() - 2, buffer.length());
        String languages = buffer.toString();
         return new String[]{String.valueOf(getUserId()), getUserName(), getPassword(), profile.getName(),
            new SimpleDateFormat("MM/dd/yyyy").format(profile.getDob()), profile.getStreet(),
                 profile.getCity(), profile.getPostal(), profile.getCountry(), profile.getIdNumber(),
                    profile.getIdType(), profile.getGender(), allergies, profile.getFoodPref(),
                    qualifications, workExp, occupations, profile.getComputerSkill(), languages,
                    StringUtil.removeBoundary(profile.getHealthRecord().toString()),
                    StringUtil.removeBoundary(profile.getCriminalRecord().toString())};
    }
}
