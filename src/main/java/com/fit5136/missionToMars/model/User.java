package com.fit5136.missionToMars.model;

public abstract class User {
    private final long userId;
    private final String userName;
    private String password;

    public User(long id, String username, String password) {
        userId = id;
        userName = username;
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
