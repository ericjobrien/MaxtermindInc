package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Notification;
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

    @Autowired
    public EmployeeService(EmployeeRepository repository,
                           NotificationService nService,
                           PositionService pService) {
        this.repository = repository;
        this.nService = nService;
        this.pService = pService;
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

    public Employee loadNotificationsUnreadByEmployeeId(int id){
        Employee employee = repository.findById(id);
        if(employee!=null){
            employee.setNotifications(findNotifications(employee, false));
        }

        return employee;
    }

    public Employee findEmployeeByLogin(String email, String pass){
        Employee employee = repository.findByEmailAndPassword(email, pass);
        if(employee!=null){
            employee.setNotifications(findNotifications(employee, false));
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

    @Transactional
    public boolean deleteEmployee(int id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private List<Notification> findNotifications(Employee employee, boolean all){
        if(all) return nService.findAllByToEmployee(employee);
        else return nService.findAllByToEmployeeAndUnread(employee, true);
    }
}
