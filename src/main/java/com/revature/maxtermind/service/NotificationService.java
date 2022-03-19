package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    NotificationRepository repository;
    PositionService pService;

    @Autowired
    public NotificationService(NotificationRepository repository, PositionService pService) {
        this.repository = repository;
        this.pService = pService;
    }

    public List<Notification> findAll() {
        return repository.findAll();
    }

    public List<Notification> findAllByUnread(boolean unread) {
        return repository.findAllByUnread(unread);
    }

    public List<Notification> findAllByDescription(String desc) {
        return repository.findAllByDescriptionContains(desc);
    }

    public List<Notification> findAllByDate(Date date) {
        return repository.findAllByDate(date);
    }

    public List<Notification> findAllByRange(Date date1, Date date2) {
        if(date1.before(date2)) return repository.findAllByDateBetween(date1, date2);
        else return repository.findAllByDateBetween(date2, date1);
    }

    public List<Notification> findAllByPosition(int id){
        return repository.findAllByPosition(pService.findByPositionId(id));
    }

    public List<Notification> findAllByToEmployee(Employee toEmployee){
        return repository.findAllByToEmployee(toEmployee);
    }

    public List<Notification> findAllByToEmployeeAndUnread(Employee toEmployee, boolean unread){
        return repository.findAllByToEmployeeAndUnread(toEmployee, unread);
    }

    public Notification findByNotificationId(int id){ return repository.findById(id); }

    @Transactional
    public boolean updateNotificationsByToEmployee(Employee toEmployee){
        try{
            repository.updateNotificationsByToEmployee(toEmployee);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public Notification saveNotification(Notification notification){
        return repository.save(notification);
    }

    @Transactional
    public Notification updateNotification(Notification notification){
        return repository.save(notification);
    }

    @Transactional
    public boolean deleteNotification(int id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}