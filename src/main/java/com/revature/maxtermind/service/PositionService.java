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

    @Autowired
    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public List<Position> findAll() {
        return repository.findAll();
    }

    public List<Position> findAllByName(String name) {
        return repository.findAllByNameContains(name);
    }

    public List<Position> findAllByIsAdmin(boolean isAdmin) {
        return repository.findAllByIsAdmin(isAdmin);
    }

    public List<Position> findAllBySalary(double salary) {
        return repository.findAllBySalary(new BigDecimal(salary));
    }

    public List<Position> findAllOpen() { return repository.findAllOpen(); }

    public List<Position> findAllClosed() { return repository.findAllClosed(); }

    public Position findByPositionId(int id) {
        return repository.findById(id);
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
