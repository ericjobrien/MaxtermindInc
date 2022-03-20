package com.revature.maxtermind.controller;

import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/action")
public class ActionController extends HandleExceptionController {

    ActionService service;

    @Autowired
    public ActionController(ActionService service) {
        this.service = service;
    }

    @GetMapping("switchtoread/{employeeId}")
    public boolean updateNotificationsByToEmployee(@PathVariable int employeeId){
        return service.updateNotificationsByToEmployee(employeeId);
    }

    @GetMapping("recommended/{positionId}/{managerId}/{employeeId}")
    public Notification recommendedAction(@PathVariable int positionId,
                                          @PathVariable int managerId,
                                          @PathVariable int employeeId){
        return service.recommendedApplication(positionId, managerId, employeeId);
    }

    @GetMapping("selected/{positionId}/{employeeId}")
    public Notification selectedAction(@PathVariable int positionId, @PathVariable int employeeId){
        return service.selectedApplication(positionId, employeeId);
    }

    @GetMapping("approved/{applicationId}/{managerId}")
    public Notification approvedAction(@PathVariable int applicationId, @PathVariable int managerId){
        return service.approvedApplication(applicationId, managerId);
    }

    @GetMapping("rejected/{applicationId}/{managerId}")
    public Notification rejectedAction(@PathVariable int applicationId, @PathVariable int managerId){
        return service.rejectedApplication(applicationId, managerId);
    }

}
