package com.revature.maxtermind.repository;



import com.revature.maxtermind.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ActionRepository extends JpaRepository<Action, Integer> {

    List<Action> findAll();
}
