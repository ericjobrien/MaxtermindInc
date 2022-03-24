package com.revature.maxtermind.modelTest;


import com.revature.maxtermind.model.Application;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Position;
import org.junit.jupiter.api.Test;


import javax.xml.stream.XMLStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class PositionTest {

    private final Employee john = new Employee();
    private List<Application> app = new ArrayList<>();



    Position position = new Position(1, "shift clerk", (BigDecimal.valueOf(39000)), true, john, app);


    @Test
    void setID_test(){
        position.setId(0);
    }

    @Test
    void setName_test(){
        position.setName("shift clerk");
    }


    @Test
    void setSalary_test(){
        position.setSalary(BigDecimal.valueOf(39000));
    }

    @Test
    void settoAdmin_test(){
        position.setAdmin(true);
    }

    @Test
    void setManager_test(){
        position.setManager(john);
    }
    @Test
    void setApplications_test(){
        position.setApplications(app);
    }

}
