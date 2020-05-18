package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.Profile;
import com.fit5136.missionToMars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<Candidate> getAll();
    int insert(Candidate candidate);
    Optional<Candidate> findById(long id);
    int updateById(long id, Candidate candidate);
    int deleteById(long id);
    int setProfile(long id, Profile profile);
    int changePassword(long id, String password);
}
