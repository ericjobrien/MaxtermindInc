package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    List<Position> findAll();

    List<Position> findAllByNameContains(String name);

    List<Position> findAllByIsAdmin(boolean isAdmin);

    List<Position> findAllBySalary(BigDecimal salary);

    List<Position> findAllByManager(Employee manager);

    @Query("from Position p where p not in ( select e.position from Employee e ) ")
    List<Position> findAllOpen();

    @Query("from Position p where p in ( select e.position from Employee e ) ")
    List<Position> findAllClosed();

    Position findById(int id);

}
