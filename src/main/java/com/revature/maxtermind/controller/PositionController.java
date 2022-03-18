package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> findAll() {
        return positionService.findAll();
    }
    @PostMapping
    public Position save(@RequestBody Position position) {
        return positionService.save(position);
    }
}