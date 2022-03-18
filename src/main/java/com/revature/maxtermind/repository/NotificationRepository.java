package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAll();
}
