package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.repository.PositionRepository;
import com.revature.maxtermind.service.ApplicationService;
import com.revature.maxtermind.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class PositionServiceTest {

    private PositionService service;

    private PositionRepository repo;
    ApplicationService aService;

    private List<Position> pos;
    private final Position manager = new Position();
    //private final Employee max = new Employee();
    private final Employee john = new Employee();
    private final Position position = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {
        repo = Mockito.mock(PositionRepository.class);
        service = new PositionService(repo, aService);

        position.setApplications(new ArrayList<>());
        position.setId(1);
        position.setManager(john);
        position.setName("Administration Level 1");
        position.setSalary(BigDecimal.valueOf(39000));


        pos = new ArrayList<>();
        manager.setApplications(new ArrayList<>());
        manager.setId(1);
        manager.setManager(john);
        manager.setName("Administration Level 1");
        manager.setSalary(BigDecimal.valueOf(39000));
        this.pos.add(manager);

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

    }

    @Test
    void findAll_test(){
        when(repo.findAll()).thenReturn(pos);
        assertEquals(service.findAll(), pos);
    }

    @Test
    void findAllByName_test(){
        when(repo.findAllByNameContains("john")).thenReturn(pos);
        assertEquals(service.findAllByName("john"), pos);
    }

    @Test
    void findAllByAdmin_test(){
        when(repo.findAllByAdmin(true)).thenReturn(pos);
        assertEquals(service.findAllByAdmin(true), pos);
    }

    @Test
    void findAllBySalary_test(){
        when(repo.findAllBySalary(BigDecimal.valueOf(39000))).thenReturn(pos);
        assertEquals(service.findAllBySalary(39000), pos);
    }
/*
    I did not understand the query, these should mirror each other, I think we can do assertTrue if Equals doesnt work.
    -Max

    @Test
    public void findAllOpen_test(){
        when(repo.findAllOpen(john)).thenReturn(employees);
        assertEquals(service.findAllOpen(0), max);
    }

    @Test
    public void findAllClosed_test(){
        when(repo.findAllClosed(john)).thenReturn(employees);
        assertEquals(service.findAllClosed(0), max);
    }
 */

    @Test
    void findByPositionId_test(){
        when(repo.findById(1)).thenReturn(manager);
        assertEquals(service.findByPositionId(1), manager);
    }

    //This is one I will have to come back to, it should result in 2 tests, one for null and one for !null. -Max
//    @Test
//    public void loadApplicationsByEmployeeId_test(){
//        when(repo.findById(0)).thenReturn(max);
//        assertEquals(service.loadCollectionsByEmployeeId(0), max);
//    }

    @Test
    void savePosition_test(){
        when(repo.save(any(Position.class))).thenReturn(manager);
        assertEquals(service.savePosition(manager), manager);
    }

    //not working
//    @Test
//    void updateEmployee_test(){
//        //here we create a position/object on our table
//        when(repo.save(manager)).thenReturn(manager);
//        //here we look to see if it is there, if so, update that position/object
//        when(repo.save(any(Employee.class))).thenReturn(john);
//        //To update position/object
//        Position newManager = new Position();
//        newManager.setApplications(new ArrayList<>());
//        newManager.setId(1);
//        newManager.setManager(john);
//        newManager.setName("Administration Level 2");
//        newManager.setSalary(BigDecimal.valueOf(39005));
//        //see if update was successful (newManager) should now exist
//        assertEquals(service.updatePosition(newManager)), john);
//    }

    @Test
    void deletePosition() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(repo).deleteById(any(Integer.class));
        service.deletePosition(1);
        assertTrue(deleteCalled);
    }


}
