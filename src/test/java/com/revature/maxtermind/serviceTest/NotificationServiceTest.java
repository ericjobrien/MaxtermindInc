package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.repository.NotificationRepository;
import com.revature.maxtermind.service.NotificationService;
import com.revature.maxtermind.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class NotificationServiceTest {

//    private NotificationService service;
//
//    private NotificationRepository repo;
//    EmployeeRepository eRepository;
//    PositionService pService;
//
//    private List<Application> apps;
//    private List<Employee> employees;
//    private List<Notification> notes;
//    private final Notification note1 = new Notification();
//    private final Application app1 = new Application();
//    private final Application app2 = new Application();
//    private final Employee john = new Employee();
//    private final Employee max = new Employee();
//    private final Position position = new Position();
//    private static boolean deleteCalled = false;
//
//    @BeforeEach
//    public void setup() {
//        repo = Mockito.mock(NotificationRepository.class);
//        service = new NotificationService(repo, eRepository, pService);
//
//        notes = new ArrayList<>();
//        note1.setId(1);
//        note1.setFromEmployee(john);
//        note1.setToEmployee(max);
//        note1.setPosition(position);
//        note1.setToEmployee(john);
//        note1.setUnread(true);
//        note1.setDate(LocalDate.parse("2001-09-11"));
//        note1.setAction(null);
//        this.notes.add(note1);
//
//
//        position.setApplications(new ArrayList<>());
//        position.setId(1);
//        position.setManager(john);
//        position.setName("Administration Level 1");
//        position.setSalary(BigDecimal.valueOf(39000));
//
//        apps = new ArrayList<>();
//        app1.setId(1);
//        app1.setDate(LocalDate.parse("2021-02-09"));
//        app1.setPosition(position);
//        app1.setEmployee(john);
//        app1.setRecommended(true);
//        app1.setSelected(true);
//        app1.setRejected(true);
//        app1.setApproved(false);
//        this.apps.add(app1);
//
//        app2.setId(2);
//        app2.setDate(LocalDate.parse("1991-02-09"));
//        app2.setPosition(position);
//        app2.setEmployee(max);
//        app2.setRecommended(true);
//        app2.setSelected(true);
//        app2.setRejected(false);
//        app2.setApproved(true);
//        this.apps.add(app2);
//
//        john.setPassword("123password");
//        john.setFirstName("John");
//        john.setLastName("Wayne");
//        john.setEmail("another@example.com");
//        john.setPhoneNumber(1234567809);
//        john.setPhoto("apple.com");
//        john.setStartDate(LocalDate.parse("2020-01-08"));
//        john.setPosition(position);
//        john.setNotifications(new ArrayList<>());
//        john.setApplications(new ArrayList<>());
//
//        employees = new ArrayList<>();
//        max.setId(0);
//        max.setPassword("password123");
//        max.setFirstName("Max");
//        max.setLastName("Hilky");
//        max.setEmail("example@example.com");
//        max.setPhoneNumber(1234567890);
//        max.setPhoto("espn.com");
//        max.setStartDate(LocalDate.parse("2020-01-08")); // Unsure of how to pass in Object Date
//        max.setPosition(position);
//        max.setNotifications(new ArrayList<>());
//        max.setApplications(new ArrayList<>());
//        this.employees.add(max);
//
//    }
//
//    @Test
//    void findAll_test(){
//        when(repo.findAll()).thenReturn(notes);
//        assertEquals(service.findAll(), notes);
//    }
//
//    @Test
//    void findAllByUnread_test(){
//        when(repo.findAllByUnread(true)).thenReturn(notes);
//        assertEquals(service.findAllByUnread(true), notes);
//    }
//
//    @Test
//    void findAllByDescription_test(){
//        when(repo.findAllByDescriptionContains("Urgent")).thenReturn(notes);
//        assertEquals(service.findAllByDescription("Urgent"), notes);
//    }
//
//    @Test
//    public void findAllByDate_test(){
//        when(repo.findAllByDate(LocalDate.parse("2001-09-11"))).thenReturn(notes);
//        assertEquals(service.findAllByDate(LocalDate.parse("2001-09-11")), notes);
//    }
//
//    //    @Test //will come back, need to tests probably for coverage - Max
////    void findAllByRange_test() {
////        when(repo.findAllByRange(LocalDate.parse("2020-01-08"))).thenReturn(apps);
////        assertEquals(service.findAllByDateBetween(LocalDate.parse("2020-01-08")), apps);
////    }
//
////    NullPointerException: Cannot invoke "com.revature.maxtermind.service.PositionService.findByPositionId(int)" because "this.pService" is null
////    @Test
////    public void findAllByPosition_test(){
////        when(repo.findAllByPosition(position)).thenReturn(employees);
////        assertEquals(service.findAllByPosition(0), employees);
////    }
//
//    @Test
//    void findAllByToEmployee_test(){
//        when(repo.findAllByToEmployee(max)).thenReturn(notes);
//        assertEquals(service.findAllByToEmployee(max), notes);
//    }
//
//    @Test
//    void findAllByToEmployeeWithId_test(){
//        when(eRepository.findById(1)).thenReturn(john);
//        assertEquals(service.findAllByToEmployee(1), notes);
//    }
//
//    @Test
//    void findAllByToEmployeeAndUnread_test(){
//        when(repo.findAllByToEmployeeAndUnread(john, false)).thenReturn(notes);
//        assertEquals(service.findAllByToEmployeeAndUnread(john, false), notes);
//    }
//
//
//    @Test
//    void findByNotificationId_test(){
//        when(repo.findById(0)).thenReturn(note1);
//        assertEquals(service.findByNotificationId(0), note1);
//    }
//
//
//    @Test
//    void saveNotification(){
//        when(repo.save(any(Notification.class))).thenReturn(note1);
//        assertEquals(service.saveNotification(note1), note1);
//    }
//
////      Need test for this method - Max
////    @Transactional
////    public boolean updateNotificationsByToEmployee(Employee toEmployee){
////        try{
////            repository.updateNotificationsByToEmployee(toEmployee);
////            return true;
////        }catch (Exception e){
////            return false;
////        }
////    }
//
//
//
//    //not working
////    @Test
////    void updateEmployee_test(){
////        when(repo.save(max)).thenReturn(max);
////        when(repo.save(any(Employee.class))).thenReturn(max);
////        Employee newMax = new Employee();
////        newMax.setId(0);
////        newMax.setPassword("password12345");
////        newMax.setFirstName("Betty");
////        newMax.setLastName("White");
////        newMax.setEmail("Bexample@example.com");
////        newMax.setPhoneNumber(1234567890);
////        newMax.setPhoto("espn.com");
////        newMax.setStartDate(LocalDate.parse("2020-01-08")); // Unsure of how to pass in Object Date
////        newMax.setPosition(position);
////        newMax.setNotifications(new ArrayList<>());
////        newMax.setApplications(new ArrayList<>());
////        assertEquals(service.updateEmployee(newMax)), max);
////    }
//
//    @Test
//    void deleteNotification() {
//        Mockito.doAnswer(invocationOnMock -> {
//            deleteCalled = true;
//            return null;
//        }).when(repo).deleteById(any(Integer.class));
//        service.deleteNotification(0);
//        assertTrue(deleteCalled);
//    }

}