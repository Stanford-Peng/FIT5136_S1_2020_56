package com.fit5136.missionToMars.model;

public class Criteria {
    private int minAge;
    private int maxAge;
    private String[] qualifications;
    private int workExp;
    private String[] occupations;
    private String computerSkill;
    private String[] languages;

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String[] getQualifications() {
        return qualifications;
    }

    public void setQualifications(String[] qualifications) {
        this.qualifications = qualifications;
    }

    public int getWorkExp() {
        return workExp;
    }

    public void setWorkExp(int workExp) {
        this.workExp = workExp;
    }

    public String[] getOccupations() {
        return occupations;
    }

    public void setOccupations(String[] occupations) {
        this.occupations = occupations;
    }

    public String getComputerSkill() {
        return computerSkill;
    }

    public void setComputerSkill(String computerSkill) {
        this.computerSkill = computerSkill;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Criteria(){
        minAge = 20;
        maxAge = 45;
        qualifications = new String[]{"MBA", "MIT", "BS"};
        workExp = 3;
        occupations = new String[]{"manufacturer", "designer", "builder","electrical engineer",
                "developer"};
        computerSkill = "intermediate";
        languages = new String[]{"English", "Mandarin", "French", "Japanese", "Spanish"};
    }
}