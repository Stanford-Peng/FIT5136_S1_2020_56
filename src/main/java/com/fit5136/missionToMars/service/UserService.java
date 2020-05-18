package com.fit5136.missionToMars.service;

import com.fit5136.missionToMars.dao.UserDao;
import com.fit5136.missionToMars.exception.DuplicateUserNameException;
import com.fit5136.missionToMars.exception.UnknownUserIdException;
import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.CriminalRecord;
import com.fit5136.missionToMars.model.HealthRecord;
import com.fit5136.missionToMars.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public List<Candidate> getAll(){
        return userDao.getAll();
    }
    public void insert(Candidate candidate) {
        try{
            userDao.insert(candidate);
        }
        catch(DuplicateUserNameException e){
            e.printStackTrace();
        }
    }
    public Optional<Candidate> findById(long id) {
        try{
            return userDao.findById(id);
        }
        catch (UnknownUserIdException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public void updateById(long id, Candidate candidate) { userDao.updateById(id, candidate); }
    public void deleteById(long id) { userDao.deleteById(id); }
    public void changePassword(long id, String password) { userDao.changePassword(id, password); }
    public void setProfile(long id, Profile profile) {
        profile.setCriminalRecord(new CriminalRecord(new ArrayList()));
        profile.setHealthRecord(new HealthRecord(new ArrayList()));
        userDao.setProfile(id, profile); }
    public int hasDuplicateUsername(String username){ return userDao.hasDuplicateUsername(username); }
    public long candidateLogin(String userName, String password){
        return userDao.candidateLogin(userName, password);
    }
}
