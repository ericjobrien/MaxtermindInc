package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.repository.ApplicationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationService {

    ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }
}
