package com.fit5136.missionToMars.dao;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;

import java.util.List;
import java.util.Optional;

public interface MissionToMarsDao {
    int insert(String[] args);
    List<String[]> getAll();
    Optional<String[]> findById(int id);
    int updateById(int id, String args);
    int deleteById(int id);
}
