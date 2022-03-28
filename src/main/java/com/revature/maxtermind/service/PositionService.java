package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;

@Service
public class PositionService {

    PositionRepository repository;
    ApplicationService aService;

    @Autowired
    public PositionService(PositionRepository repository, ApplicationService aService) {

        this.repository = repository;
        this.aService = aService;
    }

    public List<Position> findAll() {
        return repository.findAll();
    }

    public List<Position> findAllByName(String name) {
        return repository.findAllByNameContains(name);
    }

    public List<Position> findAllByAdmin(boolean admin) {
        return repository.findAllByAdmin(admin);
    }

    public List<Position> findAllBySalary(double salary) {
        return repository.findAllBySalary(new BigDecimal(salary));
    }

    public List<Position> findAllOpen() { return repository.findAllOpen(); }

    public List<Position> findAllClosed() { return repository.findAllClosed(); }

    public Position findByPositionId(int id) {
        return repository.findById(id);
    }

    public Position loadApplicationsByPositionId(int id) {
        Position position = repository.findById(id);
        if(position!=null){
            position.setApplications(aService.findAllByPosition(position));
        }
        return position;
    }

    @Transactional
    public Position savePosition(Position position){
        return repository.save(position);
    }

    @Transactional
    public Position updatePosition(Position position){
        return repository.save(position);
    }

    @Transactional
    public boolean deletePosition(int id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
