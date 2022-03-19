package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findAll();

    List<Application> findAllByEmployee(Employee employee);

    List<Application> findAllByPosition(Position position);

    List<Application> findAllByRecommended(boolean recommended);

    List<Application> findAllBySelected(boolean selected);

    List<Application> findAllByRejected(boolean rejected);

    List<Application> findAllByApproved(boolean approved);

    List<Application> findAllByDate(Date date);

    List<Application> findAllByDateBetween(Date min, Date max);

    Application findById(int id);

    Application findByEmployeeAndPosition(Employee employee, Position position);
}
