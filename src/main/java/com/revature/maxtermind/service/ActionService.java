package com.revature.maxtermind.service;

import com.revature.maxtermind.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ActionService {

    NotificationService nService;
    EmployeeService eService;
    PositionService pService;
    ApplicationService aService;

    @Autowired
    public ActionService(NotificationService nService, EmployeeService eService,
                         PositionService pService, ApplicationService aService) {
        this.eService = eService;
        this.nService = nService;
        this.pService = pService;
        this.aService = aService;
    }

    @Transactional
    public boolean updateNotificationsByToEmployee(int id) {
        return nService.updateNotificationsByToEmployee(eService.findByEmployeeId(id));
    }

    @Transactional
    public Notification recommendedAction(int positionId, int managerId, int employeeId) {
        Position position = pService.findByPositionId(positionId);
        Employee toEmployee = eService.findByEmployeeId(employeeId);
        Application application = createApplication(toEmployee, position);
        application.setRecommended(true);

        if(aService.saveApplication(application)!=null){
            return nService.saveNotification(
                    createNotification(Action.RECOMMENDATION,
                            eService.findByEmployeeId(managerId), toEmployee, position));
        }

        return null;
    }

    @Transactional
    public Notification selectedAction(int positionId, int employeeId) {
        Position position = pService.findByPositionId(positionId);
        Employee employee = eService.findByEmployeeId(employeeId);

        Application application = aService.findByEmployeeAndPosition(employee, position);
        if(application==null) application = createApplication(employee, position);

        application.setSelected(true);

        if(aService.saveApplication(application)!=null){
            return nService.saveNotification(
                    createNotification(Action.SELECTION, employee, position.getManager(), position));
        }

        return null;
    }

    @Transactional
    public Notification approvedAction(int applicationId, int managerId) {
        Application application = aService.findByApplicationId(applicationId);
        application.setSelected(true);
        Employee employee = application.getEmployee();
        employee.setPosition(application.getPosition());
        if(aService.updateApplication(application)!=null){
            return nService.saveNotification(
                    createNotification(Action.APPROVED, eService.findByEmployeeId(managerId),
                            employee, application.getPosition()));
        }

        return null;
    }

    @Transactional
    public Notification rejectedAction(int applicationId, int managerId) {
        Application application = aService.findByApplicationId(applicationId);
        application.setRejected(true);

        if(aService.updateApplication(application)!=null){
            return nService.saveNotification(
                    createNotification(Action.REJECTED, eService.findByEmployeeId(managerId),
                            application.getEmployee(), application.getPosition()));
        }

        return null;
    }

    private Application createApplication(Employee employee, Position position){
        Application application = new Application();
        application.setDate(new Date());
        application.setPosition(position);
        application.setEmployee(employee);

        return application;
    }

    private Notification createNotification(Action action, Employee fromEmployee,
                                            Employee toEmployee, Position position){
        Notification notification = new Notification();
        notification.setAction(action);
        notification.setDate(new Date());
        notification.setUnread(true);
        notification.setFromEmployee(fromEmployee);
        notification.setToEmployee(toEmployee);
        notification.setPosition(position);
        notification.setDescription(
                "Action: "+action.name()+", "+
                "From: "+fromEmployee.getFirstName()+" "+fromEmployee.getLastName()+", "+
                "To: "+toEmployee.getFirstName()+" "+toEmployee.getLastName()+", "+
                "Position: "+position.getName() );

        return notification;
    }
}