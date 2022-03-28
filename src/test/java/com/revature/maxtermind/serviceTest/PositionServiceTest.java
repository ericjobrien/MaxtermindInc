package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.PositionRepository;
import com.revature.maxtermind.service.ApplicationService;
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


public class PositionServiceTest {

    private PositionService service;

    private PositionRepository repo;
    ApplicationService aService;

    private List<Position> positions;
    private List<Application> apps;
    private final Position position0 = new Position();
    private final Employee max = new Employee();
    private final Employee john = new Employee();
    private final Position position1 = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {
        repo = Mockito.mock(PositionRepository.class);
        aService = Mockito.mock(ApplicationService.class);
        service = new PositionService(repo, aService);

        positions = new ArrayList<>();
        position1.setApplications(new ArrayList<>());
        position1.setId(1);
        position1.setManager(john);
        position1.setName("Administration Level 1");
        position1.setSalary(BigDecimal.valueOf(39000));
        position1.setAdmin(true);
        this.positions.add(position1);



        position0.setApplications(new ArrayList<>());
        position0.setId(0);
        position0.setManager(john);
        position0.setName("Employee Level 1");
        position0.setSalary(BigDecimal.valueOf(29000));
        position0.setAdmin(false);
        this.positions.add(position0);

        john.setId(1);
        john.setPassword("123password");
        john.setFirstName("John");
        john.setLastName("Wayne");
        john.setEmail("another@example.com");
        john.setPhoneNumber(1234567809);
        john.setPhoto("apple.com");
        john.setStartDate(LocalDate.parse("2020-01-08"));
        john.setPosition(position1);
        john.setNotifications(new ArrayList<>());
        john.setApplications(new ArrayList<>());


        max.setId(0);
        max.setPassword("password123");
        max.setFirstName("Max");
        max.setLastName("Hilky");
        max.setEmail("max@example.com");
        max.setPhoneNumber(1111111111);
        max.setPhoto("espn.com");
        max.setStartDate(LocalDate.parse("2020-11-28")); // Unsure of how to pass in Object Date
        max.setPosition(position0);
        max.setNotifications(new ArrayList<>());
        max.setApplications(new ArrayList<>());

        this.apps = new ArrayList<>();
        Application app = new Application();
        app.setId(1);
        app.setPosition(position1);
        app.setEmployee(max);
        app.setDate(LocalDate.parse("2020-12-15"));
        app.setRecommended(false);
        app.setSelected(true);
        app.setApproved(false);
        app.setRejected(true);
        this.apps.add(app);

    }

    @Test
    void findAll_test(){
        when(repo.findAll()).thenReturn(positions);
        assertEquals(service.findAll(), positions);
    }

    @Test
    void findAllByName_test(){
        when(repo.findAllByNameContains("level 1")).thenReturn(positions);
        assertEquals(service.findAllByName("level 1"), positions);
    }

    @Test
    void findAllByAdmin_test(){
        when(repo.findAllByAdmin(true)).thenReturn(positions);
        assertEquals(service.findAllByAdmin(true), positions);
    }

    @Test
    void findAllBySalary_test(){
        when(repo.findAllBySalary(BigDecimal.valueOf(39000))).thenReturn(positions);
        assertEquals(service.findAllBySalary(39000), positions);
    }

    @Test
    public void findAllOpen_test(){
        when(repo.findAllOpen()).thenReturn(positions);
        assertEquals(service.findAllOpen(), positions);
    }

    @Test
    public void findAllClosed_test(){
        when(repo.findAllClosed()).thenReturn(positions);
        assertEquals(service.findAllClosed(), positions);
    }

    @Test
    void findByPositionId_test(){
        when(repo.findById(1)).thenReturn(position1);
        assertEquals(service.findByPositionId(1), position1);
    }


    @Test
    public void loadApplicationsByEmployeeId_test(){
        when(repo.findById(1)).thenReturn(position1);
        when(aService.findAllByPosition(position1)).thenReturn(apps);
        assertEquals(service.loadApplicationsByPositionId(1), position1);
        assertEquals(position1.getApplications(), apps);
    }

    @Test
    void savePosition_test(){
        when(repo.save(any(Position.class))).thenReturn(position0);
        assertEquals(service.savePosition(position0), position0);
    }


    @Test
    void updateEmployee_test(){
        when(repo.save(position0)).thenReturn(position0);
        assertEquals(service.updatePosition(position0), position0);
    }

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
