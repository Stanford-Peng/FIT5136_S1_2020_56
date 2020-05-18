package com.fit5136.missionToMars.api;

import com.fit5136.missionToMars.model.Mission;
import com.fit5136.missionToMars.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mission")
public class MissionController {
    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public List<Mission> getAll(){ return missionService.getAll(); }

    @GetMapping(path = "{id}")
    public Mission findById(@PathVariable long id){ return missionService.findById(id).orElse(null); }

    @PostMapping()
    public void insert(@RequestBody Mission mission){ missionService.insert(mission); }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable long id){ missionService.deleteById(id); }

    @PutMapping(path = "{id}")
    public void updateById(@PathVariable long id, @RequestBody Mission mission){
        missionService.updateById(id, mission);
    }

    @PutMapping(path = "/selectShuttle/{id}")
    public void selectShuttle(@PathVariable long id, @RequestBody Long shuttleId){
        missionService.selectShuttle(id, shuttleId);
    }

    @GetMapping(path = "/findCandidates/{id}")
    public List<Long> findCandidates(@PathVariable long id){
        return missionService.findCandidates(id);
    }

    @PutMapping(path = "/selectCandidates/{id}")
    public void selectCandidates(@PathVariable long id, @RequestBody List<Long> candidates){
        missionService.selectCandidates(id, candidates);
    }
}
