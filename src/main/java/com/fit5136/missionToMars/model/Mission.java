package main.java.com.fit5136.missionToMars.model;

import com.fit5136.missionToMars.model.Cargo;

import java.util.Date;

public class Mission {
    private final int id;
    private final Date launchDate;
    private final String origin;
    private final int duration;
    private final String[] type;
    private final String desc;
    private final String empReq;
    private final int[] age;
    private final int minWorkExp;
    private final String qualification;
    private final String occupation;
    private final String minComputerSkill;
    private final String language;
    private final String[] SecLanguage;
    private final String cargoFor;
    private final Cargo[] cargos;

    public int getId() {
        return id;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public String getOrigin() {
        return origin;
    }

    public int getDuration() {
        return duration;
    }

    public String[] getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getEmpReq() {
        return empReq;
    }

    public int[] getAge() {
        return age;
    }

    public int getMinWorkExp() {
        return minWorkExp;
    }

    public String getQualification() {
        return qualification;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getMinComputerSkill() {
        return minComputerSkill;
    }

    public String getLanguage() {
        return language;
    }

    public String[] getSecLanguage() {
        return SecLanguage;
    }

    public String getCargoFor() {
        return cargoFor;
    }

    public Cargo[] getCargos() {
        return cargos;
    }

    public Mission(int id, Date launchDate, String origin, int duration, String[] type, String desc, String empReq, int[] age, int minWorkExp, String qualification, String occupation, String minComputerSkill, String language, String[] secLanguage, String cargoFor, Cargo[] cargos) {
        this.id = id;
        this.launchDate = launchDate;
        this.origin = origin;
        this.duration = duration;
        this.type = type;
        this.desc = desc;
        this.empReq = empReq;
        this.age = age;
        this.minWorkExp = minWorkExp;
        this.qualification = qualification;
        this.occupation = occupation;
        this.minComputerSkill = minComputerSkill;
        this.language = language;
        SecLanguage = secLanguage;
        this.cargoFor = cargoFor;
        this.cargos = cargos;
    }
}
