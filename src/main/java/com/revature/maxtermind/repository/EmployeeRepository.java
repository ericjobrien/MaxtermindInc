package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();

    List<Employee> findAllByPosition(Position position);

    @Query("from Employee e join e.position p where p.manager = :manager")
    List<Employee> findAllByManager(Employee manager);

    @Query("from Employee e where concat(e.firstName, e.lastName) like %:name%")
    List<Employee> findAllByNameContains(String name);

    @Query("from Employee e join e.position p where p.isAdmin is true ")
    List<Employee> findAllAdministrators();

    @Query("from Employee e where e in ( select p.manager from Position p )")
    List<Employee> findAllManagers();

    Employee findById(int id);

    Employee findByEmailAndPassword(String email, String password);

}

