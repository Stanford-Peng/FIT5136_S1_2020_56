package com.fit5136.missionToMars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinator extends User{

    public Coordinator(long id, String username, String pwd) {
        super(id, username, pwd);
    }
}
