package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Profile {
    private String name;
    private Date dob;
    private String street;
    private String city;
    private String postal;
    private String country;
    private String idNumber;
    private String idType;
    private String gender;
    @JsonProperty("allergies")
    private String[] allergies;
    private String foodPref;
    private String[] qualifications;
    private int[] workExp;
    private String[] occupations;
    private String computerSkill;
    private String[] languages;


    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    public void setFoodPref(String foodPref) {
        this.foodPref = foodPref;
    }

    public void setQualifications(String[] qualifications) {
        this.qualifications = qualifications;
    }

    public void setWorkExp(int[] workExp) {
        this.workExp = workExp;
    }

    public void setOccupations(String[] occupations) {
        this.occupations = occupations;
    }

    public void setComputerSkill(String computerSkill) {
        this.computerSkill = computerSkill;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getIdNumber() { return idNumber; }

    public String getPostal() { return postal; }

    public void setPostal(String postal) { this.postal = postal; }

    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIdType() {
        return idType;
    }

    public String getGender() {
        return gender;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public String getFoodPref() {
        return foodPref;
    }

    public String[] getQualifications() {
        return qualifications;
    }

    public int[] getWorkExp() {
        return workExp;
    }

    public String[] getOccupations() {
        return occupations;
    }

    public String getComputerSkill() { return computerSkill; }

    public String[] getLanguages() {
        return languages;
    }

    public Profile(@JsonProperty("name") String name,
                   @JsonProperty("dob") Date dob, @JsonProperty("street") String street,
                   @JsonProperty("city") String city, @JsonProperty("postal") String postal,
                   @JsonProperty("country") String country, @JsonProperty("idNumber") String idNumber,
                   @JsonProperty("idType") String idType, @JsonProperty("gender") String gender,
                   @JsonProperty("allergies") String[] allergies,
                   @JsonProperty("foodPref") String foodPref,
                   @JsonProperty("qualifications") String[] qualifications,
                   @JsonProperty("workExp") int[] workExp,
                   @JsonProperty("occupations") String[] occupations,
                   @JsonProperty("computerSkill") String computerSkill,
                   @JsonProperty("languages") String[] languages) {
        this.name = name;
        this.dob = dob;
        this.street = street;
        this.city = city;
        this.postal = postal;
        this.country = country;
        this.idNumber = idNumber;
        this.idType = idType;
        this.gender = gender;
        this.allergies = allergies;
        this.foodPref = foodPref;
        this.qualifications = qualifications;
        this.workExp = workExp;
        this.occupations = occupations;
        this.computerSkill = computerSkill;
        this.languages = languages;
    }

}
