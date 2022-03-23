package com.revature.maxtermind.modelTest;

import com.revature.maxtermind.model.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeTest {
    Employee employee = new Employee(0, "password123", "Max", "Hilky", "example@example.com",
            1234567890, "espn.com", LocalDate.parse("2020-01-08"), position,
            );



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
    );

    @Test
    void setID_test(){
        employee.setId(0);
    }

    @Test
    void setPassword_test(){
        employee.setId("Max");
    }

    @Test
    void setID_test(){
        employee.setId(0);
    }

    @Test
    void setID_test(){
        employee.setId(0);
    }

    @Test
    void setID_test(){
        employee.setId(0);
    }

    @Test
    void setID_test(){
        employee.setId(0);
    }


}
