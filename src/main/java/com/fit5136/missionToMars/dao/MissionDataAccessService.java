package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.Mission;
import com.fit5136.missionToMars.model.Shuttle;

import java.util.List;
import java.util.Optional;

public class MissionDataAccessService implements MissionDao {
    @Override
    public List<Mission> getAll() {
        return null;
    }

    @Override
    public int insert(Mission mission) {
        return 0;
    }

    @Override
    public Optional<Mission> findById(long id) {
        return Optional.empty();
    }

    @Override
    public int updateById(long id, Mission mission) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public int selectShuttle(long id, Shuttle shuttle) {
        return 0;
    }

    @Override
    public List<Candidate> findCandidates(long id) {
        return null;
    }

    @Override
    public int selectCandidates(long id, List<Candidate> candidates) {
        return 0;
    }
}
