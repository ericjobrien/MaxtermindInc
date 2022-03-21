package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController extends HandleExceptionController {

    PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Position> findAll() {
        return service.findAll();
    }

    @GetMapping("open")
    public List<Position> findAllOpen() {
        return service.findAllOpen();
    }

    @GetMapping("closed")
    public List<Position> findAllClosed() {
        return service.findAllClosed();
    }

    @GetMapping("name/{name}")
    public List<Position> getPositionsByName(@PathVariable String name){
        return service.findAllByName(name);
    }

    @GetMapping("admin/{admin}")
    public List<Position> getPositionsByAdmin(@PathVariable boolean admin){
        return service.findAllByAdmin(admin);
    }

    @GetMapping("salary/{salary}")
    public List<Position> getPositionsBySalary(@PathVariable double salary){
        return service.findAllBySalary(salary);
    }

    @GetMapping("{id}")
    public Position getPositionById(@PathVariable int id){
        return service.findByPositionId(id);
    }

    @PutMapping
    public Position insertPosition(@RequestBody Position position){
        return service.savePosition(position);
    }

    @PostMapping
    public Position updatePosition(@RequestBody Position position){
        return service.updatePosition(position);
    }

    @DeleteMapping("{id}")
    public boolean deletePositionById(@PathVariable int id){ return service.deletePosition(id); }
}