package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PositionRepository extends JpaRepository<Position, Integer> {

    List<Position> findAll();

}
