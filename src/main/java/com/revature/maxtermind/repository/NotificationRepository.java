package com.revature.maxtermind.repository;

import com.revature.maxtermind.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAll();

    List<Notification> findAllByUnread(boolean unread);

    List<Notification> findAllByPosition(Position position);

    List<Notification> findAllByToEmployee(Employee toEmployee);

    List<Notification> findAllByToEmployeeAndUnread(Employee toEmployee, boolean unread);

    List<Notification> findAllByDescriptionContains(String description);

    List<Notification> findAllByDate(LocalDate date);

    List<Notification> findAllByDateBetween(LocalDate min, LocalDate max);

    Notification findById(int id);

    @Modifying
    @Query("update Notification n set n.unread = false where n.toEmployee = :toEmployee")
    void updateNotificationsByToEmployee(Employee toEmployee);
}
