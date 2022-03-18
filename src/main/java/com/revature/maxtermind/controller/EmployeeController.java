package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
}
