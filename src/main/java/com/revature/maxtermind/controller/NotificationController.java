package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController extends HandleExceptionController {

    NotificationService service;

    @Autowired
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notification> findAll() {
        return service.findAll();
    }

    @GetMapping("unread/{unread}")
    public List<Notification> findAllByUnread(@PathVariable boolean unread) {

        return service.findAllByUnread(unread);
    }

    @GetMapping("position/{positionId}")
    public List<Notification> findAllByPosition(@PathVariable int positionId) {

        return service.findAllByPosition(positionId);
    }

    @GetMapping("toemployee/{toEmployeeId}")
    public List<Notification> findAllByToEmployee(@PathVariable int toEmployeeId) {

        return service.findAllByToEmployee(toEmployeeId);
    }

    @GetMapping("description/{description}")
    public List<Notification> findAllByDescription(@PathVariable String description) {

        return service.findAllByDescription(description);
    }

    @GetMapping("date/{date}")
    public List<Notification> findAllByDate(@PathVariable LocalDate date) {

        return service.findAllByDate(date);
    }

    @GetMapping("date/{date1}/{date2}")
    public List<Notification> findAllByRange(@PathVariable LocalDate date1, @PathVariable LocalDate date2) {

        return service.findAllByRange(date1, date2);
    }

    @GetMapping("{id}")
    public Notification getNotificationById(@PathVariable int id){
        return service.findByNotificationId(id);
    }

    @PutMapping
    public Notification insertNotification(@RequestBody Notification notification){
        return service.saveNotification(notification);
    }

    @PostMapping
    public Notification updateNotification(@RequestBody Notification notification){
        return service.updateNotification(notification);
    }

    @DeleteMapping("{id}")
    public boolean deleteNotificationById(@PathVariable int id){ return service.deleteNotification(id); }
}