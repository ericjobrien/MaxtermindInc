package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Action;
import com.revature.maxtermind.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping
    public List<Action> findAll() {
        return actionService.findAll();
    }
}
