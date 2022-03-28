package com.revature.maxtermind.modelTest;

import com.revature.maxtermind.model.Action;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.model.Position;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class NotificationTest {

    private final Employee john = new Employee();
    private final Employee max = new Employee();
    private final Position position = new Position();
    private Action action;

    Notification notification = new Notification(1, "Read Me", john, max,
            new Position(1, "john", BigDecimal.valueOf(39000), true, john, new ArrayList<>()),
            true, (LocalDate.parse("2020-01-08")), action);


//            new Employee(0, "password123", "Max", "Hilky", "example@example.com",
//                                 1234567890, "espn.com", LocalDate.parse("2020-01-08"),
//                    new Position(1, "john", BigDecimal.valueOf(39000), true, john, new ArrayList<>()),
//            new ArrayList<>(),new ArrayList<>()),
//            new Employee(1, "password456", "Sherlok", "Holmes", "another@example.com",
//                                 1234567890, "espn.com", LocalDate.parse("2020-01-08"),
//                    new Position(2, "surge", BigDecimal.valueOf(41000), true, max, new ArrayList<>()),
//            new ArrayList<>(),new ArrayList<>()),

    @Test
    void setID_test(){
        notification.setId(0);
    }

    @Test
    void setDescription_test(){
        notification.setDescription("Read Me");
    }

    @Test
    void setFromEmployee_test(){
        notification.setFromEmployee(john);
    }

    @Test
    void settoEmployee_test(){
        notification.setToEmployee(max);
    }

    @Test
    void setPosition_test(){
        notification.setPosition(position);
    }

    @Test
    void setUnread_test(){
        notification.setUnread(true);
    }

    @Test
    void setStartDate_test(){
        notification.setDate((LocalDate.parse("2020-01-08")));
    }

    @Test
    void setAction_test(){
        notification.setAction(action);
    }

}
