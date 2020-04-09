package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Cargo;

import java.util.List;
import java.util.Optional;

public interface CargoDao {
    int insert(Cargo cargo);
    List<Cargo> getAll();
    Optional<Cargo> findById(int id);
    int updateById(int id, Cargo cargo);
    int deleteById(int id);
}
