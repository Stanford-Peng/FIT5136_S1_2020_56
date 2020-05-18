package com.fit5136.missionToMars.api;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.Profile;
import com.fit5136.missionToMars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Candidate> getAll(){ return service.getAll(); }

    @PostMapping
    public void insert(@RequestBody Candidate candidate){
        service.insert(candidate);
    }
    @GetMapping(path = "{id}")
    public Candidate findById(@PathVariable("id") long id){
        return service.findById(id).orElse(null);
    }
    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id") long id, @RequestBody Candidate candidate){
        service.updateById(id, candidate);
    }
    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") long id){
        service.deleteById(id);
    }

    @PutMapping(path = "/setProfile/{id}")
        public void setProfile(@PathVariable("id") long id, @RequestBody Profile profile){
            service.setProfile(id, profile);
    }

    @PutMapping(path = "changePassword/{id}")
    public void changePassword(@PathVariable("id") long id, @RequestBody String password){
        service.changePassword(id, password);
    }
}
