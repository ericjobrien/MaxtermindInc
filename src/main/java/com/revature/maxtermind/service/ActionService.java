package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Action;
import com.revature.maxtermind.repository.ActionRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ActionService {

    ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<Action> findAll() {
        return actionRepository.findAll();
    }
}