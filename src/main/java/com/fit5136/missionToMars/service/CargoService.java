package com.fit5136.missionToMars.service;

import com.fit5136.missionToMars.dao.CargoDao;
import com.fit5136.missionToMars.model.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    private final CargoDao dao;

    @Autowired
    public CargoService(@Qualifier("cargoDao") CargoDao dao) {
        this.dao = dao;
    }

    public List<Cargo> getAll(){
        return dao.getAll();
    }

    public int insert(Cargo cargo) { return dao.insert(cargo); }
    public Optional<Cargo> findById(int id) { return dao.findById(id); }
    public int updateById(int id, Cargo cargo) { return dao.updateById(id, cargo); }
    public int deleteById(int id) { return dao.deleteById(id); }
}
