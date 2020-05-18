package com.fit5136.missionToMars.service;

import com.fit5136.missionToMars.dao.MissionDao;
import com.fit5136.missionToMars.dao.MissionDataAccessService;
import com.fit5136.missionToMars.model.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {
    private final MissionDao missionDao;

    @Autowired
    public MissionService(@Qualifier("missionDao") MissionDao missionDao) {
        this.missionDao = missionDao;
    }
    public List<Mission> getAll(){ return missionDao.getAll(); }
    public Optional<Mission> findById(long id){ return missionDao.findById(id); }
    public int deleteById(long id){ return missionDao.deleteById(id); }
    public int updateById(long id, Mission mission){ return missionDao.updateById(id, mission); }
    public int insert(Mission mission){ return missionDao.insert(mission); }
    public int selectShuttle(long id, Long shuttleId){ return missionDao.selectShuttle(id, shuttleId); }
    public List<Long> findCandidates(long id){ return missionDao.findCandidates(id); }
    public int selectCandidates(long id, List<Long> candidates){
        return missionDao.selectCandidates(id, candidates);}
}
