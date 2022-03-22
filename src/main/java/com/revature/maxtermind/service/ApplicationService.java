package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.ApplicationRepository;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    ApplicationRepository repository;
    PositionRepository pRepository;
    EmployeeRepository eRepository;

    @Autowired
    public ApplicationService(ApplicationRepository repository,
                              EmployeeRepository eRepository,
                              PositionRepository pRepository) {
        this.repository = repository;
        this.pRepository = pRepository;
        this.eRepository = eRepository;
    }

    public List<Application> findAll() {
        return repository.findAll();
    }

    public List<Application> findAllByPosition(int positionId) {
        return this.findAllByPosition(pRepository.findById(positionId));
    }

    public List<Application> findAllByPosition(Position position) {
        return repository.findAllByPosition(position);
    }

    public List<Application> findAllByEmployee(int employeeId) {
        return findAllByEmployee(eRepository.findById(employeeId));
    }

    public List<Application> findAllByEmployee(Employee employee) {
        return repository.findAllByEmployee(employee);
    }

    public List<Application> findAllByRecommended(boolean recommended) {
        return repository.findAllByRecommended(recommended);
    }

    public List<Application> findAllBySelected(boolean selected) {
        return repository.findAllBySelected(selected);
    }

    public List<Application> findAllByRejected(boolean rejected) {
        return repository.findAllByRejected(rejected);
    }

    public List<Application> findAllByApproved(boolean approved) {
        return repository.findAllByApproved(approved);
    }

    public List<Application> findAllByDate(LocalDate date) {
        return repository.findAllByDate(date);
    }

    public List<Application> findAllByRange(LocalDate date1, LocalDate date2) {
        if(date1.isBefore(date2)) return repository.findAllByDateBetween(date1, date2);
        else return repository.findAllByDateBetween(date2, date1);
    }

    public Application findByApplicationId(int id) { return repository.findById(id); }

    public Application findByEmployeeAndPosition(Employee employee, Position position) {
        return repository.findByEmployeeAndPosition(employee, position);
    }

    @Transactional
    public Application saveApplication(Application application){
        return repository.save(application);
    }

    @Transactional
    public Application updateApplication(Application application){
        return repository.save(application);
    }

    @Transactional
    public boolean deleteApplication(int id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
