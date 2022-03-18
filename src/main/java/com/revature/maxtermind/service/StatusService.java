package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Status;
import com.revature.maxtermind.repository.StatusRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
public class StatusService {

    StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }
}