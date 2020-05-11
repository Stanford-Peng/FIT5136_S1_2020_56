package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinatorInfo {
    private String name;
    private String email;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CoordinatorInfo(@JsonProperty String name,
                           @JsonProperty String email, @JsonProperty String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
