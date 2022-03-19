package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController extends HandleExceptionController {

    ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Application> findAll() {
        return service.findAll();
    }

    @GetMapping("position/{positionId}")
    public List<Application> findAllByPosition(@PathVariable int positionId) {
        return service.findAllByPosition(positionId);
    }

    @GetMapping("employee/{employeeId}")
    public List<Application> findAllByEmployee(@PathVariable int employeeId) {
        return service.findAllByEmployee(employeeId);
    }

    @GetMapping("date/{date}")
    public List<Application> findAllByDate(@PathVariable Date date) {

        return service.findAllByDate(date);
    }

    @GetMapping("date/{date1}/{date2}")
    public List<Application> findAllByRange(@PathVariable Date date1, @PathVariable Date date2) {

        return service.findAllByRange(date1, date2);
    }

    @GetMapping("recommended/{recommended}")
    public List<Application> findAllByRecommended(@PathVariable boolean recommended) {

        return service.findAllByRecommended(recommended);
    }

    @GetMapping("selected/{selected}")
    public List<Application> findAllBySelected(@PathVariable boolean selected) {

        return service.findAllBySelected(selected);
    }

    @GetMapping("rejected/{rejected}")
    public List<Application> findAllByRejected(@PathVariable boolean rejected) {

        return service.findAllByRejected(rejected);
    }

    @GetMapping("approved/{approved}")
    public List<Application> findAllByApproved(@PathVariable boolean approved) {

        return service.findAllByApproved(approved);
    }

    @GetMapping("{id}")
    public Application getApplicationById(@PathVariable int id){
        return service.findByApplicationId(id);
    }

    @PutMapping
    public Application insertApplication(@RequestBody Application application){
        return service.saveApplication(application);
    }

    @PostMapping
    public Application updateApplication(@RequestBody Application application){
        return service.updateApplication(application);
    }

    @DeleteMapping("{id}")
    public boolean deleteApplicationById(@PathVariable int id){ return service.deleteApplication(id); }
}
