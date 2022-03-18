package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
