package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findAll();
}
