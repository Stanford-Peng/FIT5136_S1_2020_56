package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.*;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<Candidate> getAll();
    void insert(Candidate candidate);
    Optional<Candidate> findById(long id);
    void updateById(long id, Candidate candidate);
    void deleteById(long id);
    void setProfile(long id, Profile profile);
    void changePassword(long id, String password);
    int hasDuplicateUsername(String username);
    List<Candidate> findQualifiedCandidates(Criteria criteria, Mission mission);
    long candidateLogin(String userName, String password);
}
