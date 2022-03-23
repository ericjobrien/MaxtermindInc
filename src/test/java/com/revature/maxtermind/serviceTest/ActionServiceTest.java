package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.service.ApplicationService;
import com.revature.maxtermind.service.EmployeeService;
import com.revature.maxtermind.service.NotificationService;
import com.revature.maxtermind.service.PositionService;
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

//This still needs testing applied

class ActionServiceTest {

    private EmployeeService service;

    private EmployeeRepository repo;
    private NotificationService nService;
    PositionService pService;
    ApplicationService aService;

    private List<Employee> employees;
    private final Employee john = new Employee();
    private final Employee max = new Employee();
    private final Position position = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {
        repo = Mockito.mock(EmployeeRepository.class);
        service = new EmployeeService(repo, nService, pService, aService);

        position.setApplications(new ArrayList<>());
        position.setId(1);
        position.setManager(john);
        position.setName("Administration Level 1");
        position.setSalary(BigDecimal.valueOf(39000));

        john.setPassword("123password");
        john.setFirstName("John");
        john.setLastName("Wayne");
        john.setEmail("another@example.com");
        john.setPhoneNumber(1234567809);
        john.setPhoto("apple.com");
        john.setStartDate(LocalDate.parse("2020-01-08"));
        john.setPosition(position);
        john.setNotifications(new ArrayList<>());
        john.setApplications(new ArrayList<>());

        employees = new ArrayList<>();
        max.setId(0);
        max.setPassword("password123");
        max.setFirstName("Max");
        max.setLastName("Hilky");
        max.setEmail("example@example.com");
        max.setPhoneNumber(1234567890);
        max.setPhoto("espn.com");
        max.setStartDate(LocalDate.parse("2020-01-08")); // Unsure of how to pass in Object Date
        max.setPosition(position);
        max.setNotifications(new ArrayList<>());
        max.setApplications(new ArrayList<>());
        this.employees.add(max);

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

//    @Test
//    public void findAllByManager_test(){
//        when(repo.findAllByManager(john)).thenReturn(employees);
//        assertEquals(service.findAllByManager(0), max);
//    }

    //NullPointerException: Cannot invoke "com.revature.maxtermind.service.PositionService.findByPositionId(int)" because "this.pService" is null
//    @Test
//    public void findAllByPosition_test(){
//        when(repo.findAllByPosition(position)).thenReturn(employees);
//        assertEquals(service.findAllByPosition(0), employees);
//    }

    @Test
    void findAllByName_test(){
        when(repo.findAllByNameContains("Max")).thenReturn(employees);
        assertEquals(service.findAllByName("Max"), employees);
    }

    @Test
    void findByEmployeeId_test(){
        when(repo.findById(0)).thenReturn(max);
        assertEquals(service.findByEmployeeId(0), max);
    }

    //This is one I will have to come back to. -Max
//    @Test
//    public void loadCollectionsByEmployeeId_test(){
//        when(repo.findById(0)).thenReturn(max);
//        assertEquals(service.loadCollectionsByEmployeeId(0), max);
//    }

    @Test
    void findEmployeeByLogin_test(){
        when(repo.findByEmailAndPassword("another@example.com", "123password")).thenReturn(john);
        assertEquals(john.getEmail(),"another@example.com");
        assertEquals(john.getPassword(), "123password");
    }

    @Test
    void saveEmployee_test(){
        when(repo.save(any(Employee.class))).thenReturn(max);
        assertEquals(service.saveEmployee(max), max);
    }

    //not working
//    @Test
//    void updateEmployee_test(){
//        when(repo.save(max)).thenReturn(max);
//        when(repo.save(any(Employee.class))).thenReturn(max);
//        Employee newMax = new Employee();
//        newMax.setId(0);
//        newMax.setPassword("password12345");
//        newMax.setFirstName("Betty");
//        newMax.setLastName("White");
//        newMax.setEmail("Bexample@example.com");
//        newMax.setPhoneNumber(1234567890);
//        newMax.setPhoto("espn.com");
//        newMax.setStartDate(LocalDate.parse("2020-01-08")); // Unsure of how to pass in Object Date
//        newMax.setPosition(position);
//        newMax.setNotifications(new ArrayList<>());
//        newMax.setApplications(new ArrayList<>());
//        assertEquals(service.updateEmployee(newMax)), max);
//    }

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