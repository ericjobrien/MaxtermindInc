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

    private ApplicationService service;

    private ApplicationRepository repo;
    PositionRepository pRepository;
    EmployeeRepository eRepository;

    private List<Application> apps;
    private final Application app1 = new Application();
    private final Application app2 = new Application();
    private final Employee john = new Employee();
    private final Employee max = new Employee();
    private final Employee ted = new Employee();
    private final Position position1 = new Position();
    private final Position position0 = new Position();
    private final Position position2 = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {
        repo = Mockito.mock(ApplicationRepository.class);
        eRepository = Mockito.mock(EmployeeRepository.class);
        pRepository = Mockito.mock(PositionRepository.class);
        service = new ApplicationService(repo, eRepository, pRepository);

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
        position2.setName("Administration Level 2");
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
        max.setEmail("example@example.com");
        max.setPhoneNumber(1112223344);
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
        ted.setPhoneNumber(555222334);
        ted.setPhoto("espn.com");
        ted.setStartDate(LocalDate.parse("2021-11-18"));
        ted.setPosition(position0);
        ted.setNotifications(new ArrayList<>());
        ted.setApplications(new ArrayList<>());

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

        app2.setId(2);
        app2.setDate(LocalDate.parse("2022-02-08"));
        app2.setPosition(position1);
        app2.setEmployee(ted);
        app2.setRecommended(true);
        app2.setSelected(true);
        app2.setRejected(false);
        app2.setApproved(false);
        this.apps.add(app2);

    }

    @Test
    void findAll_test() {
        when(repo.findAll()).thenReturn(apps);
        assertEquals(service.findAll(), apps);
    }

    @Test
    void findAllByPositionWithPositionId_test() {
        when(pRepository.findById(1)).thenReturn(position1);
        when(repo.findAllByPosition(position1)).thenReturn(apps);
        assertEquals(service.findAllByPosition(1), apps);
    }

    @Test
    void findAllByPosition_test() {
        when(repo.findAllByPosition(position0)).thenReturn(apps);
        assertEquals(service.findAllByPosition(position0), apps);
    }

    @Test
    void findAllByEmployee_test() {
        when(repo.findAllByEmployee(max)).thenReturn(apps);
        assertEquals(service.findAllByEmployee(max), apps);
    }

    @Test
    void findAllByEmployeeWithEmployeeId_test() {
        when(eRepository.findById(2)).thenReturn(ted);
        when(repo.findAllByEmployee(ted)).thenReturn(apps);
        assertEquals(service.findAllByEmployee(2), apps);
    }

    @Test
    void findAllByRecommended_test() {
        when(repo.findAllByRecommended(true)).thenReturn(apps);
        assertEquals(service.findAllByRecommended(true), apps);
    }

    @Test
    void findAllBySelected_test() {
        when(repo.findAllBySelected(true)).thenReturn(apps);
        assertEquals(service.findAllBySelected(true), apps);
    }

    @Test
    void findAllByApproved_test() {
        when(repo.findAllByApproved(true)).thenReturn(apps);
        assertEquals(service.findAllByApproved(true), apps);
    }

    @Test
    void findAllByRejected_test() {
        when(repo.findAllByRejected(true)).thenReturn(apps);
        assertEquals(service.findAllByRejected(true), apps);
    }

    @Test
    void findAllByDate_test() {
        when(repo.findAllByDate(LocalDate.parse("2020-01-08"))).thenReturn(apps);
        assertEquals(service.findAllByDate(LocalDate.parse("2020-01-08")), apps);
    }

    @Test
    void findAllByRange_test() {
        when(repo.findAllByDateBetween(LocalDate.parse("2020-01-08"), LocalDate.parse("2020-01-18"))).thenReturn(apps);
        assertEquals(service.findAllByRange(LocalDate.parse("2020-01-08"), LocalDate.parse("2020-01-18")), apps);
    }

    @Test
    void findByApplicationId_test(){
        when(repo.findById(1)).thenReturn(app1);
        assertEquals(service.findByApplicationId(1), app1);
    }

    @Test
    void findByEmployeeAndPosition_test(){
        when(repo.findByEmployeeAndPosition(max, position1 )).thenReturn(app1);
        assertEquals(service.findByEmployeeAndPosition(max, position1), app1);
    }

    @Test
    void saveApplication_test(){
        when(repo.save(any(Application.class))).thenReturn(app2);
        assertEquals(service.saveApplication(app2), app2);
    }


    @Test
    void updateApplication_test(){
        when(repo.save(app1)).thenReturn(app1);
        assertEquals(service.updateApplication(app1), app1);
    }

    @Test
    void deleteApplication() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(repo).deleteById(any(Integer.class));
        service.deleteApplication(1);
        assertTrue(deleteCalled);
    }

}