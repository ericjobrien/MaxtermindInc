package com.revature.maxtermind.modelTest;

import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationTest {

    private final Employee john = new Employee();
    private final Position position = new Position();

    Application app = new Application(1, (LocalDate.parse("2021-02-09")),
            new Position(1, "john", BigDecimal.valueOf(39000), true, john, new ArrayList<>()),
            john, true, true, true, false);


    @Test
    void setID_test(){
        app.setId(1);
    }

    @Test
    void setDate_test(){
        app.setDate((LocalDate.parse("2020-01-08")));
    }

    @Test
    void setPosition_test(){
        app.setPosition(position);
    }

    @Test
    void setEmployee_test(){
        app.setEmployee(john);
    }

    @Test
    void setRecommended_test(){
        app.setRecommended(true);
    }

    @Test
    void setSelected_test(){
        app.setSelected(true);
    }

    @Test
    void setRejected_test(){
        app.setRejected(true);
    }

    @Test
    void setApproved_test(){
        app.setApproved(false);
    }
}
