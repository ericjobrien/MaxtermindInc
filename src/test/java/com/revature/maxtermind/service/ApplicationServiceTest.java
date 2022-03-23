package com.revature.maxtermind.service;

import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;


@ContextConfiguration
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = EmployeeService.class)
public class ApplicationServiceTest {

    private EmployeeService target;

    //@Autowired
    private MockMvc mockMvc;

    //@MockBean
    private EmployeeRepository repo;
    private NotificationService nService;
    PositionService pService;
    ApplicationService aService;

    private List<Employee> employees;
    private Employee john;
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {

        repo = Mockito.mock(EmployeeRepository.class);
        target = new EmployeeService(repo, nService, pService, aService);


        employees = new ArrayList<>();
        Employee max = new Employee();
        max.setId(0);
        max.setPassword("password123");
        max.setFirstName("Max");
        max.setLastName("Hilky");
        max.setEmail(null);
        max.setPhoneNumber(1234567890);
        max.setPhoto(null);
        max.setStartDate(null); // Unsure of how to pass in Object Date
        max.setPosition(null);// Unsure of how to pass in object Position
        max.setNotifications(new ArrayList<>());
        max.setApplications(new ArrayList<>());
        employees.add(max);

        john = new Employee();
        john.setPassword("123password");
        john.setFirstName("John");
        john.setLastName("Wayne");
        john.setEmail(null);
        john.setPhoneNumber(1234567809);
        john.setPhoto(null);
        john.setStartDate(null); // Unsure of how to pass in Object Date
        john.setPosition(null);// Unsure of how to pass in object Position
        john.setNotifications(new ArrayList<>());
        john.setApplications(new ArrayList<>());

    }
}
