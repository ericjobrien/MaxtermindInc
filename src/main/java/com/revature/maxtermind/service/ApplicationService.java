package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {

    ApplicationRepository repository;
    PositionService pService;
    EmployeeService eService;

    @Autowired
    public ApplicationService(ApplicationRepository repository, PositionService pService, EmployeeService eService) {
        this.repository = repository;
        this.pService = pService;
        this.eService = eService;
    }

    public List<Application> findAll() {
        return repository.findAll();
    }

    public List<Application> findAllByPosition(int positionId) {
        return repository.findAllByPosition(pService.findByPositionId(positionId));
    }

    public List<Application> findAllByEmployee(int employeeId) {
        return repository.findAllByEmployee(eService.findByEmployeeId(employeeId));
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

    public List<Application> findAllByDate(Date date) {
        return repository.findAllByDate(date);
    }

    public List<Application> findAllByRange(Date date1, Date date2) {
        if(date1.before(date2)) return repository.findAllByDateBetween(date1, date2);
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
