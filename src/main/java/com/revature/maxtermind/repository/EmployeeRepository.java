package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();
}

