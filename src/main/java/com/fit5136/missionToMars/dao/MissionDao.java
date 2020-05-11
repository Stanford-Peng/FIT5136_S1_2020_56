package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.Mission;
import com.fit5136.missionToMars.model.Shuttle;

import java.util.List;
import java.util.Optional;

public interface MissionDao {
    List<Mission> getAll();
    int insert(Mission mission);
    Optional<Mission> findById(long id);
    int updateById(long id, Mission mission);
    int deleteById(long id);
    int selectShuttle(long id, Long shuttleId);
    List<Long> findCandidates(long id);
    int selectCandidates(long id, List<Long> candidates);
}
