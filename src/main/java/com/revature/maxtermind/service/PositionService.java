package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.PositionRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PositionService {

    PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
