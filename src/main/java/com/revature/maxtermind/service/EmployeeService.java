package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeService {

    EmployeeRepository repository;
    NotificationService nService;
    PositionService pService;
    ApplicationService aService;

    @Autowired
    public EmployeeService(EmployeeRepository repository,
                           NotificationService nService,
                           PositionService pService,
                           ApplicationService aService) {
        this.repository = repository;
        this.nService = nService;
        this.pService = pService;
        this.aService = aService;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public List<Employee> findAllAdministrators() {
        return repository.findAllAdministrators();
    }

    public List<Employee> findAllManagers() { return repository.findAllManagers(); }

    public List<Employee> findAllByManager(int id) {
        return repository.findAllByManager(repository.findById(id));
    }

    public List<Employee> findAllByPosition(int id) {
        return repository.findAllByPosition(pService.findByPositionId(id));
    }

    public List<Employee> findAllByName(String name) { return repository.findAllByNameContains(name); }

    public Employee findByEmployeeId(int id){
        return repository.findById(id);
    }

    public Employee loadCollectionsByEmployeeId(int id){
        Employee employee = repository.findById(id);
        if(employee!=null){
            employee.setNotifications(nService.findAllByToEmployeeAndUnread(employee, true));
            employee.setApplications(aService.findAllByEmployee(employee));
        }

        return employee;
    }

    public Employee findEmployeeByLogin(String email, String pass){
        Employee employee = repository.findByEmailAndPassword(email, pass);
        if(employee!=null){
            employee.setNotifications(nService.findAllByToEmployeeAndUnread(employee, true));
            employee.setApplications(aService.findAllByEmployee(employee));
        }

        return employee;
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }
    //Do we need to implement a try catch and or control flow if the employee we are looking for does
    //not exist?

    @Transactional
    public boolean deleteEmployee(int id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
