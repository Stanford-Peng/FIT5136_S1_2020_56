package com.fit5136.missionToMars.service;

import com.fit5136.missionToMars.dao.UserDao;
import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    public int insert(Candidate candidate) { return userDao.insert(candidate); }
    public Optional<Candidate> findById(long id) { return userDao.findById(id); }
    public int updateById(long id, Candidate candidate) { return userDao.updateById(id, candidate); }
    public int deleteById(long id) { return userDao.deleteById(id); }
    public int changePassword(long id, String password) { return userDao.changePassword(id, password); }
    public int setProfile(long id, Profile profile) { return userDao.setProfile(id, profile); }
}
