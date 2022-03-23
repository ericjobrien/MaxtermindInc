package com.revature.maxtermind.serviceTest;

import com.revature.maxtermind.model.*;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.repository.NotificationRepository;
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


class NotificationServiceTest {

    private NotificationService service;

    private NotificationRepository repo;
    EmployeeRepository eRepository;
    PositionService pService;

    private List<Notification> notes;
    private final Notification note1 = new Notification();
    private final Notification note2 = new Notification();
    private final Employee john = new Employee();
    private final Employee max = new Employee();
    private final Employee ted = new Employee();
    private final Position position0 = new Position();
    private final Position position1 = new Position();
    private final Position position2 = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {
        repo = Mockito.mock(NotificationRepository.class);
        eRepository = Mockito.mock(EmployeeRepository.class);
        pService = Mockito.mock(PositionService.class);
        service = new NotificationService(repo, eRepository, pService);

        notes = new ArrayList<>();
        note1.setId(1);
        note1.setFromEmployee(john);
        note1.setToEmployee(max);
        note1.setPosition(position1);
        note1.setUnread(true);
        note1.setDate(LocalDate.parse("2022-03-20"));
        note1.setAction(Action.RECOMMENDATION);
        this.notes.add(note1);

        note2.setId(2);
        note2.setFromEmployee(john);
        note2.setToEmployee(ted);
        note2.setPosition(position1);
        note2.setUnread(true);
        note2.setDate(LocalDate.parse("2022-03-23"));
        note2.setAction(Action.RECOMMENDATION);
        this.notes.add(note2);

        position0.setApplications(new ArrayList<>());
        position0.setId(0);
        position0.setManager(john);
        position0.setName("Employee Level 1");
        position0.setSalary(BigDecimal.valueOf(29000));
        position0.setAdmin(false);

        position1.setApplications(new ArrayList<>());
        position1.setId(1);
        position1.setManager(john);
        position1.setName("Administration Level 1");
        position1.setSalary(BigDecimal.valueOf(39000));
        position1.setAdmin(false);

        position2.setApplications(new ArrayList<>());
        position2.setId(2);
        position2.setManager(john);
        position2.setName("Administration Level 2");
        position2.setSalary(BigDecimal.valueOf(59000));
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
        max.setPhoneNumber(1234567890);
        max.setPhoto("espn.com");
        max.setStartDate(LocalDate.parse("2020-01-08")); // Unsure of how to pass in Object Date
        max.setPosition(position0);
        max.setNotifications(new ArrayList<>());
        max.setApplications(new ArrayList<>());

        ted.setId(2);
        ted.setPassword("password123");
        ted.setFirstName("Ted");
        ted.setLastName("Bav");
        ted.setEmail("ted@example.com");
        ted.setPhoneNumber(1111111111);
        ted.setPhoto("espn.com");
        ted.setStartDate(LocalDate.parse("2020-11-08")); // Unsure of how to pass in Object Date
        ted.setPosition(position0);
        ted.setNotifications(new ArrayList<>());
        ted.setApplications(new ArrayList<>());

    }

    @Test
    void findAll_test(){
        when(repo.findAll()).thenReturn(notes);
        assertEquals(service.findAll(), notes);
    }

    @Test
    void findAllByUnread_test(){
        when(repo.findAllByUnread(true)).thenReturn(notes);
        assertEquals(service.findAllByUnread(true), notes);
    }

    @Test
    void findAllByDescription_test(){
        when(repo.findAllByDescriptionContains("Urgent")).thenReturn(notes);
        assertEquals(service.findAllByDescription("Urgent"), notes);
    }

    @Test
    public void findAllByDate_test(){
        when(repo.findAllByDate(LocalDate.parse("2001-09-11"))).thenReturn(notes);
        assertEquals(service.findAllByDate(LocalDate.parse("2001-09-11")), notes);
    }

    @Test //will come back, need to tests probably for coverage - Max
    void findAllByRange_test() {
        when(repo.findAllByDateBetween(LocalDate.parse("2020-01-08"),LocalDate.parse("2020-02-08"))).thenReturn(notes);
        assertEquals(service.findAllByRange(LocalDate.parse("2020-01-08"),LocalDate.parse("2020-02-08")), notes);
    }

    @Test
    public void findAllByPosition_test(){
        when(repo.findAllByPosition(position1)).thenReturn(notes);
        when(pService.findByPositionId(1)).thenReturn(position1);
        assertEquals(service.findAllByPosition(1), notes);
    }

    @Test
    void findAllByToEmployee_test(){
        when(repo.findAllByToEmployee(max)).thenReturn(notes);
        assertEquals(service.findAllByToEmployee(max), notes);
    }

    @Test
    void findAllByToEmployeeWithId_test(){
        when(repo.findAllByToEmployee(max)).thenReturn(notes);
        when(eRepository.findById(0)).thenReturn(max);
        assertEquals(service.findAllByToEmployee(0), notes);
    }

    @Test
    void findAllByToEmployeeAndUnread_test(){
        when(repo.findAllByToEmployeeAndUnread(john, true)).thenReturn(notes);
        assertEquals(service.findAllByToEmployeeAndUnread(john, true), notes);
    }


    @Test
    void findByNotificationId_test(){
        when(repo.findById(1)).thenReturn(note1);
        assertEquals(service.findByNotificationId(1), note1);
    }


    @Test
    void saveNotification_test(){
        when(repo.save(any(Notification.class))).thenReturn(note1);
        assertEquals(service.saveNotification(note1), note1);
    }


    @Test
    void updateNotificationsByToEmployee_test(){
        //when(repo.updateNotificationsByToEmployee(john)).thenReturn(void);
        assertTrue(service.updateNotificationsByToEmployee(john));
    }




    @Test
    void updateEmployee_test(){
        when(repo.save(note2)).thenReturn(note2);
        assertEquals(service.updateNotification(note2), note2);
    }

    @Test
    void deleteNotification() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(repo).deleteById(any(Integer.class));
        service.deleteNotification(0);
        assertTrue(deleteCalled);
    }

}