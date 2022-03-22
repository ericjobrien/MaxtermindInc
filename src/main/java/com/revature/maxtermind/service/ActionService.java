package com.revature.maxtermind.service;

import com.revature.maxtermind.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
    public Notification recommendedApplication(int positionId, int managerId, int employeeId) {
        Employee manager = eService.findByEmployeeId(managerId);
        if(manager.getPosition().isAdmin()) {
            Position position = pService.findByPositionId(positionId);
            Employee toEmployee = eService.findByEmployeeId(employeeId);

            Application application = aService.findByEmployeeAndPosition(toEmployee, position);
            if (application == null) application = createApplication(toEmployee, position);

            if (!application.isRecommended() && !application.isApproved() && !application.isRejected()) {
                application.setRecommended(true);

                if (aService.saveApplication(application) != null) {
                    return nService.saveNotification(
                            createNotification(Action.RECOMMENDATION, manager, toEmployee, position));
                }
            }
        }

        return null;
    }

    @Transactional
    public Notification selectedApplication(int positionId, int employeeId) {
        Position position = pService.findByPositionId(positionId);
        Employee employee = eService.findByEmployeeId(employeeId);

        Application application = aService.findByEmployeeAndPosition(employee, position);
        if(application==null) application = createApplication(employee, position);

        if(!application.isSelected() && !application.isApproved() && !application.isRejected()) {
            application.setSelected(true);

            if (aService.saveApplication(application) != null) {
                return nService.saveNotification(
                        createNotification(Action.SELECTION, employee, position.getManager(), position));
            }
        }

        return null;
    }

    @Transactional
    public Notification approvedApplication(int applicationId, int managerId) {
        Application application = aService.findByApplicationId(applicationId);
        Employee manager = eService.findByEmployeeId(managerId);
        if(manager.getPosition().isAdmin() && manager.getPosition().getId() == application.getPosition().getId()
                && !application.isApproved() && !application.isRejected())
        {
            application.setApproved(true);
            Employee employee = application.getEmployee();
            employee.setPosition(application.getPosition());
            if (aService.updateApplication(application) != null) {

                List<Application> list = aService.findAllByPosition(application.getPosition());
                for (Application app : list) {
                    if (app.getId() != application.getId()) {
                        this.rejectedApplication(app, manager);
                    }
                }
                return nService.saveNotification(
                        createNotification(Action.APPROVED, manager, employee, application.getPosition()));
            }
        }

        return null;
    }

    @Transactional
    public Notification rejectedApplication(Application application, Employee manager) {
        if(manager.getPosition().isAdmin() && manager.getPosition().getId() == application.getPosition().getId()
                && !application.isRejected() && !application.isApproved()) {
            application.setRejected(true);
            if(aService.updateApplication(application)!=null){
                return nService.saveNotification(
                        createNotification(Action.REJECTED, manager,
                                application.getEmployee(), application.getPosition()));
            }
        }

        return null;
    }

    @Transactional
    public Notification rejectedApplication(int applicationId, int managerId) {
        return this.rejectedApplication(aService.findByApplicationId(applicationId), eService.findByEmployeeId(managerId));
    }

    private Application createApplication(Employee employee, Position position){
        Application application = new Application();
        application.setDate(LocalDate.now());
        application.setPosition(position);
        application.setEmployee(employee);

        return application;
    }

    private Notification createNotification(Action action, Employee fromEmployee,
                                            Employee toEmployee, Position position){
        Notification notification = new Notification();
        notification.setAction(action);
        notification.setDate(LocalDate.now());
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