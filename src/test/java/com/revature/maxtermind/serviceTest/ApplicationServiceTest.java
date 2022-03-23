package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.ApplicationRepository;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.repository.PositionRepository;
import com.revature.maxtermind.service.ApplicationService;
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


class ApplicationServiceTest {
//
//    private ApplicationService service;
//
//    private ApplicationRepository repo;
//    PositionRepository pRepository;
//    EmployeeRepository eRepository;
//
//    private List<Application> apps;
//    private List<Employee> employees;
//    private final Application app1 = new Application();
//    private final Application app2 = new Application();
//    private final Employee john = new Employee();
//    private final Employee max = new Employee();
//    private final Position position = new Position();
//    private static boolean deleteCalled = false;
//
//    @BeforeEach
//    public void setup() {
//        repo = Mockito.mock(ApplicationRepository.class);
//        service = new ApplicationService(repo, eRepository, pRepository);
//
//        position.setApplications(new ArrayList<>());
//        position.setId(1);
//        position.setManager(john);
//        position.setName("Administration Level 1");
//        position.setSalary(BigDecimal.valueOf(39000));
//
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
//    void findAll_test() {
//        when(repo.findAll()).thenReturn(apps);
//        assertEquals(service.findAll(), apps);
//    }
//
//    @Test //need to add object for pRepository: exception: this.pRepository in null
//    void findAllByPositionWithPositionId_test() {
//        when(pRepository.findById(1)).thenReturn(position);
//        assertEquals(service.findAllByPosition(1), apps);
//    }
//
//    @Test
//    void findAllByPosition_test() {
//        when(repo.findAllByPosition(position)).thenReturn(apps);
//        assertEquals(service.findAllByPosition(position), apps);
//    }
//
//    @Test
//    void findAllByEmployee_test() {
//        when(repo.findAllByEmployee(max)).thenReturn(apps);
//        assertEquals(service.findAllByEmployee(max), apps);
//    }
//
//    @Test
//    void findAllByRecommended_test() {
//        when(repo.findAllByRecommended(true)).thenReturn(apps);
//        assertEquals(service.findAllByRecommended(true), apps);
//    }
//
//    @Test
//    void findAllBySelected_test() {
//        when(repo.findAllBySelected(true)).thenReturn(apps);
//        assertEquals(service.findAllBySelected(true), apps);
//    }
//
//    @Test
//    void findAllByApproved_test() {
//        when(repo.findAllBySelected(true)).thenReturn(apps);
//        assertEquals(service.findAllBySelected(true), apps);
//    }
//
//    @Test
//    void findAllByDate_test() {
//        when(repo.findAllByDate(LocalDate.parse("2020-01-08"))).thenReturn(apps);
//        assertEquals(service.findAllByDate(LocalDate.parse("2020-01-08")), apps);
//    }
//
////    @Test //will come back, need to tests probably for coverage - Max
////    void findAllByRange_test() {
////        when(repo.findAllByRange(LocalDate.parse("2020-01-08"))).thenReturn(apps);
////        assertEquals(service.findAllByDateBetween(LocalDate.parse("2020-01-08")), apps);
////    }
//
//    @Test
//    void findByApplicationId_test(){
//        when(repo.findById(1)).thenReturn(app1);
//        assertEquals(service.findByApplicationId(1), app1);
//    }
//
////    @Test
////    public void findAllByManager_test(){
////        when(repo.findAllByManager(john)).thenReturn(employees);
////        assertEquals(service.findAllByManager(0), max);
////    }
//
//    //NullPointerException: Cannot invoke "com.revature.maxtermind.service.PositionService.findByPositionId(int)" because "this.pService" is null
////    @Test
////    public void findAllByPosition_test(){
////        when(repo.findAllByPosition(position)).thenReturn(employees);
////        assertEquals(service.findAllByPosition(0), employees);
////    }
//
//    @Test
//    void findByEmployeeAndPosition_test(){
//        when(repo.findByEmployeeAndPosition(john, position )).thenReturn(app1);
//        assertEquals(service.findByEmployeeAndPosition(john, position), app1);
//    }
//
//    @Test
//    void saveApplication_test(){
//        when(repo.save(any(Application.class))).thenReturn(app1);
//        assertEquals(service.saveApplication(app1), app1);
//    }
//
////    not working
////    @Test
////    void updateApplication_test(){
////        when(repo.save(app1)).thenReturn(app1);
////        when(repo.save(any(Application.class))).thenReturn(app1);
////        Application app2 = new Application();
////        app2.setId(2);
////        app2.setDate(LocalDate.parse("1991-02-09"));
////        app2.setPosition(position);
////        app2.setEmployee(max);
////        app2.setRecommended(true);
////        app2.setSelected(true);
////        app2.setRejected(false);
////        app2.setApproved(true);
////        assertEquals(service.updateApplication(app2), app1);
////    }
//
//    @Test
//    void deleteApplication() {
//        Mockito.doAnswer(invocationOnMock -> {
//            deleteCalled = true;
//            return null;
//        }).when(repo).deleteById(any(Integer.class));
//        service.deleteApplication(1);
//        assertTrue(deleteCalled);
//    }

}