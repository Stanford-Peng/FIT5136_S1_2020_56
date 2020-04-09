package com.fit5136.missionToMars.model;

import java.util.Date;

public class Candidate {
    private final int id;
    private final String name;
    private final Date dob;
    private final String street;
    private final String city;
    private final String postal;
    private final String country;
    private final String phone;
    private final String idType;
    private final String gender;
    private final String allergy;
    private final String foodPref;
    private final String[] qualifications;
    private final int[] workExp;
    private final String[] occupations;
    private final String computerSkill;
    private final String[] languages;

    public int getId() {
        return id;
    }

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

    public String getPostal() {
        return postal;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdType() {
        return idType;
    }

    public String getGender() {
        return gender;
    }

    public String getAllergy() {
        return allergy;
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

    public String getComputerSkill() {
        return computerSkill;
    }

    public String[] getLanguages() {
        return languages;
    }

    public Candidate(int id, String name, Date dob, String street, String city, String postal, String country, String phone, String idType, String gender, String allergy, String foodPref, String[] qualifications, int[] workExp, String[] occupations, String computerSkill, String[] languages) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.street = street;
        this.city = city;
        this.postal = postal;
        this.country = country;
        this.phone = phone;
        this.idType = idType;
        this.gender = gender;
        this.allergy = allergy;
        this.foodPref = foodPref;
        this.qualifications = qualifications;
        this.workExp = workExp;
        this.occupations = occupations;
        this.computerSkill = computerSkill;
        this.languages = languages;
    }
}
