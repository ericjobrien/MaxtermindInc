package com.revature.maxtermind.controllerTest;

import com.revature.maxtermind.controller.EmployeeController;
import com.revature.maxtermind.model.Action;
import com.revature.maxtermind.model.Employee;
import com.revature.maxtermind.model.Notification;
import com.revature.maxtermind.model.Position;
import com.revature.maxtermind.repository.EmployeeRepository;
import com.revature.maxtermind.service.EmployeeService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.Assert.isTrue;

@ContextConfiguration
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(classes = EmployeeController.class)
class EmployeeControllerTest {

    JSONObject jsonRequest;
    JSONObject jsonReturn;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    private List<Notification> notes;
    private final Notification note1 = new Notification();
    private List<Employee> employees = new ArrayList<>();
    private Employee john;
    private Employee max = new Employee();
    private Position position0 = new Position();
    private Position position = new Position();
    private static boolean deleteCalled = false;

    @BeforeEach
    public void setup() {

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
    @Test
    public void getAllEmployees() {
        System.out.println(max);
        isTrue(employees.size() == 1);
        int nullCounter = 0;

        for(int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
            if(employees.get(i) == null) {
                nullCounter ++;
            }
        }
        isTrue(nullCounter == 0);
    }

    @Test
    public void getPosition() {

        System.out.println(String.valueOf(employees));
        System.out.println(String.valueOf(position));
        Assert.isTrue(position != null);
    }

    @Test
    public void findAll() throws Exception {
        when(this.service.findAll()).thenReturn(this.employees);
        this.mockMvc.perform(get("/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
    }

    @Test
    public void getEmployeesbyName() throws Exception {
        when(service.findAllByName(any(String.class))).thenReturn(employees);
        this.mockMvc.perform(get("/employee/name/Max"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
    }

    @Test
    public void getAdministrators() throws Exception {
        when(service.findAllAdministrators()).thenReturn(employees);
        this.mockMvc.perform(get("/employee/administrators"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
    }

    @Test
    public void getManagers() throws Exception {
        when(service.findAllManagers()).thenReturn(employees);
        this.mockMvc.perform(get("/employee/managers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
    }

    @Test
    void getEmployeesByManager() throws Exception{
        when(service.findAllByManager(any(Integer.class))).thenReturn(employees);
        this.mockMvc.perform(get("/employee/manager/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
    }

    @Test
    void getEmployeesByPosition() throws Exception{
        when(service.findAllByPosition(any(Integer.class))).thenReturn(employees);
        this.mockMvc.perform(get("/employee/position/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
    }

//    @Test
//    void getEmployeeByLogin() throws Exception{
//        when(service.findEmployeeByLogin("example@example.com", "password123")).thenReturn(max);
//        this.mockMvc.perform(post("/employee/login"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
//                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
//                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
//                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }]"));
//    }
//
    @Test
    void getEmployeeById() throws Exception{

        when(service.loadCollectionsByEmployeeId(anyInt())).thenReturn(max);
        this.mockMvc.perform(get("/employee/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }"));
    }

    @Test
    void insertEmployee() throws Exception {
        when(service.saveEmployee(any(Employee.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Employee.class);});

        this.mockMvc.perform(put("/employee/saveEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }"));
    }

    @Test
    void updateEmployee() throws Exception {
        when(service.updateEmployee(any(Employee.class))).thenReturn(max);
        this.mockMvc.perform(post("/employee")
                        .content("{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                                "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                                "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                                "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\" : 0, \"password\" : \"password123\", \"firstName\" : \"Max\", " +
                        "\"lastName\" : \"Hilky\", \"email\" : \"example@example.com\", \"phoneNumber\" : 1234567890, " +
                        "\"photo\" : \"espn.com\", \"startDate\" : \"2020-01-08\", \"position\" : { \"id\" : 1, " +
                        "\"name\" : \"Administration Level 1\", \"salary\" : 39000, \"name\" : \"Administration Level 1\"}, \"notifications\" : [] }"));
    }


    @Test
    void deleteEmployeeById() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(service).deleteEmployee(any(Integer.class));
        this.mockMvc.perform(delete("/employee/0"))
                .andDo(print())
                .andExpect(status().isOk()); // the json parser doesn't want to parse the literal value true, so I can't test the return value of this endpoint
        assertTrue(deleteCalled);
    }









}
