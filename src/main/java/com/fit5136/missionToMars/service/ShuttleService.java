package com.fit5136.missionToMars.service;

import com.fit5136.missionToMars.dao.ShuttleDao;
import com.fit5136.missionToMars.model.Shuttle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShuttleService {
    private final ShuttleDao shuttleDao;
    @Autowired
    public ShuttleService(@Qualifier("shuttleDao") ShuttleDao shuttleDao) {
        this.shuttleDao = shuttleDao;
    }

    public List<Shuttle> getAll(){ return shuttleDao.getAll(); }
    public Optional<Shuttle> findById(long id){ return shuttleDao.findById(id); }
}
