package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.repository.NotificationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationService {

    NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }
}