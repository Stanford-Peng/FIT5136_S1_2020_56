package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class User {
    private final long userId;
    private final String userName;
    private String password;

    public User(@JsonProperty("userId") long userId, @JsonProperty("userName") String userName,
                @JsonProperty("password") String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
