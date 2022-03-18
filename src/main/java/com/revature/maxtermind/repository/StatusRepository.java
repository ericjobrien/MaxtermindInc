package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface StatusRepository extends JpaRepository<Status, Integer> {
    List<Status> findAll();
}
