package com.revature.maxtermind.controllerTest;

import com.revature.maxtermind.model.*;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationControllerTest {




    private List<Application> apps;
    private List<Notification> notes;
    private final Application app1 = new Application();
    private final Notification note1 = new Notification();
    private List<Employee> employees = new ArrayList<>();
    private Employee john;
    private Employee max = new Employee();
    private Position position0 = new Position();
    private Position position = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {



        apps = new ArrayList<>();
        app1.setId(1);
        app1.setDate(LocalDate.parse("2021-02-09"));
        app1.setPosition(position);
        app1.setEmployee(john);
        app1.setRecommended(true);
        app1.setSelected(true);
        app1.setRejected(true);
        app1.setApproved(false);
        this.apps.add(app1);

        notes = new ArrayList<>();
        note1.setId(1);
        note1.setFromEmployee(john);
        note1.setToEmployee(max);
        note1.setPosition(position);
        note1.setUnread(true);
        note1.setDate(LocalDate.parse("2022-03-20"));
        note1.setAction(Action.RECOMMENDATION);
        this.notes.add(note1);

        position0.setApplications(new ArrayList<>());
        position0.setId(0);
        position0.setManager(john);
        position0.setName("Employee Level 1");
        position0.setSalary(BigDecimal.valueOf(29000));
        position0.setAdmin(true);

        position.setApplications(new ArrayList<>());
        position.setId(1);
        position.setManager(john);
        position.setName("Administration Level 1");
        position.setSalary(BigDecimal.valueOf(39000));
        position.setAdmin(true);

        john = new Employee();
        john.setId(0);
        john.setPassword("jpassword123");
        john.setFirstName("John");
        john.setLastName("Wayne");
        john.setEmail("jexample@example.com");
        john.setPhoneNumber(1234567890);
        john.setPhoto("jespn.com");
        john.setStartDate(LocalDate.parse("2020-01-08")); // Unsure of how to pass in Object Date
        john.setPosition(position);// Unsure of how to pass in object Position
        john.setNotifications(new ArrayList<>());
        john.setApplications(new ArrayList<>());

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
}
