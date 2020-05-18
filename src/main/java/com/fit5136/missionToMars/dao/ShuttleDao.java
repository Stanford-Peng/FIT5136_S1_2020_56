package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Shuttle;

import java.util.List;
import java.util.Optional;

public interface ShuttleDao {
    List<Shuttle> getAll();
    Optional<Shuttle> findById(long id);
}
