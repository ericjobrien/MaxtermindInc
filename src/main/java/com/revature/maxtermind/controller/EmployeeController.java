package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name){
        return service.findAllByName(name);
    }

    @GetMapping("administrators")
    public List<Employee> getAdministrators(){
        return service.findAllAdministrators();
    }

    @GetMapping("managers")
    public List<Employee> getManagers(){
        return service.findAllManagers();
    }

    @GetMapping("manager/{managerId}")
    public List<Employee> getEmployeesByManager(@PathVariable int managerId){
        return service.findAllByManager(managerId);
    }

    @GetMapping("position/{positionId}")
    public List<Employee> getEmployeesByPosition(@PathVariable int positionId){
        return service.findAllByPosition(positionId);
    }

    @PostMapping("login")
    public Employee getEmployeeByLogin(@RequestParam String email,  @RequestParam String password){
        return service.findEmployeeByLogin(email, password);
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return service.loadNotificationsUnreadByEmployeeId(id);
    }

    @PutMapping
    public Employee insertEmployee(@RequestBody Employee employee){
        return service.saveEmployee(employee);
    }

    @PostMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        return service.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public boolean deleteEmployeeById(@PathVariable int id){ return service.deleteEmployee(id); }
}
