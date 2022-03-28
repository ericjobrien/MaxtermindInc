package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.*;
import com.revature.maxtermind.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ActionServiceTest {

    private ActionService service;

    private NotificationService nService;
    private EmployeeService eService;
    private PositionService pService;
    private ApplicationService aService;

    private final Employee john = new Employee();
    private final Employee max = new Employee();
    private final Employee ted = new Employee();
    private final Position position1 = new Position();
    private final Position position0 = new Position();
    private final Position position2 = new Position();
    private final Notification note1 = new Notification();
    private final Notification note2 = new Notification();
    private final Notification note3 = new Notification();
    private final Notification note4 = new Notification();
    private List<Application> apps;
    private final Application app1 = new Application();
    private final Application app2 = new Application();

    @BeforeEach
    public void setup() {
        eService = Mockito.mock(EmployeeService.class);
        nService = Mockito.mock(NotificationService.class);
        pService = Mockito.mock(PositionService.class);
        aService = Mockito.mock(ApplicationService.class);
        service = new ActionService(nService, eService, pService, aService);

        position1.setApplications(new ArrayList<>());
        position1.setId(1);
        position1.setManager(john);
        position1.setName("Administration Level 1");
        position1.setSalary(BigDecimal.valueOf(39000));
        position1.setAdmin(true);

        position0.setApplications(new ArrayList<>());
        position0.setId(0);
        position0.setManager(john);
        position0.setName("Employee Level 1");
        position0.setSalary(BigDecimal.valueOf(29000));
        position0.setAdmin(false);

        position2.setApplications(new ArrayList<>());
        position2.setId(2);
        position2.setManager(john);
        position2.setName("Administrator Level 2");
        position2.setSalary(BigDecimal.valueOf(49000));
        position2.setAdmin(true);

        john.setId(1);
        john.setPassword("123password");
        john.setFirstName("John");
        john.setLastName("Wayne");
        john.setEmail("another@example.com");
        john.setPhoneNumber(1234567809);
        john.setPhoto("apple.com");
        john.setStartDate(LocalDate.parse("2020-01-08"));
        john.setPosition(position2);
        john.setNotifications(new ArrayList<>());
        john.setApplications(new ArrayList<>());

        max.setId(0);
        max.setPassword("password123");
        max.setFirstName("Max");
        max.setLastName("Hilky");
        max.setEmail("max@example.com");
        max.setPhoneNumber(999456789);
        max.setPhoto("espn.com");
        max.setStartDate(LocalDate.parse("2020-01-08"));
        max.setPosition(position0);
        max.setNotifications(new ArrayList<>());
        max.setApplications(new ArrayList<>());

        ted.setId(2);
        ted.setPassword("pass123word");
        ted.setFirstName("Ted");
        ted.setLastName("Bav");
        ted.setEmail("ted@example.com");
        ted.setPhoneNumber(1111456789);
        ted.setPhoto("espn.com");
        ted.setStartDate(LocalDate.parse("2021-11-08"));
        ted.setPosition(position0);
        ted.setNotifications(new ArrayList<>());
        ted.setApplications(new ArrayList<>());

        apps = new ArrayList<>();
        app1.setId(1);
        app1.setDate(LocalDate.now());
        app1.setPosition(position1);
        app1.setEmployee(max);
        app1.setRecommended(false);
        app1.setSelected(false);
        app1.setRejected(false);
        app1.setApproved(false);
        apps.add(app1);

        app2.setId(2);
        app2.setDate(LocalDate.now());
        app2.setPosition(position1);
        app2.setEmployee(ted);
        app2.setRecommended(true);
        app2.setSelected(false);
        app2.setRejected(false);
        app2.setApproved(false);
        apps.add(app1);

        note1.setId(1);
        note1.setFromEmployee(john);
        note1.setToEmployee(max);
        note1.setPosition(position1);
        note1.setUnread(true);
        note1.setDate(LocalDate.now());
        note1.setAction(Action.RECOMMENDATION);
        note1.setDescription(
                "Action: "+Action.RECOMMENDATION.name()+", From: "+john.getFirstName()+" "+john.getLastName()+", "+
                        "To: "+max.getFirstName()+" "+max.getLastName()+", Position: "+position1.getName() );

        note2.setId(2);
        note2.setFromEmployee(ted);
        note2.setToEmployee(john);
        note2.setPosition(position1);
        note2.setUnread(true);
        note2.setDate(LocalDate.parse("2022-03-03"));
        note2.setAction(Action.SELECTION);
        note2.setDescription(
                "Action: "+Action.SELECTION.name()+", From: "+ted.getFirstName()+" "+ted.getLastName()+", "+
                        "To: "+john.getFirstName()+" "+john.getLastName()+", Position: "+position1.getName() );

        note3.setId(3);
        note3.setFromEmployee(john);
        note3.setToEmployee(ted);
        note3.setPosition(position1);
        note3.setUnread(true);
        note3.setDate(LocalDate.now());
        note3.setAction(Action.APPROVED);

        note4.setId(4);
        note4.setFromEmployee(john);
        note4.setToEmployee(max);
        note4.setPosition(position1);
        note4.setUnread(true);
        note4.setDate(LocalDate.parse("2022-03-24"));
        note4.setAction(Action.REJECTED);

    }

    @Test
    void updateNotificationsByToEmployee_test(){
        when(eService.findByEmployeeId(0)).thenReturn(max);
        when(nService.updateNotificationsByToEmployee(max)).thenReturn(true);
        assertTrue(service.updateNotificationsByToEmployee(0));
    }

    @Test
    void recommendedApplicationByPositionIdAndManagerIdAndEmployeeId_test(){
        when(eService.findByEmployeeId(0)).thenReturn(max);
        when(eService.findByEmployeeId(1)).thenReturn(john);
        when(pService.findByPositionId(1)).thenReturn(position1);
        when(aService.findByEmployeeAndPosition(max, position1)).thenReturn(app1);
        when(aService.saveApplication(any(Application.class))).thenReturn(app1);
        when(nService.saveNotification(any(Notification.class))).thenReturn(note1);
        assertEquals(service.recommendedApplication(1,1,0), note1);
    }

    @Test
    void selectedApplicationByPositionIdAndEmployeeId_test(){
        when(eService.findByEmployeeId(2)).thenReturn(ted);
        when(pService.findByPositionId(1)).thenReturn(position1);
        when(aService.findByEmployeeAndPosition(ted, position1)).thenReturn(app2);
        when(aService.saveApplication(any(Application.class))).thenReturn(app2);
        when(nService.saveNotification(any(Notification.class))).thenReturn(note2);
        assertEquals(service.selectedApplication(1,2), note2);
    }

    @Test
    void approvedApplicationByApplicationIdAndManagerId_test(){
        when(eService.findByEmployeeId(1)).thenReturn(john);
        when(aService.findByApplicationId(2)).thenReturn(app2);
        when(aService.findAllByPosition(position1)).thenReturn(apps);
        when(aService.updateApplication(any(Application.class))).thenReturn(app2);
        when(nService.saveNotification(any(Notification.class))).thenReturn(note3);
        assertEquals(service.approvedApplication(2,1), note3);
    }

    @Test
    void rejectedApplicationByApplicationIdAndManagerId_test(){
        when(eService.findByEmployeeId(1)).thenReturn(john);
        when(aService.findByApplicationId(1)).thenReturn(app1);
        when(aService.updateApplication(any(Application.class))).thenReturn(app1);
        when(nService.saveNotification(any(Notification.class))).thenReturn(note4);
        assertEquals(service.rejectedApplication(1,1), note4);
    }

    @Test
    void rejectedApplicationByApplicationAndManager_test(){
        when(aService.updateApplication(any(Application.class))).thenReturn(app1);
        when(nService.saveNotification(any(Notification.class))).thenReturn(note4);
        assertEquals(service.rejectedApplication(app1,john), note4);
    }
}