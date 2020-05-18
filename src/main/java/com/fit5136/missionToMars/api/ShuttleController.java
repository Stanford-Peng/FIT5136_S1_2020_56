package com.fit5136.missionToMars.api;

import com.fit5136.missionToMars.model.Shuttle;
import com.fit5136.missionToMars.service.ShuttleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/shuttle")
public class ShuttleController {
    private final ShuttleService shuttleService;
    @Autowired
    public ShuttleController(ShuttleService shuttleService) {
        this.shuttleService = shuttleService;
    }

    @GetMapping()
    public List<Shuttle> getAll(){ return shuttleService.getAll(); }

    @GetMapping(path = "{id}")
    public Shuttle findById(@PathVariable long id){ return shuttleService.findById(id).orElse(null); }
}
