package com.fit5136.missionToMars.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fit5136.missionToMars.model.Cargo;
import com.fit5136.missionToMars.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("api/cargo")
@RestController
public class cargoController {
    private final CargoService service;

    @Autowired
    public cargoController(CargoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cargo> getAll(){ return service.getAll(); }

    @PostMapping
    public void insert(@RequestBody Cargo cargo){
        service.insert(cargo);
    }
    @GetMapping(path = "{id}")
    public Cargo findById(@PathVariable("id") int id){
        return service.findById(id).orElse(null);
    }
    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id") int id, @RequestBody Cargo cargo){
        service.updateById(id, cargo);
    }
    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") int id){
        service.deleteById(id);
    }
}
