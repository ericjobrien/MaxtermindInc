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

    @PostMapping("update_read/{employeeId}")
    public boolean updateNotificationsByToEmployee(@PathVariable int employeeId){
        return service.updateNotificationsByToEmployee(employeeId);
    }

    @PostMapping("recommended/{positionId}/{managerId}/{employeeId}")
    public Notification recommendedAction(@PathVariable int positionId,
                                          @PathVariable int managerId,
                                          @PathVariable int employeeId){
        return service.recommendedAction(positionId, managerId, employeeId);
    }

    @PostMapping("selected/{positionId}/{employeeId}")
    public Notification selectedAction(@PathVariable int positionId, @PathVariable int employeeId){
        return service.selectedAction(positionId, employeeId);
    }

    @PostMapping("approved/{applicationId}/{managerId}")
    public Notification approvedAction(@PathVariable int applicationId, @PathVariable int managerId){
        return service.approvedAction(applicationId, managerId);
    }

    @PostMapping("rejected/{applicationId}/{managerId}")
    public Notification rejectedAction(@PathVariable int applicationId, @PathVariable int managerId){
        return service.rejectedAction(applicationId, managerId);
    }

}
