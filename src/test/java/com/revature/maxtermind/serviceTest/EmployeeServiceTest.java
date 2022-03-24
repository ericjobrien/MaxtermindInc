package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.*;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.service.ApplicationService;
import com.revature.maxtermind.service.EmployeeService;
import com.revature.maxtermind.service.NotificationService;
import com.revature.maxtermind.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



class EmployeeServiceTest {

    private EmployeeService service;

    private EmployeeRepository repo;
    private NotificationService nService;
    PositionService pService;
    ApplicationService aService;

    private List<Notification> notes;
    private final Notification note1 = new Notification();
    private List<Application> apps;
    private final Application app1 = new Application();
    private List<Employee> employees;
    private final Employee john = new Employee();
    private final Employee max = new Employee();
    private final Employee ted = new Employee();
    private final Position position1 = new Position();
    private final Position position0 = new Position();
    private final Position position2 = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {
        repo = Mockito.mock(EmployeeRepository.class);
        nService = Mockito.mock(NotificationService.class);
        pService = Mockito.mock(PositionService.class);
        aService = Mockito.mock(ApplicationService.class);
        service = new EmployeeService(repo, nService, pService, aService);

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

        employees = new ArrayList<>();
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
        employees.add(john);

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
        employees.add(max);

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
        employees.add(ted);

        apps = new ArrayList<>();
        app1.setId(1);
        app1.setDate(LocalDate.parse("2022-02-09"));
        app1.setPosition(position1);
        app1.setEmployee(max);
        app1.setRecommended(true);
        app1.setSelected(false);
        app1.setRejected(false);
        app1.setApproved(false);
        this.apps.add(app1);

        notes = new ArrayList<>();
        note1.setId(1);
        note1.setFromEmployee(john);
        note1.setToEmployee(max);
        note1.setPosition(position1);
        note1.setUnread(true);
        note1.setDate(LocalDate.parse("2022-03-20"));
        note1.setAction(Action.RECOMMENDATION);
        this.notes.add(note1);

    }

    @Test
    void findAll_test(){
        when(repo.findAll()).thenReturn(employees);
        assertEquals(service.findAll(), employees);
    }

    @Test
    void findAllAdministrators_test(){
        when(repo.findAllAdministrators()).thenReturn(employees);
        assertEquals(service.findAllAdministrators(), employees);
    }

    @Test
    void findAllManagers_test(){
        when(repo.findAllManagers()).thenReturn(employees);
        assertEquals(service.findAllManagers(), employees);
    }

    @Test
    public void findAllByManager_test(){
        when(repo.findById(1)).thenReturn(john);
        when(repo.findAllByManager(john)).thenReturn(employees);
        assertEquals(service.findAllByManager(1), employees);
    }

    @Test
    public void findAllByPosition_test(){
        when(pService.findByPositionId(0)).thenReturn(position0);
        when(repo.findAllByPosition(position0)).thenReturn(employees);
        assertEquals(service.findAllByPosition(0), employees);
    }

    @Test
    void findAllByName_test(){
        when(repo.findAllByNameContains("Max")).thenReturn(employees);
        assertEquals(service.findAllByName("Max"), employees);
    }

    @Test
    void findByEmployeeId_test(){
        when(repo.findById(2)).thenReturn(ted);
        assertEquals(service.findByEmployeeId(2), ted);
    }

    @Test
    public void loadCollectionsByEmployeeId_test(){
        when(repo.findById(0)).thenReturn(max);
        when(aService.findAllByEmployee(max)).thenReturn(apps);
        when(nService.findAllByToEmployeeAndUnread(max,true)).thenReturn(notes);
        assertEquals(service.loadCollectionsByEmployeeId(0), max);
        assertEquals(max.getApplications(), apps);
        assertEquals(max.getNotifications(), notes);
    }

    @Test
    void findEmployeeByLogin_test(){
        when(aService.findAllByEmployee(john)).thenReturn(apps);
        when(nService.findAllByToEmployeeAndUnread(john,true)).thenReturn(notes);
        when(repo.findByEmailAndPassword("another@example.com", "123password")).thenReturn(john);
        assertEquals(service.findEmployeeByLogin("another@example.com", "123password"), john);
        assertEquals(john.getEmail(),"another@example.com");
        assertEquals(john.getPassword(), "123password");
        assertEquals(john.getNotifications(), notes);
        assertEquals(john.getApplications(), apps);

    }

    @Test
    void saveEmployee_test(){
        when(repo.save(any(Employee.class))).thenReturn(max);
        assertEquals(service.saveEmployee(max), max);
    }

    @Test
    void updateEmployee_test(){
        when(repo.save(ted)).thenReturn(ted);
        assertEquals(service.updateEmployee(ted), ted);
    }

    @Test
    void deleteEmployee() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(repo).deleteById(any(Integer.class));
        service.deleteEmployee(0);
        assertTrue(deleteCalled);
    }

}